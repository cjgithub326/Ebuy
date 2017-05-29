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
 * @date 2017��3��2������10:20:34
 *
 */
@Component
public class InitAction implements ServletContextListener,ApplicationContextAware{
	
	//Ϊ�˷���ʹ��spring������,��applicationContext����Ϊstatic����
	private static ApplicationContext applicationContext;

	/* (non-Javadoc)
	 * @see javax.servlet.ServletContextListener#contextDestroyed(javax.servlet.ServletContextEvent)
	 * ��Servlet������ֹWebӦ��ʱ���ø÷������ڵ��ø÷���֮ǰ�����������������е�Servlet��Filter��������
	 */
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see javax.servlet.ServletContextListener#contextInitialized(javax.servlet.ServletContextEvent)
	 * ��Servlet��������WebӦ��ʱ���ø÷������ڵ�����÷���֮�������ٶ�Filter��ʼ��,
	 * ���Ҷ���Щ��WebӦ������ʱ����Ҫ����ʼ����Servlet���г�ʼ����
	 */
	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		ServletContext application = servletContextEvent.getServletContext();
		//ApplicationContext��getBean��������ȡSpring�������ѳ�ʼ����bean�����������Ѽ���applicationContext.xmlע������spring�����bean��
		//��ȻҲ����ͨ��ֱ�Ӷ�applicationContext.xml�ļ���ʽ����ȡapplicationContext������
		//ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		ProductBigTypeService productBigTypeService = (ProductBigTypeService) applicationContext.getBean("productBigTypeService");
		List<ProductBigType> bigTypeList = productBigTypeService.findAllBigTypeList();
		//�ѻ�ȡ����Ʒ����ŵ�application�У�����������ռ䣩
		application.setAttribute("bigTypeList", bigTypeList); 
		
		//��ȡ��ǩ,�ѱ�ǩ���뻺����
		TagService tagService= (TagService) applicationContext.getBean("tagService");
		List<Tag> tagList = tagService.findTagList();
		application.setAttribute("tagList", tagList);
		
		//��ȡ���¹���,�ѹ�����뻺����
		NoticeService noticeService = (NoticeService) applicationContext.getBean("noticeService");
		List<Notice> noticeList= noticeService.findNoticeList(null, new PageBean(1, 7));
		application.setAttribute("noticeList", noticeList);
		
		//��ȡ��������,�����ż��뻺����
		NewsService newsService = (NewsService) applicationContext.getBean("newsService");
		List<News> newsList = newsService.findNewsList(null, new PageBean(1, 7));
		application.setAttribute("newsList", newsList);
		
		//��ȡ�����ؼ���Ʒ,�ѽ����ؼ���Ʒ���뻺����
		ProductService productService= (ProductService) applicationContext.getBean("productService");
		Product specialPriceProduct = new Product();
		specialPriceProduct.setSpecialPrice(1);
		List<Product> specialPriceProductList= productService.findProductList(specialPriceProduct, new PageBean(1, 8));
		application.setAttribute("specialPriceProductList", specialPriceProductList);
		
		//��ȡ�����Ƽ���Ʒ,�������Ƽ���Ʒ���뻺����
		Product hotProduct = new Product();
		hotProduct.setHot(1);
		List<Product> hotProductList = productService.findProductList(hotProduct, new PageBean(1, 6));
		application.setAttribute("hotProductList", hotProductList);
		
	}

	/* (non-Javadoc)
	 * @see org.springframework.context.ApplicationContextAware#setApplicationContext(org.springframework.context.ApplicationContext)
	 * ʵ��ApplicationContextAware�ӿڿɻ�ȡ��applicationContext��
	 */
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext; 
	}

}
