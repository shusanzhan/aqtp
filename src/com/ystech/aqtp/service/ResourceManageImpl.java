package com.ystech.aqtp.service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Component;

import com.ystech.aqtp.model.Resource;
import com.ystech.core.dao.HibernateEntityDao;
@Component("resourceManageImpl")
public class ResourceManageImpl extends HibernateEntityDao<Resource>{
	@SuppressWarnings("unchecked")
	public List<Resource> queryResourceByUserId(Integer userId) {
		String sql="SELECT * FROM resource WHERE dbid IN (SELECT resourceId from roleresource where roleId IN (SELECT roleId FROM userroles where userroles.userId="+userId+")) ORDER BY orderNo";
		Session session = this.getSession();
		List<Resource> resources = executeSql(sql, new Object[]{});
		//List<Resource> resources = session.createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(Resource.class)).list();
		return resources;
	}
}
