/**
 * 
 */
package com.ystech.springsecurity.service;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.stereotype.Component;

import com.ystech.aqtp.model.DragType;
import com.ystech.aqtp.service.DragTypeManageImpl;
import com.ystech.core.dao.HibernateEntityDao;
import com.ystech.core.test.SpringTxTestCase;
import com.ystech.core.util.TestGenerateUtil;

/**
 * @author shusanzhan
 * @date 2013-6-22
 */
public class DragTypeManageImplTest extends SpringTxTestCase{
	private DragTypeManageImpl dragTypeManageImpl;

	@Resource
	public void setDragTypeManageImpl(DragTypeManageImpl dragTypeManageImpl) {
		this.dragTypeManageImpl = dragTypeManageImpl;
	}
	@Test
	public void testCRUD() throws Exception {
		//create
		DragType dragType=new DragType();

		dragType.setName("name");
		dragType.setCode("code");

		dragTypeManageImpl.save(dragType);
		assertNotNull(dragType.getDbid());
		//get
		dragType=dragTypeManageImpl.get(dragType.getDbid());
		assertNotNull(dragType.getDbid());
		assertEquals("name",dragType.getName());
		assertEquals("code",dragType.getCode());

		//update
		dragType.setName("name1");
		dragTypeManageImpl.save(dragType);
		dragType=dragTypeManageImpl.get(dragType.getDbid());
		assertNotNull(dragType.getDbid());
		assertEquals("name1",dragType.getName());
		assertEquals("code",dragType.getCode());

		//delete
		dragTypeManageImpl.deleteById(dragType.getDbid());

	}
	public static void main(String[] args) {
		TestGenerateUtil.generate(DragType.class);
	}
	
}
