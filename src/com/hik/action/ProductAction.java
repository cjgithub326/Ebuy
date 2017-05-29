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
 * @date 2017年3月16日上午12:03:56
 *
 */
@Controller
public class ProductAction extends ActionSupport implements ServletRequestAware{

	/**
	 * 序列号
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
		navCode = NavUtil.genNavCode("商品列表");
		mainPage = "product/productList.jsp";
		
		return super.execute();
	}
	
	public String showProduct(){
		productDetail= productService.getProductbyId(productId);
		saveCurrentBrowse(productDetail);
		navCode = NavUtil.genNavCode("商品详情");
		mainPage = "product/productDetails.jsp";
		return SUCCESS;
	}
	
	/**
	 * 
	 * @MethodName: saveCurrentBrowse
	 * @Description: 把商品存入session中 
	 * @author jed
	 * @date 2017年3月19日下午10:08:17
	 * @param @param product    
	 * @return void    返回类型
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
		//如果缓存session中存在则不添加，反之新增加的添加。
		for(Product p:currentBrowseProduct){
			if(p.getId()==product.getId()){
				flag = false;
				break;
			}
		}
		//集合中从第一个位置开始添加
		if(flag){
			currentBrowseProduct.add(0, product);
		}
		//如果超过4个，则把最后的一个移出去。
		if(currentBrowseProduct.size()==5){
			currentBrowseProduct.remove(4);
		}
		//存入session中 
		session.setAttribute("currentBrowse", currentBrowseProduct);
	}
	
	/**
	 * 
	 * @MethodName: list
	 * @Description: 获取商品
	 * @author jed
	 * @date 2017年5月7日下午5:23:23
	 * @param @return    
	 * @return String    返回类型
	 * @return
	 * @throws Exception 
	 *
	 */
	public String list() throws Exception{
		PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
		List<Product> productList = productService.findProductList(productDetail, pageBean);
		long total = productService.getProductCount(productDetail);
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setExcludes(new String[]{"orderProductList"}); //去除要排除的属性   前台不展示
		jsonConfig.registerJsonValueProcessor(java.util.Date.class, new DateJsonValueProcessor("yyyy-MM-dd")); //日期处理
		jsonConfig.registerJsonValueProcessor(ProductBigType.class, new ObjectJsonValueProcessor(new String[]{"id","name"}, ProductBigType.class));//商品大类的id和name
		jsonConfig.registerJsonValueProcessor(ProductSmallType.class, new ObjectJsonValueProcessor(new String[]{"id","name"},ProductSmallType.class )); //商品小类的id和name
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
	 * @Description: 保存商品
	 * @author jed
	 * @date 2017年5月16日下午9:49:14
	 * @param @return    
	 * @return String    返回类型
	 * @return
	 * @throws Exception 
	 *
	 */
	public String save() throws Exception{
		if(proPic!=null){//文件不为空，把文件从struts内存拷贝到本地，同时把文件路径存放到数据库
			String imageName = DateUtil.getCurrentDateStr(); //获取文件名
			String realPath = ServletActionContext.getServletContext().getRealPath("/images/product"); //图片存放绝对路径
			String imageFile = imageName + "."+proPicFileName.split("\\.")[1]; //对图片重命名
			File saveFile = new File(realPath, imageFile); //图片保存路径
			FileUtils.copyFile(proPic, saveFile); //把文件从struts内存拷贝到本地
			product.setProPic("images/product/"+imageFile); //把文件路径存放到数据库
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
	 * @Description: 删除商品
	 * @author jed
	 * @date 2017年5月18日下午8:49:22
	 * @param @return    
	 * @return String    返回类型
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
	 * @Description: 设置为热卖商品 
	 * @author jed
	 * @date 2017年5月18日下午8:56:12
	 * @param @return    
	 * @return String    返回类型
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
	 * @Description: 设置为特价商品
	 * @author jed
	 * @date 2017年5月18日下午8:57:04
	 * @param @return    
	 * @return String    返回类型
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
