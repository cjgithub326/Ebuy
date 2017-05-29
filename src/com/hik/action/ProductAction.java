/**
 * 
 */
package com.hik.action;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.hibernate.sql.Delete;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.ServletContextAware;

import com.hik.entity.PageBean;
import com.hik.entity.Product;
import com.hik.entity.ProductBigType;
import com.hik.entity.ProductSmallType;
import com.hik.service.ProductService;
import com.hik.util.DateUtil;
import com.hik.util.NavUtil;
import com.hik.util.PageUtil;
import com.hik.util.ResponseUtil;
import com.hik.util.StringUtil;
import com.opensymphony.xwork2.ActionSupport;
import com.sun.net.httpserver.Authenticator.Success;

import freemarker.ext.beans.BeansWrapper;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

/**
 * @ClassName: ProductAction
 * @Description: TODO
 * @author jed
 * @date 2017��3��16������12:03:56
 *
 */
@Controller
public class ProductAction extends ActionSupport implements ServletRequestAware{

	/**
	 * ���к�
	 */
	private static final long serialVersionUID = 1L;

	@Resource
	private ProductService productService;
	
	private List<Product> productList;
	
	private Product product;
	
	private String page;
	
	private String rows;
	
	private long total; 
	
	private String pageCode;
	
	private String navCode;
	
	private String mainPage;
	
	private int productId;
	
	private Product productDetail;
	
	private HttpServletRequest request;
	
	private File proPic;
	
	private String proPicFileName;
	
	private String ids;
	
	@Override
	public String execute() throws Exception {
		if(StringUtil.isEmpty(page)){
			page="1";
		}
		
		PageBean pageBean = new PageBean(Integer.parseInt(page), 8);
		productList = productService.findProductList(product, pageBean);
		total = productService.getProductCount(product);
		StringBuffer param = new StringBuffer();
		if(product!=null){
			if(product.getBigType()!=null){
				param.append("product.bigType.id="+product.getBigType().getId());
			}
			if(product.getSmallType()!=null){
				param.append("product.smallType.id="+product.getSmallType().getId());
			}
			if(StringUtil.isNotEmpty(product.getName())){
				param.append("product.name ="+product.getName());
			}
		}
		
		pageCode = PageUtil.genPagination(request.getContextPath()+"/product.action", total, Integer.parseInt(page), 8, param.toString());
		navCode = NavUtil.genNavCode("��Ʒ�б�");
		mainPage = "product/productList.jsp";
		
		return super.execute();
	}
	
	public String showProduct(){
		productDetail= productService.getProductbyId(productId);
		saveCurrentBrowse(productDetail);
		navCode = NavUtil.genNavCode("��Ʒ����");
		mainPage = "product/productDetails.jsp";
		return SUCCESS;
	}
	
	/**
	 * 
	 * @MethodName: saveCurrentBrowse
	 * @Description: ����Ʒ����session�� 
	 * @author jed
	 * @date 2017��3��19������10:08:17
	 * @param @param product    
	 * @return void    ��������
	 * @param product
	 *
	 */
	public void saveCurrentBrowse(Product product){
		HttpSession session = request.getSession();
		List<Product> currentBrowseProduct= (List<Product>) session.getAttribute("currentBrowse");
		if(currentBrowseProduct==null){
			currentBrowseProduct = new LinkedList<Product>();
		}
		boolean flag = true;
		//�������session�д�������ӣ���֮�����ӵ���ӡ�
		for(Product p:currentBrowseProduct){
			if(p.getId()==product.getId()){
				flag = false;
				break;
			}
		}
		//�����дӵ�һ��λ�ÿ�ʼ���
		if(flag){
			currentBrowseProduct.add(0, product);
		}
		//�������4�����������һ���Ƴ�ȥ��
		if(currentBrowseProduct.size()==5){
			currentBrowseProduct.remove(4);
		}
		//����session�� 
		session.setAttribute("currentBrowse", currentBrowseProduct);
	}
	
	/**
	 * 
	 * @MethodName: list
	 * @Description: ��ȡ��Ʒ
	 * @author jed
	 * @date 2017��5��7������5:23:23
	 * @param @return    
	 * @return String    ��������
	 * @return
	 * @throws Exception 
	 *
	 */
	public String list() throws Exception{
		PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
		List<Product> productList = productService.findProductList(productDetail, pageBean);
		long total = productService.getProductCount(productDetail);
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setExcludes(new String[]{"orderProductList"}); //ȥ��Ҫ�ų�������   ǰ̨��չʾ
		jsonConfig.registerJsonValueProcessor(java.util.Date.class, new DateJsonValueProcessor("yyyy-MM-dd")); //���ڴ���
		jsonConfig.registerJsonValueProcessor(ProductBigType.class, new ObjectJsonValueProcessor(new String[]{"id","name"}, ProductBigType.class));//��Ʒ�����id��name
		jsonConfig.registerJsonValueProcessor(ProductSmallType.class, new ObjectJsonValueProcessor(new String[]{"id","name"},ProductSmallType.class )); //��ƷС���id��name
		JSONArray rows = JSONArray.fromObject(productList, jsonConfig);
		JSONObject result = new JSONObject();
		result.put("rows", rows);
		result.put("total", total);
		ResponseUtil.write(ServletActionContext.getResponse(), result);
		return null;
	}
	
