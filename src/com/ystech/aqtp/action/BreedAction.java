/**
 * 
 */
package com.ystech.aqtp.action;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ystech.aqtp.model.Breed;
import com.ystech.aqtp.model.Grade;
import com.ystech.aqtp.service.BreedManageImpl;
import com.ystech.core.dao.support.Page;
import com.ystech.core.util.ParamUtil;
import com.ystech.core.web.BaseController;

/**
 * @author shusanzhan
 * @date 2013-7-7
 */
@Component("breedAction")
@Scope("prototype")
public class BreedAction extends BaseController {
	private BreedManageImpl breedManageImpl;
	private Breed breed;
	public BreedManageImpl getBreedManageImpl() {
		return breedManageImpl;
	}
	@Resource
	public void setBreedManageImpl(BreedManageImpl breedManageImpl) {
		this.breedManageImpl = breedManageImpl;
	}
	public Breed getBreed() {
		return breed;
	}
	public void setBreed(Breed breed) {
		this.breed = breed;
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
			 page= breedManageImpl.pageQuery(pageNo, pageSize, "from Breed where name like '%"+name+"%'", new Object[]{});
		}else{
			page= breedManageImpl.pageQuery(pageNo, pageSize, "from Breed ", new Object[]{});
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
			breedManageImpl.save(breed);
		} catch (Exception e) {
			log.error("保存品系时发生错误！保存数据失败！"+e);
			e.printStackTrace();
			renderErrorMsg(e, "");
			return ;
		}
		renderMsg("/breed/queryList", "保存数据成功！");
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
			Breed breed2 = breedManageImpl.get(dbid);
			request.setAttribute("breed", breed2);
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
					breedManageImpl.deleteById(dbid);
				}
			} catch (Exception e) {
				e.printStackTrace();
				renderErrorMsg(e, "");
				return ;
			}
		}
		String query = ParamUtil.getQueryUrl(request);
		renderMsg("/breed/queryList"+query, "删除数据成功！");
		return ;
	}
}
