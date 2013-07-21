/**
 * 
 */
package com.ystech.aqtp.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ystech.aqtp.model.Breeder;
import com.ystech.aqtp.model.ChickenBatch;
import com.ystech.core.dao.HibernateEntityDao;

/**
 * @author shusanzhan
 * @date 2013-6-22
 */
@Component("chickenBatchManageImpl")
public class ChickenBatchManageImpl extends HibernateEntityDao<ChickenBatch>{

	/**
	 * @return
	 */
	public List<ChickenBatch> queryByIndex() {
		String sql="SELECT * FROM ChickenBatch   ORDER BY dbid DESC LIMIT 6";
		List<ChickenBatch> list = executeSqlQuery(ChickenBatch.class, sql, new Object[]{}).list();
		return list;
	}

}
