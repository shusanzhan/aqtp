/**
 * 
 */
package com.ystech.aqtp.service;

import org.springframework.stereotype.Component;

import com.ystech.aqtp.model.OperateLog;
import com.ystech.core.dao.HibernateEntityDao;

/**
 * @author shusanzhan
 * @date 2013-6-22
 */
@Component("operateLogManageImpl")
public class OperateLogManageImpl extends HibernateEntityDao<OperateLog>{

}
