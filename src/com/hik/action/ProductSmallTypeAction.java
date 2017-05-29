/**
 * 
 */
package com.hik.action;

import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Controller;

import com.hik.entity.PageBean;
import com.hik.entity.ProductBigType;
import com.hik.entity.ProductSmallType;
import com.hik.service.ProductService;
import com.hik.service.ProductSmallTypeService;
import com.hik.util.ResponseUtil;
import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

/**
 * @ClassName: ProductSmallTypeAction
 * @Description: TODO
 * @author jed
 * @date 2017��5��13������8:14:38
 *
 */
@Controller
public class ProductSmallTypeAction extends ActionSupport{

	/**
	 * ���к�
	 */
	private static final long serialVersionUID = 1L;
	
	private String page;
	
	private String rows;
	
	private String ids;
	
	private ProductSmallType s_productSmallType;
	
	private ProductSmallType productSmallType;
	
	@Resource
	private ProductSmallTypeService productSmallTypeService;
	
	@Resource
	private ProductService productService;
	
	/**
	 * 
	 * @MethodName: comboList
	 * @Description: ��ȡ��������ƷС������
	 * @author jed
	 * @date 2017��5��13������8:16:39
	 * @param @return    
	 * @return String    ��������
	 * @return
	 * @throws Exception 
	 *
	 */
	public String comboList() throws Exception{
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("id", "");
		jsonObject.put("name", "��ѡ��...");
		jsonArray.add(jsonObject);
		List<ProductSmallType> productSmallTypeList = productSmallTypeService.findProductSmallTypeList(productSmallType,null);
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setExcludes(new String[]{"bigType","productList"});
		JSONArray rows = JSONArray.fromObject(productSmallTypeList, jsonConfig);
		jsonArray.addAll(rows);
		ResponseUtil.write(ServletActionContext.getResponse(), jsonArray);
		return null;
	}


	/**
	 * 
	 * @MethodName: list
	 * @Description: ��ҳ��ѯ��ƷС��
	 * @author jed
	 * @date 2017��5��19������9:49:01
	 * @param @return    
	 * @return String    ��������
	 * @return
	 * @throws Exception 
	 *
	 */
	public String list() throws Exception{
		PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
		List<ProductSmallType> productSmallTypeList = productSmallTypeService.findProductSmallTypeList(productSmallType, pageBean);
		long total = productSmallTypeService.getProductSmallTypeCount(productSmallType);
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setExcludes(new String[]{"productList"});
		jsonConfig.registerJsonValueProcessor(ProductBigType.class, new ObjectJsonValueProcessor(new String[]{"id","name"},ProductBigType.class));
		JSONArray rows = JSONArray.fromObject(productSmallTypeList, jsonConfig);
		JSONObject result = new JSONObject();
		result.put("rows", rows);
		result.put("total", total);
		ResponseUtil.write(ServletActionContext.getResponse(), result);
		return null;
	}

	/**
	 * 
	 * @MethodName: save
	 * @Description: ������ƷС��
	 * @author jed
	 * @date 2017��5��21������9:37:15
	 * @param @return    
	 * @return String    ��������
	 * @return
	 * @throws Exception 
	 *
	 */
	public String save() throws Exception{
		productSmallTypeService.saveProductSmallType(s_productSmallType);
		JSONObject result = new JSONObject();
		result.put("success", true);
		ResponseUtil.write(ServletActionContext.getResponse(), result);
		return null;
	}
	
	/**
	 * 
	 * @MethodName: delete
	 * @Description: ɾ����ƷС��
	 * @author jed
	 * @date 2017��5��21������9:39:51
	 * @param @return    
	 * @return String    ��������
	 * @return
	 * @throws Exception 
	 *
	 */
	public String delete() throws Exception{
		JSONObject result = new JSONObject();
		String[] idsStr = ids.split(",");
		for (int i = 0; i < idsStr.length; i++) { // ��ƷС���Ƿ������Ʒ
			if (productService.existProductWithSmallTypeId(Integer.parseInt(idsStr[i]))) {
				result.put("exist", "��ƷС�������Ʒ");
			} else {
				ProductSmallType productSmallType = productSmallTypeService
						.getProductSmallTypeById(Integer.parseInt(idsStr[i]));
				if (productSmallType != null) {
					productSmallTypeService.deleteProductSmallType(productSmallType);
				}
			}
		}
		result.put("success", true);
		ResponseUtil.write(ServletActionContext.getResponse(), result);
		return null;
	}

	public ProductSmallType getProductSmallType() {
		return productSmallType;
	}

	public void setProductSmallType(ProductSmallType productSmallType) {
		this.productSmallType = productSmallType;
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

	public ProductSmallType getS_productSmallType() {
		return s_productSmallType;
	}

	public void setS_productSmallType(ProductSmallType s_productSmallType) {
		this.s_productSmallType = s_productSmallType;
	}
	
}
