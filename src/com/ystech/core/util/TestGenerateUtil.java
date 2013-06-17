package com.ystech.core.util;

import java.lang.reflect.Field;

import org.apache.commons.lang.StringUtils;

public class TestGenerateUtil {

	public static void generate(Class clazz) {
		System.out.println("//create");
		String className = getClassNameWithoutPackage(clazz);
		System.out.println(className + " " + getObjectName(clazz) + "=new " + className + "();");
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			System.out.println(generateSetter(clazz, field));
		}
		System.out.println(getManageNameOfModel(clazz) + ".save(" + getObjectName(clazz) + ");");
		System.out.println("assertNotNull(" + getObjectName(clazz) + ".getDbid());");

		// 生成读取测试代码
		System.out.println("//get");
		System.out.println(getObjectName(clazz) + "=" + getManageNameOfModel(clazz) + ".get(" + getObjectName(clazz)
				+ ".getDbid());");
		for (Field field : fields) {
			System.out.println(generateAssert(clazz, field));
		}

		// 生成更新代码
		System.out.println("//update");
		for (Field field : fields) {
			if (!field.getName().equals("dbid") && field.getType().equals(java.lang.String.class)) {
				System.out.println(getObjectName(clazz) + ".set" + StringUtils.capitalize(field.getName()) + "(\""
						+ field.getName() + "1\");");
				break;
			}
		}
		System.out.println(getManageNameOfModel(clazz) + ".save(" + getObjectName(clazz) + ");");
		System.out.println(getObjectName(clazz) + "=" + getManageNameOfModel(clazz) + ".get(" + getObjectName(clazz)
				+ ".getDbid());");
		for (Field field : fields) {
			System.out.println(generateAssert(clazz, field));
		}

		// 生成删除代码
		System.out.println("//delete");
		System.out.println(getManageNameOfModel(clazz) + ".deleteById(" + getObjectName(clazz) + ".getDbid());");
	}

	private static String generateAssert(Class clazz, Field field) {
		if ("dbid".equals(field.getName())) {
			return "assertNotNull(" + getObjectName(clazz) + ".getDbid());";
		} else if (field.getType().equals(java.lang.Double.class)) {
			return "assertEquals(new Double(1.0f)," + getObjectName(clazz) + ".get"
					+ StringUtils.capitalize(field.getName()) + "());";
		} else if (field.getType().toString().equals("double")) {
			return "assertEquals(1.0f," + getObjectName(clazz) + ".get" + StringUtils.capitalize(field.getName())
					+ "());";
		} else if (field.getType().equals(java.lang.Boolean.class)) {
			return "assertEquals(Boolean.TRUE," + getObjectName(clazz) + ".get"
					+ StringUtils.capitalize(field.getName()) + "());";
		} else if (field.getType().toString().equals("boolean")) {
			return "assertEquals(true," + getObjectName(clazz) + ".is" + StringUtils.capitalize(field.getName())
					+ "());";
		} else if (field.getType().equals(java.util.Date.class)) {
			return "assertNotNull(" + getObjectName(clazz) + ".get" + StringUtils.capitalize(field.getName()) + "());";
		} else if (field.getType().toString().equals("int")) {
			return "assertEquals(1," + getObjectName(clazz) + ".get" + StringUtils.capitalize(field.getName()) + "());";
		} else if (field.getType().equals(java.lang.Integer.class)) {
			return "assertEquals(new Integer(1)," + getObjectName(clazz) + ".get"
					+ StringUtils.capitalize(field.getName()) + "());";
		} else if (field.getType().equals(java.util.Set.class)) {
			return "";
		
		} else {
			return "assertEquals(\"" + field.getName() + "\"," + getObjectName(clazz) + ".get"
					+ StringUtils.capitalize(field.getName()) + "());";
		
		
		
		}

	}

	private static String generateSetter(Class clazz, Field field) {
		if ("dbid".equals(field.getName())) {
			return "";
		} else if (field.getType().equals(java.lang.Double.class)) {
			return getObjectName(clazz) + ".set" + StringUtils.capitalize(field.getName()) + "(new Double(1.0f));";
		} else if (field.getType().toString().equals("double")) {
			return getObjectName(clazz) + ".set" + StringUtils.capitalize(field.getName()) + "(new Double(1.0f));";
		} else if (field.getType().equals(java.util.Date.class)) {
			return getObjectName(clazz) + ".set" + StringUtils.capitalize(field.getName()) + "(new java.util.Date());";
		} else if (field.getType().equals(java.lang.Boolean.class)) {
			return getObjectName(clazz) + ".set" + StringUtils.capitalize(field.getName()) + "(true);";
		} else if (field.getType().toString().equals("boolean")) {
			return getObjectName(clazz) + ".set" + StringUtils.capitalize(field.getName()) + "(true);";
		} else if (field.getType().toString().equals("int")) {
			return getObjectName(clazz) + ".set" + StringUtils.capitalize(field.getName()) + "(1);";
		} else if (field.getType().equals(java.lang.Integer.class)) {
			return getObjectName(clazz) + ".set" + StringUtils.capitalize(field.getName()) + "(1);";
		} else if (field.getType().equals(java.util.Set.class)) {
			return "";
		}
		return getObjectName(clazz) + ".set" + StringUtils.capitalize(field.getName()) + "(\"" + field.getName()
				+ "\");";
	}

	private static String getClassNameWithoutPackage(Class clazz) {
		String name = clazz.getName();
		return name.substring(name.lastIndexOf(".") + 1);
	}

	public static String getManageNameOfModel(Class clazz) {
		return getObjectName(clazz) + "ManageImpl";
	}

	public static String getObjectName(Class clazz) {
		return StringUtils.uncapitalize(getClassNameWithoutPackage(clazz));
	}

}
