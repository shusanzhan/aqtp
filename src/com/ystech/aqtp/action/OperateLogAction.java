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
import com.ystech.aqtp.service.OperateLogManageImpl;
import com.ystech.core.dao.support.Page;
import com.ystech.core.util.ParamUtil;
import com.ystech.core.web.BaseController;

/**
 * @author shusanzhan
 * @date 2013-7-7
 */
@Component("operateLogAction")
@Scope("prototype")
public class OperateLogAction extends BaseController{
	private OperateLogManageImpl operateLogManageImpl;
	@Resource
	public void setOperateLogManageImpl(OperateLogManageImpl operateLogManageImpl) {
		this.operateLogManageImpl = operateLogManageImpl;
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
		String name = request.getParameter("operator");
		Page page=null;
		if(null!=name&&name.trim().length()>0){
			 page= operateLogManageImpl.pageQuery(pageNo, pageSize, "from OperateLog where operator like '%"+name+"%'", new Object[]{});
		}else{
			page= operateLogManageImpl.pageQuery(pageNo, pageSize, "from OperateLog ", new Object[]{});
		}
		request.setAttribute("page", page);
		return "list";
	}
}
