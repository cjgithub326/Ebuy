/**
 * 
 */
package com.hik.service;

import java.util.List;

import com.hik.entity.Notice;
import com.hik.entity.PageBean;

/**
 * @ClassName: NoticeService
 * @Description: 公告接口
 * @author jed
 * @date 2017年3月5日下午7:58:42
 *
 */
public interface NoticeService {

	/**
	 * 
	 * @MethodName: findNoticeList
	 * @Description: 获取最新公告（只取前几条）
	 * @author jed
	 * @date 2017年3月5日下午8:01:06
	 * @param @param notice
	 * @param @param pageBean
	 * @param @return    
	 * @return List<Notice>    返回类型
	 * @param notice
	 * @param pageBean
	 * @return
	 *
	 */
	public List<Notice> findNoticeList(Notice notice,PageBean pageBean);
	
	/**
	 * 
	 * @MethodName: getNoticeById
	 * @Description: 根据id获取公告信息
	 * @author jed
	 * @date 2017年3月20日下午10:12:38
	 * @param @param noticeId
	 * @param @return    
	 * @return Notice    返回类型
	 * @param noticeId
	 * @return
	 *
	 */
	public Notice getNoticeById(int noticeId);
} 
