/**
 * 
 */
package com.hik.action;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ApplicationAware;
import org.springframework.stereotype.Controller;

import com.hik.entity.News;
import com.hik.entity.Notice;
import com.hik.entity.PageBean;
import com.hik.entity.Product;
import com.hik.entity.ProductBigType;
import com.hik.entity.Tag;
import com.hik.service.NewsService;
import com.hik.service.NoticeService;
import com.hik.service.ProductBigTypeService;
import com.hik.service.ProductService;
import com.hik.service.TagService;
import com.hik.util.ResponseUtil;
import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSONObject;

/**
 * @ClassName: SysAction
 * @Description: TODO
 * @author jed
 * @date 2017年6月25日下午10:40:31
 *
 */
@Controller
public class SysAction extends ActionSupport implements ApplicationAware{

	/**
	 * 序列号
	 */
	private static final long serialVersionUID = 1L;
	
	private Map<String, Object> application;
	
	@Resource
	private ProductBigTypeService productBigTypeService;
	
	@Resource
	private TagService tagService;
	
	@Resource
	private NoticeService noticeService;
	
	@Resource
	private NewsService newsService;
	
	@Resource
	private ProductService productService;

	@Override
	public void setApplication(Map<String, Object> application) {
		this.application = application;
	}

	/**
	 * 
	 * @MethodName: refreshSystem
	 * @Description: 刷新系统缓存
	 * @author jed
	 * @date 2017年6月25日下午10:46:05
	 * @param @return    
	 * @return String    返回类型
	 * @return
	 * @throws Exception 
	 *
	 */
	public String refreshSystem() throws Exception{
		List<ProductBigType> bigTypeList = productBigTypeService.findAllBigTypeList();
		//把获取到商品大类放到application中（服务器共享空间）
		application.put("bigTypeList", bigTypeList); 
		
		//获取标签,把标签加入缓存中
		List<Tag> tagList = tagService.findTagList(null,null);
		application.put("tagList", tagList);
		
		//获取最新公告,把公告加入缓存中
		List<Notice> noticeList= noticeService.findNoticeList(null, new PageBean(1, 7));
		application.put("noticeList", noticeList);
		
		//获取最新新闻,把新闻加入缓存中
		List<News> newsList = newsService.findNewsList(null, new PageBean(1, 7));
		application.put("newsList", newsList);
		
		//获取今日特价商品,把今日特价商品加入缓存中
		Product specialPriceProduct = new Product();
		specialPriceProduct.setSpecialPrice(1);
		List<Product> specialPriceProductList= productService.findProductList(specialPriceProduct, new PageBean(1, 8));
		application.put("specialPriceProductList", specialPriceProductList);
		
		//获取热卖推荐商品,把热卖推荐商品加入缓存中
		Product hotProduct = new Product();
		hotProduct.setHot(1);
		List<Product> hotProductList = productService.findProductList(hotProduct, new PageBean(1, 6));
		application.put("hotProductList", hotProductList);
		JSONObject result = new JSONObject();
		result.put("success", true);
		ResponseUtil.write(ServletActionContext.getResponse(), result);
		return null;
	}
}
