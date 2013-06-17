package com.ystech.core.util;

import java.text.ParseException;
import java.util.Date;
import java.util.Enumeration;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class ParamUtil {

	public static Date getDateParam(HttpServletRequest request, String name) {
		name = request.getParameter(name);
		if (name == null || "".equals(name.trim()))
			return null;
		java.text.SimpleDateFormat sf = null;
		if (name.length() > 10) {
			sf = new java.text.SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		} else {
			sf = new java.text.SimpleDateFormat("yyyy-MM-dd");
		}
		try {
			Date date = sf.parse(name);
			return date;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new Date();
	}

	public static Double getDoubleParam(HttpServletRequest request,
			String name, double def) {
		Double integer = null;
		String temp = request.getParameter(name);

		try {
			integer = new Double(temp);
		} catch (NumberFormatException e) {
			return def;
		}
		if (integer == null) {
			return def;

		} else {
			return integer;
		}
	}

	public static Integer[] getIntArray(HttpServletRequest request, String s) {

		String[] s1 = getStrArray(request, s);
		Integer[] j = null;

		if (s1 != null) {
			for (int i = 0; i < s1.length; i++) {  
				if (i == 0)
					j = new Integer[s1.length];
				j[i] = new Integer(s1[i]);
			}
		}
		return j;
	}

	/**
	 * @param request
	 */
	public static Integer[] getIntArraryByDbids(HttpServletRequest request,String name) {
		String dbids = request.getParameter(name);
		Integer[] ids=null;
		if(null!=dbids&&dbids.length()>0){
			String[] split = dbids.split(",");
			ids=new Integer[split.length];
			for (int i=0;i<split.length;i++) {
				ids[i]=Integer.parseInt(split[i]);
			}
		}
		return ids;
	}
	public static Integer getIntParam(HttpServletRequest request, String name,
			int def) {
		Integer integer = null;
		String temp = request.getParameter(name);
		try {
			integer = new Integer(temp);
		} catch (NumberFormatException e) {
			return def;
		}

		if (integer == null) {
			return def;
		} else {
			return integer;
		}
	}

	public static Boolean getBoolParam(HttpServletRequest request, String name,
			Boolean def) {
		Boolean result = null;
		String strVal = request.getParameter(name);
		try {
			result = Boolean.parseBoolean(strVal);
		} catch (Exception e) {
			result = def;
		}
		return result;
	}

	public static Long getLongParam(HttpServletRequest request, String name,
			Long def) {
		Long result = null;
		String temp = request.getParameter(name);
		try {
			result = new Long(temp);
		} catch (NumberFormatException e) {
			return def;
		}
		if (result == null) {
			return def;
		} else
			return result;
	}

	public static String getParam(HttpServletRequest request, String name,
			String def) {
		String string = request.getParameter(name);
		if (string == null || "".equals(string))
			string = def;
		return string;
	}

	public static String[] getStrArray(HttpServletRequest request, String s) {
		return request.getParameterValues(s);
	}

	public static Integer[] getIntArrayByIds(HttpServletRequest request,
			String s) {
		String ids = request.getParameter(s);
		Integer[] idInts = null;
		if (null != ids && ids.trim().length() > 0) {
			String[] idStr = ids.split(",");
			idInts = new Integer[idStr.length];
			for (int i = 0; i < idStr.length; i++) {
				idInts[i] = Integer.parseInt(idStr[i]);
			}
		}
		return idInts;
	}

	/**
	 * 消除js字符串常量中的三个特殊字符',",\
	 * 
	 * @param s
	 *            String 要转换的字符串
	 * @return String 转换后的字符串
	 */
	public static String toJSString(String s) {
		String temp = new String(s);
		if (temp != null && !temp.equals(""))
			temp = temp.replace("\\", "\\\\").replace("'", "\\'")
					.replace("\"", "\\\"");// 把字符串的\-->\\,'-->\',"-->\"
		return temp;

	}
	public static String getQueryUrl(HttpServletRequest request){
		Enumeration<String> parameterNames = request.getParameterNames();
		String param="?";
		while (parameterNames.hasMoreElements()) {
			String name = (String) parameterNames.nextElement();
			String value = request.getParameter(name);
			param=param+name+"="+value+"&";
		}
		return param;
	}
	
}
