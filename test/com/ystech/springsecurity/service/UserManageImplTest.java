package com.ystech.springsecurity.service;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Test;

import com.ystech.core.test.SpringTxTestCase;
import com.ystech.core.util.TestGenerateUtil;
import com.ystech.springsecurity.model.User;

public class UserManageImplTest extends SpringTxTestCase{
	private UserManageImpl userManageImpl;
	@Resource
	public void setUserManageImpl(UserManageImpl userManageImpl) {
		this.userManageImpl = userManageImpl;
	}
	@Test
	public void testCRUD() throws Exception {
		//create
		User user=new User();

		user.setUserId("userId");
		user.setRealName("realName");
		user.setPassword("password");
		user.setEmail("email");

		userManageImpl.save(user);
		assertNotNull(user.getDbid());
		//get
		user=userManageImpl.get(user.getDbid());
		assertNotNull(user.getDbid());
		assertEquals("userId",user.getUserId());
		assertEquals("realName",user.getRealName());
		assertEquals("password",user.getPassword());
		assertEquals("email",user.getEmail());

		//update
		user.setUserId("userId1");
		userManageImpl.save(user);
		user=userManageImpl.get(user.getDbid());
		assertNotNull(user.getDbid());
		assertEquals("userId1",user.getUserId());
		assertEquals("realName",user.getRealName());
		assertEquals("password",user.getPassword());
		assertEquals("email",user.getEmail());

		//delete
		userManageImpl.deleteById(user.getDbid());

	}
	public static void main(String[] args) {
		TestGenerateUtil.generate(User.class);
	}
	
}
