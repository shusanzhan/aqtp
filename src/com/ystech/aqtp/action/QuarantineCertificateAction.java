/**
 * 
 */
package com.ystech.aqtp.action;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ystech.aqtp.model.Breeder;
import com.ystech.aqtp.model.ChickenBatch;
import com.ystech.aqtp.model.Feeder;
import com.ystech.aqtp.model.QuarantineCertificate;
import com.ystech.aqtp.service.ChickenBatchManageImpl;
import com.ystech.aqtp.service.QuarantineCertificateManageImpl;
import com.ystech.core.util.ParamUtil;
import com.ystech.core.web.BaseController;

/**
 * @author shusanzhan
 * @date 2013-7-21
 */
@Component("quarantineCertificateAction")
@Scope("prototype")
public class QuarantineCertificateAction extends BaseController {
	private QuarantineCertificate quarantineCertificate;
	private QuarantineCertificateManageImpl quarantineCertificateManageImpl;
	private ChickenBatchManageImpl chickenBatchManageImpl;
	public QuarantineCertificate getQuarantineCertificate() {
		return quarantineCertificate;
	}
	public void setQuarantineCertificate(QuarantineCertificate quarantineCertificate) {
		this.quarantineCertificate = quarantineCertificate;
	}
	@Resource
	public void setQuarantineCertificateManageImpl(
			QuarantineCertificateManageImpl quarantineCertificateManageImpl) {
		this.quarantineCertificateManageImpl = quarantineCertificateManageImpl;
	}
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
	public String add() throws Exception {
		
		return "edit";
	}
	/**
	 * 功能描述：保存
	 * 参数描述：
	 * 逻辑描述：
	 * @return
	 * @throws Exception
	 */
	public void save() throws Exception {
		HttpServletRequest request = getRequest();
		Integer chickenBatchDbid = ParamUtil.getIntParam(request, "chickenBatchDbid", -1);
		if (chickenBatchDbid<0) {
			renderErrorMsg(new Throwable("没有选择数据，请确认！"), "");
			return ;
		}
		ChickenBatch chickenBatch = chickenBatchManageImpl.get(chickenBatchDbid);
		try {
			quarantineCertificate.setChickenbatch(chickenBatch);
			quarantineCertificateManageImpl.save(quarantineCertificate);
		} catch (Exception e) {
			log.error("保存检疫证明发生错误！保存数据失败！"+e);
			e.printStackTrace();
			renderErrorMsg(e, "");
			return ;
		}
		renderMsg("/chickenBatch/index?dbid="+chickenBatch.getDbid(), "添加检疫信息成功！");
		return ;
	}
	/**
	 * 功能描述：
	 * 参数描述：
	 * 逻辑描述：
	 * @return
	 * @throws Exception
	 */
	public String edit() throws Exception {
		HttpServletRequest request = getRequest();
		Integer dbid = ParamUtil.getIntParam(request, "dbid",-1);
		if(dbid>-1){
			QuarantineCertificate quarantineCertificate2 = quarantineCertificateManageImpl.get(dbid);
			request.setAttribute("quarantineCertificate", quarantineCertificate2);
		}
		return "edit";
	}
	/**
	 * 功能描述：查看饲养员信息
	 * 参数描述：
	 * 逻辑描述：
	 * @return
	 * @throws Exception
	 */
	public String showQuarantineCertificat() throws Exception {
		HttpServletRequest request = getRequest();
		Integer dbid = ParamUtil.getIntParam(request, "dbid",-1);
		if(dbid>-1){
			QuarantineCertificate quarantineCertificate2 = quarantineCertificateManageImpl.get(dbid);
			request.setAttribute("quarantineCertificate", quarantineCertificate2);
		}
		return "showQuarantineCertificat";
	}
	/**
	 * 功能描述：
	 * 参数描述：
	 * 逻辑描述：
	 * @return
	 * @throws Exception
	 */
	public void delete() throws Exception {
		HttpServletRequest request = this.getRequest();
		Integer[] dbids = ParamUtil.getIntArraryByDbids(request,"dbids");
		if(null!=dbids&&dbids.length>0){
			try {
				for (Integer dbid : dbids) {
					quarantineCertificateManageImpl.deleteById(dbid);
				}
			} catch (Exception e) {
				e.printStackTrace();
				renderErrorMsg(e, "");
				return ;
			}
		}
		Integer chickenBatchDbid = ParamUtil.getIntParam(request, "chickenBatchDbid", -1);
		renderMsg("/chickenBatch/index?dbid="+chickenBatchDbid, "删除检疫信息成功！");
		return ;
	}
	
}
