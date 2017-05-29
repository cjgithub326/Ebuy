/**
 * 
 */
package com.hik.action;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

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

/**
 * @ClassName: InitAction
 * @Description: TODO
 * @author jed
 * @date 2017年3月2日下午10:20:34
 *
 */
@Component
public class InitAction implements ServletContextListener,ApplicationContextAware{
	
	//为了方便使用spring上下文,把applicationContext定义为static变量
	private static ApplicationContext applicationContext;

	/* (non-Javadoc)
	 * @see javax.servlet.ServletContextListener#contextDestroyed(javax.servlet.ServletContextEvent)
	 * 当Servlet容器终止Web应用时调用该方法。在调用该方法之前，容器会先销毁所有的Servlet和Filter过滤器。
	 */
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see javax.servlet.ServletContextListener#contextInitialized(javax.servlet.ServletContextEvent)
	 * 当Servlet容器启动Web应用时调用该方法。在调用完该方法之后，容器再对Filter初始化,
	 * 并且对那些在Web应用启动时就需要被初始化的Servlet进行初始化。
	 */
	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		ServletContext application = servletContextEvent.getServletContext();
		//ApplicationContext的getBean方法来获取Spring容器中已初始化的bean，容器启动已加载applicationContext.xml注册所有spring管理的bean。
		//当然也可以通过直接读applicationContext.xml文件方式来获取applicationContext上下文
		//ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		ProductBigTypeService productBigTypeService = (ProductBigTypeService) applicationContext.getBean("productBigTypeService");
		List<ProductBigType> bigTypeList = productBigTypeService.findAllBigTypeList();
		//把获取到商品大类放到application中（服务器共享空间）
		application.setAttribute("bigTypeList", bigTypeList); 
		
		//获取标签,把标签加入缓存中
		TagService tagService= (TagService) applicationContext.getBean("tagService");
		List<Tag> tagList = tagService.findTagList();
		application.setAttribute("tagList", tagList);
		
		//获取最新公告,把公告加入缓存中
		NoticeService noticeService = (NoticeService) applicationContext.getBean("noticeService");
		List<Notice> noticeList= noticeService.findNoticeList(null, new PageBean(1, 7));
		application.setAttribute("noticeList", noticeList);
		
		//获取最新新闻,把新闻加入缓存中
		NewsService newsService = (NewsService) applicationContext.getBean("newsService");
		List<News> newsList = newsService.findNewsList(null, new PageBean(1, 7));
		application.setAttribute("newsList", newsList);
		
		//获取今日特价商品,把今日特价商品加入缓存中
		ProductService productService= (ProductService) applicationContext.getBean("productService");
		Product specialPriceProduct = new Product();
		specialPriceProduct.setSpecialPrice(1);
		List<Product> specialPriceProductList= productService.findProductList(specialPriceProduct, new PageBean(1, 8));
		application.setAttribute("specialPriceProductList", specialPriceProductList);
		
		//获取热卖推荐商品,把热卖推荐商品加入缓存中
		Product hotProduct = new Product();
		hotProduct.setHot(1);
		List<Product> hotProductList = productService.findProductList(hotProduct, new PageBean(1, 6));
		application.setAttribute("hotProductList", hotProductList);
		
	}

	/* (non-Javadoc)
	 * @see org.springframework.context.ApplicationContextAware#setApplicationContext(org.springframework.context.ApplicationContext)
	 * 实现ApplicationContextAware接口可获取到applicationContext。
	 */
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext; 
	}

}
