package com.hik.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

	/**
	 * 
	 * @MethodName: formatDate
	 * @Description: ת����Ϊʱ���ַ�����ʽ
	 * @author jed
	 * @date 2017��4��25������9:53:43
	 * @param @param date
	 * @param @param format
	 * @param @return    
	 * @return String    ��������
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
	 * @Description: װ�����ַ�����ʽΪ���ڸ�ʽ
	 * @author jed
	 * @date 2017��4��25������9:54:34
	 * @param @param str 
	 * @param @param format
	 * @param @return
	 * @param @throws Exception    
	 * @return Date    ��������
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
	 * @Description: ��ȡ��ǰ�����ַ�����ʽ
	 * @author jed
	 * @date 2017��4��25������9:55:31
	 * @param @return
	 * @param @throws Exception    
	 * @return String    ��������
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
