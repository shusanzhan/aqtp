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

import com.ystech.aqtp.model.Breed;
import com.ystech.aqtp.model.Drag;
import com.ystech.aqtp.model.DragType;
import com.ystech.aqtp.service.DragManageImpl;
import com.ystech.aqtp.service.DragTypeManageImpl;
import com.ystech.core.dao.support.Page;
import com.ystech.core.util.ParamUtil;
import com.ystech.core.web.BaseController;

/**
 * @author shusanzhan
 * @date 2013-7-7
 */
@Component("dragAction")
@Scope("prototype")
public class DragAction extends BaseController{
	private DragManageImpl dragManageImpl;
	private Drag drag;
	private DragTypeManageImpl dragTypeManageImpl;
	public DragManageImpl getDragManageImpl() {
		return dragManageImpl;
	}
	@Resource
	public void setDragManageImpl(DragManageImpl dragManageImpl) {
		this.dragManageImpl = dragManageImpl;
	}
	public Drag getDrag() {
		return drag;
	}
	public void setDrag(Drag drag) {
		this.drag = drag;
	}
	@Resource
	public void setDragTypeManageImpl(DragTypeManageImpl dragTypeManageImpl) {
		this.dragTypeManageImpl = dragTypeManageImpl;
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
		List<DragType> dragTypes = dragTypeManageImpl.getAll();
		request.setAttribute("dragTypes", dragTypes);
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
			 page= dragManageImpl.pageQuery(pageNo, pageSize, "from Drag where name like '%"+name+"%'", new Object[]{});
		}else{
			page= dragManageImpl.pageQuery(pageNo, pageSize, "from Drag ", new Object[]{});
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
		Integer dargDbid = ParamUtil.getIntParam(request, "dragTypeId", -1);
		if(dargDbid<0){
			renderErrorMsg(new Throwable("药品数据类型错误"), "");
			return ;
		}
		try {
			DragType dragType = dragTypeManageImpl.get(dargDbid);
			drag.setDragtype(dragType);
			dragManageImpl.save(drag);
		} catch (Exception e) {
			log.error("保存药品信息时发生错误！保存数据失败！"+e);
			e.printStackTrace();
			renderErrorMsg(e, "");
			return ;
		}
		renderMsg("/drag/queryList", "保存数据成功！");
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
		List<DragType> dragTypes = dragTypeManageImpl.getAll();
		request.setAttribute("dragTypes", dragTypes);
		Integer dbid = ParamUtil.getIntParam(request, "dbid",-1);
		if(dbid>-1){
			Drag drag = dragManageImpl.get(dbid);
			request.setAttribute("drag", drag);
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
					dragManageImpl.deleteById(dbid);
				}
			} catch (Exception e) {
				e.printStackTrace();
				renderErrorMsg(e, "");
				return ;
			}
		}
		String query = ParamUtil.getQueryUrl(request);
		renderMsg("/drag/queryList"+query, "删除数据成功！");
		return ;
	}
}
