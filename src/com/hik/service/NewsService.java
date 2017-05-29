/**
 * 
 */
package com.hik.service;

import java.util.List;

import com.hik.entity.News;
import com.hik.entity.PageBean;

/**
 * @ClassName: NewsService
 * @Description: ���Žӿ�
 * @author jed
 * @date 2017��3��5������8:17:54
 *
 */
public interface NewsService {
	
	/**
	 * 
	 * @MethodName: findNewsList
	 * @Description: ��ȡ�������ţ�ֻ��ȡǰ������
	 * @author jed
	 * @date 2017��3��5������8:18:57
	 * @param @param news
	 * @param @param pageBean
	 * @param @return    
	 * @return List<News>    ��������
	 * @param news
	 * @param pageBean
	 * @return
	 *
	 */
	public List<News> findNewsList(News news,PageBean pageBean);
	
	/**
	 * 
	 * @MethodName: getNewsById
	 * @Description: ��������id��ȡ����
	 * @author jed
	 * @date 2017��3��20������10:14:53
	 * @param @param newsId
	 * @param @return    
	 * @return News    ��������
	 * @param newsId
	 * @return
	 *
	 */
	public News getNewsById(int newsId);
}
