package com.zhbit.xuexin.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期处理工具类
 * 
 * @author 梁日宇
 * 
 */
public class DateUtil {

	private static SimpleDateFormat sdfLong = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");
	private static SimpleDateFormat sdfShort = new SimpleDateFormat(
			"yyyy-MM-dd");

	/**
	 * 将日期字符串转换成短日期
	 * @param dateString
	 * @return
	 */
	public static Date formartStringToShortDate(String dateString) {
		Date date;
		try {
			date = sdfShort.parse(dateString);
			return date;
		} catch (ParseException e) {
			return null;
		}
	}
	
	/**
	 * 将日期字符串转换成日期
	 * @param dateString
	 * @return
	 */
	public static Date formartStringToLongDate(String dateString) {
		Date date;
		try {
			date = sdfLong.parse(dateString);
			return date;
		} catch (ParseException e) {
			return null;
		}
	}
	
	/**
	 * 日期格式化成字符串
	 * @param date 日期
	 * @param pattern 格式
	 * @return
	 */
	public static String formatDate(Date date,String pattern){
		SimpleDateFormat format=new SimpleDateFormat(pattern);
		return format.format(date);
	}
	
	/**
	 * 长日期格式化成字符串
	 * @param date
	 * @return
	 */
	public static String formartDateLong(Date date){
		return sdfLong.format(date);
	}
	
	/**
	 * 短日期格式化成字符串
	 * @param date
	 * @return
	 */
	public static String formartDateShort(Date date){
		return sdfShort.format(date);
	}
	
}
