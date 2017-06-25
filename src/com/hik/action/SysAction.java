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
 * @date 2017��6��25������10:40:31
 *
 */
@Controller
public class SysAction extends ActionSupport implements ApplicationAware{

	/**
	 * ���к�
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
	 * @Description: ˢ��ϵͳ����
	 * @author jed
	 * @date 2017��6��25������10:46:05
	 * @param @return    
	 * @return String    ��������
	 * @return
	 * @throws Exception 
	 *
	 */
	public String refreshSystem() throws Exception{
		List<ProductBigType> bigTypeList = productBigTypeService.findAllBigTypeList();
		//�ѻ�ȡ����Ʒ����ŵ�application�У�����������ռ䣩
		application.put("bigTypeList", bigTypeList); 
		
		//��ȡ��ǩ,�ѱ�ǩ���뻺����
		List<Tag> tagList = tagService.findTagList(null,null);
		application.put("tagList", tagList);
		
		//��ȡ���¹���,�ѹ�����뻺����
		List<Notice> noticeList= noticeService.findNoticeList(null, new PageBean(1, 7));
		application.put("noticeList", noticeList);
		
		//��ȡ��������,�����ż��뻺����
		List<News> newsList = newsService.findNewsList(null, new PageBean(1, 7));
		application.put("newsList", newsList);
		
		//��ȡ�����ؼ���Ʒ,�ѽ����ؼ���Ʒ���뻺����
		Product specialPriceProduct = new Product();
		specialPriceProduct.setSpecialPrice(1);
		List<Product> specialPriceProductList= productService.findProductList(specialPriceProduct, new PageBean(1, 8));
		application.put("specialPriceProductList", specialPriceProductList);
		
		//��ȡ�����Ƽ���Ʒ,�������Ƽ���Ʒ���뻺����
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
