package com.hik.util;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;


public class ResponseUtil {

	/**
	 * 
	 * @MethodName: write
	 * @Description: 把json对象转为json字符串写出到页面
	 * @author jed
	 * @date 2017年3月21日下午10:13:05
	 * @param @param response
	 * @param @param o
	 * @param @throws Exception    
	 * @return void    返回类型
	 * @param response
	 * @param o
	 * @throws Exception
	 *
	 */
	public static void write(HttpServletResponse response,Object o)throws Exception{
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		out.println(o.toString());
		out.flush();
		out.close();
	}
}
