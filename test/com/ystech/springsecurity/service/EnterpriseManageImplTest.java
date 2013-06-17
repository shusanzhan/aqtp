/**
 * 
 */
package com.ystech.springsecurity.service;

import javax.annotation.Resource;
import javax.persistence.Temporal;

import org.junit.Test;

import com.ystech.core.test.SpringTxTestCase;
import com.ystech.core.util.TestGenerateUtil;
import com.ystech.springsecurity.model.Enterprise;

/**
 * @author shusanzhan
 * @date 2013-6-2
 */
public class EnterpriseManageImplTest extends SpringTxTestCase{
	private EnterpriseManageImpl enterpriseManageImpl;

	@Resource
	public void setEnterpriseManageImpl(EnterpriseManageImpl enterpriseManageImpl) {
		this.enterpriseManageImpl = enterpriseManageImpl;
	}
	@Test
	public void testCRUD() throws Exception {
		//create
		Enterprise enterprise=new Enterprise();

		enterprise.setName("name");
		enterprise.setPhone("phone");
		enterprise.setFax("fax");
		enterprise.setZipCode("zipCode");
		enterprise.setAddress("address");
		enterprise.setWebAddress("webAddress");
		enterprise.setEmail("email");
		enterprise.setBank("bank");
		enterprise.setAccount("account");
		enterprise.setContent("content");
		enterpriseManageImpl.save(enterprise);
		assertNotNull(enterprise.getDbid());
		//get
		enterprise=enterpriseManageImpl.get(enterprise.getDbid());
		assertNotNull(enterprise.getDbid());
		assertEquals("name",enterprise.getName());
		assertEquals("phone",enterprise.getPhone());
		assertEquals("fax",enterprise.getFax());
		assertEquals("zipCode",enterprise.getZipCode());
		assertEquals("address",enterprise.getAddress());
		assertEquals("webAddress",enterprise.getWebAddress());
		assertEquals("email",enterprise.getEmail());
		assertEquals("bank",enterprise.getBank());
		assertEquals("account",enterprise.getAccount());
		assertEquals("content",enterprise.getContent());
		//update
		enterprise.setName("name1");
		enterpriseManageImpl.save(enterprise);
		enterprise=enterpriseManageImpl.get(enterprise.getDbid());
		assertNotNull(enterprise.getDbid());
		assertEquals("name1",enterprise.getName());
		assertEquals("phone",enterprise.getPhone());
		assertEquals("fax",enterprise.getFax());
		assertEquals("zipCode",enterprise.getZipCode());
		assertEquals("address",enterprise.getAddress());
		assertEquals("webAddress",enterprise.getWebAddress());
		assertEquals("email",enterprise.getEmail());
		assertEquals("bank",enterprise.getBank());
		assertEquals("account",enterprise.getAccount());
		assertEquals("content",enterprise.getContent());
		//delete
		enterpriseManageImpl.deleteById(enterprise.getDbid());

	}
	public static void main(String[] args) {
		TestGenerateUtil.generate(Enterprise.class);
	}
	
}
