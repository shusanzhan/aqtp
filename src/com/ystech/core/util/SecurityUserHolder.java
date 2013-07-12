package com.ystech.core.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.ystech.aqtp.model.User;



public class SecurityUserHolder {
	/**  
	 * Returns the current user  
	 *   
	 * @return  
	 */
	public static User getCurrentUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication.getPrincipal() instanceof User) {
			return (User) authentication.getPrincipal();
		}
		return null;
	}

}
