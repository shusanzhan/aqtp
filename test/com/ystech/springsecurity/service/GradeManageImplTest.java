/**
 * 
 */
package com.ystech.springsecurity.service;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.stereotype.Component;

import com.ystech.aqtp.model.Grade;
import com.ystech.aqtp.service.GradeManageImpl;
import com.ystech.core.dao.HibernateEntityDao;
import com.ystech.core.test.SpringTxTestCase;
import com.ystech.core.util.TestGenerateUtil;

/**
 * @author shusanzhan
 * @date 2013-6-22
 */
public class GradeManageImplTest extends SpringTxTestCase{
	private GradeManageImpl gradeManageImpl;

	@Resource
	public void setGradeManageImpl(GradeManageImpl gradeManageImpl) {
		this.gradeManageImpl = gradeManageImpl;
	}
	@Test
	public void testCRUD() throws Exception {
		//create
		Grade grade=new Grade();

		grade.setName("name");
		grade.setLevel("level");
		grade.setNote("note");
		grade.setRetailPrice(Float.valueOf("1.0"));

		gradeManageImpl.save(grade);
		assertNotNull(grade.getDbid());
		//get
		grade=gradeManageImpl.get(grade.getDbid());
		assertNotNull(grade.getDbid());
		assertEquals("name",grade.getName());
		assertEquals("level",grade.getLevel());
		assertEquals("note",grade.getNote());

		//update
		grade.setName("name1");
		gradeManageImpl.save(grade);
		grade=gradeManageImpl.get(grade.getDbid());
		assertNotNull(grade.getDbid());
		assertEquals("name1",grade.getName());
		assertEquals("level",grade.getLevel());
		assertEquals("note",grade.getNote());

		//delete
		gradeManageImpl.deleteById(grade.getDbid());

	}
	public static void main(String[] args) {
		TestGenerateUtil.generate(Grade.class);
	}
	
}
