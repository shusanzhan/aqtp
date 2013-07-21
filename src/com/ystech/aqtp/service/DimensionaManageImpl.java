/**
 * 
 */
package com.ystech.aqtp.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ystech.aqtp.model.ChickenBatch;
import com.ystech.aqtp.model.Dimensiona;
import com.ystech.core.dao.HibernateEntityDao;

/**
 * @author shusanzhan
 * @date 2013-6-22
 */
@Component("dimensionaManageImpl")
public class DimensionaManageImpl extends HibernateEntityDao<Dimensiona>{

	/**
	 * @return
	 */
	public List<Dimensiona> queryByIndex() {
		String sql="SELECT * FROM Dimensiona   ORDER BY dbid DESC LIMIT 6";
		List<Dimensiona> list = executeSqlQuery(Dimensiona.class, sql, new Object[]{}).list();
		return list;
	}

}
