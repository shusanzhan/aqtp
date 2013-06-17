package com.ystech.core.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtil {

	/**
	 * 将字符串形式的日期转换为java.util.Date 格式 yyyy-MM-dd
	 * 
	 * @param string
	 * @return
	 */
	public static Date string2Date(String string) {

		if (string.equals("")) {
			return null;
		}
		DateFormat format = DateFormat.getDateInstance(DateFormat.MEDIUM);
		Date date = null;
		try {
			date = format.parse(string);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 过滤掉日期后缀
	 */
	public static Date formatDate(Date date) {
		String format = DateFormat.getDateInstance(DateFormat.DEFAULT).format(date);
		return string2Date(format);
	}

	/**
	 * 格式 yyyy-MM-dd
	 * 
	 * @param string
	 * @return
	 */
	@SuppressWarnings("static-access")
	public static Date nextDay(String string) {
		Calendar date = Calendar.getInstance();
		date.setTime(DateUtil.string2Date(string));
		date.add(date.DATE, 1);
		Date nextday = date.getTime();
		return nextday;
	}
	/*
	 * 格式 yyyy-MM-dd
	 * 
	 * @param string
	 * @return
	 */
	@SuppressWarnings("static-access")
	public static Date lastYear(String string) {
		Calendar date = Calendar.getInstance();
		date.setTime(DateUtil.string2Date(string));
		date.add(date.YEAR, -1);
		Date LastYear = date.getTime();
		return LastYear;
	}
	/**
	 * 格式 yyyy-MM-dd
	 * 
	 * @param string
	 * @return
	 */
	@SuppressWarnings("static-access")
	public static Date lastYear(Date tempDate) {
		Calendar date = Calendar.getInstance();
		date.setTime(tempDate);
		date.add(date.YEAR, -1);
		Date LastYear = date.getTime();
		return LastYear;
	}

	/**

	/**
	 * 格式化Data:yyyy-MM-dd
	 * 
	 * @param date
	 * @return
	 */
	public static String format(Date date) {
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
		return f.format(date);
	}
	
/**
 * 格式化Data:yyyy-1-1
 * @param date
 * @return
 */
	public static String format2Date(Date date) {
	SimpleDateFormat f = new SimpleDateFormat("yyyy-1-1");
	String format = f.format(date);
		return format;
}
	/**
	 * 格式化Data:yyyy-1-1
	 * 
	 * @param date
	 * @return
	 */
	public static Date format2Date11(Date date) {
		SimpleDateFormat f = new SimpleDateFormat("yyyy-1-1");
		String format = f.format(date);
		System.err.println(format);
		return string2Date(format);
	}
	

	/**
	 * 格式化Data:yyyy-MM-dd hh:mm
	 * 
	 * @param date
	 * @return
	 */
	public static String format3(Date date) {
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd hh:mm");
		return f.format(date);
	}

	/**
	 * 格式化Data:yyyy-MM-dd hh:mm:ss
	 * 
	 * @param date
	 * @return
	 */
	public static String format2(Date date) {
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		return f.format(date);
	}
	public static String formatFile(Date date){
		SimpleDateFormat f = new SimpleDateFormat("yyyyMMddhhmmss");
		return f.format(date);
	}
	/**
	 * 计算所给出的2个日期之间相差多少天
	 * 
	 * @param startday
	 * @param endday
	 * @return
	 */
	public static int getIntervalDays(Date startday, Date endday) {
		if (startday.after(endday)) {
			Date cal = startday;
			startday = endday;
			endday = cal;
		}
		long sl = startday.getTime();
		long el = endday.getTime();
		long ei = el - sl;
		return (int) (ei / (1000 * 60 * 60 * 24));
	}

	/**
	 * 计算所给出的2个日期之间相差多少小时
	 * 
	 * @param startday
	 * @param endday
	 * @return
	 */
	public static int getIntervalHours(Date startday, Date endday) {
		if (startday.after(endday)) {
			Date cal = startday;
			startday = endday;
			endday = cal;
		}
		long sl = startday.getTime();
		long el = endday.getTime();
		long ei = el - sl;
		return (int) (ei / (1000 * 60 * 60));
	}

	public static Date stringDateWithHHMM(String date) {
		SimpleDateFormat sdDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
		try {
			return sdDateFormat.parse(date);
		} catch (ParseException e) {
			System.out.println("===========>日期转换格式出错！");
			e.printStackTrace();
			return null;
		}
	}
	public static Date stringDateWithHHMMSS(String date) {
		SimpleDateFormat sdDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			return sdDateFormat.parse(date);
		} catch (ParseException e) {
			System.out.println("===========>日期转换格式出错！");
			e.printStackTrace();
			return null;
		}
	}
	public static Date stringDateWithYyyyHH(String date) {
		SimpleDateFormat sdDateFormat = new SimpleDateFormat("yyyy年MM月");
		try {
			return sdDateFormat.parse(date);
		} catch (ParseException e) {
			System.out.println("===========>日期转换格式出错！");
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 计算所给出的2个日期之间相差多少月 参数无日期前后顺序
	 * 
	 * @param day1
	 * @param day2
	 * @return
	 */
	@SuppressWarnings("static-access")
	public static int getIntervalMonths(Date day1, Date day2) {
		// 确认day1 早于day2
		if (day1.after(day2)) {
			Date cal = day1;
			day1 = day2;
			day2 = cal;
		}
		Calendar dateStart = Calendar.getInstance();
		Calendar dateEnd = Calendar.getInstance();
		dateStart.setTime(day1);
		dateEnd.setTime(day2);
		int month = dateEnd.get(dateEnd.MONTH) - dateStart.get(dateStart.MONTH);
		int year = dateEnd.get(dateEnd.YEAR) - dateStart.get(dateStart.YEAR);
		int result = 12 * year + month;
		return result;
	}

	/**
	 * 计算所给出的2个日期之间相差多少年 参数无日期前后顺序
	 * 
	 * @param day1
	 * @param day2
	 * @return
	 */
	@SuppressWarnings("static-access")
	public static int getIntervalYears(Date day1, Date day2) {
		if (day1.after(day2)) {
			Date cal = day1;
			day1 = day2;
			day2 = cal;
		}
		Calendar dateStart = Calendar.getInstance();
		Calendar dateEnd = Calendar.getInstance();
		dateStart.setTime(day1);
		dateEnd.setTime(day2);

		int year = dateEnd.get(dateEnd.YEAR) - dateStart.get(dateStart.YEAR);
		// System.out.println("dateEnd.get(dateEnd.MONTH)==>" +
		// dateEnd.get(dateEnd.YEAR));
		// System.out.println("dateStart.get(dateStart.MONTH)==>" +
		// dateStart.get(dateStart.YEAR));
		return year;
	}
	/**
	 * 计算所给出的2个日期之间相差多少年 参数无日期前后顺序
	 * 
	 * @param day1
	 * @param day2
	 * @return
	 */
	@SuppressWarnings("static-access")
	 public static Integer getIntervalYears2(Date day1, Date day2) {
		if (day1.after(day2)) {
			Date cal = day1;
			day1 = day2;
			day2 = cal;
		}
		Calendar dateStart = Calendar.getInstance();
		Calendar dateEnd = Calendar.getInstance();
		dateStart.setTime(day1);
		dateEnd.setTime(day2);
		int day = dateEnd.get(dateEnd.DATE) - dateStart.get(dateStart.DATE);
		int month = dateEnd.get(dateEnd.MONTH) - dateStart.get(dateStart.MONTH);
		int year = dateEnd.get(dateEnd.YEAR) - dateStart.get(dateStart.YEAR);
		
		if (day < 0) {
			month = month - 1;
		}
		
		int months = 12 * year + month;
		
		return months / 12;
	}

	public static Date addMonth(Date date, Integer month) {
		Calendar temp = Calendar.getInstance();
		temp.setTime(date);
		temp.add(Calendar.MONTH, month);
		date = temp.getTime();
		return date;
	}

	public static Date addYear(Date date, Integer year) {
		Calendar temp = Calendar.getInstance();
		temp.setTime(date);
		temp.add(Calendar.YEAR, -year);
		date = temp.getTime();
		return date;
	}

	/**
	 * 日期转换成字符串
	 * 
	 * @param date
	 * @return str
	 */
	public static String DateToStr(Date date) {

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String str = format.format(date);
		return str;
	}
	
	public static String DateToStrChina(Date date){
		SimpleDateFormat format=new SimpleDateFormat("yyyy年MM月dd日");
		String str=format.format(date);
		return str;
	}
	public static String dateToString(Date date) {

		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		String str = format.format(date);
		return str;
	}

	/**
	 * 字符串转换成日期
	 * 
	 * @param str
	 * @return date
	 */
	public static Date StrToDate(String str) {
		if (str == null) {
			return null;
		}
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = null;
		try {
			date = format.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 
	 * @param date
	 * @param oldName
	 * @return 新名字，格式如下 日期(20101001)+毫秒末6位(123456)+原来的名字<br>
	 *         (oldName,长度已固定为4位字符长度-页面验证字符长度必须为4位 ) 20101001123456name
	 */
	public static String generatedName(Date date, String oldName) {
		String time = "" + System.currentTimeMillis();
		int beginIndex = time.length() - 6;
		int endIndex = time.length();
		String newName = DateUtil.dateToString(new Date()) + time.substring(beginIndex, endIndex) + oldName;
		return newName;
	}

	/**
	 * 
	 * @param date
	 * @param oldName
	 * @return 新名字，格式如下 日期(20101001)+毫秒末6位(123456)<br>
	 *         20101001123456
	 */
	public static String generatedName(Date date) {
		String time = "" + System.currentTimeMillis();
		int beginIndex = time.length() - 6;
		int endIndex = time.length();
		String newName = DateUtil.dateToString(new Date()) + time.substring(beginIndex, endIndex);
		return newName;
	}

	/**
	 * 
	 * 
	 * @param weekday
	 * @return
	 */
	public static String getMondayOfWeek() {
		int weeks = 0;
		int mondayPlus = getMondayPlus();
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.add(GregorianCalendar.DATE, mondayPlus);
		return format(currentDate.getTime());
	}

	public static String getFridayOfWeek() {
		int weeks = 0;
		int mondayPlus = getMondayPlus();
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.add(GregorianCalendar.DATE, mondayPlus + 4);
		return format(currentDate.getTime());
	}
	/**
	 * 获取当前时间上一个月时间
	 * 
	 * @return
	 */
	public static String getLastMonth() {
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.add(GregorianCalendar.MONTH, -1);
		return format(currentDate.getTime());
	}
	/**
	 * 获取当前时间下一个月时间
	 * 
	 * @return
	 */
	public static String getNextMonth() {
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.add(GregorianCalendar.MONTH, 1);
		return format(currentDate.getTime());
	}

	/**
	 * 获得当前日期与本周日相差的天数
	 * 
	 * @return
	 */
	private static int getMondayPlus() {
		Calendar cd = Calendar.getInstance();
		int dayOfWeek = cd.get(Calendar.DAY_OF_WEEK) - 1;
		if (dayOfWeek == 1) {
			return 0;
		} else {
			return 1 - dayOfWeek;
		}
	}
	
}
