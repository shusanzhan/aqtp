/**
 * 
 */
package com.ystech.springsecurity.service;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.stereotype.Component;

import com.ystech.aqtp.model.Drag;
import com.ystech.aqtp.model.DragType;
import com.ystech.aqtp.service.DragManageImpl;
import com.ystech.aqtp.service.DragTypeManageImpl;
import com.ystech.core.dao.HibernateEntityDao;
import com.ystech.core.test.SpringTxTestCase;
import com.ystech.core.util.TestGenerateUtil;

/**
 * @author shusanzhan
 * @date 2013-6-22
 */
public class DragManageImplTest extends SpringTxTestCase {
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
		//get
		drag=dragManageImpl.get(drag.getDbid());
		assertNotNull(drag.getDbid());
		assertEquals("name",drag.getName());
		assertEquals("generateBatch",drag.getGenerateBatch());
		assertEquals("effect",drag.getEffect());
		assertEquals("specification",drag.getSpecification());
		assertEquals("directions",drag.getDirections());
		assertEquals("note",drag.getNote());
		assertEquals(new Integer(1),drag.getRecordId());


		//update
		drag.setName("name1");
		dragManageImpl.save(drag);
		drag=dragManageImpl.get(drag.getDbid());
		assertNotNull(drag.getDbid());
		assertEquals("name1",drag.getName());
		assertEquals("generateBatch",drag.getGenerateBatch());
		assertEquals("effect",drag.getEffect());
		assertEquals("specification",drag.getSpecification());
		assertEquals("directions",drag.getDirections());
		assertEquals("note",drag.getNote());
		assertEquals(new Integer(1),drag.getRecordId());


		//delete
		dragManageImpl.deleteById(drag.getDbid());

	}
	public static void main(String[] args) {
		TestGenerateUtil.generate(Drag.class);
	}
	
}
