/**
 * 
 */
package com.ystech.aqtp.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ystech.aqtp.model.Access;
import com.ystech.aqtp.model.BreaderBreed;
import com.ystech.aqtp.model.ChickenBatch;
import com.ystech.aqtp.model.Dimensiona;
import com.ystech.aqtp.model.FeedFeeder;
import com.ystech.aqtp.model.HealthCare;
import com.ystech.aqtp.model.Immune;
import com.ystech.aqtp.model.ImmuneDrag;
import com.ystech.aqtp.model.News;
import com.ystech.aqtp.model.NewsType;
import com.ystech.aqtp.model.QuarantineCertificate;
import com.ystech.aqtp.service.ChickenBatchManageImpl;
import com.ystech.aqtp.service.FeedFeederManageImpl;
import com.ystech.aqtp.service.HealthCareManageImpl;
import com.ystech.aqtp.service.ImmuneManageImpl;
import com.ystech.aqtp.service.NewsManageImpl;
import com.ystech.aqtp.service.NewsTypeManageImpl;
import com.ystech.aqtp.service.QuarantineCertificateManageImpl;
import com.ystech.core.dao.support.Page;
import com.ystech.core.util.ParamUtil;
import com.ystech.core.util.SignUtil;
import com.ystech.core.web.BaseController;

/**
 * @author shusanzhan
 * @date 2013-11-7
 */
@Component("wapHomeAction")
@Scope("prototype")
public class WapHomeAction extends BaseController {
	private ChickenBatchManageImpl chickenBatchManageImpl;
	private FeedFeederManageImpl feedFeederManageImpl;
	private ImmuneManageImpl immuneManageImpl;
	private HealthCareManageImpl healthCareManageImpl;
	private QuarantineCertificateManageImpl quarantineCertificateManageImpl;
	@Resource
	public void setChickenBatchManageImpl(
			ChickenBatchManageImpl chickenBatchManageImpl) {
		this.chickenBatchManageImpl = chickenBatchManageImpl;
	}


	@Resource
	public void setFeedFeederManageImpl(FeedFeederManageImpl feedFeederManageImpl) {
		this.feedFeederManageImpl = feedFeederManageImpl;
	}
	@Resource
	public void setImmuneManageImpl(ImmuneManageImpl immuneManageImpl) {
		this.immuneManageImpl = immuneManageImpl;
	}
	@Resource
	public void setHealthCareManageImpl(HealthCareManageImpl healthCareManageImpl) {
		this.healthCareManageImpl = healthCareManageImpl;
	}
	
	@Resource
	public void setQuarantineCertificateManageImpl(
			QuarantineCertificateManageImpl quarantineCertificateManageImpl) {
		this.quarantineCertificateManageImpl = quarantineCertificateManageImpl;
	}


	/**
	 * 功能描述： 参数描述： 逻辑描述：
	 * 
	 * @return
	 * @throws Exception
	 */
	public String home() throws Exception {
		HttpServletRequest request = this.getRequest();
		String batchNo = getBatchNo(request);
		try {
			ChickenBatch chickenBatch = chickenBatchManageImpl.findUniqueBy("batchNo", batchNo);
			request.setAttribute("chickenBatch", chickenBatch);
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		return "home";
	}


	/**
	 * @param request
	 * @return
	 */
	private String getBatchNo(HttpServletRequest request) {
		String batchNo = request.getParameter("batchNo");
		if (null!=batchNo&&batchNo.trim().length()>0) {
			batchNo=batchNo.substring(0, batchNo.length()-5);
		}else{
			batchNo=null;
		}
		return batchNo;
	}
	/**
	 * 功能描述：饲养饲料信息
	 * 参数描述：
	 * 逻辑描述：
	 * @return
	 * @throws Exception
	 */
	public String fodder() throws Exception {
		HttpServletRequest request = this.getRequest();
		String batchNo = getBatchNo(request);
		try {
			ChickenBatch chickenBatch = chickenBatchManageImpl.findUniqueBy("batchNo", batchNo);
			List<FeedFeeder> feeders = feedFeederManageImpl.findBy("chickenbatch.dbid", chickenBatch.getDbid());
			request.setAttribute("feeders", feeders);
			request.setAttribute("chickenBatch", chickenBatch);
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		return "fodder";
	}
	/**
	 * 功能描述：
	 * 参数描述：
	 * 逻辑描述：
	 * @return
	 * @throws Exception
	 */
	public String mianyi() throws Exception {
		HttpServletRequest request = this.getRequest();
		String batchNo = getBatchNo(request);
		try {
			ChickenBatch chickenBatch = chickenBatchManageImpl.findUniqueBy("batchNo", batchNo);
			List<Immune> immunes = immuneManageImpl.findBy("chickenbatch.dbid", chickenBatch.getDbid());
			request.setAttribute("immunes", immunes);
			request.setAttribute("chickenBatch", chickenBatch);
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		return "mianyi";
	}
	/**
	 * 功能描述：
	 * 参数描述：
	 * 逻辑描述：
	 * @return
	 * @throws Exception
	 */
	public String baojian() throws Exception {
		HttpServletRequest request = this.getRequest();
		String batchNo = getBatchNo(request);
		try {
			ChickenBatch chickenBatch = chickenBatchManageImpl.findUniqueBy("batchNo", batchNo);
			List<HealthCare> healthCares = healthCareManageImpl.findBy("chickenbatch.dbid", chickenBatch.getDbid());
			request.setAttribute("healthCares", healthCares);
			request.setAttribute("chickenBatch", chickenBatch);
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		return "baojian";
	}
	/**
	 * 功能描述：
	 * 参数描述：
	 * 逻辑描述：
	 * @return
	 * @throws Exception
	 */
	public String jianyi() throws Exception {
		HttpServletRequest request = this.getRequest();
		String batchNo = getBatchNo(request);
		try {
			ChickenBatch chickenBatch = chickenBatchManageImpl.findUniqueBy("batchNo", batchNo);
			List<QuarantineCertificate> certificates = quarantineCertificateManageImpl.findBy("chickenbatch.dbid", chickenBatch.getDbid());
			request.setAttribute("certificates", certificates);
			request.setAttribute("chickenBatch", chickenBatch);
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		return "jianyi";
	}
}
