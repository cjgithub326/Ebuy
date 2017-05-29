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
 * @date 2017��5��13������7:56:32
 *
 */
@Controller
public class ProductBigTypeAction extends ActionSupport{

	/**
	 * ���к�
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
	 * @Description: ��ȡ��������Ʒ��������
	 * @author jed
	 * @date 2017��5��13������7:59:02
	 * @param @return    
	 * @return String    ��������
	 * @return
	 * @throws Exception 
	 *
	 */
	public String comboList() throws Exception{
		JSONArray jsonArray = new JSONArray(); //json���飬��Ŷ��jsonObject
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("id", "");
		jsonObject.put("name", "��ѡ��..."); //û����Ĭ��Ϊ��ѡ��
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
	 * @Description: ��ҳ��ѯ��Ʒ����
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
	 * @Description: ������Ʒ����
	 * @author jed
	 * @date 2017��5��21������9:37:15
	 * @param @return    
	 * @return String    ��������
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
	 * @Description: ɾ����Ʒ����
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
		for (int i = 0; i < idsStr.length; i++) { // ��Ʒ���������ƷС��
			if (productSmallTypeService.existSmallTypeWithBigTypeId(Integer.parseInt(idsStr[i]))) {
				result.put("exist", "��Ʒ���������ƷС��");
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
