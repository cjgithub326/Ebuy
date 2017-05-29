/**
 * 
 */
package com.hik.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.stereotype.Controller;

import com.hik.entity.Product;
import com.hik.entity.ShoppingCart;
import com.hik.entity.ShoppingCartItem;
import com.hik.entity.User;
import com.hik.service.ProductService;
import com.hik.util.NavUtil;
import com.hik.util.ResponseUtil;
import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSONObject;
import net.sf.json.processors.JsDateJsonBeanProcessor;

/**
 * @ClassName: ShoppingAction
 * @Description: TODO
 * @author jed
 * @date 2017��4��2������10:56:48
 *
 */
@Controller
public class ShoppingAction extends ActionSupport implements ServletRequestAware{

	/**
	 * ���л�
	 */
	private static final long serialVersionUID = 1L;
	
	private HttpServletRequest request;
	
	private int productId;
	
	private String mainPage;
	
	private String navCode;
	
	private int count;
	
	@Resource
	private ProductService productService;

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	
	/**
	 * 
	 * @MethodName: addShoppingCartItem
	 * @Description: �����Ʒ�����ﳵ
	 * @author jed
	 * @date 2017��4��2������11:01:41
	 * @param @return    
	 * @return String    ��������
	 * @return
	 *
	 */
	public String addShoppingCartItem() throws Exception{
		HttpSession session = request.getSession();
		Product product = productService.getProductbyId(productId);
		//��session�в�ѯ���ﳵ
		ShoppingCart shoppingCart = (ShoppingCart) session.getAttribute("shoppingCart");
		if(shoppingCart==null){//��һ�����,���Ұ��û�
			shoppingCart = new ShoppingCart();
			User currentUser = (User) session.getAttribute("currentUser");
			if(currentUser!=null){
				shoppingCart.setUserId(currentUser.getId());
			}
		}
		
		List<ShoppingCartItem> shoppingCartItemList = shoppingCart.getShoppingCartItems();//���ﳵ����Ʒ��Ŀ
		boolean addshoppingflag = true;
		for(ShoppingCartItem sci:shoppingCartItemList){
			//������ﳵ�д��ڸ���Ʒ�����,����Ʒ��Ŀ��1
			if(product.getId()==sci.getProduct().getId()){
				sci.setCount(sci.getCount()+1);
				addshoppingflag = false;
				break;
			}
		}
		ShoppingCartItem shoppingCartItem = new ShoppingCartItem();
		
		if(addshoppingflag){//���ﳵ�����δ��ӹ�����Ʒ
			shoppingCartItem.setProduct(product);
			shoppingCartItem.setCount(1);
			shoppingCartItemList.add(shoppingCartItem);
		}
		
		session.setAttribute("shoppingCart", shoppingCart);  //���ﳵ����session��
		
		//ǰ̨ajax���󷵻�json�ַ�������
		JSONObject result = new JSONObject();
		result.put("success", true);
		ResponseUtil.write(ServletActionContext.getResponse(), result);
		//ajax���󷵻�null
		return null;
	}
	
	/**
	 * 
	 * @MethodName: buyShopping
	 * @Description: ������Ʒ
	 * @author jed
	 * @date 2017��4��2������10:25:59
	 * @param @return    
	 * @return String    ��������
	 * @return
	 *
	 */
	public String buyShopping(){
		HttpSession session = request.getSession();
		Product product = productService.getProductbyId(productId);
		//��session�в�ѯ���ﳵ
		ShoppingCart shoppingCart = (ShoppingCart) session.getAttribute("shoppingCart");
		if(shoppingCart==null){//��һ�����,���Ұ��û�
			shoppingCart = new ShoppingCart();
			User currentUser = (User) session.getAttribute("currentUser");
			if(currentUser!=null){
				shoppingCart.setUserId(currentUser.getId());
			}
		}
		
		List<ShoppingCartItem> shoppingCartItemList = shoppingCart.getShoppingCartItems();//���ﳵ����Ʒ��Ŀ
		boolean addshoppingflag = true;
		for(ShoppingCartItem sci:shoppingCartItemList){
			//������ﳵ�д��ڸ���Ʒ�����,����Ʒ��Ŀ��1
			if(product.getId()==sci.getProduct().getId()){
				sci.setCount(sci.getCount()+1);
				addshoppingflag = false;
				break;
			}
		}
		ShoppingCartItem shoppingCartItem = new ShoppingCartItem();
		
		if(addshoppingflag){//���ﳵ�����δ��ӹ�����Ʒ
			shoppingCartItem.setProduct(product);
			shoppingCartItem.setCount(1);
			shoppingCartItemList.add(shoppingCartItem);
		}
		
		session.setAttribute("shoppingCart", shoppingCart);  //���ﳵ����session��
		mainPage ="shopping/shopping.jsp";
		navCode =NavUtil.genNavCode("���ﳵ");
		return SUCCESS;
	}
	
