/**
 * 
 */
package com.ystech.aqtp.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ystech.aqtp.model.Breeder;
import com.ystech.aqtp.model.LoginLog;
import com.ystech.core.dao.HibernateEntityDao;

/**
 * @author shusanzhan
 * @date 2013-6-22
 */
@Component("breederManageImpl")
public class BreederManageImpl extends HibernateEntityDao<Breeder>{

	/**
	 * 
	 */
	@SuppressWarnings("unchecked")
	public List<Breeder> queryByIndex() {
		String sql="SELECT * FROM Breeder   ORDER BY dbid DESC LIMIT 6";
		List<Breeder> list = executeSqlQuery(Breeder.class, sql, new Object[]{}).list();
		return list;
	}

}
