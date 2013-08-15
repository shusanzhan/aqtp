/**
 * 
 */
package com.ystech.aqtp.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ystech.aqtp.model.ChickenBatch;
import com.ystech.aqtp.model.Drag;
import com.ystech.aqtp.model.HealthCare;
import com.ystech.aqtp.model.HealthCareDrag;
import com.ystech.aqtp.model.Immune;
import com.ystech.aqtp.model.ImmuneDrag;
import com.ystech.aqtp.service.ChickenBatchManageImpl;
import com.ystech.aqtp.service.DragManageImpl;
import com.ystech.aqtp.service.HealthCareDragManageImpl;
import com.ystech.aqtp.service.HealthCareManageImpl;
import com.ystech.aqtp.service.ImmuneDragManageImpl;
import com.ystech.aqtp.service.ImmuneManageImpl;
import com.ystech.core.util.ParamUtil;
import com.ystech.core.web.BaseController;

/**
 * @author shusanzhan
 * @date 2013-7-21
 */
@Component("healthCareAction")
@Scope("prototype")
public class HealthCareAction extends BaseController{
	private HealthCare healthCare;
	private HealthCareManageImpl healthCareManageImpl;
	private ChickenBatchManageImpl chickenBatchManageImpl;
	private HealthCareDragManageImpl healthCareDragManageImpl;
	private DragManageImpl  dragManageImpl;
	
	public HealthCare getHealthCare() {
		return healthCare;
	}
	public void setHealthCare(HealthCare healthCare) {
		this.healthCare = healthCare;
	}
	@Resource
	public void setHealthCareManageImpl(HealthCareManageImpl healthCareManageImpl) {
		this.healthCareManageImpl = healthCareManageImpl;
	}
	@Resource
	public void setChickenBatchManageImpl(
			ChickenBatchManageImpl chickenBatchManageImpl) {
		this.chickenBatchManageImpl = chickenBatchManageImpl;
	}
	@Resource
	public void setDragManageImpl(DragManageImpl dragManageImpl) {
		this.dragManageImpl = dragManageImpl;
	}
	@Resource
	public void setHealthCareDragManageImpl(
			HealthCareDragManageImpl healthCareDragManageImpl) {
		this.healthCareDragManageImpl = healthCareDragManageImpl;
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
			healthCare.setChickenbatch(chickenBatch);
			healthCareManageImpl.save(healthCare);
			
			//先清空原来数据
			int deleteByHealthCareDbid = healthCareDragManageImpl.deleteByHealthCareDbid(healthCare.getDbid());
			
			if(null!=dragIds&&dragIds.length>0){
				for (int i = 0; i < doses.length; i++) {
					HealthCareDrag healthCareDrag=new HealthCareDrag();
					Drag drag = dragManageImpl.get(Integer.parseInt(dragIds[i]));
					healthCareDrag.setDose(doses[i]);
					healthCareDrag.setDrag(drag);
					healthCareDrag.setHealthcare(healthCare);
					healthCareDrag.setName(drag.getName());
					healthCareDragManageImpl.save(healthCareDrag);
				}
			}
		} catch (Exception e) {
			log.error("保存保健信息发生错误！保存数据失败！"+e);
			e.printStackTrace();
			renderErrorMsg(e, "");
			return ;
		}
		renderMsg("/chickenBatch/index?dbid="+chickenBatch.getDbid(), "添加保健信息成功！");
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
			HealthCare healthCare2 = healthCareManageImpl.get(dbid);
			request.setAttribute("healthCare", healthCare2);
			List<HealthCareDrag> healthCareDrags = healthCareDragManageImpl.findBy("healthcare.dbid", healthCare2.getDbid());
			request.setAttribute("healthCareDrags", healthCareDrags);
			request.setAttribute("healthCareDragSize", healthCareDrags.size());
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
	public String showHealthCare() throws Exception {
		HttpServletRequest request = getRequest();
		Integer dbid = ParamUtil.getIntParam(request, "dbid",-1);
		if(dbid>-1){
			HealthCare healthCare2 = healthCareManageImpl.get(dbid);
			request.setAttribute("healthCare", healthCare2);
			List<HealthCareDrag> healthCareDrags = healthCareDragManageImpl.findBy("healthcare.dbid", healthCare2.getDbid());
			request.setAttribute("healthCareDrags", healthCareDrags);
		}
		return "showHealthCare";
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
					healthCareManageImpl.deleteById(dbid);
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
	/**
	 * 功能描述：
	 * 参数描述：
	 * 逻辑描述：
	 * @return
	 * @throws Exception
	 */
	public void autoDrag() throws Exception {
		HttpServletRequest request = this.getRequest();
		String dragName = request.getParameter("q");
		List<Drag> drags=new ArrayList<Drag>();
		drags= dragManageImpl.executeSql("select * from drag where name like ? or pingyin like ?", new Object[]{"%"+dragName+"%","%"+dragName+"%",});
		if(null==drags||drags.size()<0){
			drags = dragManageImpl.getAll();
		}
		JSONArray  array=new JSONArray();
		if(null!=drags&&drags.size()>0){
			for (Drag drag : drags) {
				JSONObject object=new JSONObject();
				object.put("dbid", drag.getDbid());
				object.put("name", drag.getName());
				array.put(object);
			}
			renderJson(array.toString());
		}else{
			renderJson("1");
		}
		
		return ;
	}
}
