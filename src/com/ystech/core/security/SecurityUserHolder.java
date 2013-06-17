/**
 * 
 */
package com.ystech.core.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.ystech.springsecurity.model.User;

/**
 * @author shusanzhan
 * @date 2012-11-23
 */
public class SecurityUserHolder {
	public static User getCurrentUser(){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(authentication.getPrincipal() instanceof User){
			return (User) authentication.getPrincipal();
		}
		return null;
	}
}
