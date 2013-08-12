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
import com.ystech.aqtp.model.User;
import com.ystech.aqtp.service.DragManageImpl;
import com.ystech.aqtp.service.DragTypeManageImpl;
import com.ystech.aqtp.service.LoginLogManageImpl;
import com.ystech.aqtp.service.OperateLogManageImpl;
import com.ystech.core.dao.support.Page;
import com.ystech.core.security.SecurityUserHolder;
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
		User user = SecurityUserHolder.getCurrentUser();
		Page page= loginLogManageImpl.pageQuery(pageNo, pageSize, "from LoginLog where userId=? order by loginDate DESC", new Object[]{user.getDbid()});
		request.setAttribute("page", page);
		return "list";
	}
	/**
	 * 功能描述：删除登录日志信息
	 * @throws Exception
	 */
	public void delete() throws Exception {
		HttpServletRequest request = this.getRequest();
		String dbids = request.getParameter("dbids");
		int contNum=0;
		if(null!=dbids&&dbids.trim().length()>0){
			try {
				contNum = loginLogManageImpl.deleteByIds(dbids);
			} catch (Exception e) {
				e.printStackTrace();
				renderErrorMsg(e, "");
				return ;
			}
		}else{
			renderErrorMsg(new Throwable("未选择操作数据！"), "");
			return ;
		}
		String query = ParamUtil.getQueryUrl(request);
		renderMsg("/loginLog/queryList"+query, "成功删除数据【"+contNum+"】！");
		return;
	}
}
