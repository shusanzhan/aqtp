package com.ystech.core.util;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

public class DebugUtil {

    public static void showAllPorperty(List<?> items) {
		 System.out.println("============================集合size为:" + items.size() + "============================");
		for (Object object : items) {
			System.out.println(ReflectionToStringBuilder.toString(object));
		}

    }
    public static void showAllPorperty(Set<?> items) {
		// System.out.println("============================集合size为:" +
		// items.size() + "============================");
		// for (Object object : items) {
		// System.out.println(ReflectionToStringBuilder.toString(object));
		// }

    }

    public static void showAllPorperty(Object object) {
		 System.out.println(ReflectionToStringBuilder.toString(object));
    }

    public static void showParameter(HttpServletRequest re) {
    	
    }
    @SuppressWarnings("unchecked")
    public static void showRequestParameterNames(HttpServletRequest request) {
		// Enumeration v = request.getParameterNames();
		// if (null != request.getParameter("method")) {
		// // System.out.println("=================method==============" +
		// // "     " + request.getParameter("method"));
		// }
		// while (v.hasMoreElements()) {
		// String temp = (String) v.nextElement();
		// String[] parameters = request.getParameterValues(temp);
		// if (parameters.length == 0) {
		// System.out.println(temp + "<===>" + request.getParameter(temp));
		// } else {
		// StringBuffer sb = new StringBuffer();
		// for (int i = 0; i < parameters.length; i++) {
		// sb.append(parameters[i] + "       ");
		// }
		// System.out.println(temp + "<===>" + sb.toString());
		// }
		// }
    }
}
