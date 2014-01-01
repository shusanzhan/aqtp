package com.ystech.aqtp.action;

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

import com.ystech.aqtp.model.Breeder;
import com.ystech.aqtp.model.Role;
import com.ystech.aqtp.model.User;
import com.ystech.aqtp.service.BreederManageImpl;
import com.ystech.aqtp.service.RoleManageImpl;
import com.ystech.aqtp.service.UserManageImpl;
import com.ystech.core.dao.support.Page;
import com.ystech.core.security.SecurityUserHolder;
import com.ystech.core.util.Md5;
import com.ystech.core.util.ParamUtil;
import com.ystech.core.web.BaseController;
@Component("userAction")
@Scope("prototype")
public class UserAction extends BaseController{
	private User user;
	private Breeder breeder;
	private UserManageImpl userManageImpl;
	private RoleManageImpl roleManageImpl;
	private BreederManageImpl breederManageImpl;
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	public Breeder getBreeder() {
		return breeder;
	}
	public void setBreeder(Breeder breeder) {
		this.breeder = breeder;
	}
	@Resource
	public void setUserManageImpl(UserManageImpl userManageImpl) {
		this.userManageImpl = userManageImpl;
	}
	@Resource
	public void setRoleManageImpl(RoleManageImpl roleManageImpl) {
		this.roleManageImpl = roleManageImpl;
	}
	@Resource
	public void setBreederManageImpl(BreederManageImpl breederManageImpl) {
		this.breederManageImpl = breederManageImpl;
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
			//保存用户信息
			String calcMD5 = Md5.calcMD5("123456{"+user.getUserId()+"}");
			user.setPassword(calcMD5);
			Integer dbid = user.getDbid();
			if(null!=dbid&&dbid>0){
				User user2 = userManageImpl.get(dbid);
				Set<Role> roles = user2.getRoles();
				user.setRoles(roles);
			}
			userManageImpl.save(user);
			
			breeder.setUser(user);
			breeder.setName(user.getRealName());
			breederManageImpl.save(breeder);
			
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
			request.setAttribute("breeder", user.getBreeder());
		}
		return "edit";
	}
	/**
	 * 功能描述：保存用户信息
	 * @throws Exception
	 */
	public void saveEdit() throws Exception {
		try{
			//保存用户信息
			String calcMD5 = Md5.calcMD5("123456{"+user.getUserId()+"}");
			Integer dbid = user.getDbid();
			User user2 = userManageImpl.get(dbid);
			user2.setEmail(user.getEmail());
			user2.setMobilePhone(user.getMobilePhone());
			user2.setPassword(user.getPassword());
			user2.setPhone(user.getPhone());
			user2.setRealName(user.getRealName());
			user2.setUserId(user.getUserId());
			user2.setPassword(calcMD5);
			userManageImpl.save(user2);
			
			if(null==breeder.getDbid()||breeder.getDbid()<=0){
				breeder.setUser(user);
				breeder.setName(user.getRealName());
				breederManageImpl.save(breeder);
			}
			else{
				Breeder breeder2 = breederManageImpl.get(breeder.getDbid());
				breeder2.setUser(user2);
				breeder2.setName(user2.getRealName());
				breeder2.setEducationalBackground(breeder.getEducationalBackground());
				breeder2.setBirthday(breeder.getBirthday());
				breeder2.setGraduationSchool(breeder.getGraduationSchool());
				breeder2.setPhoto(breeder.getPhoto());
				breeder2.setSex(breeder.getSex());
				breeder2.setIntroduction(breeder.getIntroduction());
				breederManageImpl.save(breeder2);
			}
		}catch (Exception e) {
			e.printStackTrace();
			renderErrorMsg(e, "");
		}
		renderMsg("/user/queryList", "保存数据成功！");
		return ;
	}
	public void delete() throws Exception {
		HttpServletRequest request = this.getRequest();
		Integer[] dbids = ParamUtil.getIntArraryByDbids(request,"dbids");
		if(null!=dbids&&dbids.length>0){
			try {
				for (Integer dbid : dbids) {
					User user = userManageImpl.get(dbid);
					breederManageImpl.deleteById(user.getBreeder().getDbid());
					userManageImpl.deleteById(dbid);
				}
			} catch (Exception e) {
				e.printStackTrace();
				renderErrorMsg(e, "");
				return ;
			}
		}
		String query = ParamUtil.getQueryUrl(request);
		renderMsg("/user/queryList"+query, "删除数据成功！");
		return;
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
	public void validateUser() {
		HttpServletRequest request = this.getRequest();
		String userId =null;
		userId=request.getParameter("user.userId");
		List<User> users=null;
		if(null!=userId&&userId.trim().length()>0){
			users = userManageImpl.findBy("userId", userId);
		}else{
			renderText("系统已经存在该用户了!请换一个用户ID!");//输入的账户类型不匹配！
			return ;
		}
		
		if (null!=users&&users.size()>0) {
			renderText("系统已经存在该用户了!请换一个用户ID!");//已经注册，请直接登录！
		}else{
			renderText("success");//
		}
		
	}
	/**
	 * 功能描述：个人设置-设置用户信息
	 * @return
	 * @throws Exception
	 */
	public String editSelf() throws Exception {
		User user = SecurityUserHolder.getCurrentUser();
		HttpServletRequest request = this.getRequest();
		if(null!=user&&user.getDbid()>0){
			User user2 = userManageImpl.get(user.getDbid());
			request.setAttribute("user", user2);
			request.setAttribute("breeder", user2.getBreeder());
		}
		return "editSelf";
	}
	/**
	 * 功能描述：保存用户信息
	 * @throws Exception
	 */
	public void saveEditSelf() throws Exception {
		try{
			//保存用户信息
			String calcMD5 = Md5.calcMD5("123456{"+user.getUserId()+"}");
			Integer dbid = user.getDbid();
			User user2 = userManageImpl.get(dbid);
			user2.setEmail(user.getEmail());
			user2.setMobilePhone(user.getMobilePhone());
			user2.setPassword(user.getPassword());
			user2.setPhone(user.getPhone());
			user2.setRealName(user.getRealName());
			user2.setUserId(user.getUserId());
			user2.setPassword(calcMD5);
			userManageImpl.save(user2);
			
			Breeder breeder2 = breederManageImpl.get(breeder.getDbid());
			breeder2.setUser(user2);
			breeder2.setName(user2.getRealName());
			breeder2.setEducationalBackground(breeder.getEducationalBackground());
			breeder2.setBirthday(breeder.getBirthday());
			breeder2.setGraduationSchool(breeder.getGraduationSchool());
			breeder2.setPhoto(breeder.getPhoto());
			breeder2.setSex(breeder.getSex());
			breeder2.setIntroduction(breeder.getIntroduction());
			breederManageImpl.save(breeder2);
		}catch (Exception e) {
			e.printStackTrace();
			renderErrorMsg(e, "");
		}
		renderMsg("/user/editSelf", "保存数据成功！");
		return ;
	}
	/**
	 * 功能描述：修改密码
	 * @return
	 * @throws Exception
	 */
	public String modifyPassword() throws Exception {
		User user = SecurityUserHolder.getCurrentUser();
		HttpServletRequest request = this.getRequest();
		if(null!=user&&user.getDbid()>0){
			User user2 = userManageImpl.get(user.getDbid());
			request.setAttribute("user", user2);
		}
		return "modifyPassword";
	}
	/**
	 * 功能描述：修改密码
	 * @return
	 * @throws Exception
	 */
	public void updateModifyPassword() throws Exception {
		HttpServletRequest request = getRequest();
		Integer dbid = ParamUtil.getIntParam(request, "dbid", -1);
		String oldPassword = request.getParameter("oldPassword");
		String password = request.getParameter("password");
		if(null==oldPassword||oldPassword.trim().length()<=0){
			renderErrorMsg(new Throwable("输入旧密码错误！"), "");
			return ;
		}
		if(null==password||password.trim().length()<=0){
			renderErrorMsg(new Throwable("密码输入错误！"), "");
			return ;
		}
		try{
			if (dbid>0) {
				User user2 = userManageImpl.get(dbid);
				String password2 = user2.getPassword();
				String calcMD5 = Md5.calcMD5(oldPassword+"{"+user2.getUserId()+"}");
				if(password2.equals(calcMD5)){
					user2.setPassword(Md5.calcMD5(password+"{"+user2.getUserId()+"}"));
					userManageImpl.save(user2);
				}else{
					renderErrorMsg(new Throwable("旧密码输入错误！"), "");
					return ;	
				}
			} else {
				renderErrorMsg(new Throwable("操作数据错误！"), "");
				return ;
			}	
		}catch (Exception e) {
			e.printStackTrace();
			renderErrorMsg(e, "");
		}
		renderMsg("/user/modifyPassword", "修改密码成功！");
		return ;
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
