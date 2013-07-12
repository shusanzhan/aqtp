/**
 * 
 */
package com.ystech.springsecurity.service;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.stereotype.Component;

import com.ystech.aqtp.model.LoginLog;
import com.ystech.aqtp.service.LoginLogManageImpl;
import com.ystech.core.dao.HibernateEntityDao;
import com.ystech.core.test.SpringTxTestCase;
import com.ystech.core.util.TestGenerateUtil;

/**
 * @author shusanzhan
 * @date 2013-6-22
 */
public class LoginLogManageImplTest extends SpringTxTestCase{
	private LoginLogManageImpl loginLogManageImpl;

	@Resource
	public void setLoginLogManageImpl(LoginLogManageImpl loginLogManageImpl) {
		this.loginLogManageImpl = loginLogManageImpl;
	}
	@Test
	public void testCRUD() throws Exception {
		//create
		LoginLog loginLog=new LoginLog();

		loginLog.setUserId(1);
		loginLog.setUserName("userName");
		loginLog.setLoginDate(new java.util.Date());
		loginLog.setIpAddress("ipAddress");
		loginLog.setLoginAddress("loginAddress");
		loginLogManageImpl.save(loginLog);
		assertNotNull(loginLog.getDbid());
		//get
		loginLog=loginLogManageImpl.get(loginLog.getDbid());
		assertNotNull(loginLog.getDbid());
		assertEquals(new Integer(1),loginLog.getUserId());
		assertEquals("userName",loginLog.getUserName());
		assertNotNull(loginLog.getLoginDate());
		assertEquals("ipAddress",loginLog.getIpAddress());
		assertEquals("loginAddress",loginLog.getLoginAddress());
		//update
		loginLog.setUserName("userName1");
		loginLogManageImpl.save(loginLog);
		loginLog=loginLogManageImpl.get(loginLog.getDbid());
		assertNotNull(loginLog.getDbid());
		assertEquals(new Integer(1),loginLog.getUserId());
		assertEquals("userName1",loginLog.getUserName());
		assertNotNull(loginLog.getLoginDate());
		assertEquals("ipAddress",loginLog.getIpAddress());
		assertEquals("loginAddress",loginLog.getLoginAddress());
		//delete
		loginLogManageImpl.deleteById(loginLog.getDbid());

	}
	public static void main(String[] args) {
		TestGenerateUtil.generate(LoginLog.class);
	}
}
