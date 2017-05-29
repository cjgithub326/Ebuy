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
 * @date 2017年4月2日上午10:56:48
 *
 */
@Controller
public class ShoppingAction extends ActionSupport implements ServletRequestAware{

	/**
	 * 序列化
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
	 * @Description: 添加商品到购物车
	 * @author jed
	 * @date 2017年4月2日上午11:01:41
	 * @param @return    
	 * @return String    返回类型
	 * @return
	 *
	 */
	public String addShoppingCartItem() throws Exception{
		HttpSession session = request.getSession();
		Product product = productService.getProductbyId(productId);
		//从session中查询购物车
		ShoppingCart shoppingCart = (ShoppingCart) session.getAttribute("shoppingCart");
		if(shoppingCart==null){//第一次添加,并且绑定用户
			shoppingCart = new ShoppingCart();
			User currentUser = (User) session.getAttribute("currentUser");
			if(currentUser!=null){
				shoppingCart.setUserId(currentUser.getId());
			}
		}
		
		List<ShoppingCartItem> shoppingCartItemList = shoppingCart.getShoppingCartItems();//购物车中商品条目
		boolean addshoppingflag = true;
		for(ShoppingCartItem sci:shoppingCartItemList){
			//如果购物车中存在该商品则不添加,该商品数目加1
			if(product.getId()==sci.getProduct().getId()){
				sci.setCount(sci.getCount()+1);
				addshoppingflag = false;
				break;
			}
		}
		ShoppingCartItem shoppingCartItem = new ShoppingCartItem();
		
		if(addshoppingflag){//购物车中添加未添加过的商品
			shoppingCartItem.setProduct(product);
			shoppingCartItem.setCount(1);
			shoppingCartItemList.add(shoppingCartItem);
		}
		
		session.setAttribute("shoppingCart", shoppingCart);  //购物车存入session中
		
		//前台ajax请求返回json字符串对象
		JSONObject result = new JSONObject();
		result.put("success", true);
		ResponseUtil.write(ServletActionContext.getResponse(), result);
		//ajax请求返回null
		return null;
	}
	
	/**
	 * 
	 * @MethodName: buyShopping
	 * @Description: 购买商品
	 * @author jed
	 * @date 2017年4月2日下午10:25:59
	 * @param @return    
	 * @return String    返回类型
	 * @return
	 *
	 */
	public String buyShopping(){
		HttpSession session = request.getSession();
		Product product = productService.getProductbyId(productId);
		//从session中查询购物车
		ShoppingCart shoppingCart = (ShoppingCart) session.getAttribute("shoppingCart");
		if(shoppingCart==null){//第一次添加,并且绑定用户
			shoppingCart = new ShoppingCart();
			User currentUser = (User) session.getAttribute("currentUser");
			if(currentUser!=null){
				shoppingCart.setUserId(currentUser.getId());
			}
		}
		
		List<ShoppingCartItem> shoppingCartItemList = shoppingCart.getShoppingCartItems();//购物车中商品条目
		boolean addshoppingflag = true;
		for(ShoppingCartItem sci:shoppingCartItemList){
			//如果购物车中存在该商品则不添加,该商品数目加1
			if(product.getId()==sci.getProduct().getId()){
				sci.setCount(sci.getCount()+1);
				addshoppingflag = false;
				break;
			}
		}
		ShoppingCartItem shoppingCartItem = new ShoppingCartItem();
		
		if(addshoppingflag){//购物车中添加未添加过的商品
			shoppingCartItem.setProduct(product);
			shoppingCartItem.setCount(1);
			shoppingCartItemList.add(shoppingCartItem);
		}
		
		session.setAttribute("shoppingCart", shoppingCart);  //购物车存入session中
		mainPage ="shopping/shopping.jsp";
		navCode =NavUtil.genNavCode("购物车");
		return SUCCESS;
	}
	
	/**
	 * 
	 * @MethodName: updateShoppingCartItem
	 * @Description: 更新购物车条目数量
	 * @author jed
	 * @date 2017年4月3日下午6:47:07
	 * @param @return    
	 * @return String    返回类型
	 * @return
	 * @throws Exception 
	 *
	 */
	public String updateShoppingCartItem() throws Exception{
		HttpSession session = request.getSession();
		Product product = productService.getProductbyId(productId);
		ShoppingCart shoppingCart = (ShoppingCart) session.getAttribute("shoppingCart");
		List<ShoppingCartItem> shoppingCartItemList = shoppingCart.getShoppingCartItems();//购物车中商品条目
		for(ShoppingCartItem sci:shoppingCartItemList){
			if(product.getId()==sci.getProduct().getId()){//前台传过来商品id和购物车中商品id相等，则更新数量，重新设置商品条目count
				sci.setCount(count);
				break;
			}
		}
		
		//前台ajax请求返回json字符串对象
		session.setAttribute("shoppingCart", shoppingCart);
		JSONObject result = new JSONObject();
		result.put("success", true);
		ResponseUtil.write(ServletActionContext.getResponse(), result);
		//ajax请求返回null
		return null;
	}
	
	/**
	 * 
	 * @MethodName: removeShoppingCartItem
	 * @Description: 删除购物车商品
	 * @author jed
	 * @date 2017年4月3日下午7:14:28
	 * @param @return    
	 * @return String    返回类型
	 * @return
	 *
	 */
	public String removeShoppingCartItem() {
		HttpSession session = request.getSession();
		ShoppingCart shoppingCart = (ShoppingCart) session.getAttribute("shoppingCart");
		List<ShoppingCartItem> shoppingCartItemList = shoppingCart.getShoppingCartItems();// 购物车中商品条目
		for (int i = 0; i < shoppingCartItemList.size(); i++) {//这里用传统的遍历是因为有索引。remove方法好使用。
			if (productId == shoppingCartItemList.get(i).getProduct().getId()) {// 前台传过来商品id和购物车中商品id相等，则删除该购物车条目。
				shoppingCartItemList.remove(i);
			}
		}
		shoppingCart.setShoppingCartItems(shoppingCartItemList);//商品条目列表重新放入购物车里 
		session.setAttribute("shoppingCart", shoppingCart);//更新session,返回购物车页面 
		return "list"; 
	}
	
	/**
	 * 
	 * @MethodName: list
	 * @Description: 展示所有加入购物车的商品
	 * @author jed
	 * @date 2017年4月2日下午8:41:37
	 * @param @return    
	 * @return String    返回类型
	 * @return
	 *
	 */
	public String list(){
		mainPage ="shopping/shopping.jsp";
		navCode =NavUtil.genNavCode("购物车");
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
