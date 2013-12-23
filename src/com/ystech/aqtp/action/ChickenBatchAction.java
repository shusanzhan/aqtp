/**
 * 
 */
package com.ystech.aqtp.action;

import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ystech.aqtp.model.BreaderBreed;
import com.ystech.aqtp.model.Breed;
import com.ystech.aqtp.model.Breeder;
import com.ystech.aqtp.model.ChickenBatch;
import com.ystech.aqtp.model.Dimensiona;
import com.ystech.aqtp.model.FeedFeeder;
import com.ystech.aqtp.model.Feeder;
import com.ystech.aqtp.model.Grade;
import com.ystech.aqtp.service.BreaderBreedManageImpl;
import com.ystech.aqtp.service.BreedManageImpl;
import com.ystech.aqtp.service.BreederManageImpl;
import com.ystech.aqtp.service.ChickenBatchManageImpl;
import com.ystech.aqtp.service.DimensionaManageImpl;
import com.ystech.aqtp.service.FeedFeederManageImpl;
import com.ystech.aqtp.service.FeederManageImpl;
import com.ystech.aqtp.service.GradeManageImpl;
import com.ystech.aqtp.service.HealthCareManageImpl;
import com.ystech.aqtp.service.ImmuneManageImpl;
import com.ystech.aqtp.service.QuarantineCertificateManageImpl;
import com.ystech.core.dao.support.Page;
import com.ystech.core.util.ParamUtil;
import com.ystech.core.web.BaseController;

/**
 * @author shusanzhan
 * @date 2013-7-20
 */
