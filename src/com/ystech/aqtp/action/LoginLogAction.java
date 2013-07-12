/**
 * 
 */
package com.ystech.aqtp.action;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.catalina.connector.Request;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ystech.aqtp.model.Breed;
import com.ystech.aqtp.model.Drag;
import com.ystech.aqtp.model.DragType;
import com.ystech.aqtp.service.DragManageImpl;
import com.ystech.aqtp.service.DragTypeManageImpl;
import com.ystech.aqtp.service.LoginLogManageImpl;
import com.ystech.aqtp.service.OperateLogManageImpl;
import com.ystech.core.dao.support.Page;
import com.ystech.core.util.ParamUtil;
import com.ystech.core.web.BaseController;

/**
 * @author shusanzhan
 * @date 2013-7-7
 */
@Component("loginLogAction")
@Scope("prototype")
public class LoginLogAction extends BaseController{
	private LoginLogManageImpl loginLogManageImpl;
	@Resource
	public void setLoginLogManageImpl(LoginLogManageImpl loginLogManageImpl) {
		this.loginLogManageImpl = loginLogManageImpl;
	}

	/**
	 * 功能描述：列表查询
	 * 参数描述：
	 * 逻辑描述：
	 * @return
	 * @throws Exception
	 */
	public String queryList() throws Exception {
		HttpServletRequest request = this.getRequest();
		Integer pageSize = ParamUtil.getIntParam(request, "pageSize", 10);
		Integer pageNo = ParamUtil.getIntParam(request, "currentPage", 1);
		String name = request.getParameter("userName");
		Page page=null;
		if(null!=name&&name.trim().length()>0){
			 page= loginLogManageImpl.pageQuery(pageNo, pageSize, "from LoginLog where userName like '%"+name+"%'", new Object[]{});
		}else{
			page= loginLogManageImpl.pageQuery(pageNo, pageSize, "from LoginLog ", new Object[]{});
		}
		request.setAttribute("page", page);
		return "list";
	}
}
