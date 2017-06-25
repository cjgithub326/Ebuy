/**
 * 
 */
package com.hik.service;

import java.util.List;

import com.hik.entity.News;
import com.hik.entity.PageBean;

/**
 * @ClassName: NewsService
 * @Description: 新闻接口
 * @author jed
 * @date 2017年3月5日下午8:17:54
 *
 */
public interface NewsService {
	
	/**
	 * 
	 * @MethodName: findNewsList
	 * @Description: 获取最新新闻（只获取前几条）
	 * @author jed
	 * @date 2017年3月5日下午8:18:57
	 * @param @param news
	 * @param @param pageBean
	 * @param @return    
	 * @return List<News>    返回类型
	 * @param news
	 * @param pageBean
	 * @return
	 *
	 */
	public List<News> findNewsList(News news,PageBean pageBean);
	
	/**
	 * 
	 * @MethodName: getNewsById
	 * @Description: 根据新闻id获取新闻
	 * @author jed
	 * @date 2017年3月20日下午10:14:53
	 * @param @param newsId
	 * @param @return    
	 * @return News    返回类型
	 * @param newsId
	 * @return
	 *
	 */
	public News getNewsById(int newsId);
	
	/**
	 * 
	 * @MethodName: getNewsCount
	 * @Description: 获取新闻总数
	 * @author jed
	 * @date 2017年6月25日上午11:04:41
	 * @param @param news
	 * @param @return    
	 * @return Long    返回类型
	 * @param news
	 * @return
	 *
	 */
	public Long getNewsCount(News news);
	
	/**
	 * 
	 * @MethodName: saveNews
	 * @Description: 保存新闻
	 * @author jed
	 * @date 2017年6月25日上午11:05:36
	 * @param @param news    
	 * @return void    返回类型
	 * @param news
	 *
	 */
	public void saveNews(News news);
	
	/**
	 * 
	 * @MethodName: delete
	 * @Description: 删除新闻
	 * @author jed
	 * @date 2017年6月25日上午11:05:46
	 * @param @param news    
	 * @return void    返回类型
	 * @param news
	 *
	 */
	public void delete(News news);
}