@Component("chickenBatchAction")
@Scope("prototype")
public class ChickenBatchAction extends BaseController{
	private ChickenBatch chickenBatch;
	private ChickenBatchManageImpl chickenBatchManageImpl;
	private GradeManageImpl gradeManageImpl;
	private BreedManageImpl breedManageImpl;
	private FeederManageImpl feederManageImpl;
	private BreederManageImpl breederManageImpl;
	private QuarantineCertificateManageImpl quarantineCertificateManageImpl;
	private DimensionaManageImpl dimensionaManageImpl;
	private  ImmuneManageImpl immuneManageImpl;
	private HealthCareManageImpl healthCareManageImpl;
	private FeedFeederManageImpl feedFeederManageImpl;
	private BreaderBreedManageImpl breaderBreedManageImpl;
	public ChickenBatch getChickenBatch() {
		return chickenBatch;
	}
	public void setChickenBatch(ChickenBatch chickenBatch) {
		this.chickenBatch = chickenBatch;
	}
	@Resource
	public void setChickenBatchManageImpl(
			ChickenBatchManageImpl chickenBatchManageImpl) {
		this.chickenBatchManageImpl = chickenBatchManageImpl;
	}
	@Resource
	public void setGradeManageImpl(GradeManageImpl gradeManageImpl) {
		this.gradeManageImpl = gradeManageImpl;
	}
	@Resource
	public void setBreedManageImpl(BreedManageImpl breedManageImpl) {
		this.breedManageImpl = breedManageImpl;
	}
	@Resource
	public void setFeederManageImpl(FeederManageImpl feederManageImpl) {
		this.feederManageImpl = feederManageImpl;
	}
	@Resource
	public void setBreederManageImpl(BreederManageImpl breederManageImpl) {
		this.breederManageImpl = breederManageImpl;
	}
	@Resource
	public void setQuarantineCertificateManageImpl(
			QuarantineCertificateManageImpl quarantineCertificateManageImpl) {
		this.quarantineCertificateManageImpl = quarantineCertificateManageImpl;
	}
	@Resource
	public void setDimensionaManageImpl(DimensionaManageImpl dimensionaManageImpl) {
		this.dimensionaManageImpl = dimensionaManageImpl;
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
	public void setFeedFeederManageImpl(FeedFeederManageImpl feedFeederManageImpl) {
		this.feedFeederManageImpl = feedFeederManageImpl;
	}
	@Resource
	public void setBreaderBreedManageImpl(
			BreaderBreedManageImpl breaderBreedManageImpl) {
		this.breaderBreedManageImpl = breaderBreedManageImpl;
	}
	/**
	 * 功能描述：
	 * 参数描述：
	 * 逻辑描述：
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		HttpServletRequest request = getRequest();
		List<Grade> grades = gradeManageImpl.getAll();
		List<Breed> breeds = breedManageImpl.getAll();
		request.setAttribute("grades",grades);
		request.setAttribute("breeds",breeds);
		return "edit";
	}
	/**
	 * 功能描述：列表查询
	 * 参数描述：
	 * 逻辑描述：
	 * @return
	 * @throws Exception
	 */
	public String queryList() throws Exception {
		HttpServletRequest request = this.getRequest();
		Integer pageSize = ParamUtil.getIntParam(request, "pageSize", 10);
		Integer pageNo = ParamUtil.getIntParam(request, "currentPage", 1);
		String name = request.getParameter("name");
		Page page=null;
		if(null!=name&&name.trim().length()>0){
			 page= chickenBatchManageImpl.pageQuery(pageNo, pageSize, "from ChickenBatch where name like '%"+name+"%'", new Object[]{});
		}else{
			page= chickenBatchManageImpl.pageQuery(pageNo, pageSize, "from ChickenBatch ", new Object[]{});
		}
		request.setAttribute("page", page);
		return "list";
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
		Integer breedId = ParamUtil.getIntParam(request, "breedDbid", -1);
		Integer gradeId = ParamUtil.getIntParam(request, "gradeDbid", -1);
		if(breedId>0){
			Breed breed = breedManageImpl.get(breedId);
			chickenBatch.setBreed(breed);
		}
		if(gradeId>0){
			Grade grade = gradeManageImpl.get(gradeId);
			chickenBatch.setGrade(grade);
		}
		try {
			chickenBatchManageImpl.save(chickenBatch);
		} catch (Exception e) {
			log.error("保存品系时发生错误！保存数据失败！"+e);
			e.printStackTrace();
			renderErrorMsg(e, "");
			return ;
		}
		renderMsg("/chickenBatch/queryList", "保存数据成功！");
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
		List<Grade> grades = gradeManageImpl.getAll();
		List<Breed> breeds = breedManageImpl.getAll();
		request.setAttribute("grades",grades);
		request.setAttribute("breeds",breeds);
		if(dbid>-1){
			ChickenBatch chickenBatch2 = chickenBatchManageImpl.get(dbid);
			request.setAttribute("chickenBatch", chickenBatch2);
		}
		return "edit";
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
					/*Set healthcares = chickenBatch2.getHealthcares();
					if(healthcares.)
					healthCareManageImpl.executeSql("delete from healthCare where chickenBatchDbid="+dbid);
					dimensionaManageImpl.executeSql("delete from dimensiona where chickenBatchDbid="+dbid);
					feedFeederManageImpl.executeSql("delete from feedFeeder where chickenBatchDbid="+dbid);
					quarantineCertificateManageImpl.executeSql("delete from quarantinecertificate where chickenBatchDbid="+dbid);
					immuneManageImpl.executeSql("delete from immune where chickenBatchDbid="+dbid);*/
					chickenBatchManageImpl.deleteById(dbid);
				}
			} catch (Exception e) {
				e.printStackTrace();
				renderErrorMsg(e, "");
				return ;
			}
		}
		String query = ParamUtil.getQueryUrl(request);
		renderMsg("/chickenBatch/queryList"+query, "删除数据成功！");
		return ;
	}
	/**
	 * 功能描述：
	 * 参数描述：
	 * 逻辑描述：
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({ "unused", "unchecked" })
	public String index() throws Exception {
		HttpServletRequest request = getRequest();
		Integer dbid = ParamUtil.getIntParam(request, "dbid",-1);
		if(dbid>-1){
			ChickenBatch chickenBatch2 = chickenBatchManageImpl.get(dbid);
			Set<FeedFeeder> feedfeeders = chickenBatch2.getFeedfeeders();
			Set<BreaderBreed> breaderbreeds = chickenBatch2.getBreaderbreeds();
			Set quarantinecertificates = chickenBatch2.getQuarantinecertificates();
			Set<Dimensiona> dimensionas = chickenBatch2.getDimensionas();
			Set immunes = chickenBatch2.getImmunes();
			Set healthcares = chickenBatch2.getHealthcares();
			request.setAttribute("chickenBatch", chickenBatch2);
			request.setAttribute("feedfeeders", feedfeeders);
			request.setAttribute("quarantinecertificates", quarantinecertificates);
			request.setAttribute("dimensionas", dimensionas);
			request.setAttribute("breaderBreeds", breaderbreeds);
			request.setAttribute("immunes", immunes);
			request.setAttribute("healthCares", healthcares);
		}
		return "index";
	}
	
	/**
	 * 功能描述：添加饲料信息
	 * 参数描述：
	 * 逻辑描述：
	 * @return
	 * @throws Exception
	 */
	public String addFeeder() throws Exception {
		HttpServletRequest request = getRequest();
		List<Feeder> feeders = feederManageImpl.getAll();
		request.setAttribute("feeders", feeders);
		return "addFeeder";
	}
	/**
	 * 功能描述：保存饲料信息
	 * 参数描述：
	 * 逻辑描述：
	 * @return
	 * @throws Exception
	 */
	public void saveFeeder() throws Exception {
		HttpServletRequest request = getRequest();
		Integer chickenBatchDbid = ParamUtil.getIntParam(request, "chickenBatchDbid", -1);
		Integer[] breederIds = ParamUtil.getIntArraryByDbids(request, "breederIds");
		if(null==breederIds||breederIds.length<=0||chickenBatchDbid<0){
			renderErrorMsg(new Throwable("没有选择数据，请确认！"), "");
			return ;
		}
		ChickenBatch chickenBatch = chickenBatchManageImpl.get(chickenBatchDbid);
		try {
			for (int i = 0; i < breederIds.length; i++) {
				Feeder feeder = feederManageImpl.get(breederIds[i]);
				FeedFeeder feedFeeder=new FeedFeeder();
				feedFeeder.setChickenbatch(chickenBatch);
				feedFeeder.setFeeder(feeder);
				feedFeeder.setName(feeder.getName());
				feedFeederManageImpl.save(feedFeeder);
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderErrorMsg(e, "");
			return ;
		}
		renderMsg("/chickenBatch/index?dbid="+chickenBatch.getDbid(), "添加饲料信息成功！");
		return ;
	}
	/**
	 * 功能描述：删除饲料信息
	 * 参数描述：
	 * 逻辑描述：
	 * @return
	 * @throws Exception
	 */
	public void deleteFeeder() throws Exception {
		HttpServletRequest request = this.getRequest();
		Integer[] dbids = ParamUtil.getIntArraryByDbids(request,"dbids");
		if(null!=dbids&&dbids.length>0){
			try {
				for (Integer dbid : dbids) {
					feedFeederManageImpl.deleteById(dbid);
				}
			} catch (Exception e) {
				e.printStackTrace();
				renderErrorMsg(e, "");
				return ;
			}
		}
		Integer chickenBatchDbid = ParamUtil.getIntParam(request, "chickenBatchDbid", -1);
		renderMsg("/chickenBatch/index?dbid="+chickenBatchDbid, "删除饲料信息成功！");
		return ;
	}
	/**
	 * 功能描述：查看饲料信息
	 * 参数描述：
	 * 逻辑描述：
	 * @return
	 * @throws Exception
	 */
	public String showFeeder() throws Exception {
		HttpServletRequest request = getRequest();
		Integer dbid = ParamUtil.getIntParam(request, "dbid",-1);
		if(dbid>-1){
			Feeder feeder = feederManageImpl.get(dbid);
			request.setAttribute("feeder", feeder);
		}
		return "showFeeder";
	}
	
	//添加饲养员信息开始--------------------------------------------------------------//
	/**
	 * 功能描述：添加饲养员信息
	 * 参数描述：
	 * 逻辑描述：
	 * @return
	 * @throws Exception
	 */
	public String addBreeder() throws Exception {
		HttpServletRequest request = getRequest();
		List<Breeder> breeders = breederManageImpl.getAll();
		request.setAttribute("breeders", breeders);
		return "addBreeder";
	}
	/**
	 * 功能描述：保存饲养员信息
	 * 参数描述：
	 * 逻辑描述：
	 * @return
	 * @throws Exception
	 */
	public void saveBreeder() throws Exception {
		HttpServletRequest request = getRequest();
		Integer chickenBatchDbid = ParamUtil.getIntParam(request, "chickenBatchDbid", -1);
		Integer[] breederIds = ParamUtil.getIntArraryByDbids(request, "breederIds");
		if(null==breederIds||breederIds.length<=0||chickenBatchDbid<0){
			renderErrorMsg(new Throwable("没有选择数据，请确认！"), "");
			return ;
		}
		ChickenBatch chickenBatch = chickenBatchManageImpl.get(chickenBatchDbid);
		try {
			for (int i = 0; i < breederIds.length; i++) {
				Breeder breeder = breederManageImpl.get(breederIds[i]);
				BreaderBreed breaderBreed = new BreaderBreed();
				breaderBreed.setChickenbatch(chickenBatch);
				breaderBreed.setBreeder(breeder);
				breaderBreed.setName(breeder.getName());
				breaderBreedManageImpl.save(breaderBreed);
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderErrorMsg(e, "");
			return ;
		}
		renderMsg("/chickenBatch/index?dbid="+chickenBatch.getDbid(), "添加饲养员信息成功！");
		return ;
	}
	/**
	 * 功能描述：删除饲养员信息
	 * 参数描述：
	 * 逻辑描述：
	 * @return
	 * @throws Exception
	 */
	public void deleteBreeder() throws Exception {
		HttpServletRequest request = this.getRequest();
		Integer[] dbids = ParamUtil.getIntArraryByDbids(request,"dbids");
		if(null!=dbids&&dbids.length>0){
			try {
				for (Integer dbid : dbids) {
					breaderBreedManageImpl.deleteById(dbid);
				}
			} catch (Exception e) {
				e.printStackTrace();
				renderErrorMsg(e, "");
				return ;
			}
		}
		Integer chickenBatchDbid = ParamUtil.getIntParam(request, "chickenBatchDbid", -1);
		renderMsg("/chickenBatch/index?dbid="+chickenBatchDbid, "删除饲养员息成功！");
		return ;
	}
	/**
	 * 功能描述：查看饲养员信息
	 * 参数描述：
	 * 逻辑描述：
	 * @return
	 * @throws Exception
	 */
	public String showBreeder() throws Exception {
		HttpServletRequest request = getRequest();
		Integer dbid = ParamUtil.getIntParam(request, "dbid",-1);
		if(dbid>-1){
			Breeder breeder = breederManageImpl.get(dbid);
			request.setAttribute("user", breeder.getUser());
			request.setAttribute("breeder", breeder);
		}
		return "showBreeder";
	}
	//添加饲养员信息介绍--------------------------------------------------------------//
	//添加检疫证明信息--------------------------------------------------------------//
	
}

