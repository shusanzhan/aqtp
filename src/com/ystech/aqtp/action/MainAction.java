/**
 * 
 */
package com.ystech.aqtp.action;

import java.util.List;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder.In;
import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ystech.aqtp.model.Breeder;
import com.ystech.aqtp.model.ChickenBatch;
import com.ystech.aqtp.model.Dimensiona;
import com.ystech.aqtp.model.LoginLog;
import com.ystech.aqtp.model.User;
import com.ystech.aqtp.service.BreederManageImpl;
import com.ystech.aqtp.service.ChickenBatchManageImpl;
import com.ystech.aqtp.service.DimensionaManageImpl;
import com.ystech.aqtp.service.FeederManageImpl;
import com.ystech.aqtp.service.LoginLogManageImpl;
import com.ystech.core.security.SecurityUserHolder;
import com.ystech.core.web.BaseController;

/**
 * @author shusanzhan
 * @date 2013-5-1
 */
@Component("mainAction")
@Scope("prototype")
public class MainAction extends BaseController{
	private BreederManageImpl breederManageImpl;
	private ChickenBatchManageImpl chickenBatchManageImpl;
	private LoginLogManageImpl loginLogManageImpl;
	private DimensionaManageImpl dimensionaManageImpl;
	@Resource
	public void setChickenBatchManageImpl(
			ChickenBatchManageImpl chickenBatchManageImpl) {
		this.chickenBatchManageImpl = chickenBatchManageImpl;
	}
	@Resource
	public void setLoginLogManageImpl(LoginLogManageImpl loginLogManageImpl) {
		this.loginLogManageImpl = loginLogManageImpl;
	}
	@Resource
	public void setBreederManageImpl(BreederManageImpl breederManageImpl) {
		this.breederManageImpl = breederManageImpl;
	}
	@Resource
	public void setDimensionaManageImpl(DimensionaManageImpl dimensionaManageImpl) {
		this.dimensionaManageImpl = dimensionaManageImpl;
	}
	public String index() throws Exception {
		HttpServletRequest request = getRequest();
		User user = SecurityUserHolder.getCurrentUser();
		List<LoginLog> loginLogs = loginLogManageImpl.findBy("userId", user.getDbid());
		request.setAttribute("loginLogs", loginLogs);
		return "index";
	}
	/**
	 * 功能描述：
	 * 参数描述：
	 * 逻辑描述：
	 * @return
	 * @throws Exception
	 */
	public String contentIndex() throws Exception {
		HttpServletRequest request = getRequest();
		User user = SecurityUserHolder.getCurrentUser();
		Integer userId = user.getDbid();
		List<Breeder> breeders = breederManageImpl.queryByIndex();
		List<LoginLog> loginLogs = loginLogManageImpl.queryByIndex(userId);
		List<ChickenBatch> chickenBatchs =chickenBatchManageImpl.queryByIndex();
		List<Dimensiona> dimensionas =dimensionaManageImpl.queryByIndex();
		request.setAttribute("loginLogs", loginLogs);
		request.setAttribute("breeders", breeders);
		request.setAttribute("chickenBatchs", chickenBatchs);
		request.setAttribute("dimensionas", dimensionas);
		return "contentIndex";
	}
	public String logout() throws Exception {
		return "logout";
	}
}
