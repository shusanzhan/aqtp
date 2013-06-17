/**
 * 
 */
package com.ystech.springsecurity.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ystech.core.web.BaseController;

/**
 * @author shusanzhan
 * @date 2013-5-1
 */
@Component("mainAction")
@Scope("prototype")
public class MainAction extends BaseController{
	
	public String index() throws Exception {
		return "index";
	}
	public String logout() throws Exception {
		return "logout";
	}
}
