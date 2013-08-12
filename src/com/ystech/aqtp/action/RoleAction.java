package com.ystech.aqtp.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.stereotype.Component;

import com.ystech.aqtp.model.Resource;
import com.ystech.aqtp.model.Role;
import com.ystech.aqtp.service.ResourceManageImpl;
import com.ystech.aqtp.service.RoleManageImpl;
import com.ystech.core.dao.support.Page;
import com.ystech.core.security.service.SpringSecurityMetadataSource;
import com.ystech.core.util.ParamUtil;
import com.ystech.core.web.BaseController;
@Component("roleAction")
@Scope("prototype")
public class RoleAction extends BaseController{
	private Role role;
	private RoleManageImpl roleManageImpl;
	private ResourceManageImpl resourceManageImpl;
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	@javax.annotation.Resource
	public void setRoleManageImpl(RoleManageImpl roleManageImpl) {
		this.roleManageImpl = roleManageImpl;
	}
	@javax.annotation.Resource
	public void setResourceManageImpl(ResourceManageImpl resourceManageImpl) {
		this.resourceManageImpl = resourceManageImpl;
	}
	public String queryList() throws Exception {
		HttpServletRequest request = this.getRequest();
		Integer pageSize = ParamUtil.getIntParam(request, "pageSize", 10);
		Integer pageNo = ParamUtil.getIntParam(request, "currentPage", 1);
		Page<Role> page = roleManageImpl.pageQuery(pageNo, pageSize, "from Role", new Object[]{});
		request.setAttribute("page", page);
		return "list";
	}
	public void save() throws Exception {
		try{
			roleManageImpl.save(role);
		}catch (Exception e) {
			e.printStackTrace();
			renderErrorMsg(e, "");
		}
		renderMsg("/role/queryList", "保存数据成功！");
		return ;
	}
	public String edit() throws Exception {
		HttpServletRequest request = this.getRequest();
		Integer dbid = ParamUtil.getIntParam(request, "dbid", -1);
		if(dbid>0){
			Role role = roleManageImpl.get(dbid);
			request.setAttribute("role", role);
		}
		return "edit";
	}
	public void delete() throws Exception {
		HttpServletRequest request = this.getRequest();
		Integer dbid = ParamUtil.getIntParam(request, "dbid", -1);
		try {
			roleManageImpl.deleteById(dbid);
		} catch (Exception e) {
			e.printStackTrace();
			renderErrorMsg(e, "");
		}
		renderMsg("/role/queryList", "保存数据成功！");
		return ;
	}
	//权限分配跳转页面
	public String roleResource() throws Exception {
		HttpServletRequest request2 = this.getRequest();
		Integer dbid = ParamUtil.getIntParam(request2, "dbid", -1);
		if(dbid>0){
			role = roleManageImpl.get(dbid);
			String resourceIds = getResourceIds(role.getResources());
			request2.setAttribute("resourceIds", resourceIds);
		}
		return "roleResource";
	}
	public void saveResource() throws Exception {
		HttpServletRequest request2 = this.getRequest();
		Integer[] ids = ParamUtil.getIntArrayByIds(request2, "resourceIds");
		Integer dbid = ParamUtil.getIntParam(request2, "roleId", -1);
		Set<Resource> rSet=new HashSet<Resource>();
		if(dbid>0){
			Role role2 = roleManageImpl.get(dbid);
			Set<Resource> resources = role2.getResources();
			if(null!=ids&&ids.length>0){
				resources.clear();
				role2.setResources(null);
				for (Integer id : ids) {
					rSet.add(resourceManageImpl.get(id));
				}
				role2.setResources(rSet);
			}
			try{
				roleManageImpl.save(role2);
				Map<String, Collection<ConfigAttribute>> resourceMap = SpringSecurityMetadataSource.resourceMap;
				Map<String, Collection<ConfigAttribute>> loadResourceDefine = loadResourceDefine(resourceMap);
				SpringSecurityMetadataSource.resourceMap=loadResourceDefine;
			}catch (Exception e) {
				e.printStackTrace();
				renderErrorMsg(e, "");
			}
			renderMsg("/role/queryList", role2.getName()+"授权成功！");
			return ;
		}
	}
	//前台通过ajax获取权限树
	public void roleResourceJson(){
		HttpServletRequest request2 = this.getRequest();
		Integer dbid = ParamUtil.getIntParam(request2, "dbid", -1);
		if(dbid>0){
			Role role2 = roleManageImpl.get(dbid);
			Set<Resource> resources = role2.getResources();
			try {
				JSONArray makeJson = makeJson(resources);
				if(null!=makeJson){
					renderJson(makeJson.toString());
				}else{
					renderJson("1");
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			renderJson("1");
		}
	}
	//通过平面构造资源树
	private JSONArray makeJson(Set<Resource> roleResource) throws JSONException{
		JSONArray array=null;
		List<Resource> resources = resourceManageImpl.getAll();
		if(null!=resources&&resources.size()>0){
			array=new JSONArray();
			for (Resource resource : resources) {
				JSONObject object=new JSONObject();
				if(null!=roleResource&&roleResource.size()>0){
					for (Resource resource2 : roleResource) {
						if(resource.getDbid()==resource2.getDbid()){
							object.putOpt("checked", true);
							break;
						}
					}
				}
				object.put("id", resource.getDbid());
				object.put("name", resource.getTitle());
				if (resource.getMenu() == 0) {
					if (resource.getParent() != null&&resource.getParent().getDbid()>0) {
						object.put("icon","/widgets/ztree/css/zTreeStyle/img/diy/2.png");// 菜单阶段
						object.put("pId", resource.getParent().getDbid());
						object.put("open", true);
					} else {
						object.put("icon","/widgets/ztree/css/zTreeStyle/img/diy/1_open.png");// 根节点
						object.put("root", "root");
						object.put("pId", 0);
						object.put("open", true);
					}
				}else if(resource.getMenu()==1){
					object.put("icon","/widgets/ztree/css/zTreeStyle/img/diy/3.png");// 菜单阶段
					object.put("pId", resource.getParent().getDbid());
					object.put("open", true);
				}else{
					object.put("pId", resource.getParent().getDbid());
				}
				
				array.put(object);
			}
		}
		return array;
	}
	private String getResourceIds(Set<Resource> resources){
		StringBuffer rBuffer=new StringBuffer();
		if(null!=resources&&resources.size()>0){
			for (Resource resource : resources) {
				rBuffer.append(resource.getDbid()).append(",");
			}
			return rBuffer.toString();
		}
		return null;
	}
	// 加载所有资源与权限的关系初始化数据
	private Map<String, Collection<ConfigAttribute>>  loadResourceDefine(Map<String, Collection<ConfigAttribute>> resourceMap) {
		if (resourceMap == null) {
			resourceMap = new HashMap<String, Collection<ConfigAttribute>>();
		}else{
			List<Role> roles = roleManageImpl.getAll();
			if(null!=roles){
				for (Role role : roles) {
					Set<Resource> resources = role.getResources();
					ConfigAttribute ca = new SecurityConfig(role.getName());
					if (null != resources && resources.size() > 0) {
						for (Resource resource : resources) {
							String url=resource.getContent();
							if(resourceMap.containsKey(url)){
								Collection<ConfigAttribute> value = resourceMap.get(url);
							     value.add(ca);
							     resourceMap.put(url, value);
							}else{
								Collection<ConfigAttribute> atts = new ArrayList<ConfigAttribute>();
							     atts.add(ca);
							     resourceMap.put(url, atts);
							}
						}
					}
				}
			}
		}
		return resourceMap;
	}
}
