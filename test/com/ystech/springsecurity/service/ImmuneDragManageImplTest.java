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
import com.ystech.aqtp.model.Immune;
import com.ystech.aqtp.model.ImmuneDrag;
import com.ystech.aqtp.service.BreedManageImpl;
import com.ystech.aqtp.service.ChickenBatchManageImpl;
import com.ystech.aqtp.service.DragManageImpl;
import com.ystech.aqtp.service.DragTypeManageImpl;
import com.ystech.aqtp.service.GradeManageImpl;
import com.ystech.aqtp.service.ImmuneDragManageImpl;
import com.ystech.aqtp.service.ImmuneManageImpl;
import com.ystech.core.dao.HibernateEntityDao;
import com.ystech.core.test.SpringTxTestCase;
import com.ystech.core.util.TestGenerateUtil;

/**
 * @author shusanzhan
 * @date 2013-6-22
 */
public class ImmuneDragManageImplTest extends SpringTxTestCase{
	private ImmuneDragManageImpl immuneDragManageImpl;

	@Resource
	public void setImmuneDragManageImpl(ImmuneDragManageImpl immuneDragManageImpl) {
		this.immuneDragManageImpl = immuneDragManageImpl;
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
		assertNotNull(drag.getDbid());
		
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
		//create
		ImmuneDrag immuneDrag=new ImmuneDrag();

		immuneDrag.setDrag(drag);
		immuneDrag.setImmune(immune);
		immuneDrag.setName("name");
		immuneDrag.setDose("dose");
		immuneDragManageImpl.save(immuneDrag);
		assertNotNull(immuneDrag.getDbid());
		//get
		immuneDrag=immuneDragManageImpl.get(immuneDrag.getDbid());
		assertNotNull(immuneDrag.getDbid());
		assertEquals("name",immuneDrag.getName());
		assertEquals("dose",immuneDrag.getDose());
		//update
		immuneDrag.setName("name1");
		immuneDragManageImpl.save(immuneDrag);
		immuneDrag=immuneDragManageImpl.get(immuneDrag.getDbid());
		assertNotNull(immuneDrag.getDbid());
		assertEquals("name1",immuneDrag.getName());
		assertEquals("dose",immuneDrag.getDose());
		//delete
		immuneDragManageImpl.deleteById(immuneDrag.getDbid());

	}
	public static void main(String[] args) {
		TestGenerateUtil.generate(ImmuneDrag.class);
	}
	
}
