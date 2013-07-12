/**
 * 
 */
package com.ystech.aqtp.service;

import org.springframework.stereotype.Component;

import com.ystech.aqtp.model.Feeder;
import com.ystech.core.dao.HibernateEntityDao;

/**
 * @author shusanzhan
 * @date 2013-6-22
 */
@Component("feederManageImpl")
public class FeederManageImpl extends HibernateEntityDao<Feeder> {

}
