/**
 * 
 */
package com.ystech.aqtp.action;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ystech.aqtp.model.Grade;
import com.ystech.aqtp.service.GradeManageImpl;
import com.ystech.core.dao.support.Page;
import com.ystech.core.util.ParamUtil;
import com.ystech.core.web.BaseController;

/**
 * @author shusanzhan
 * @date 2013-7-7
 */
@Component("gradeAction")
@Scope("prototype")
public class GradeAction extends  BaseController {
	private GradeManageImpl gradeManageImpl;
	private Grade grade;
	@Resource
	public void setGradeManageImpl(GradeManageImpl gradeManageImpl) {
		this.gradeManageImpl = gradeManageImpl;
	}
	
	public Grade getGrade() {
		return grade;
	}

	public void setGrade(Grade grade) {
		this.grade = grade;
	}

	/**
	 * 功能描述：
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
			 page= gradeManageImpl.pageQuery(pageNo, pageSize, "from Grade where name like '%"+name+"%'", new Object[]{});
		}else{
			page= gradeManageImpl.pageQuery(pageNo, pageSize, "from Grade ", new Object[]{});
		}
		request.setAttribute("page", page);
		return "list";
	}
	/**
	 * 功能描述：保存评级
	 * 参数描述：grade品级基本信息
	 * 逻辑描述：
	 * @return
	 * @throws Exception
	 */
	public void save() throws Exception {
		try {
			gradeManageImpl.save(grade);
		} catch (Exception e) {
			log.error("保存品级时发生错误！保存数据失败！"+e);
			e.printStackTrace();
			renderErrorMsg(e, "");
			return ;
		}
		renderMsg("/grade/queryList", "保存数据成功！");
		return ;
	}
	/**
	 * 功能描述：删除评级
	 * 参数描述：dbids前台传递评级ID，可以为多个值
	 * 逻辑描述：查询条件也一同提交至后台
	 * @return
	 * @throws Exception
	 */
	public void delete() throws Exception {
		HttpServletRequest request = this.getRequest();
		Integer[] dbids = ParamUtil.getIntArraryByDbids(request,"dbids");
		if(null!=dbids&&dbids.length>0){
			try {
				for (Integer dbid : dbids) {
					gradeManageImpl.deleteById(dbid);
				}
			} catch (Exception e) {
				e.printStackTrace();
				renderErrorMsg(e, "");
				return ;
			}
		}
		String query = ParamUtil.getQueryUrl(request);
		renderMsg("/grade/queryList"+query, "删除数据成功！");
		return ;
	}
	/**
	 * 功能描述：编辑评级方法
	 * 参数描述：
	 * 逻辑描述：
	 * @return
	 * @throws Exception
	 */
	public String edit() throws Exception {
		HttpServletRequest request = getRequest();
		Integer dbid = ParamUtil.getIntParam(request, "dbid",-1);
		if(dbid>-1){
			Grade grade2 = gradeManageImpl.get(dbid);
			request.setAttribute("grade", grade2);
		}
		return "edit";
	}
}
