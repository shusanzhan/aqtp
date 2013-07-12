/**
 * 
 */
package com.ystech.springsecurity.service;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.stereotype.Component;

import com.ystech.aqtp.model.Breed;
import com.ystech.aqtp.model.ChickenBatch;
import com.ystech.aqtp.model.Grade;
import com.ystech.aqtp.model.HealthCare;
import com.ystech.aqtp.service.BreedManageImpl;
import com.ystech.aqtp.service.ChickenBatchManageImpl;
import com.ystech.aqtp.service.GradeManageImpl;
import com.ystech.aqtp.service.HealthCareManageImpl;
import com.ystech.core.dao.HibernateEntityDao;
import com.ystech.core.test.SpringTxTestCase;
import com.ystech.core.util.TestGenerateUtil;

/**
 * @author shusanzhan
 * @date 2013-6-22
 */
public class HealthCareManageImplTest extends SpringTxTestCase{
	private HealthCareManageImpl healthCareManageImpl;

	@Resource
	public void setHealthCareManageImpl(HealthCareManageImpl healthCareManageImpl) {
		this.healthCareManageImpl = healthCareManageImpl;
	}
	private ChickenBatchManageImpl chickenBatchManageImpl;

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
		HealthCare healthCare=new HealthCare();

		healthCare.setChickenbatch(chickenBatch);
		healthCare.setName("name");
		healthCare.setBeginDate(new java.util.Date());
		healthCare.setEndDate(new java.util.Date());

		healthCareManageImpl.save(healthCare);
		assertNotNull(healthCare.getDbid());
		//get
		healthCare=healthCareManageImpl.get(healthCare.getDbid());
		assertNotNull(healthCare.getDbid());
		assertEquals("name",healthCare.getName());
		assertNotNull(healthCare.getBeginDate());
		assertNotNull(healthCare.getEndDate());

		//update
		healthCare.setName("name1");
		healthCareManageImpl.save(healthCare);
		healthCare=healthCareManageImpl.get(healthCare.getDbid());
		assertNotNull(healthCare.getDbid());
		assertEquals("name1",healthCare.getName());
		assertNotNull(healthCare.getBeginDate());
		assertNotNull(healthCare.getEndDate());

		//delete
		healthCareManageImpl.deleteById(healthCare.getDbid());

	}
	public static void main(String[] args) {
		TestGenerateUtil.generate(HealthCare.class);
	}
	
}
