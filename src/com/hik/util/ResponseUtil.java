package com.hik.util;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;


public class ResponseUtil {

	/**
	 * 
	 * @MethodName: write
	 * @Description: ��json����תΪjson�ַ���д����ҳ��
	 * @author jed
	 * @date 2017��3��21������10:13:05
	 * @param @param response
	 * @param @param o
	 * @param @throws Exception    
	 * @return void    ��������
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
