package com.ystech.springsecurity.service;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Test;

import com.ystech.aqtp.model.User;
import com.ystech.aqtp.service.UserManageImpl;
import com.ystech.core.test.SpringTxTestCase;
import com.ystech.core.util.TestGenerateUtil;

public class UserManageImplTest extends SpringTxTestCase{
	private UserManageImpl userManageImpl;
	@Resource
	public void setUserManageImpl(UserManageImpl userManageImpl) {
		this.userManageImpl = userManageImpl;
	}
	@Test
	public void testCRUD() throws Exception {
		//create
		//create
		User user=new User();

		user.setUserId("userId");
		user.setRealName("realName");
		user.setPassword("password");
		user.setEmail("email");
		user.setMobilePhone("mobilePhone");
		user.setPhone("phone");
		user.setState(true);


		user.setAccountNonExpired(true);
		user.setAccountNonLocked(true);
		user.setCredentialsNonExpired(true);
		userManageImpl.save(user);
		assertNotNull(user.getDbid());
		//get
		user=userManageImpl.get(user.getDbid());
		assertNotNull(user.getDbid());
		assertEquals("userId",user.getUserId());
		assertEquals("realName",user.getRealName());
		assertEquals("password",user.getPassword());
		assertEquals("email",user.getEmail());
		assertEquals("mobilePhone",user.getMobilePhone());
		assertEquals("phone",user.getPhone());
		assertEquals(true,user.isState());


		assertEquals(true,user.isAccountNonExpired());
		assertEquals(true,user.isAccountNonLocked());
		assertEquals(true,user.isCredentialsNonExpired());
		//update
		user.setUserId("userId1");
		userManageImpl.save(user);
		user=userManageImpl.get(user.getDbid());
		assertNotNull(user.getDbid());
		assertEquals("userId1",user.getUserId());
		assertEquals("realName",user.getRealName());
		assertEquals("password",user.getPassword());
		assertEquals("email",user.getEmail());
		assertEquals("mobilePhone",user.getMobilePhone());
		assertEquals("phone",user.getPhone());
		assertEquals(true,user.isState());


		assertEquals(true,user.isAccountNonExpired());
		assertEquals(true,user.isAccountNonLocked());
		assertEquals(true,user.isCredentialsNonExpired());
		//delete
		userManageImpl.deleteById(user.getDbid());


	}
	public static void main(String[] args) {
		TestGenerateUtil.generate(User.class);
	}
	
}
