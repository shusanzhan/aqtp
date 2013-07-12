package com.ystech.springsecurity.service;

import javax.annotation.Resource;

import org.junit.Test;

import com.ystech.aqtp.model.Role;
import com.ystech.aqtp.service.RoleManageImpl;
import com.ystech.core.test.SpringTxTestCase;
import com.ystech.core.util.TestGenerateUtil;

public class RoleManageImplTest extends SpringTxTestCase{
	private RoleManageImpl roleManageImpl;
	
	@Resource
	public void setRoleManageImpl(RoleManageImpl roleManageImpl) {
		this.roleManageImpl = roleManageImpl;
	}
	@Test
	public void testCRUD() throws Exception {
		//create
		Role role=new Role();

		role.setName("name");
		role.setState(1);
		role.setNote("note");

		roleManageImpl.save(role);
		assertNotNull(role.getDbid());
		//get
		role=roleManageImpl.get(role.getDbid());
		assertNotNull(role.getDbid());
		assertEquals("name",role.getName());
		assertEquals(new Integer(1),role.getState());
		assertEquals("note",role.getNote());

		//update
		role.setName("name1");
		roleManageImpl.save(role);
		role=roleManageImpl.get(role.getDbid());
		assertNotNull(role.getDbid());
		assertEquals("name1",role.getName());
		assertEquals(new Integer(1),role.getState());
		assertEquals("note",role.getNote());

		//delete
		roleManageImpl.deleteById(role.getDbid());

	}
	public static void main(String[] args) {
		TestGenerateUtil.generate(Role.class);
	}
	
}
