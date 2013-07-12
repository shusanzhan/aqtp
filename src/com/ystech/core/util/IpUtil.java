package com.ystech.core.util;

import javax.servlet.http.HttpServletRequest;

import com.ystech.core.web.BaseController;

public class IpUtil extends BaseController {
	/**
	 * 获取客户端IP地址
	 * @param request
	 * @return
	 */
	public String getIpAddr() {
		HttpServletRequest request = getRequest();
		String ip = request.getHeader("x-forwarded-for");
		if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
		ip = request.getHeader("Proxy-Client-IP");
		}
		if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
		ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
		ip = request.getRemoteAddr();
		}
		return ip;
	}
}
