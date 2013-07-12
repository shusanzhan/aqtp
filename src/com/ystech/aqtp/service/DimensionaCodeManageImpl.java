/**
 * 
 */
package com.ystech.aqtp.service;

import org.hibernate.Session;
import org.springframework.stereotype.Component;

import com.ystech.aqtp.model.DimensionaCode;
import com.ystech.core.dao.HibernateEntityDao;

/**
 * @author shusanzhan
 * @date 2013-6-22
 */
@Component("dimensionaCodeManageImpl")
public class DimensionaCodeManageImpl extends HibernateEntityDao<DimensionaCode>{
	public int deleteBDimensionaDbid(Integer dbid)  {
		String sql="DELETE FROM dimensionacode WHERE dimensionaId="+dbid;
		Session session = getSession();
		int executeUpdate = session.createSQLQuery(sql).executeUpdate();
		return executeUpdate;
	}
}
