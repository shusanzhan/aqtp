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
import com.ystech.aqtp.model.QuarantineCertificate;
import com.ystech.aqtp.service.BreedManageImpl;
import com.ystech.aqtp.service.ChickenBatchManageImpl;
import com.ystech.aqtp.service.GradeManageImpl;
import com.ystech.aqtp.service.QuarantineCertificateManageImpl;
import com.ystech.core.dao.HibernateEntityDao;
import com.ystech.core.test.SpringTxTestCase;
import com.ystech.core.util.TestGenerateUtil;

/**
 * @author shusanzhan
 * @date 2013-6-22
 */
public class QuarantineCertificateManageImplTest extends SpringTxTestCase{
	private QuarantineCertificateManageImpl quarantineCertificateManageImpl;

	@Resource
	public void setQuarantineCertificateManageImpl(
			QuarantineCertificateManageImpl quarantineCertificateManageImpl) {
		this.quarantineCertificateManageImpl = quarantineCertificateManageImpl;
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
		assertNotNull(chickenBatch.getDbid());
		//create
		QuarantineCertificate quarantineCertificate=new QuarantineCertificate();

		quarantineCertificate.setChickenbatch(chickenBatch);
		quarantineCertificate.setAwardGroup("awardGroup");
		quarantineCertificate.setAwardDate(new java.util.Date());
		quarantineCertificate.setCertificateImage("certificateImage");
		quarantineCertificate.setNote("note");
		quarantineCertificate.setTitle("title");
		quarantineCertificateManageImpl.save(quarantineCertificate);
		assertNotNull(quarantineCertificate.getDbid());
		//get
		quarantineCertificate=quarantineCertificateManageImpl.get(quarantineCertificate.getDbid());
		assertNotNull(quarantineCertificate.getDbid());
		assertEquals("awardGroup",quarantineCertificate.getAwardGroup());
		assertNotNull(quarantineCertificate.getAwardDate());
		assertEquals("certificateImage",quarantineCertificate.getCertificateImage());
		assertEquals("note",quarantineCertificate.getNote());
		assertEquals("title",quarantineCertificate.getTitle());
		//update
		quarantineCertificate.setAwardGroup("awardGroup1");
		quarantineCertificateManageImpl.save(quarantineCertificate);
		quarantineCertificate=quarantineCertificateManageImpl.get(quarantineCertificate.getDbid());
		assertNotNull(quarantineCertificate.getDbid());
		assertEquals("awardGroup1",quarantineCertificate.getAwardGroup());
		assertNotNull(quarantineCertificate.getAwardDate());
		assertEquals("certificateImage",quarantineCertificate.getCertificateImage());
		assertEquals("note",quarantineCertificate.getNote());
		assertEquals("title",quarantineCertificate.getTitle());
		//delete
		quarantineCertificateManageImpl.deleteById(quarantineCertificate.getDbid());

	}
	public static void main(String[] args) {
		TestGenerateUtil.generate(QuarantineCertificate.class);
	}
	
}
