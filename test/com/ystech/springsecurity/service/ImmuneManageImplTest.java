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
import com.ystech.aqtp.model.Immune;
import com.ystech.aqtp.service.BreedManageImpl;
import com.ystech.aqtp.service.ChickenBatchManageImpl;
import com.ystech.aqtp.service.GradeManageImpl;
import com.ystech.aqtp.service.ImmuneManageImpl;
import com.ystech.core.dao.HibernateEntityDao;
import com.ystech.core.test.SpringTxTestCase;
import com.ystech.core.util.TestGenerateUtil;

/**
 * @author shusanzhan
 * @date 2013-6-22
 */
public class ImmuneManageImplTest extends SpringTxTestCase {
	private ImmuneManageImpl immuneManageImpl;
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
	@Resource
	public void setImmuneManageImpl(ImmuneManageImpl immuneManageImpl) {
		this.immuneManageImpl = immuneManageImpl;
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
		Immune immune=new Immune();

		immune.setChickenbatch(chickenBatch);
		immune.setImmuneDate(new java.util.Date());
		immune.setImmunePerson("immunePerson");
		immune.setNote("note");

		immuneManageImpl.save(immune);
		assertNotNull(immune.getDbid());
		//get
		immune=immuneManageImpl.get(immune.getDbid());
		assertNotNull(immune.getDbid());
		assertNotNull(immune.getImmuneDate());
		assertEquals("immunePerson",immune.getImmunePerson());
		assertEquals("note",immune.getNote());

		//update
		immune.setImmunePerson("immunePerson1");
		immuneManageImpl.save(immune);
		immune=immuneManageImpl.get(immune.getDbid());
		assertNotNull(immune.getDbid());
		assertNotNull(immune.getImmuneDate());
		assertEquals("immunePerson1",immune.getImmunePerson());
		assertEquals("note",immune.getNote());

		//delete
		immuneManageImpl.deleteById(immune.getDbid());

	}
	public static void main(String[] args) {
		TestGenerateUtil.generate(Immune.class);
	}
	
}
