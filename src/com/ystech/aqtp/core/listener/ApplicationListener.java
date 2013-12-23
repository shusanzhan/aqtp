/**
 * 
 */
package com.ystech.aqtp.core.listener;

import javax.annotation.Resource;
import javax.servlet.Filter;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.ystech.aqtp.model.Access;
import com.ystech.aqtp.service.AccessManageImpl;


/**
 * @author shusanzhan
 * @date 2013-11-28
 */
public class ApplicationListener implements HttpSessionListener{

	@Override
	public void sessionCreated(HttpSessionEvent arg0) {
		ApplicationContext applicationContext = (ApplicationContext) ServletActionContext
				.getServletContext()
				.getAttribute(
						WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
		//获取spring的环境信息
		AccessManageImpl accessManageImpl  =(AccessManageImpl)applicationContext.getBean("accessManageImpl");
		Access access = accessManageImpl.get(1);
		int count;
		count = access.getAccess();
		++count;
		access.setAccess(count);
		accessManageImpl.save(access);
		arg0.getSession().setAttribute("count", count);
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent arg0) {
		
	}

}
