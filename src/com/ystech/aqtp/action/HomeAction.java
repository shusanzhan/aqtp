/**
 * 
 */
package com.ystech.aqtp.action;

import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ystech.aqtp.model.BreaderBreed;
import com.ystech.aqtp.model.ChickenBatch;
import com.ystech.aqtp.model.Dimensiona;
import com.ystech.aqtp.model.FeedFeeder;
import com.ystech.aqtp.service.ChickenBatchManageImpl;
import com.ystech.core.web.BaseController;

/**
 * @author shusanzhan
 * @date 2013-11-7
 */
@Component("homeAction")
@Scope("prototype")
public class HomeAction extends BaseController{
	private ChickenBatchManageImpl chickenBatchManageImpl;

	@Resource
	public void setChickenBatchManageImpl(
			ChickenBatchManageImpl chickenBatchManageImpl) {
		this.chickenBatchManageImpl = chickenBatchManageImpl;
	}
	/**
	 * 功能描述：
	 * 参数描述：
	 * 逻辑描述：
	 * @return
	 * @throws Exception
	 */
	public String home() throws Exception {
		return "home";
	}
	/**
	 * 功能描述：
	 * 参数描述：
	 * 逻辑描述：
	 * @return
	 * @throws Exception
	 */
	public String search() throws Exception {
		HttpServletRequest request = this.getRequest();
		String batchNo = request.getParameter("batchNo");
		ChickenBatch chickenBatch = chickenBatchManageImpl.findUniqueBy("batchNo", batchNo);
		request.setAttribute("chickenBatch", chickenBatch);
		Set<FeedFeeder> feedfeeders = chickenBatch.getFeedfeeders();
		Set<BreaderBreed> breaderbreeds = chickenBatch.getBreaderbreeds();
		Set quarantinecertificates = chickenBatch.getQuarantinecertificates();
		Set<Dimensiona> dimensionas = chickenBatch.getDimensionas();
		Set immunes = chickenBatch.getImmunes();
		Set healthcares = chickenBatch.getHealthcares();
		request.setAttribute("chickenBatch", chickenBatch);
		request.setAttribute("feedfeeders", feedfeeders);
		request.setAttribute("quarantinecertificates", quarantinecertificates);
		request.setAttribute("dimensionas", dimensionas);
		request.setAttribute("breaderBreeds", breaderbreeds);
		request.setAttribute("immunes", immunes);
		request.setAttribute("healthCares", healthcares);
		return "home";
	}
	
}
