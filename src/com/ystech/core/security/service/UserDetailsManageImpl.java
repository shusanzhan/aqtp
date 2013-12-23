/**
 * 
 */
package com.ystech.core.security.service;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.ystech.aqtp.model.Role;
import com.ystech.aqtp.model.User;
import com.ystech.aqtp.service.UserManageImpl;

/**
 * @author shusanzhan
 * @date 2012-11-23
 */
@Component("userDetailsManageImpl")
public class UserDetailsManageImpl implements UserDetailsService{
	private UserManageImpl userManageImpl;
	@Resource
	public void setUserManageImpl(UserManageImpl userManageImpl) {
		this.userManageImpl = userManageImpl;
	}
	
	public UserDetails loadUserByUsername(String userId)throws UsernameNotFoundException {
		 System.err.println("================= spring 登陆成功后调用此方法 第二步 调用userDetailsManageImpl");
		 User user = userManageImpl.findUniqueBy("userId", userId);
		 if(null!=user){
			 Set<GrantedAuthority> authorities = obtionGrantedAuthorities(user);
			 /*for (GrantedAuthority grantedAuthority : authorities) {
				System.out.println("grantedAuthorit======"+grantedAuthority.getAuthority());
			 }*/
			 user.setAuthorities(authorities);
		    return user;
		 }else{
			 throw new UsernameNotFoundException("User " + userId + " has no GrantedAuthority");
		 }
	}
	 //取得用户的权限  
    private Set<GrantedAuthority> obtionGrantedAuthorities(User user) {  
        Set<GrantedAuthority> authSet = new HashSet<GrantedAuthority>();  
        Set<Role> roles = user.getRoles();  
        if(null!=roles&&roles.size()>0){
        	for(Role role : roles) {  
        		authSet.add(new GrantedAuthorityImpl(role.getName()));  
        	}  
        }
        return authSet;  
    }  
	
}
