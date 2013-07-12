/**
 * 
 */
package com.ystech.springsecurity.service;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.stereotype.Component;

import com.ystech.aqtp.model.Feeder;
import com.ystech.aqtp.service.FeederManageImpl;
import com.ystech.core.dao.HibernateEntityDao;
import com.ystech.core.test.SpringTxTestCase;
import com.ystech.core.util.TestGenerateUtil;

/**
 * @author shusanzhan
 * @date 2013-6-22
 */
public class FeederManageImplTest extends SpringTxTestCase {
	private FeederManageImpl feederManageImpl;

	@Resource
	public void setFeederManageImpl(FeederManageImpl feederManageImpl) {
		this.feederManageImpl = feederManageImpl;
	}
	@Test
	public void testCRUD() throws Exception {
		//create
		Feeder feeder=new Feeder();

		feeder.setName("name");
		feeder.setElementsPercentage("elementsPercentage");
		feeder.setImage("image");
		feeder.setNote("note");

		feederManageImpl.save(feeder);
		assertNotNull(feeder.getDbid());
		//get
		feeder=feederManageImpl.get(feeder.getDbid());
		assertNotNull(feeder.getDbid());
		assertEquals("name",feeder.getName());
		assertEquals("elementsPercentage",feeder.getElementsPercentage());
		assertEquals("image",feeder.getImage());
		assertEquals("note",feeder.getNote());

		//update
		feeder.setName("name1");
		feederManageImpl.save(feeder);
		feeder=feederManageImpl.get(feeder.getDbid());
		assertNotNull(feeder.getDbid());
		assertEquals("name1",feeder.getName());
		assertEquals("elementsPercentage",feeder.getElementsPercentage());
		assertEquals("image",feeder.getImage());
		assertEquals("note",feeder.getNote());

		//delete
		feederManageImpl.deleteById(feeder.getDbid());

	}
	public static void main(String[] args) {
		TestGenerateUtil.generate(Feeder.class);
	}
	
}
