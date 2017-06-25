/**
 * 
 */
package com.hik.service.impl;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hik.dao.BaseDAO;
import com.hik.entity.News;
import com.hik.entity.PageBean;
import com.hik.service.NewsService;
import com.hik.util.StringUtil;

/**
 * @ClassName: NewsServiceImpl
 * @Description: TODO
 * @author jed
 * @date 2017年3月5日下午8:19:54
 *
 */
@Service("newsService")
public class NewsServiceImpl implements NewsService{

	@Resource
	private BaseDAO<News> baseDao;
	@Override
	public List<News> findNewsList(News news, PageBean pageBean) {
		List<Object> param = new LinkedList<Object>(); //插入和删除操作用linkList效率要高
		StringBuffer sb = new StringBuffer("from News");
		if(news!=null){
			if(StringUtil.isNotEmpty(news.getTitle())){
				sb.append(" and title like ?");
				param.add("%"+news.getTitle()+"%");
			}
		}
		if(pageBean!=null){
			return baseDao.find(sb.toString().replaceFirst("and", "where"), param, pageBean);
		}else{
			return null;
		}
	}
	
	@Override
	public News getNewsById(int newsId) {
		return baseDao.get(News.class, newsId);
	}

	@Override
	public Long getNewsCount(News news) {
		List<Object> param = new LinkedList<Object>();
		StringBuffer sb = new StringBuffer("select count(*) from News");
		if(news!=null){
			if(StringUtil.isNotEmpty(news.getTitle())){
				sb.append(" and title like ?");
				param.add("%"+news.getTitle()+"%");
			}
		}
		return baseDao.count(sb.toString().replaceFirst("and", "where"), param);
	}

	@Override
	public void saveNews(News news) {
		baseDao.merge(news);
	}

	@Override
	public void delete(News news) {
		baseDao.delete(news);
	}

}
