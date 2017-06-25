/**
 * 
 */
package com.hik.action;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Controller;

import com.hik.entity.News;
import com.hik.entity.PageBean;
import com.hik.service.NewsService;
import com.hik.util.NavUtil;
import com.hik.util.ResponseUtil;
import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

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
	
	private String page;
	
	private String rows;
	
	private String ids;
	
	@Resource
	private NewsService newsService;
	
	public String showNews(){
		news = newsService.getNewsById(newsId);
		navCode = NavUtil.genNavCode("新闻");
		mainPage ="news/newsDetails.jsp";
		return SUCCESS;
	}

	/**
	 * 
	 * @MethodName: list
	 * @Description: 分页查询新闻信息
	 * @author jed
	 * @date 2017年6月25日上午11:16:01
	 * @param @return
	 * @param @throws Exception    
	 * @return String    返回类型
	 * @return
	 * @throws Exception
	 *
	 */
    public String list() throws Exception{
    	PageBean pageBean  = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
    	List<News> newsList = newsService.findNewsList(news, pageBean);
    	long total = newsService.getNewsCount(news);
    	JsonConfig jsonConfig = new JsonConfig();
    	jsonConfig.registerJsonValueProcessor(java.util.Date.class, new DateJsonValueProcessor("yyyy-MM-dd"));
    	JSONArray rows = JSONArray.fromObject(newsList, jsonConfig);
    	JSONObject result = new JSONObject();
    	result.put("rows", rows);
    	result.put("total", total);
		ResponseUtil.write(ServletActionContext.getResponse(), result);
		return null;
    }
    
    /**
     * 
     * @MethodName: save
     * @Description: 保存新闻信息
     * @author jed
     * @date 2017年6月25日上午11:17:23
     * @param @return
     * @param @throws Exception    
     * @return String    返回类型
     * @return
     * @throws Exception
     *
     */
    public String save() throws Exception{
    	if(news.getId()==0){//新增新闻内容
    		news.setCreateTime(new Date());
    	}
    	newsService.saveNews(news);
    	JSONObject result = new JSONObject();
    	result.put("success", true);
    	ResponseUtil.write(ServletActionContext.getResponse(), result);
		return null;
    }
    
    /**
     * 
     * @MethodName: delete
     * @Description: 删除新闻信息
     * @author jed
     * @date 2017年6月25日上午11:18:46
     * @param @return
     * @param @throws Exception    
     * @return String    返回类型
     * @return
     * @throws Exception
     *
     */
    public String delete() throws Exception{
    	JSONObject result = new JSONObject();
    	String[] idsStr = ids.split(",");
    	for(int i=0;i<idsStr.length;i++){
    		News news = newsService.getNewsById(Integer.parseInt(idsStr[i]));
    		if(news!=null){
    			newsService.delete(news);
    		}
    	}
    	result.put("success", true);
    	ResponseUtil.write(ServletActionContext.getResponse(), result);
		return null;
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

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public String getRows() {
		return rows;
	}

	public void setRows(String rows) {
		this.rows = rows;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}
	
	

}
