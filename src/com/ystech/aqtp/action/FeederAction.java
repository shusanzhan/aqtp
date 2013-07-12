/**
 * 
 */
package com.ystech.aqtp.action;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ystech.aqtp.model.Breed;
import com.ystech.aqtp.model.Feeder;
import com.ystech.aqtp.service.FeederManageImpl;
import com.ystech.core.dao.support.Page;
import com.ystech.core.util.ParamUtil;
import com.ystech.core.web.BaseController;

/**
 * @author shusanzhan
 * @date 2013-7-7
 */
@Component("feederAction")
@Scope("prototype")
public class FeederAction extends BaseController {
	private Feeder feeder;
	private FeederManageImpl feederManageImpl;
	public Feeder getFeeder() {
		return feeder;
	}
	public void setFeeder(Feeder feeder) {
		this.feeder = feeder;
	}
	public FeederManageImpl getFeederManageImpl() {
		return feederManageImpl;
	}
	@Resource
	public void setFeederManageImpl(FeederManageImpl feederManageImpl) {
		this.feederManageImpl = feederManageImpl;
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
			 page= feederManageImpl.pageQuery(pageNo, pageSize, "from Feeder where name like '%"+name+"%'", new Object[]{});
		}else{
			page= feederManageImpl.pageQuery(pageNo, pageSize, "from Feeder ", new Object[]{});
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
		try {
			feederManageImpl.save(feeder);
		} catch (Exception e) {
			log.error("保存品饲料发生错误！保存数据失败！"+e);
			e.printStackTrace();
			renderErrorMsg(e, "");
			return ;
		}
		renderMsg("/feeder/queryList", "保存数据成功！");
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
			Feeder feeder = feederManageImpl.get(dbid);
			request.setAttribute("feeder", feeder);
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
					feederManageImpl.deleteById(dbid);
				}
			} catch (Exception e) {
				e.printStackTrace();
				renderErrorMsg(e, "");
				return ;
			}
		}
		String query = ParamUtil.getQueryUrl(request);
		renderMsg("/feeder/queryList"+query, "删除数据成功！");
		return ;
	}
	
}
