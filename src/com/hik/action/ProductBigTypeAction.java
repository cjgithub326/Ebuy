/**
 * 
 */
package com.hik.action;

import java.util.List;

import javax.annotation.Resource;
import javax.management.loading.PrivateClassLoader;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Controller;

import com.hik.entity.PageBean;
import com.hik.entity.ProductBigType;
import com.hik.service.ProductBigTypeService;
import com.hik.service.ProductSmallTypeService;
import com.hik.util.ResponseUtil;
import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

/**
 * @ClassName: ProductBigTypeAction
 * @Description: TODO
 * @author jed
 * @date 2017年5月13日上午7:56:32
 *
 */
@Controller
public class ProductBigTypeAction extends ActionSupport{

	/**
	 * 序列号
	 */
	private static final long serialVersionUID = 1L;
	
	private String page;
	
	private String rows;
	
	private ProductBigType productBigType;
	
	private String ids;
	
	private ProductBigType s_productBigType;
	
	@Resource
	private ProductBigTypeService productBigTypeService;
	
	@Resource
	private ProductSmallTypeService productSmallTypeService;

	/**
	 * 
	 * @MethodName: comboList
	 * @Description: 获取下拉框商品大类数据
	 * @author jed
	 * @date 2017年5月13日上午7:59:02
	 * @param @return    
	 * @return String    返回类型
	 * @return
	 * @throws Exception 
	 *
	 */
	public String comboList() throws Exception{
		JSONArray jsonArray = new JSONArray(); //json数组，存放多个jsonObject
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("id", "");
		jsonObject.put("name", "请选择..."); //没数据默认为请选择
		jsonArray.add(jsonObject);
		List<ProductBigType> productBigTypeList= productBigTypeService.findAllBigTypeList();
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setExcludes(new String[]{"productList","productSmallList"});
		JSONArray rows = JSONArray.fromObject(productBigTypeList, jsonConfig);
		jsonArray.addAll(rows);
		ResponseUtil.write(ServletActionContext.getResponse(), jsonArray);
		return null;
	}
	
	/**
	 * 
	 * @MethodName: list
	 * @Description: 分页查询商品大类
	 * @author jed
	 * @date 2017年5月19日下午9:49:01
	 * @param @return    
	 * @return String    返回类型
	 * @return
	 * @throws Exception 
	 *
	 */
	public String list() throws Exception{
		PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
		List<ProductBigType> productBigTypeList = productBigTypeService.findProductBigTypeList(productBigType, pageBean);
		long total = productBigTypeService.getProductBigTypeCount(productBigType);
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setExcludes(new String[]{"productList","productSmallList"});
		JSONArray rows = JSONArray.fromObject(productBigTypeList, jsonConfig);
		JSONObject result = new JSONObject();
		result.put("rows", rows);
		result.put("total", total);
		ResponseUtil.write(ServletActionContext.getResponse(), result);
		return null;
	}

	/**
	 * 
	 * @MethodName: save
	 * @Description: 保存商品大类
	 * @author jed
	 * @date 2017年5月21日上午9:37:15
	 * @param @return    
	 * @return String    返回类型
	 * @return
	 * @throws Exception 
	 *
	 */
	public String save() throws Exception{
		productBigTypeService.saveProductBigType(s_productBigType);
		JSONObject result = new JSONObject();
		result.put("success", true);
		ResponseUtil.write(ServletActionContext.getResponse(), result);
		return null;
	}
	
	/**
	 * 
	 * @MethodName: delete
	 * @Description: 删除商品大类
	 * @author jed
	 * @date 2017年5月21日上午9:39:51
	 * @param @return    
	 * @return String    返回类型
	 * @return
	 * @throws Exception 
	 *
	 */
	public String delete() throws Exception{
		JSONObject result = new JSONObject();
		String[] idsStr = ids.split(",");
		for (int i = 0; i < idsStr.length; i++) { // 商品大类存在商品小类
			if (productSmallTypeService.existSmallTypeWithBigTypeId(Integer.parseInt(idsStr[i]))) {
				result.put("exist", "商品大类包含商品小类");
			} else {
				ProductBigType productBigType = productBigTypeService
						.getProductBigTypeById(Integer.parseInt(idsStr[i]));
				if (productBigType != null) {
					productBigTypeService.deleteProductBigType(productBigType);
				}
			}
		}
		result.put("success", true);
		ResponseUtil.write(ServletActionContext.getResponse(), result);
		return null;
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

	public ProductBigType getProductBigType() {
		return productBigType;
	}

	public void setProductBigType(ProductBigType productBigType) {
		this.productBigType = productBigType;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public ProductBigType getS_productBigType() {
		return s_productBigType;
	}

	public void setS_productBigType(ProductBigType s_productBigType) {
		this.s_productBigType = s_productBigType;
	}
	
	
}
