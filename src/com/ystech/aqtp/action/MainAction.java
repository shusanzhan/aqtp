/**
 * 
 */
package com.ystech.aqtp.action;

import java.io.Serializable;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.criteria.CriteriaBuilder.In;
import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ystech.aqtp.model.Breeder;
import com.ystech.aqtp.model.ChickenBatch;
import com.ystech.aqtp.model.Dimensiona;
import com.ystech.aqtp.model.LoginLog;
import com.ystech.aqtp.model.Role;
import com.ystech.aqtp.model.User;
import com.ystech.aqtp.model.Resource;
import com.ystech.aqtp.service.AccessManageImpl;
import com.ystech.aqtp.service.BreederManageImpl;
import com.ystech.aqtp.service.ChickenBatchManageImpl;
import com.ystech.aqtp.service.DimensionaManageImpl;
import com.ystech.aqtp.service.FeederManageImpl;
import com.ystech.aqtp.service.LoginLogManageImpl;
import com.ystech.aqtp.service.NewsManageImpl;
import com.ystech.aqtp.service.ResourceManageImpl;
import com.ystech.core.security.SecurityUserHolder;
import com.ystech.core.web.BaseController;

/**
 * @author shusanzhan
 * @date 2013-5-1
 */
@Component("mainAction")
@Scope("prototype")
public class MainAction extends BaseController{
	private BreederManageImpl breederManageImpl;
	private ChickenBatchManageImpl chickenBatchManageImpl;
	private LoginLogManageImpl loginLogManageImpl;
	private DimensionaManageImpl dimensionaManageImpl;
	private ResourceManageImpl resourceManageImpl;
	private NewsManageImpl newsManageImpl;
	private AccessManageImpl accessManageImpl;
	@javax.annotation.Resource
	public void setResourceManageImpl(ResourceManageImpl resourceManageImpl) {
		this.resourceManageImpl = resourceManageImpl;
	}
	@javax.annotation.Resource
	public void setNewsManageImpl(NewsManageImpl newsManageImpl) {
		this.newsManageImpl = newsManageImpl;
	}
	@javax.annotation.Resource
	public void setAccessManageImpl(AccessManageImpl accessManageImpl) {
		this.accessManageImpl = accessManageImpl;
	}
	@javax.annotation.Resource
	public void setChickenBatchManageImpl(
			ChickenBatchManageImpl chickenBatchManageImpl) {
		this.chickenBatchManageImpl = chickenBatchManageImpl;
	}
	@javax.annotation.Resource
	public void setLoginLogManageImpl(LoginLogManageImpl loginLogManageImpl) {
		this.loginLogManageImpl = loginLogManageImpl;
	}
	@javax.annotation.Resource
	public void setBreederManageImpl(BreederManageImpl breederManageImpl) {
		this.breederManageImpl = breederManageImpl;
	}
	@javax.annotation.Resource
	public void setDimensionaManageImpl(DimensionaManageImpl dimensionaManageImpl) {
		this.dimensionaManageImpl = dimensionaManageImpl;
	}
	public String index() throws Exception {
		HttpServletRequest request = getRequest();
		User user = SecurityUserHolder.getCurrentUser();
		List<LoginLog> loginLogs = loginLogManageImpl.findBy("userId", user.getDbid());
		request.setAttribute("loginLogs", loginLogs);
		return "index";
	}
	/**
	 * 功能描述：
	 * 参数描述：
	 * 逻辑描述：
	 * @return
	 * @throws Exception
	 */
	public String contentIndex() throws Exception {
		HttpServletRequest request = getRequest();
		User user = SecurityUserHolder.getCurrentUser();
		Integer userId = user.getDbid();
		List<Breeder> breeders = breederManageImpl.queryByIndex();
		List<LoginLog> loginLogs = loginLogManageImpl.queryByIndex(userId);
		List<ChickenBatch> chickenBatchs =chickenBatchManageImpl.queryByIndex();
		List<Dimensiona> dimensionas =dimensionaManageImpl.queryByIndex();
		request.setAttribute("loginLogs", loginLogs);
		request.setAttribute("breeders", breeders);
		request.setAttribute("chickenBatchs", chickenBatchs);
		request.setAttribute("dimensionas", dimensionas);
		return "contentIndex";
	}
	/**
	 * 功能描述：获取用户的权限
	 * @throws Exception
	 */
	public void userResource() throws Exception {
		User user = SecurityUserHolder.getCurrentUser();
		JSONArray resourceJson=new JSONArray();
		boolean fristMenu=false;
		if(null==user){
			renderJson("false");
			return;
		}
		try{
			//List<Resource> resources = getResources(user);
			List<Resource> resources = getResourcesSort(user);
			for (Resource resource : resources) {
				if(resource.getTitle().equals("权限根节点")){
					continue;
				}
				if(resource.getTitle().contains("系统主页")){
					continue;
				}
				JSONObject jsonObject=new JSONObject();
				if(resource.getMenu()==0){
					jsonObject.put("id", resource.getDbid());
					jsonObject.put("pId", 0);
					jsonObject.put("name", resource.getTitle());
					if(fristMenu==false){
						jsonObject.put("open", true);
						fristMenu=true;
					}
					resourceJson.put(jsonObject);
				}
				if(resource.getMenu()==1){
					jsonObject.put("id", resource.getDbid());
					jsonObject.put("pId", resource.getParent().getDbid());
					jsonObject.put("name", resource.getTitle());
					jsonObject.put("target", "contentUrl");
					jsonObject.put("url", resource.getContent());
					resourceJson.put(jsonObject);
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
			renderJson("false");
			return ;
		}
		//System.out.println("======"+resourceJson.toString());
		renderJson(resourceJson.toString());
		return ;
	}
	private static Set<Resource> getResources(User user){
		
		Set<Role> roles = user.getRoles();
		Set<Resource> resources2=new HashSet<Resource>();
		for (Role role : roles) {
			Set<Resource> resourcesRole = role.getResources();
			for (Resource resource : resourcesRole) {
				resources2.add(resource);
			}
		}
		return resources2;
	}
	private  List<Resource> getResourcesSort(User user) {
		/*Set<Resource> resources = getResources(user);
        SortedSet<Resource> resources2 =new TreeSet<Resource>(new ResourceComparator());
        SortedSet<Resource> resources3 =new TreeSet<Resource>(new ResourceComparator());
        
        for (Resource resource : resources) {
        	if(resource.getMenu()==0){
        		resources2.add(resource);
        	}else if(resource.getMenu()==1){
        		resources3.add(resource);
        	}
        }
        List<Resource> resourcesList=new ArrayList<Resource>();
        for (Resource resource : resources2) {
			resourcesList.add(resource);
		}
        for (Resource resource : resources3) {
			resourcesList.add(resource);
		}
        return resourcesList;*/
        List<Resource> resources = resourceManageImpl.queryResourceByUserId(user.getDbid());
		return resources;
    }
    private static class ResourceComparator implements Comparator<Resource>, Serializable {
        public int compare(Resource g1, Resource g2) {
        	return g1.getOrderNo().compareTo(g2.getOrderNo());
        }
    }
	public String logout() throws Exception {
		return "logout";
	}
}
