/**
 * 
 */
package com.ystech.core.security.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.AntPathRequestMatcher;
import org.springframework.security.web.util.RegexRequestMatcher;
import org.springframework.security.web.util.RequestMatcher;
import org.springframework.security.web.util.UrlUtils;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import com.ystech.springsecurity.model.Resource;
import com.ystech.springsecurity.model.Role;
import com.ystech.springsecurity.model.User;
import com.ystech.springsecurity.service.ResourceManageImpl;
import com.ystech.springsecurity.service.RoleManageImpl;

/**
 * @author shusanzhan
 * @date 2012-12-2 系统第一次调用加载资源和权限的关系 如果动态修改系统权限将无法进行动态加载，是一个比较严重的bug
 */
public class SpringSecurityMetadataSource implements
		FilterInvocationSecurityMetadataSource {
	private ResourceManageImpl resourceManageImpl;
	private RoleManageImpl roleManageImpl;
	private static Map<String, Collection<ConfigAttribute>> resourceMap = null;
	public SpringSecurityMetadataSource() {
		// loadResourceDefine();
	}

	public ResourceManageImpl getResourceManageImpl() {
		return resourceManageImpl;
	}

	@javax.annotation.Resource
	public void setResourceManageImpl(ResourceManageImpl resourceManageImpl) {
		this.resourceManageImpl = resourceManageImpl;
	}

	public RoleManageImpl getRoleManageImpl() {
		return roleManageImpl;
	}

	@javax.annotation.Resource
	public void setRoleManageImpl(RoleManageImpl roleManageImpl) {
		this.roleManageImpl = roleManageImpl;
	}

	@Override
	public Collection<ConfigAttribute> getAttributes(Object object) {
		String requestUrl = ((FilterInvocation) object).getRequestUrl();
		System.out.println("requestUrl is " + requestUrl);

		int firstQuestionMarkIndex = requestUrl.indexOf("?");
		if (firstQuestionMarkIndex != -1) {
			requestUrl = requestUrl.substring(0, firstQuestionMarkIndex);
		}
		if(null==resourceMap){
			loadResourceDefine();
		}
		Iterator<String> ite = resourceMap.keySet().iterator();
		Collection<ConfigAttribute> collection=new ArrayList<ConfigAttribute>();
		while (ite.hasNext()) {
			String resURL = ite.next();
			System.out.println("========="+resourceMap.get(resURL)+"----"+resURL);
			
			if (requestUrl.equals(resURL)) {
				Collection<ConfigAttribute> coll1 = resourceMap.get(resURL);
				collection.addAll(coll1);
			}
		}
		return collection;
	}

	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		return null;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return true;
	}

	// 加载所有资源与权限的关系初始化数据
	private void loadResourceDefine() {
		if (resourceMap == null) {
			resourceMap = new HashMap<String, Collection<ConfigAttribute>>();
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
	}
}
