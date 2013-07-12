/**
 * 
 */
package com.ystech.springsecurity.service;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.stereotype.Component;

import com.ystech.aqtp.model.BreaderBreed;
import com.ystech.aqtp.model.Breed;
import com.ystech.aqtp.model.Breeder;
import com.ystech.aqtp.model.ChickenBatch;
import com.ystech.aqtp.model.Grade;
import com.ystech.aqtp.model.User;
import com.ystech.aqtp.service.BreaderBreedManageImpl;
import com.ystech.aqtp.service.BreedManageImpl;
import com.ystech.aqtp.service.BreederManageImpl;
import com.ystech.aqtp.service.ChickenBatchManageImpl;
import com.ystech.aqtp.service.GradeManageImpl;
import com.ystech.aqtp.service.UserManageImpl;
import com.ystech.core.dao.HibernateEntityDao;
import com.ystech.core.test.SpringTxTestCase;
import com.ystech.core.util.TestGenerateUtil;

/**
 * @author shusanzhan
 * @date 2013-6-22
 */
public class BreaderBreedManageImplTest extends SpringTxTestCase{
	private BreaderBreedManageImpl breaderBreedManageImpl;
	private ChickenBatchManageImpl chickenBatchManageImpl;
	private UserManageImpl userManageImpl;
	private BreederManageImpl breederManageImpl;
	@Resource
	public void setUserManageImpl(UserManageImpl userManageImpl) {
		this.userManageImpl = userManageImpl;
	}
	@Resource
	public void setBreederManageImpl(BreederManageImpl breederManageImpl) {
		this.breederManageImpl = breederManageImpl;
	}
	@Resource
	public void setBreaderBreedManageImpl(
			BreaderBreedManageImpl breaderBreedManageImpl) {
		this.breaderBreedManageImpl = breaderBreedManageImpl;
	}

	@Resource
	public void setChickenBatchManageImpl(
			ChickenBatchManageImpl chickenBatchManageImpl) {
		this.chickenBatchManageImpl = chickenBatchManageImpl;
	}
	private BreedManageImpl breedManageImpl;

	@Resource
	public void setBreedManageImpl(BreedManageImpl breedManageImpl) {
		this.breedManageImpl = breedManageImpl;
	}
	private GradeManageImpl gradeManageImpl;

	@Resource
	public void setGradeManageImpl(GradeManageImpl gradeManageImpl) {
		this.gradeManageImpl = gradeManageImpl;
	}
	@Test
	public void testCRUD() throws Exception {
		//create
		ChickenBatch chickenBatch=new ChickenBatch();
		Breed breed=new Breed();
		breed.setName("name");
		breed.setCharacteristic("characteristic");
		breed.setNote("note");
		breed.setCharCode("charCode");

		breedManageImpl.save(breed);
		
		//create
		Grade grade=new Grade();

		grade.setName("name");
		grade.setLevel("level");
		grade.setNote("note");
		grade.setRetailPrice(Float.valueOf("1.0"));

		gradeManageImpl.save(grade);
		
		chickenBatch.setBreed(breed);
		chickenBatch.setGrade(grade);
		chickenBatch.setBatchNo("batchNo");
		chickenBatch.setName("name");
		chickenBatch.setBirthday(new java.util.Date());
		chickenBatch.setAge(1);
		chickenBatch.setOutBarDate(new java.util.Date());
		chickenBatch.setIntoBarDate(new java.util.Date());




		chickenBatchManageImpl.save(chickenBatch);
		
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
		
		//create
		BreaderBreed breaderBreed=new BreaderBreed();

		breaderBreed.setChickenbatch(chickenBatch);
		breaderBreed.setBreeder(breeder);
		breaderBreed.setName("name");
		breaderBreedManageImpl.save(breaderBreed);
		assertNotNull(breaderBreed.getDbid());
		//get
		breaderBreed=breaderBreedManageImpl.get(breaderBreed.getDbid());
		assertNotNull(breaderBreed.getDbid());
		assertEquals("name",breaderBreed.getName());
		//update
		breaderBreed.setName("name1");
		breaderBreedManageImpl.save(breaderBreed);
		breaderBreed=breaderBreedManageImpl.get(breaderBreed.getDbid());
		assertNotNull(breaderBreed.getDbid());
		assertEquals("name1",breaderBreed.getName());
		//delete
		breaderBreedManageImpl.deleteById(breaderBreed.getDbid());

	}
	
	
	public static void main(String[] args) {
		TestGenerateUtil.generate(BreaderBreed.class);
	}
	
}
