package com.ystech.springsecurity.action;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ystech.core.dao.support.Page;
import com.ystech.core.util.Md5;
import com.ystech.core.util.ParamUtil;
import com.ystech.core.web.BaseController;
import com.ystech.springsecurity.model.Role;
import com.ystech.springsecurity.model.User;
import com.ystech.springsecurity.service.RoleManageImpl;
import com.ystech.springsecurity.service.UserManageImpl;
@Component("userAction")
@Scope("prototype")
public class UserAction extends BaseController{
	private User user;
	private UserManageImpl userManageImpl;
	private RoleManageImpl roleManageImpl;
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	@Resource
	public void setUserManageImpl(UserManageImpl userManageImpl) {
		this.userManageImpl = userManageImpl;
	}
	@Resource
	public void setRoleManageImpl(RoleManageImpl roleManageImpl) {
		this.roleManageImpl = roleManageImpl;
	}
	public String queryList() throws Exception {
		HttpServletRequest request = this.getRequest();
		Integer pageSize = ParamUtil.getIntParam(request, "pageSize", 10);
		Integer pageNo = ParamUtil.getIntParam(request, "currentPage", 1);
		String userId = request.getParameter("userName");
		Page<User> page=null;
		if(null!=userId&&userId.trim().length()>0){
			page = userManageImpl.pageQuery(pageNo, pageSize, "from User where userId like '%"+userId+"%'", new Object[]{});
		}else{
			page = userManageImpl.pageQuery(pageNo, pageSize, "from User ", new Object[]{});
		}
		List<User> result = page.getResult();
		for (User user : result) {
			System.out.println(user.getEmail());
		}
		request.setAttribute("page", page);
		return "list";
	}
	public void save() throws Exception {
		try{
			String calcMD5 = Md5.calcMD5("123456{"+user.getUserId()+"}");
			user.setPassword(calcMD5);
			userManageImpl.save(user);
		}catch (Exception e) {
			e.printStackTrace();
			renderErrorMsg(e, "");
		}
		renderMsg("/user/queryList", "保存数据成功！");
		return ;
	}
	public String edit() throws Exception {
		HttpServletRequest request = this.getRequest();
		Integer dbid = ParamUtil.getIntParam(request, "dbid", -1);
		if(dbid>0){
			User user = userManageImpl.get(dbid);
			request.setAttribute("user", user);
		}
		return "edit";
	}
	public void delete() throws Exception {
		HttpServletRequest request = this.getRequest();
		Integer dbid = ParamUtil.getIntParam(request, "dbid", -1);
		try {
			userManageImpl.deleteById(dbid);
		} catch (Exception e) {
			e.printStackTrace();
			renderErrorMsg(e, "");
		}
		renderMsg("/user/queryList", "保存数据成功！");
		return ;
	}
	
	/**系统配置角色**/
	public String userRole() throws Exception {
		HttpServletRequest request = this.getRequest();
		Integer dbid = ParamUtil.getIntParam(request, "dbid", -1);
		if(dbid>0){
			user=userManageImpl.get(dbid);
		}
		return "userRole";
	}
	public void saveUserRole() throws Exception {
		HttpServletRequest request = this.getRequest();
		Integer dbid = ParamUtil.getIntParam(request, "dbid", -1);
		Integer[] roleIds = ParamUtil.getIntArray(request, "id");
		Set<Role> roles=new HashSet<Role>();
		try{
			if(dbid>0){
				user = userManageImpl.get(dbid);
				Set<Role> rols = user.getRoles();
				roles.clear();
				if(null!=roleIds&&roleIds.length>0){
					for (Integer roId : roleIds) {
						Role role = roleManageImpl.get(roId);
						roles.add(role);
					}
				}
				user.setRoles(roles);
				userManageImpl.save(user);
			}
			renderMsg("/user/queryList", user.getUserId()+"分配角色成功！");
		}catch (Exception e) {
			e.printStackTrace();
			renderErrorMsg(e, "");
			return ;
		}
		return ;
	}
	public void userRoleJson() throws JSONException {
		HttpServletRequest request = this.getRequest();
		Integer dbid = ParamUtil.getIntParam(request, "dbid", -1);
		if(dbid>0){
			user=userManageImpl.get(dbid);
			Set<Role> roles = user.getRoles();
			JSONArray makeJson = makeJson(roles);
			renderJson(makeJson.toString());
			return ;
		}
		renderJson("1");
		return ;
	}
	private JSONArray makeJson(Set<Role> roles) throws JSONException{
		List<Role> roList = roleManageImpl.findBy("state", 1);
		JSONArray jsonArray=null;
		if(null!=roList&&roList.size()>0){
			jsonArray=new JSONArray();
			for (Role role : roList) {
				JSONObject object=new JSONObject();
				object.put("dbid", role.getDbid());
				object.put("name", role.getName());
				if(null!=roles&&roles.size()>0){
					for (Role role2 : roles) {
						if(role.getDbid()==role2.getDbid()){
							object.put("checked", true);
							break;
						}
					}
				}
				jsonArray.put(object);
			}
		}
		return jsonArray;
	}
}
