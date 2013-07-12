/**
 * 
 */
package com.ystech.springsecurity.service;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.stereotype.Component;

import com.ystech.aqtp.model.Breed;
import com.ystech.aqtp.model.ChickenBatch;
import com.ystech.aqtp.model.Drag;
import com.ystech.aqtp.model.DragType;
import com.ystech.aqtp.model.Grade;
import com.ystech.aqtp.model.HealthCare;
import com.ystech.aqtp.model.HealthCareDrag;
import com.ystech.aqtp.service.BreedManageImpl;
import com.ystech.aqtp.service.ChickenBatchManageImpl;
import com.ystech.aqtp.service.DragManageImpl;
import com.ystech.aqtp.service.DragTypeManageImpl;
import com.ystech.aqtp.service.GradeManageImpl;
import com.ystech.aqtp.service.HealthCareDragManageImpl;
import com.ystech.aqtp.service.HealthCareManageImpl;
import com.ystech.core.dao.HibernateEntityDao;
import com.ystech.core.test.SpringTxTestCase;
import com.ystech.core.util.TestGenerateUtil;

/**
 * @author shusanzhan
 * @date 2013-6-22
 */
public class HealthCareDragManageImplTest extends SpringTxTestCase {
	private HealthCareDragManageImpl healthCareDragManageImpl;

	@Resource
	public void setHealthCareDragManageImpl(
			HealthCareDragManageImpl healthCareDragManageImpl) {
		this.healthCareDragManageImpl = healthCareDragManageImpl;
	}
	private DragManageImpl dragManageImpl;

	@Resource
	public void setDragManageImpl(DragManageImpl dragManageImpl) {
		this.dragManageImpl = dragManageImpl;
	}
	private DragTypeManageImpl dragTypeManageImpl;

	@Resource
	public void setDragTypeManageImpl(DragTypeManageImpl dragTypeManageImpl) {
		this.dragTypeManageImpl = dragTypeManageImpl;
	}
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
		DragType dragType=new DragType();

		dragType.setName("name");
		dragType.setCode("code");

		dragTypeManageImpl.save(dragType);
		//create
		Drag drag=new Drag();

		drag.setDragtype(dragType);
		drag.setName("name");
		drag.setGenerateBatch("generateBatch");
		drag.setEffect("effect");
		drag.setSpecification("specification");
		drag.setDirections("directions");
		drag.setNote("note");
		drag.setRecordId(1);
		dragManageImpl.save(drag);
		
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
		//create
		HealthCareDrag healthCareDrag=new HealthCareDrag();

		healthCareDrag.setDrag(drag);
		healthCareDrag.setHealthcare(healthCare);
		healthCareDrag.setName("name");
		healthCareDrag.setDose("dose");
		healthCareDragManageImpl.save(healthCareDrag);
		assertNotNull(healthCareDrag.getDbid());
		//get
		healthCareDrag=healthCareDragManageImpl.get(healthCareDrag.getDbid());
		assertNotNull(healthCareDrag.getDbid());
		assertEquals("name",healthCareDrag.getName());
		assertEquals("dose",healthCareDrag.getDose());
		//update
		healthCareDrag.setName("name1");
		healthCareDragManageImpl.save(healthCareDrag);
		healthCareDrag=healthCareDragManageImpl.get(healthCareDrag.getDbid());
		assertNotNull(healthCareDrag.getDbid());
		assertEquals("name1",healthCareDrag.getName());
		assertEquals("dose",healthCareDrag.getDose());
		//delete
		healthCareDragManageImpl.deleteById(healthCareDrag.getDbid());

	}
	public static void main(String[] args) {
		TestGenerateUtil.generate(HealthCareDrag.class);
	}
	
}
