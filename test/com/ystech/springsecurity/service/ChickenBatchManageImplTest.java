/**
 * 
 */
package com.ystech.springsecurity.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.stereotype.Component;

import com.ystech.aqtp.model.Breed;
import com.ystech.aqtp.model.ChickenBatch;
import com.ystech.aqtp.model.Dimensiona;
import com.ystech.aqtp.model.DimensionaCode;
import com.ystech.aqtp.model.Grade;
import com.ystech.aqtp.pdf.ItextPdfManageImpl;
import com.ystech.aqtp.service.BreedManageImpl;
import com.ystech.aqtp.service.ChickenBatchManageImpl;
import com.ystech.aqtp.service.DimensionaCodeManageImpl;
import com.ystech.aqtp.service.DimensionaManageImpl;
import com.ystech.aqtp.service.GradeManageImpl;
import com.ystech.core.dao.HibernateEntityDao;
import com.ystech.core.test.SpringTxTestCase;
import com.ystech.core.util.TestGenerateUtil;

/**
 * @author shusanzhan
 * @date 2013-6-22
 */
public class ChickenBatchManageImplTest extends SpringTxTestCase{
	private ChickenBatchManageImpl chickenBatchManageImpl;
	private DimensionaCodeManageImpl dimensionaCodeManageImpl;
	private DimensionaManageImpl dimensionaManageImpl;
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
	public void setDimensionaCodeManageImpl(
			DimensionaCodeManageImpl dimensionaCodeManageImpl) {
		this.dimensionaCodeManageImpl = dimensionaCodeManageImpl;
	}
	@Resource
	public void setDimensionaManageImpl(DimensionaManageImpl dimensionaManageImpl) {
		this.dimensionaManageImpl = dimensionaManageImpl;
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
		//get
		chickenBatch=chickenBatchManageImpl.get(chickenBatch.getDbid());
		assertNotNull(chickenBatch.getDbid());
		assertEquals("batchNo",chickenBatch.getBatchNo());
		assertEquals("name",chickenBatch.getName());
		assertNotNull(chickenBatch.getBirthday());
		assertEquals(new Integer(1),chickenBatch.getAge());
		assertNotNull(chickenBatch.getOutBarDate());
		assertNotNull(chickenBatch.getIntoBarDate());






		//update
		chickenBatch.setBatchNo("batchNo1");
		chickenBatchManageImpl.save(chickenBatch);
		chickenBatch=chickenBatchManageImpl.get(chickenBatch.getDbid());
		assertNotNull(chickenBatch.getDbid());
		assertEquals("batchNo1",chickenBatch.getBatchNo());
		assertEquals("name",chickenBatch.getName());
		assertNotNull(chickenBatch.getBirthday());
		assertEquals(new Integer(1),chickenBatch.getAge());
		assertNotNull(chickenBatch.getOutBarDate());
		assertNotNull(chickenBatch.getIntoBarDate());




		//delete
		chickenBatchManageImpl.deleteById(chickenBatch.getDbid());

	}
	@Test
	public void testPDF() throws Exception {
		ChickenBatch chickenBatch = chickenBatchManageImpl.get(1);
		List<Dimensiona> dimensionas = dimensionaManageImpl.findBy("chickenbatch.dbid", 1);
		List<DimensionaCode> di=new ArrayList<DimensionaCode>();
		for (Dimensiona object : dimensionas) {
			if(null!=object){
				List<DimensionaCode> dimensionaCodes = dimensionaCodeManageImpl.findBy("dimensiona.dbid", object.getDbid());
				di.addAll(dimensionaCodes);
			}
		}
		try{
			ItextPdfManageImpl testPdf=new ItextPdfManageImpl();
			testPdf.createPdf(chickenBatch,dimensionas.get(0), di);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		TestGenerateUtil.generate(ChickenBatch.class);
	}
}