	/**
	 * 
	 * @MethodName: save
	 * @Description: ������Ʒ
	 * @author jed
	 * @date 2017��5��16������9:49:14
	 * @param @return    
	 * @return String    ��������
	 * @return
	 * @throws Exception 
	 *
	 */
	public String save() throws Exception{
		if(proPic!=null){//�ļ���Ϊ�գ����ļ���struts�ڴ濽�������أ�ͬʱ���ļ�·����ŵ����ݿ�
			String imageName = DateUtil.getCurrentDateStr(); //��ȡ�ļ���
			String realPath = ServletActionContext.getServletContext().getRealPath("/images/product"); //ͼƬ��ž���·��
			String imageFile = imageName + "."+proPicFileName.split("\\.")[1]; //��ͼƬ������
			File saveFile = new File(realPath, imageFile); //ͼƬ����·��
			FileUtils.copyFile(proPic, saveFile); //���ļ���struts�ڴ濽��������
			product.setProPic("images/product/"+imageFile); //���ļ�·����ŵ����ݿ�
		}else if(StringUtil.isEmpty(product.getProPic())){
			product.setProPic("");
		}
		productService.saveProduct(product);
		JSONObject result = new JSONObject();
		result.put("success", true);
		ResponseUtil.write(ServletActionContext.getResponse(), result);
		return null;
	}
	
	/**
	 * 
	 * @MethodName: Delete
	 * @Description: ɾ����Ʒ
	 * @author jed
	 * @date 2017��5��18������8:49:22
	 * @param @return    
	 * @return String    ��������
	 * @return
	 * @throws Exception 
	 *
	 */
	public String delete() throws Exception{
		JSONObject result = new JSONObject();
		String []idsStr = ids.split(",");
		for(int i=0;i<idsStr.length;i++){
			Product product = productService.getProductbyId(Integer.parseInt(idsStr[i]));
			if(product!=null){
				productService.deleteProduct(product);
			}
		}
		result.put("success", true);
		ResponseUtil.write(ServletActionContext.getResponse(), result);
		return null;
	}
	
	/**
	 * 
	 * @MethodName: setProductWithHot
	 * @Description: ����Ϊ������Ʒ 
	 * @author jed
	 * @date 2017��5��18������8:56:12
	 * @param @return    
	 * @return String    ��������
	 * @return
	 * @throws Exception 
	 *
	 */
	public String setProductWithHot() throws Exception{
		JSONObject result = new JSONObject();
		String[] idsStr = ids.split(",");
		for(int i=0;i<idsStr.length;i++){
			productService.setProductWithHot(Integer.parseInt(idsStr[i]));
		}
		result.put("success", true);
		ResponseUtil.write(ServletActionContext.getResponse(), result);
		return null;
	}
	
	/**
	 * 
	 * @MethodName: setProductWithSpecialPrice
	 * @Description: ����Ϊ�ؼ���Ʒ
	 * @author jed
	 * @date 2017��5��18������8:57:04
	 * @param @return    
	 * @return String    ��������
	 * @return
	 * @throws Exception 
	 *
	 */
	public String setProductWithSpecialPrice() throws Exception{
		JSONObject result = new JSONObject();
		String[] idsStr = ids.split(",");
		for(int i=0;i<idsStr.length;i++){
			productService.setProductWithSpecialPrice(Integer.parseInt(idsStr[i]));
		}
		result.put("success", true);
		ResponseUtil.write(ServletActionContext.getResponse(), result);
		return null;
	}
	
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public List<Product> getProductList() {
		return productList;
	}

	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public String getPageCode() {
		return pageCode;
	}

	public void setPageCode(String pageCode) {
		this.pageCode = pageCode;
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

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public Product getProductDetail() {
		return productDetail;
	}

	public void setProductDetail(Product productDetail) {
		this.productDetail = productDetail;
	}

	public String getRows() {
		return rows;
	}

	public void setRows(String rows) {
		this.rows = rows;
	}

	public File getProPic() {
		return proPic;
	}

	public void setProPic(File proPic) {
		this.proPic = proPic;
	}

	public String getProPicFileName() {
		return proPicFileName;
	}

	public void setProPicFileName(String proPicFileName) {
		this.proPicFileName = proPicFileName;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}


}
