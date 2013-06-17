/**
 * 
 */
package com.ystech.core.security.filter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.ystech.core.util.Md5;
import com.ystech.springsecurity.model.User;
import com.ystech.springsecurity.service.UserManageImpl;

/**
 * @author shusanzhan
 * @date 2012-12-2
 */
public class LoginAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	public static final String VALIDATE_CODE = "validateCode";  
    public static final String USERNAME = "j_username";  
    public static final String PASSWORD = "j_password";
   
    private UserManageImpl userManageImpl;
    
	public UserManageImpl getUserManageImpl() {
		return userManageImpl;
	}
	 @Resource
	public void setUserManageImpl(UserManageImpl userManageImpl) {
		this.userManageImpl = userManageImpl;
	}

	public static String getValidateCode() {
		return VALIDATE_CODE;
	}

	public static String getUsername() {
		return USERNAME;
	}

	public static String getPassword() {
		return PASSWORD;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request,	HttpServletResponse response) throws AuthenticationException {
		  System.err.println("================= spring scurity 第一步 调用loginFilter");
		  if (!request.getMethod().equals("POST")) {  
	            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());  
	        }  
	        //检测验证码  
	        //checkValidateCode(request);  
	          
	        String username = obtainUsername(request);  
	        String password = obtainPassword(request);  
	          
	        //验证用户账号与密码是否对应  
	        username = username.trim();  
	          
	        User users = userManageImpl.findBy("userId", username).get(0);  
	        String calcMD5 = Md5.calcMD5(users.getUserId()+password);
	        if(users == null || !users.getPassword().equals(calcMD5)) {  
	    /* 
	        在我们配置的simpleUrlAuthenticationFailureHandler处理登录失败的处理类在这么一段 
	        这样我们可以在登录失败后，向用户提供相应的信息。 
	        if (forwardToDestination) { 
	            request.setAttribute(WebAttributes.AUTHENTICATION_EXCEPTION, exception); 
	        } else { 
	            HttpSession session = request.getSession(false); 
	 
	            if (session != null || allowSessionCreation) { 
	                request.getSession().setAttribute(WebAttributes.AUTHENTICATION_EXCEPTION, exception); 
	            } 
	        } 
	     */  
	            throw new AuthenticationServiceException("用户名或者密码错误！");   
	        }  
	          
	        //UsernamePasswordAuthenticationToken实现 Authentication  
	        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, calcMD5);  
	        // Place the last username attempted into HttpSession for views  
	          
	        // 允许子类设置详细属性  
	        setDetails(request, authRequest);  
	          
	        // 运行UserDetailsService的loadUserByUsername 再次封装Authentication 
	        AuthenticationManager authenticationManager2 = this.getAuthenticationManager();
	        Authentication authenticate = authenticationManager2.authenticate(authRequest);
	        return authenticate;
	}
	
	protected void checkValidateCode(HttpServletRequest request) {   
        HttpSession session = request.getSession();  
          
        String sessionValidateCode = obtainSessionValidateCode(session);   
        //让上一次的验证码失效  
        session.setAttribute(VALIDATE_CODE, null);  
        String validateCodeParameter = obtainValidateCodeParameter(request);    
        if (StringUtils.isEmpty(validateCodeParameter) || !sessionValidateCode.equalsIgnoreCase(validateCodeParameter)) {    
            throw new AuthenticationServiceException("验证码错误！");    
        }    
    }  
	private String obtainValidateCodeParameter(HttpServletRequest request) {  
        Object obj = request.getParameter(VALIDATE_CODE);  
        return null == obj ? "" : obj.toString();  
    } 
	 protected String obtainSessionValidateCode(HttpSession session) {  
	        Object obj = session.getAttribute(VALIDATE_CODE);  
	        return null == obj ? "" : obj.toString();  
	 }  
	  
    @Override  
    protected String obtainUsername(HttpServletRequest request) {  
        Object obj = request.getParameter(USERNAME);  
        return null == obj ? "" : obj.toString();  
    }  
	  
    @Override  
    protected String obtainPassword(HttpServletRequest request) {  
        Object obj = request.getParameter(PASSWORD);  
        return null == obj ? "" : obj.toString();  
    }  
}
