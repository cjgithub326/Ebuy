/**
 * 
 */
package com.hik.action;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import com.hik.entity.News;
import com.hik.service.NewsService;
import com.hik.util.NavUtil;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @ClassName: NewsAction
 * @Description: TODO
 * @author jed
 * @date 2017年3月20日下午10:23:27
 *
 */
@Controller
public class NewsAction extends ActionSupport{

	/**
	 * 序列号
	 */
	private static final long serialVersionUID = 1L;
	
	private int newsId;
	
	private News news;
	
	private String navCode;
	
	private String mainPage;
	
	@Resource
	private NewsService newsService;
	
	public String showNews(){
		news = newsService.getNewsById(newsId);
		navCode = NavUtil.genNavCode("新闻");
		mainPage ="news/newsDetails.jsp";
		return SUCCESS;
	}



	public int getNewsId() {
		return newsId;
	}

	public void setNewsId(int newsId) {
		this.newsId = newsId;
	}

	public News getNews() {
		return news;
	}

	public void setNews(News news) {
		this.news = news;
	}

	public String getNavCode() {
		return navCode;
	}

	public void setNavCode(String navCode) {
		this.navCode = navCode;
	}

	public String getMainPage() {
		return mainPage;
	}

	public void setMainPage(String mainPage) {
		this.mainPage = mainPage;
	}
	
	

}
