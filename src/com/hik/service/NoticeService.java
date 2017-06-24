/**
 * 
 */
package com.hik.service;

import java.util.List;

import com.hik.entity.Notice;
import com.hik.entity.PageBean;

/**
 * @ClassName: NoticeService
 * @Description: ����ӿ�
 * @author jed
 * @date 2017��3��5������7:58:42
 *
 */
public interface NoticeService {

	/**
	 * 
	 * @MethodName: findNoticeList
	 * @Description: ��ȡ���¹��棨ֻȡǰ������
	 * @author jed
	 * @date 2017��3��5������8:01:06
	 * @param @param notice
	 * @param @param pageBean
	 * @param @return    
	 * @return List<Notice>    ��������
	 * @param notice
	 * @param pageBean
	 * @return
	 *
	 */
	public List<Notice> findNoticeList(Notice notice,PageBean pageBean);
	
	/**
	 * 
	 * @MethodName: getNoticeById
	 * @Description: ����id��ȡ������Ϣ
	 * @author jed
	 * @date 2017��3��20������10:12:38
	 * @param @param noticeId
	 * @param @return    
	 * @return Notice    ��������
	 * @param noticeId
	 * @return
	 *
	 */
	public Notice getNoticeById(int noticeId);
	
	/**
	 * 
	 * @MethodName: getNoticeCount
	 * @Description: ��ȡ��������
	 * @author jed
	 * @date 2017��6��24������8:35:45
	 * @param @param notice
	 * @param @return    
	 * @return Long    ��������
	 * @param notice
	 * @return
	 *
	 */
	public Long getNoticeCount(Notice notice);
	
	/**
	 * 
	 * @MethodName: saveNotice
	 * @Description: ���湫����Ϣ
	 * @author jed
	 * @date 2017��6��24������8:36:13
	 * @param @param notice    
	 * @return void    ��������
	 * @param notice
	 *
	 */
	public void saveNotice(Notice notice);
	
	/**
	 * 
	 * @MethodName: delete
	 * @Description: ɾ��������Ϣ
	 * @author jed
	 * @date 2017��6��24������8:36:29
	 * @param @param notice    
	 * @return void    ��������
	 * @param notice
	 *
	 */
	public void delete(Notice notice);
} 
