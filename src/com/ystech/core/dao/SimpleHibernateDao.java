package com.ystech.core.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.metadata.ClassMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.util.Assert;

import com.ystech.core.util.ReflectionUtils;


/**
 * 封装Hibernate原生API的DAO泛型基类.
 * 可在Service层直接使用,也可以扩展泛型DAO子类使用.
 * 参考Spring2.5自带的Petlinc例子,取消了HibernateTemplate,直接使用Hibernate原生API.
 * @param <T> DAO操作的对象类型
 * @author shusanzhan
 */
@SuppressWarnings("unchecked")
public class SimpleHibernateDao<T> implements EntityDao<T>{
	private final String DELETEBYIDS;
	protected Logger logger = LoggerFactory.getLogger(getClass());
	 
	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;

	private Class<T> entityClass;

	/**
	 * 用于Dao层子类使用的构造函数.
	 * 通过子类的泛型定义取得对象类型Class.
	 * public class UserDao extends SimpleHibernateDao<User>
	 */
	public SimpleHibernateDao() {
		this.entityClass = ReflectionUtils.getSuperClassGenricType(getClass());
		DELETEBYIDS="delete from "+entityClass.getSimpleName();
	}

	/**
	 * 用于用于省略Dao层, 在Service层直接使用通用SimpleHibernateDao的构造函数.
	 * 在构造函数中定义对象类型Class.
	 * eg.
	 * SimpleHibernateDao<User, Long> userDao = new SimpleHibernateDao<User, Long>(sessionFactory, User.class);
	 */
	public SimpleHibernateDao(SessionFactory sessionFactory,Class<T> entityClass) {
		this.sessionFactory = sessionFactory;
		this.entityClass = entityClass;
		DELETEBYIDS="delete from "+entityClass.getSimpleName();
	}

	/**
	 * 取得sessionFactory.
	 */
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	/**
	 * 采用@Autowired按类型注入SessionFactory,当有多个SesionFactory的时候Override本函数.
	 */
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**
	 * 取得当前Session.
	 */
	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	/**
	 * 保存新增或修改的对象.
	 */
	public void save(T entity) {
		getSession().saveOrUpdate(entity);
	}

	/**
	 * 删除对象.
	 * 
	 * @param entity 对象必须是session中的对象或含id属性的transient对象.
	 */
	public void delete(T entity) {
		getSession().delete(entity);
	}

	/**
	 * 按id删除对象.
	 */
	public void deleteById(Serializable  id) {
		delete(get(id));
	}
	/**
	 * 功能描述：主要用于批量删除，批量更新数据
	 * 参数描述：
	 * 逻辑描述：
	 * @return
	 * @throws Exception
	 */
	public int executeSql(String sql)  {
		Session session = getSession();
		int executeUpdate = session.createSQLQuery(sql).executeUpdate();
		return executeUpdate;
	}
	
	/**
	 * 批量删除数据
	 * @param ids
	 */
	public int deleteByIds(String ids){
		String hql=DELETEBYIDS+" where "+getIdName(entityClass)+" in("+ids+")";
		Session session = this.getSession();
		Query createQuery = session.createQuery(hql);
		int updateNum = createQuery.executeUpdate();
		return updateNum;
	}
	
	/**
	 * 按id获取对象.
	 */
	public T get(Serializable id) {
		return (T) getSession().get(entityClass, id);
	}

	/**
	 * 获取所有数据
	 * @return
	 */
	public List<T> getAll() {
		return find();
	}

	/**
	 * 获取全部对象,支持排序.
	 * @param orderBy，为bean熟悉
	 * @param isAsc，为升序
	 * @return
	 */
	public List<T> getAll(String orderBy, boolean isAsc) {
		Criteria criteria = createCriteria();
		if (isAsc) {
			criteria.addOrder(Order.asc(orderBy));
		} else {
			criteria.addOrder(Order.desc(orderBy));
		}
		return criteria.list();
	}

	/**
	 * 按属性查找对象列表,匹配方式为相等
	 * @param propertyName
	 * @param value
	 * @return
	 */
	public List<T> findBy(String propertyName, Object value) {
		Criterion criterion = Restrictions.eq(propertyName, value);
		return createCriteria(criterion).list();
	}
	/**
	 * 按ids列表获取对象.
	 * @param ids
	 * @return
	 */
	public List<T> findByIds(Class<T> entityClasee, List<Serializable> ids) {
		return find(Restrictions.in(getIdName(entityClasee), ids));
	}
	/**
	 * 按属性查找唯一对象,匹配方式为相等.
	 * @param propertyName
	 * @param value
	 * @return
	 */
	public T findUniqueBy(String propertyName, Object value) {
		Criterion criterion = Restrictions.eq(propertyName, value);
		return (T) createCriteria(criterion).uniqueResult();
	}

