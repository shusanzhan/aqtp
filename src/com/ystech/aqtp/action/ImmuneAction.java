/**
 * 
 */
package com.ystech.aqtp.action;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ystech.aqtp.model.ChickenBatch;
import com.ystech.aqtp.model.Drag;
import com.ystech.aqtp.model.HealthCareDrag;
import com.ystech.aqtp.model.Immune;
import com.ystech.aqtp.model.ImmuneDrag;
import com.ystech.aqtp.service.ChickenBatchManageImpl;
import com.ystech.aqtp.service.DragManageImpl;
import com.ystech.aqtp.service.ImmuneDragManageImpl;
import com.ystech.aqtp.service.ImmuneManageImpl;
import com.ystech.core.util.ParamUtil;
import com.ystech.core.web.BaseController;

/**
 * @author shusanzhan
 * @date 2013-7-21
 */
@Component("immuneAction")
@Scope("prototype")
public class ImmuneAction extends BaseController{
	private Immune immune;
	private ImmuneManageImpl immuneManageImpl;
	private ChickenBatchManageImpl chickenBatchManageImpl;
	private ImmuneDragManageImpl immuneDragManageImpl;
	private DragManageImpl  dragManageImpl;
	public Immune getImmune() {
		return immune;
	}
	public void setImmune(Immune immune) {
		this.immune = immune;
	}
	@Resource
	public void setImmuneManageImpl(ImmuneManageImpl immuneManageImpl) {
		this.immuneManageImpl = immuneManageImpl;
	}
	@Resource
	public void setChickenBatchManageImpl(
			ChickenBatchManageImpl chickenBatchManageImpl) {
		this.chickenBatchManageImpl = chickenBatchManageImpl;
	}
	@Resource
	public void setImmuneDragManageImpl(ImmuneDragManageImpl immuneDragManageImpl) {
		this.immuneDragManageImpl = immuneDragManageImpl;
	}
	@Resource
	public void setDragManageImpl(DragManageImpl dragManageImpl) {
		this.dragManageImpl = dragManageImpl;
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
		String[] dragIds = request.getParameterValues("dragId");
		String[] doses = request.getParameterValues("dose");
		if (chickenBatchDbid<0) {
			renderErrorMsg(new Throwable("没有选择数据，请确认！"), "");
			return ;
		}
		
		ChickenBatch chickenBatch = chickenBatchManageImpl.get(chickenBatchDbid);
		try {
			immune.setChickenbatch(chickenBatch);
			immuneManageImpl.save(immune);
			//先清空原来数据
			int deleteByHealthCareDbid = immuneDragManageImpl.deleteByHealthCareDbid(immune.getDbid());
			if(null!=dragIds&&dragIds.length>0){
				for (int i = 0; i < doses.length; i++) {
					ImmuneDrag immuneDrag=new ImmuneDrag();
					Drag drag = dragManageImpl.get(Integer.parseInt(dragIds[i]));
					immuneDrag.setDose(doses[i]);
					immuneDrag.setDrag(drag);
					immuneDrag.setImmune(immune);
					immuneDrag.setName(drag.getName());
					immuneDragManageImpl.save(immuneDrag);
				}
			}
		} catch (Exception e) {
			log.error("保存免疫信息发生错误！保存数据失败！"+e);
			e.printStackTrace();
			renderErrorMsg(e, "");
			return ;
		}
		renderMsg("/chickenBatch/index?dbid="+chickenBatch.getDbid(), "添加免疫信息成功！");
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
		try{
			if(dbid>-1){
				Immune immune2 = immuneManageImpl.get(dbid);
				request.setAttribute("immune", immune2);
				List<ImmuneDrag> immuneDrags = immuneDragManageImpl.findBy("immune.dbid", immune2.getDbid());
				request.setAttribute("immuneDrags", immuneDrags);
				request.setAttribute("immuneDragSize", immuneDrags.size());
			}
		}catch (Exception e) {
			e.printStackTrace();
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
	public String showImmune() throws Exception {
		HttpServletRequest request = getRequest();
		Integer dbid = ParamUtil.getIntParam(request, "dbid",-1);
		if(dbid>-1){
			Immune immune2 = immuneManageImpl.get(dbid);
			request.setAttribute("immune", immune2);
			List<ImmuneDrag> immuneDrags = immuneDragManageImpl.findBy("immune.dbid", immune2.getDbid());
			request.setAttribute("immuneDrags", immuneDrags);
		}
		return "showImmune";
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
					immuneManageImpl.deleteById(dbid);
				}
			} catch (Exception e) {
				e.printStackTrace();
				renderErrorMsg(e, "");
				return ;
			}
		}
		Integer chickenBatchDbid = ParamUtil.getIntParam(request, "chickenBatchDbid", -1);
		renderMsg("/chickenBatch/index?dbid="+chickenBatchDbid, "删除免疫信息成功！");
		return ;
	}
	
}
