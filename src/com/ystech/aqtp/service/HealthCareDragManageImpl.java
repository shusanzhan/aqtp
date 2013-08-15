/**
 * 
 */
package com.ystech.aqtp.service;

import org.springframework.stereotype.Component;

import com.ystech.aqtp.model.HealthCareDrag;
import com.ystech.core.dao.HibernateEntityDao;

/**
 * @author shusanzhan
 * @date 2013-6-22
 */
@Component("healthCareDragManageImpl")
public class HealthCareDragManageImpl extends HibernateEntityDao<HealthCareDrag> {
	public int deleteByHealthCareDbid(Integer dbid) {
		int delete = executeSql("delete from healthCareDrag where healthCareId="+dbid);
		return delete;
	}
}