	/**
	 * 按Criteria查询对象列表.
	 * @param criterions 数量可变的Criterion.
	 */
	public List<T> find(Criterion... criterions) {
		return createCriteria(criterions).list();
	}

	/**
	 * 按Criteria查询唯一对象.
	 * @param criterions 数量可变的Criterion.
	 */
	public T findUnique(Criterion... criterions) {
		return (T) createCriteria(criterions).uniqueResult();
	}
	
	
	
	/**
	 * 通过hql进行数据查询,参数值通过Object[]{}数组完成
	 * @param hql
	 * @param values
	 * @return
	 */
	public <T> List<T> find(String hql, Object... values) {
		return createQuery(hql, values).list();
	}

	/**
	 * 通过hql进行唯一数据查询，参数值通过Object[]{}数组完成
	 * @param hql
	 * @param values
	 * @return
	 */
	public <T> T findUnique(String hql, Object... values) {
		return (T) createQuery(hql, values).uniqueResult();
	}

	/**
	 * 根据查询HQL与参数列表创建Query对象
	 * 本类封装的find()函数全部默认返回对象类型为T,当不为T时使用本函数.
	 * @param queryString
	 * @param values 参数为Object[]{}
	 * @return
	 */
	public Query createQuery(String hql, Object... values) {
		Assert.hasText(hql, "queryString不能为空");
		Query query = getSession().createQuery(hql);
		if (values != null&&values.length>0) {
			for (int i = 0; i < values.length; i++) {
				query.setParameter(i, values[i]);
			}
		}
		return query;
	}

	
	/**
	 * 执行sql语法的查询语句; 获取符合多个条件的LIST<T>
	 * @see HibernateGenericDao#getAll(Class)
	 */
	public List<T> executeSql(String sql, Object... values) {
		return executeSqlQuery(entityClass,sql, values).list();
	}

	/**
	 * 通过原生sql，数据
	 * @param entityClass
	 * @param sql
	 * @param values
	 * @return
	 */
	public Query executeSqlQuery( Class<T> entityClass,String sql, Object... values) {
		Assert.hasText(sql);
		Query query = getSession().createSQLQuery(sql).addEntity(entityClass);
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				query.setParameter(i, values[i]);
			}
		}
		return query;
	}
	
	/**
	 * 用于数据统计方法，组要能够执行原始sql进行统计
	 * @param sql select count(*) from table
	 * @param values
	 * @return
	 */
	public <T> T count(String sql, Object... values) {
		return (T) executeSqlCount(sql, values).uniqueResult();
	}
	/**
	 * 通过原生sql，这个要用于分页是做统计是用
	 * @param sql select count(*) from tablename where conlum=? and conlum1=? 
	 * @param values Ojbect[];
	 * @return Query
	 */
	protected Query executeSqlCount(String sql, Object... values) {
		Assert.hasText(sql);
		Query query = getSession().createSQLQuery(sql);
		if (values != null&&values.length>0) {
			for (int i = 0; i < values.length; i++) {
				query.setParameter(i, values[i]);
			}
		}
		return query;
	}

	
	/**
	 * 根据Criterion条件创建Criteria.
	 * 本类封装的find()函数全部默认返回对象类型为T,当不为T时使用本函数.
	 * @param criterions 数量可变的Criterion.
	 */
	
	protected Criteria createCriteria(Criterion... criterions) {
		Criteria criteria = getSession().createCriteria(entityClass);
		if (criterions.length>0) {
			for (Criterion c : criterions) {
				criteria.add(c);
			}
		}
		return criteria;
	}

	/**
	 * 初始化对象.
	 * 使用load()方法得到的仅是对象Proxy, 在传到View层前需要进行初始化.
	 * 只初始化entity的直接属性,但不会初始化延迟加载的关联集合和属性.
	 * 如需初始化关联属性,可实现新的函数,执行:
	 * Hibernate.initialize(user.getRoles())，初始化User的直接属性和关联集合.
	 * Hibernate.initialize(user.getDescription())，初始化User的直接属性和延迟加载的Description属性.
	 */
	protected void initEntity(T entity) {
		Hibernate.initialize(entity);
	}

	/**
	 * @see #initEntity(Object)
	 */
	protected void initEntity(List<T> entityList) {
		for (T entity : entityList) {
			Hibernate.initialize(entity);
		}
	}

	/**
	 * 取得对象的主键名.
	 * @return
	 */
	public String getIdName(Class<T> entityClass) {
		ClassMetadata meta = getSessionFactory().getClassMetadata(entityClass);
		return meta.getIdentifierPropertyName();
	}

	@Override
	public void flush() {
		getSession().flush();
	}

	@Override
	public void clear() {
		getSession().clear();
	}

}