package com.ystech.springsecurity.service;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import com.ystech.core.test.SpringTxTestCase;
import com.ystech.core.util.TestGenerateUtil;

public class ResourceManageImplTest extends SpringTxTestCase{
	private ResourceManageImpl resourceManageImpl;
	
	@Resource
	public void setResourceManageImpl(ResourceManageImpl resourceManageImpl) {
		this.resourceManageImpl = resourceManageImpl;
	}
	@Test
	@Rollback(false)
	public void testCRUD() throws Exception {
		//create
		com.ystech.springsecurity.model.Resource resource=new com.ystech.springsecurity.model.Resource();

		resource.setType("type");
		resource.setContent("content");
		resource.setTitle("title");
		resource.setParentId(1);
		resource.setMenu(1);
		resource.setOrderNo(1);
		resource.setParent(null);


		resourceManageImpl.save(resource);
		assertNotNull(resource.getDbid());
		//get
		resource=resourceManageImpl.get(resource.getDbid());
		assertNotNull(resource.getDbid());
		assertEquals("type",resource.getType());
		assertEquals("content",resource.getContent());
		assertEquals("title",resource.getTitle());
		assertEquals(new Integer(1),resource.getParentId());
		assertEquals(new Integer(1),resource.getOrderNo());


		//update
		resource.setType("type1");
		resourceManageImpl.save(resource);
		resource=resourceManageImpl.get(resource.getDbid());
		assertNotNull(resource.getDbid());
		assertEquals("type1",resource.getType());
		assertEquals("content",resource.getContent());
		assertEquals("title",resource.getTitle());
		assertEquals(new Integer(1),resource.getParentId());
		assertEquals(new Integer(1),resource.getOrderNo());


		//delete
		resourceManageImpl.deleteById(resource.getDbid());

	}
	public static void main(String[] args) {
		TestGenerateUtil.generate(com.ystech.springsecurity.model.Resource.class);
	}
	
}
