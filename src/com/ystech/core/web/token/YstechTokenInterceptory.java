package com.ystech.core.web.token;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


import org.apache.struts2.ServletActionContext;
import org.apache.struts2.StrutsStatics;
import org.apache.struts2.interceptor.TokenInterceptor;
import org.apache.struts2.util.TokenHelper;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;

/** 
 * @author 作者  舒三战
 * @version 创建时间：2012-11-13 下午3:52:12 
 * 类说明 
 **/
public class YstechTokenInterceptory extends TokenInterceptor{
	public static final String INVALID_TOKEN_CODE = "invalid.token";
	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		ActionContext actionContext = ActionContext.getContext();

		HttpServletRequest request = (HttpServletRequest) actionContext
				.get(StrutsStatics.HTTP_REQUEST);
		HttpSession session = ServletActionContext.getRequest().getSession(true);
		String requestURI = request.getRequestURI();
		if(requestURI.contains("save")){
			if (log.isDebugEnabled()) {
	            log.debug("Intercepting invocation to check for valid transaction token.");
	        }
	
	        //see WW-2902: we need to use the real HttpSession here, as opposed to the map
	        //that wraps the session, because a new wrap is created on every request
	
	        synchronized (session) {
	            if (!TokenHelper.validToken()) {
	                return handleInvalidToken(invocation);
	            }
	        }
	        return handleValidToken(invocation);
		}
		else{
			return handleValidToken(invocation);
		}
	}

	@Override
	protected String handleInvalidToken(ActionInvocation invocation)
			throws Exception {
		 String handleInvalidToken = super.handleInvalidToken(invocation);
		 return handleInvalidToken;
	}

	@Override
	protected String handleValidToken(ActionInvocation invocation)
			throws Exception {
		return super.handleValidToken(invocation);
	}
	
}
