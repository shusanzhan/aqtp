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

import com.ystech.aqtp.model.Enterprise;
import com.ystech.aqtp.service.EnterpriseManageImpl;
import com.ystech.core.web.BaseController;

/**
 * @author shusanzhan
 * @date 2013-6-2
 */
@Component("enterpriseAction")
@Scope("prototype")
public class EnterpriseAction extends BaseController{
	private Enterprise enterprise;
	
	public Enterprise getEnterprise() {
		return enterprise;
	}
	public void setEnterprise(Enterprise enterprise) {
		this.enterprise = enterprise;
	}
	private EnterpriseManageImpl enterpriseManageImpl;

	@Resource
	public void setEnterpriseManageImpl(EnterpriseManageImpl enterpriseManageImpl) {
		this.enterpriseManageImpl = enterpriseManageImpl;
	}
	public String enterprise() throws Exception {
		HttpServletRequest request = getRequest();
		List<Enterprise> enterprises = enterpriseManageImpl.getAll();
		if(null!=enterprises&&enterprises.size()>0){
			request.setAttribute("enterprise", enterprises.get(0));
		}
		return "enterprise";
	}
	public void saveEnterprise() {
		try {
			enterpriseManageImpl.save(enterprise);
		} catch (Exception e) {
			e.printStackTrace();
			renderErrorMsg(e, "");
			return ;
		}
		renderMsg("/enterprise/enterprise", "保存数据成功！");
	}
	
}
