/**
 * 
 */
package com.ystech.aqtp.action;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ystech.aqtp.model.Department;
import com.ystech.aqtp.model.Enterprise;
import com.ystech.aqtp.model.User;
import com.ystech.aqtp.service.DepartmentManageImpl;
import com.ystech.aqtp.service.EnterpriseManageImpl;
import com.ystech.aqtp.service.UserManageImpl;
import com.ystech.core.util.ParamUtil;
import com.ystech.core.web.BaseController;

/**
 * @author shusanzhan
 * @date 2013-5-23
 */
@Component("departmentAction")
@Scope("prototype")
public class DepartmentAction extends BaseController{
	private Department department;
	private DepartmentManageImpl departmentManageImpl;
	private EnterpriseManageImpl enterpriseManageImpl;
	private UserManageImpl userManageImpl;
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	@Resource
	public void setDepartmentManageImpl(DepartmentManageImpl departmentManageImpl) {
		this.departmentManageImpl = departmentManageImpl;
	}
	@Resource
	public void setEnterpriseManageImpl(EnterpriseManageImpl enterpriseManageImpl) {
		this.enterpriseManageImpl = enterpriseManageImpl;
	}
	@Resource
	public void setUserManageImpl(UserManageImpl userManageImpl) {
		this.userManageImpl = userManageImpl;
	}
	/**
	 * 功能描述：部门树页面
	 * @return
	 * @throws Exception
	 */
	public String list() throws Exception {
		return "list";
	}
	
	/**
	 * 功能描述：添加部门信息
	 * @return
	 * @throws Exception
	 */
	public String edit() throws Exception {
		HttpServletRequest request = this.getRequest();
		Integer dbid = ParamUtil.getIntParam(request, "dbid", -1);
		if (dbid > 0) {
			Department department = departmentManageImpl.get(dbid);
			request.setAttribute("department", department);
		}
		return "edit";
	}
	/**
	 * 功能描述：部门信息保存
	 * @throws Exception
	 */
	public void save() throws Exception {
		HttpServletRequest request = this.getRequest();
		Integer parentId = ParamUtil.getIntParam(request, "parentId", -1);
		Integer manager = ParamUtil.getIntParam(request,"managerId", -1);
		try {
			if(manager>0){
				User user = userManageImpl.get(manager);
				department.setManager(user);
			}
			
			if(parentId>0){
				Department parent = departmentManageImpl.get(parentId);
				department.setParent(parent);
				
				departmentManageImpl.save(department);
			}else{
				Department parent = new Department();
				parent.setDbid(-1);
				department.setParent(parent);	
				departmentManageImpl.save(department);
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderErrorMsg(e, "");
		}
		renderMsg("/department/list", "保存数据成功！");
		return ;
	}
	/**
	 * 功能描述：删除部门信息
	 * @throws Exception
	 */
	public void delete() throws Exception {
		HttpServletRequest request = this.getRequest();
		Integer dbid = ParamUtil.getIntParam(request, "dbid", -1);
		try {
			departmentManageImpl.deleteById(dbid);
		} catch (Exception e) {
			e.printStackTrace();
			renderErrorMsg(e, "");
			return ;
		}
		renderMsg("", "删除数据成功！");
		return;
	}
	public void getDepartmentByDbid() throws Exception{
		HttpServletRequest request = this.getRequest();
		Integer dbid = ParamUtil.getIntParam(request, "dbid", -1);
		if(dbid>0){
			JSONObject object=new JSONObject();
			Department department2 = departmentManageImpl.get(dbid);
			if(null!=department2){
				object.put("dbid", department2.getDbid());
				object.put("name", department2.getName());
				object.put("phone", department2.getPhone());
				object.put("fax", department2.getFax());
				if(null!=department2.getManager())
					object.put("manager", department2.getManager().getRealName());
				object.put("suqNo", department2.getSuqNo());
				object.put("discription", department2.getDiscription());
				renderJson(object.toString());
			}else{
				renderText("error");
				return ;
			}
		}
		else{
			renderText("error");
			return ;
		}
	}
	/**
	 * 功能描述：部门树生成JSON串
	 * 逻辑描述：默认绑定一颗根节点，更节点的父节点为0
	 */
	public void editResourceJson() {
		try {
			JSONObject jsonObject=null;
			List<Department> departments = departmentManageImpl.executeSql("select * from department where  ISNULL(parentId) ",new Object[] {});
			if (null != departments && departments.size() > 0) {
				jsonObject=new JSONObject();
				jsonObject = makeJSONObject(departments.get(0));
			} 
			JSONArray array=new JSONArray();
			if(jsonObject!=null){
				array.put(jsonObject);
			}
			
			JSONObject jsonObject2 =new  JSONObject();
			List<Enterprise> enterprises = enterpriseManageImpl.getAll();
			if(null!=enterprises&&enterprises.size()==1){
				Enterprise enterprise = enterprises.get(0);
				jsonObject2.put("icon","/widgets/ztree/css/zTreeStyle/img/diy/1_open.png");// 根节点
				jsonObject2.put("root", "root");
				jsonObject2.put("id", enterprise.getDbid());
				jsonObject2.put("name", enterprise.getName());
				jsonObject2.put("open", true);
				jsonObject2.put("children",array);
				renderJson(jsonObject2.toString());
				System.err.println(jsonObject2.toString());
			}else{
				renderJson("1");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return;
	}
	/**
	 *功能描述： 将传入的对象转化为JSON数据格式
	 * 部门树：根节点，具有子结构节点，子节点
	 * @throws JSONException
	 */
	private JSONObject makeJSONObject(Department department) throws JSONException {
		JSONObject jObject = new JSONObject();
		List<Department> children = departmentManageImpl.find("from Department where parent.dbid=? order by suqNo",	new Object[] { department.getDbid() });
		if (null != children && children.size() > 0) {// 如果子部门不空
			if (department.getParent() != null&&department.getParent().getDbid()>0) {
				jObject.put("icon","/widgets/ztree/css/zTreeStyle/img/diy/2.png");// 菜单阶段
			} else{
				jObject.put("icon","/widgets/ztree/css/zTreeStyle/img/diy/2.png");// 菜单阶段
			}
			
			jObject.put("id", department.getDbid());
			jObject.put("name", department.getName());
			jObject.put("open", true);
			jObject.put("children", makeJSONChildren(children));
			return jObject;
		} else {
			if (department.getParent() != null&&department.getParent().getDbid()>0) {
				jObject.put("icon","/widgets/ztree/css/zTreeStyle/img/diy/2.png");// 菜单阶段
			}
			jObject.put("id", department.getDbid());
			jObject.put("name", department.getName());
			jObject.put("children", "");
			return jObject;
		}
	}
	/**
	 * 将部门数据生成可以编辑的JSON格式
	 * **/
	private JSONArray makeJSONChildren(List<Department> children)throws JSONException {
		JSONArray jsonArray = new JSONArray();
		for (Department department : children) {
			JSONObject subJSONjObject = makeJSONObject(department);
			jsonArray.put(subJSONjObject);
		}
		return jsonArray;
	}
}
