/**
 * 
 */
package com.ystech.springsecurity.service;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.stereotype.Component;

import com.ystech.aqtp.model.Breed;
import com.ystech.aqtp.model.ChickenBatch;
import com.ystech.aqtp.model.FeedFeeder;
import com.ystech.aqtp.model.Feeder;
import com.ystech.aqtp.model.Grade;
import com.ystech.aqtp.service.BreedManageImpl;
import com.ystech.aqtp.service.ChickenBatchManageImpl;
import com.ystech.aqtp.service.FeedFeederManageImpl;
import com.ystech.aqtp.service.FeederManageImpl;
import com.ystech.aqtp.service.GradeManageImpl;
import com.ystech.core.dao.HibernateEntityDao;
import com.ystech.core.test.SpringTxTestCase;
import com.ystech.core.util.TestGenerateUtil;

/**
 * @author shusanzhan
 * @date 2013-6-22
 */
public class FeedFeederManageImplTest extends SpringTxTestCase{
	private FeedFeederManageImpl feedFeederManageImpl;

	@Resource
	public void setFeedFeederManageImpl(FeedFeederManageImpl feedFeederManageImpl) {
		this.feedFeederManageImpl = feedFeederManageImpl;
	}
	private FeederManageImpl feederManageImpl;

	@Resource
	public void setFeederManageImpl(FeederManageImpl feederManageImpl) {
		this.feederManageImpl = feederManageImpl;
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
		Feeder feeder=new Feeder();

		feeder.setName("name");
		feeder.setElementsPercentage("elementsPercentage");
		feeder.setImage("image");
		feeder.setNote("note");

		feederManageImpl.save(feeder);
		//create
		FeedFeeder feedFeeder=new FeedFeeder();

		feedFeeder.setFeeder(feeder);
		feedFeeder.setChickenbatch(chickenBatch);
		feedFeeder.setName("name");
		feedFeederManageImpl.save(feedFeeder);
		assertNotNull(feedFeeder.getDbid());
		//get
		feedFeeder=feedFeederManageImpl.get(feedFeeder.getDbid());
		assertNotNull(feedFeeder.getDbid());
		assertEquals("name",feedFeeder.getName());
		//update
		feedFeeder.setName("name1");
		feedFeederManageImpl.save(feedFeeder);
		feedFeeder=feedFeederManageImpl.get(feedFeeder.getDbid());
		assertNotNull(feedFeeder.getDbid());
		assertEquals("name1",feedFeeder.getName());
		//delete
		feedFeederManageImpl.deleteById(feedFeeder.getDbid());

	}
	public static void main(String[] args) {
		TestGenerateUtil.generate(FeedFeeder.class);
	}
	
}
