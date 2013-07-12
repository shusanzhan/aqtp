/**
 * 
 */
package com.ystech.springsecurity.service;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.stereotype.Component;

import com.ystech.aqtp.model.Breeder;
import com.ystech.aqtp.model.User;
import com.ystech.aqtp.service.BreederManageImpl;
import com.ystech.aqtp.service.UserManageImpl;
import com.ystech.core.dao.HibernateEntityDao;
import com.ystech.core.test.SpringTxTestCase;
import com.ystech.core.util.TestGenerateUtil;

/**
 * @author shusanzhan
 * @date 2013-6-22
 */
public class BreederManageImplTest extends SpringTxTestCase{
	private BreederManageImpl breederManageImpl;
	private UserManageImpl userManageImpl;
	@Resource
	public void setUserManageImpl(UserManageImpl userManageImpl) {
		this.userManageImpl = userManageImpl;
	}
	@Resource
	public void setBreederManageImpl(BreederManageImpl breederManageImpl) {
		this.breederManageImpl = breederManageImpl;
	}
	@Test
	public void testCRUD() throws Exception {
		//create
		Breeder breeder=new Breeder();
		//create
		User user=new User();

		user.setUserId("userId");
		user.setRealName("realName");
		user.setPassword("password");
		user.setEmail("email");
		user.setMobilePhone("12222");
		
		userManageImpl.save(user);
		breeder.setUser(user);
		breeder.setName("name");
		breeder.setSex("sex");
		breeder.setBirthday(new java.util.Date());
		breeder.setPhoto("photo");
		breeder.setEducationalBackground("1");
		breeder.setGraduationSchool("graduationSchool");

		breederManageImpl.save(breeder);
		assertNotNull(breeder.getDbid());
		//get
		breeder=breederManageImpl.get(breeder.getDbid());
		assertNotNull(breeder.getDbid());
		assertEquals("name",breeder.getName());
		assertEquals("sex",breeder.getSex());
		assertNotNull(breeder.getBirthday());
		assertEquals("photo",breeder.getPhoto());
		assertEquals(new Integer(1),breeder.getEducationalBackground());
		assertEquals("graduationSchool",breeder.getGraduationSchool());

		//update
		breeder.setName("name1");
		breederManageImpl.save(breeder);
		breeder=breederManageImpl.get(breeder.getDbid());
		assertNotNull(breeder.getDbid());
		assertEquals("name1",breeder.getName());
		assertEquals("sex",breeder.getSex());
		assertNotNull(breeder.getBirthday());
		assertEquals("photo",breeder.getPhoto());
		assertEquals(new Integer(1),breeder.getEducationalBackground());
		assertEquals("graduationSchool",breeder.getGraduationSchool());

		//delete
		breederManageImpl.deleteById(breeder.getDbid());

	}
	public static void main(String[] args) {
		TestGenerateUtil.generate(Breeder.class);
	}
	
}
