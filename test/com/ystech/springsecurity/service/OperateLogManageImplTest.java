/**
 * 
 */
package com.ystech.springsecurity.service;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.stereotype.Component;

import com.ystech.aqtp.model.OperateLog;
import com.ystech.aqtp.service.OperateLogManageImpl;
import com.ystech.core.dao.HibernateEntityDao;
import com.ystech.core.test.SpringTxTestCase;
import com.ystech.core.util.TestGenerateUtil;

/**
 * @author shusanzhan
 * @date 2013-6-22
 */
public class OperateLogManageImplTest extends SpringTxTestCase{
	private OperateLogManageImpl operateLogManageImpl;

	@Resource
	public void setOperateLogManageImpl(OperateLogManageImpl operateLogManageImpl) {
		this.operateLogManageImpl = operateLogManageImpl;
	}
	@Test
	public void testCRUD() throws Exception {
		//create
		OperateLog operateLog=new OperateLog();

		operateLog.setOperator("operator");
		operateLog.setOperatedate(new java.util.Date());
		operateLog.setOperateobj("operateobj");
		operateLog.setOperatetype("operatetype");
		operateLog.setUserId(1);
		operateLog.setOperatefeild("operatefeild");
		operateLog.setIpAddress("ipAddress");
		operateLogManageImpl.save(operateLog);
		assertNotNull(operateLog.getDbid());
		//get
		operateLog=operateLogManageImpl.get(operateLog.getDbid());
		assertNotNull(operateLog.getDbid());
		assertEquals("operator",operateLog.getOperator());
		assertNotNull(operateLog.getOperatedate());
		assertEquals("operateobj",operateLog.getOperateobj());
		assertEquals("operatetype",operateLog.getOperatetype());
		assertEquals(new Integer(1),operateLog.getUserId());
		assertEquals("operatefeild",operateLog.getOperatefeild());
		assertEquals("ipAddress",operateLog.getIpAddress());
		//update
		operateLog.setOperator("operator1");
		operateLogManageImpl.save(operateLog);
		operateLog=operateLogManageImpl.get(operateLog.getDbid());
		assertNotNull(operateLog.getDbid());
		assertEquals("operator1",operateLog.getOperator());
		assertNotNull(operateLog.getOperatedate());
		assertEquals("operateobj",operateLog.getOperateobj());
		assertEquals("operatetype",operateLog.getOperatetype());
		assertEquals(new Integer(1),operateLog.getUserId());
		assertEquals("operatefeild",operateLog.getOperatefeild());
		assertEquals("ipAddress",operateLog.getIpAddress());
		//delete
		operateLogManageImpl.deleteById(operateLog.getDbid());

	}
	public static void main(String[] args) {
		TestGenerateUtil.generate(OperateLog.class);
	}
	
}
