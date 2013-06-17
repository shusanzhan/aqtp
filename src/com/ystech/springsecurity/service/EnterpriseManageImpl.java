/**
 * 
 */
package com.ystech.springsecurity.service;

import org.springframework.stereotype.Component;

import com.ystech.core.dao.HibernateEntityDao;
import com.ystech.springsecurity.model.Enterprise;

/**
 * @author shusanzhan
 * @date 2013-6-2
 */
@Component("enterpriseManageImpl")
public class EnterpriseManageImpl extends HibernateEntityDao<Enterprise>{
	
}
