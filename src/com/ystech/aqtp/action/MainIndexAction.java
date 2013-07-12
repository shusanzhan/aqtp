/**
 * 
 */
package com.ystech.aqtp.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ystech.core.web.BaseController;

/**
 * @author shusanzhan
 * @date 2013-6-24
 */
@Component("mainIndexAction")
@Scope("prototype")
public class MainIndexAction extends BaseController{
	/**
	 * 功能描述：
	 * 参数描述：
	 * 逻辑描述：
	 * @return
	 * @throws Exception
	 */
	public String mainIndex() throws Exception {
		
		return "mainIndex";
	}
}
