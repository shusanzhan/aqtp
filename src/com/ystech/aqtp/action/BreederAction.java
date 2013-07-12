/**
 * 
 */
package com.ystech.aqtp.action;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ystech.aqtp.model.Breeder;
import com.ystech.aqtp.model.User;
import com.ystech.aqtp.service.BreederManageImpl;
import com.ystech.aqtp.service.RoleManageImpl;
import com.ystech.aqtp.service.UserManageImpl;
import com.ystech.core.dao.support.Page;
import com.ystech.core.util.Md5;
import com.ystech.core.util.ParamUtil;
import com.ystech.core.web.BaseController;

/**
 * @author shusanzhan
 * @date 2013-7-7
 */
@Component("breederAction")
@Scope("prototype")
public class BreederAction extends BaseController{
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
		Page<Breeder> page=null;
		if(null!=userId&&userId.trim().length()>0){
			page = breederManageImpl.pageQuery(pageNo, pageSize, "from Breeder where name like '%"+userId+"%'", new Object[]{});
		}else{
			page = breederManageImpl.pageQuery(pageNo, pageSize, "from Breeder ", new Object[]{});
		}
		request.setAttribute("page", page);
		return "list";
	}
	/**
	 * 保存
	 * @throws Exception
	 */
	public void save() throws Exception {
		try{
			//保存用户信息
			String calcMD5 = Md5.calcMD5("123456{"+user.getUserId()+"}");
			user.setPassword(calcMD5);
			userManageImpl.save(user);
			
			//保存饲养员信息
			breeder.setUser(user);
			breeder.setName(user.getRealName());
			breederManageImpl.save(breeder);
			
		}catch (Exception e) {
			e.printStackTrace();
			renderErrorMsg(e, "");
		}
		renderMsg("/breeder/queryList", "保存数据成功！");
		return ;
	}
	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public String edit() throws Exception {
		HttpServletRequest request = this.getRequest();
		Integer dbid = ParamUtil.getIntParam(request, "dbid", -1);
		if(dbid>0){
			Breeder breeder2 = breederManageImpl.get(dbid);
			request.setAttribute("user", breeder2.getUser());
			request.setAttribute("breeder", breeder2);
		}
		return "edit";
	}
	public void delete() throws Exception {
		HttpServletRequest request = this.getRequest();
		Integer[] dbids = ParamUtil.getIntArraryByDbids(request,"dbids");
		if(null!=dbids&&dbids.length>0){
			try {
				for (Integer dbid : dbids) {
					Breeder breeder2 = breederManageImpl.get(dbid);
					breederManageImpl.deleteById(dbid);
					if(null!=breeder2.getUser())
						userManageImpl.deleteById(breeder2.getUser().getDbid());
				}
			} catch (Exception e) {
				e.printStackTrace();
				renderErrorMsg(e, "");
				return ;
			}
		}
		String query = ParamUtil.getQueryUrl(request);
		renderMsg("/breeder/queryList"+query, "删除数据成功！");
		return;
	}
}
