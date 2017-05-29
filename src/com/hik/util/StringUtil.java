package com.hik.util;

/**
 * ×Ö·û´®¹¤¾ßÀà
 * @author 
 *
 */
public class StringUtil {

	/**
	 * ÅÐ¶Ï×Ö·û´®ÊÇ·ñÊÇ¿Õ
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str){
		if(str==null||"".equals(str.trim())){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * ÅÐ¶Ï×Ö·û´®ÊÇ·ñ²»ÊÇ¿Õ
	 * @param str
	 * @return
	 */
	public static boolean isNotEmpty(String str){
		if((str!=null)&&!"".equals(str.trim())){
			return true;
		}else{
			return false;
		}
	}
}
