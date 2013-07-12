/**
 * 
 */
package com.ystech.aqtp.action;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ystech.aqtp.service.UserManageImpl;
import com.ystech.core.web.BaseController;

/**
 * @author shusanzhan
 * @date 2012-11-24
 */
@Component("loginAction")
@Scope("prototype")
public class LoginAction extends BaseController{
	private UserManageImpl userManageImpl;
	@Resource
	public void setUserManageImpl(UserManageImpl userManageImpl) {
		this.userManageImpl = userManageImpl;
	}
	public String login() throws Exception {
		return "login";
	}
	public String check() throws Exception {
		HttpServletRequest request = this.getRequest();
		String userName = request.getParameter("userName");
		String pa = request.getParameter("password");
		boolean checkUser = userManageImpl.checkUser(userName,pa);
		if(checkUser==true){
			return "index";
		}
		return "login";
	}
}