	/**
	 * 
	 * @MethodName: updateShoppingCartItem
	 * @Description: ���¹��ﳵ��Ŀ����
	 * @author jed
	 * @date 2017��4��3������6:47:07
	 * @param @return    
	 * @return String    ��������
	 * @return
	 * @throws Exception 
	 *
	 */
	public String updateShoppingCartItem() throws Exception{
		HttpSession session = request.getSession();
		Product product = productService.getProductbyId(productId);
		ShoppingCart shoppingCart = (ShoppingCart) session.getAttribute("shoppingCart");
		List<ShoppingCartItem> shoppingCartItemList = shoppingCart.getShoppingCartItems();//���ﳵ����Ʒ��Ŀ
		for(ShoppingCartItem sci:shoppingCartItemList){
			if(product.getId()==sci.getProduct().getId()){//ǰ̨��������Ʒid�͹��ﳵ����Ʒid��ȣ����������������������Ʒ��Ŀcount
				sci.setCount(count);
				break;
			}
		}
		
		//ǰ̨ajax���󷵻�json�ַ�������
		session.setAttribute("shoppingCart", shoppingCart);
		JSONObject result = new JSONObject();
		result.put("success", true);
		ResponseUtil.write(ServletActionContext.getResponse(), result);
		//ajax���󷵻�null
		return null;
	}
	
	/**
	 * 
	 * @MethodName: removeShoppingCartItem
	 * @Description: ɾ�����ﳵ��Ʒ
	 * @author jed
	 * @date 2017��4��3������7:14:28
	 * @param @return    
	 * @return String    ��������
	 * @return
	 *
	 */
	public String removeShoppingCartItem() {
		HttpSession session = request.getSession();
		ShoppingCart shoppingCart = (ShoppingCart) session.getAttribute("shoppingCart");
		List<ShoppingCartItem> shoppingCartItemList = shoppingCart.getShoppingCartItems();// ���ﳵ����Ʒ��Ŀ
		for (int i = 0; i < shoppingCartItemList.size(); i++) {//�����ô�ͳ�ı�������Ϊ��������remove������ʹ�á�
			if (productId == shoppingCartItemList.get(i).getProduct().getId()) {// ǰ̨��������Ʒid�͹��ﳵ����Ʒid��ȣ���ɾ���ù��ﳵ��Ŀ��
				shoppingCartItemList.remove(i);
			}
		}
		shoppingCart.setShoppingCartItems(shoppingCartItemList);//��Ʒ��Ŀ�б����·��빺�ﳵ�� 
		session.setAttribute("shoppingCart", shoppingCart);//����session,���ع��ﳵҳ�� 
		return "list"; 
	}
	
	/**
	 * 
	 * @MethodName: list
	 * @Description: չʾ���м��빺�ﳵ����Ʒ
	 * @author jed
	 * @date 2017��4��2������8:41:37
	 * @param @return    
	 * @return String    ��������
	 * @return
	 *
	 */
	public String list(){
		mainPage ="shopping/shopping.jsp";
		navCode =NavUtil.genNavCode("���ﳵ");
		return SUCCESS;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getMainPage() {
		return mainPage;
	}

	public void setMainPage(String mainPage) {
		this.mainPage = mainPage;
	}

	public String getNavCode() {
		return navCode;
	}

	public void setNavCode(String navCode) {
		this.navCode = navCode;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	

}
