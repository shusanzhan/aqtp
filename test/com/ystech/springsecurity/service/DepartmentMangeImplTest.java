/**
 * 
 */
package com.ystech.springsecurity.service;

import javax.annotation.Resource;

import org.junit.Test;

import com.ystech.core.test.SpringTxTestCase;
import com.ystech.core.util.TestGenerateUtil;
import com.ystech.springsecurity.model.Department;

/**
 * @author shusanzhan
 * @date 2013-5-23
 */
public class DepartmentMangeImplTest extends SpringTxTestCase{
	private DepartmentManageImpl departmentManageImpl;

	@Resource
	public void setDepartmentManageImpl(DepartmentManageImpl departmentManageImpl) {
		this.departmentManageImpl = departmentManageImpl;
	}
	@Test
	public void testCRUD() throws Exception {
		//create
		Department department=new Department();

		department.setName("name");
		department.setDiscription("discription");
		department.setParentId(1);
		department.setSuqNo(1);
		//department.setParent("parent");

		departmentManageImpl.save(department);
		assertNotNull(department.getDbid());
		//get
		department=departmentManageImpl.get(department.getDbid());
		assertNotNull(department.getDbid());
		assertEquals("name",department.getName());
		assertEquals("discription",department.getDiscription());
		assertEquals(new Integer(1),department.getParentId());
		assertEquals(new Integer(1),department.getSuqNo());
		//assertEquals("parent",department.getParent());

		//update
		department.setName("name1");
		departmentManageImpl.save(department);
		department=departmentManageImpl.get(department.getDbid());
		assertNotNull(department.getDbid());
		assertEquals("name1",department.getName());
		assertEquals("discription",department.getDiscription());
		assertEquals(new Integer(1),department.getParentId());
		assertEquals(new Integer(1),department.getSuqNo());
		//assertEquals("parent",department.getParent());

		//delete
		departmentManageImpl.deleteById(department.getDbid());

	}
	
	public static void main(String[] args) {
		TestGenerateUtil.generate(Department.class);
	}
}
