/**
 * 
 */
package com.ystech.core.ip;

import javax.servlet.http.HttpServletRequest;

/**
 * @author shusanzhan
 * @date 2013-3-6
 */
public class IPHepler {
	/**
	 * 获取客户端IP地址
	 * @param request
	 * @return
	 */
	public static String getIpAddr(HttpServletRequest request) {
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
	/**
	 * 获取省份信息
	 * @param contry
	 * @return
	 */
	public static String getProvince(String contry){
		if(null!=contry){
			if(contry.contains("省")){
				int indexOf = contry.indexOf("省");
				return contry.substring(0, indexOf+1);
			}else if(contry.contains("市")){
				int indexOf = contry.indexOf("市");
				return contry.substring(0, indexOf+1);
			}else if(contry.contains("自治区")){
				int indexOf=contry.indexOf("自治区");
				return contry.substring(0, indexOf+1);
			}else
				return contry; 
		}
		return null;
	}
	
}
