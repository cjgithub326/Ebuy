package com.hik.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

	/**
	 * 
	 * @MethodName: formatDate
	 * @Description: 转日期为时间字符串格式
	 * @author jed
	 * @date 2017年4月25日下午9:53:43
	 * @param @param date
	 * @param @param format
	 * @param @return    
	 * @return String    返回类型
	 * @param date
	 * @param format
	 * @return
	 *
	 */
	public static String formatDate(Date date,String format){
		String result="";
		SimpleDateFormat sdf=new SimpleDateFormat(format);
		if(date!=null){
			result=sdf.format(date);
		}
		return result;
	}
	
	/**
	 * 
	 * @MethodName: formatString
	 * @Description: 装日期字符串格式为日期格式
	 * @author jed
	 * @date 2017年4月25日下午9:54:34
	 * @param @param str 
	 * @param @param format
	 * @param @return
	 * @param @throws Exception    
	 * @return Date    返回类型
	 * @param str
	 * @param format
	 * @return
	 * @throws Exception
	 *
	 */
	public static Date formatString(String str,String format) throws Exception{
		if(StringUtil.isEmpty(str)){
			return null;
		}
		SimpleDateFormat sdf=new SimpleDateFormat(format);
		return sdf.parse(str);
	}
	
	/**
	 * 
	 * @MethodName: getCurrentDateStr
	 * @Description: 获取当前日期字符串格式
	 * @author jed
	 * @date 2017年4月25日下午9:55:31
	 * @param @return
	 * @param @throws Exception    
	 * @return String    返回类型
	 * @return
	 * @throws Exception
	 *
	 */
	public static String getCurrentDateStr()throws Exception{
		Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddhhmmss");
		return sdf.format(date);
	}
}
