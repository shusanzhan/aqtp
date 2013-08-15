/**
 * 
 */
package com.ystech.aqtp.service;

import org.springframework.stereotype.Component;

import com.ystech.aqtp.model.ImmuneDrag;
import com.ystech.core.dao.HibernateEntityDao;

/**
 * @author shusanzhan
 * @date 2013-6-22
 */
@Component("immuneDragManageImpl")
public class ImmuneDragManageImpl extends HibernateEntityDao<ImmuneDrag>{

	/**
	 * @param dbid
	 * @return
	 */
	public int deleteByHealthCareDbid(Integer dbid) {
			int delete = executeSql("delete from ImmuneDrag where healthCareId="+dbid);
			return delete;
		}

}
