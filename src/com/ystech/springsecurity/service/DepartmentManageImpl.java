/**
 * 
 */
package com.ystech.springsecurity.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ystech.core.dao.HibernateEntityDao;
import com.ystech.springsecurity.model.Department;

/**
 * @author shusanzhan
 * @date 2013-5-23
 */
@Component("departmentManageImpl")
public class DepartmentManageImpl extends HibernateEntityDao<Department>{

	/**
	 * @return
	 */
	public String getDepartmentSelect() {
		List<Department> departments=getAll();
		String select="";
		if (null!=departments&&departments.size()>0) {
			Department department=departments.get(0);
			String lest = getListDep(department, "-");
			select=select+lest;
		}
		return select;
	}
	public String getListDep(Department department,String indent){
		try{
			StringBuilder sb = new StringBuilder();
			if (null!=department) {
				if (department.getDbid()==11) {
					sb.append("<option value='"+department.getDbid()+"'>"+department.getName()+"</option>");
				}
				List<Department> children = findBy("parent.dbid",department.getDbid());
				
				if (null!=children&&children.size()>0) {
					for (Department department2 : children) {
						sb.append("<option value='"+department2.getDbid()+"dp'>"+indent+""+department2.getName()+"</option>");
						List<Department> findBy = findBy("parent.dbid",department2.getDbid());
						if (null!=findBy&&findBy.size()>0.) {
							sb.append(getListDep(department2, indent+"-"));
						}
					}
				}
			}
			return sb.toString();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}
	public void save() {
	}
}
