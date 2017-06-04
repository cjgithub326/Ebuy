/**
 * 
 */
package com.hik.action;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.stereotype.Controller;

import com.hik.entity.Order;
import com.hik.entity.OrderProduct;
import com.hik.entity.PageBean;
import com.hik.entity.Product;
import com.hik.entity.ProductBigType;
import com.hik.entity.ProductSmallType;
import com.hik.entity.ShoppingCart;
import com.hik.entity.ShoppingCartItem;
import com.hik.entity.User;
import com.hik.service.OrderService;
import com.hik.util.DateUtil;
import com.hik.util.NavUtil;
import com.hik.util.ResponseUtil;
import com.hik.util.StringUtil;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.util.ResolverUtil.NameEndsWith;
import com.sun.jmx.snmp.SnmpString;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

/**
 * @ClassName: OrderAction
 * @Description: TODO
 * @author jed
 * @date 2017年4月25日下午9:35:46
 *
 */
@Controller
public class OrderAction extends ActionSupport implements ServletRequestAware{

	/**
	 * 序列号
	 */
	private static final long serialVersionUID = 1L;

	private HttpServletRequest request;
	
	private String mainPage;
	
	private String navCode;
	
	private Order order;
	
	private int status;
	
	private String orderNo;
	
	private String page;
	
	private String rows;
	
	private List<Order> orderList;
	
	private String orderId;
	
	private String orderNos;
	
	@Resource
	private OrderService orderService;
	
	/**
	 * 
	 * @MethodName: save
	 * @Description: 提交订单
	 * @author jed
	 * @date 2017年4月25日下午10:47:51
	 * @param @return
	 * @param @throws Exception    
	 * @return String    返回类型
	 * @return
	 * @throws Exception
	 *
	 */
	public String save() throws Exception{
		HttpSession session = request.getSession();
		Order order = new Order();
		User currentUser = (User) session.getAttribute("currentUser");
		order.setUser(currentUser);
		order.setCreateTime(new Date());
		order.setOrderNo(DateUtil.getCurrentDateStr());
		order.setStatus(1);
		
		ShoppingCart shoppingCart = (ShoppingCart) session.getAttribute("shoppingCart");
		List<ShoppingCartItem> shoppingCartItemList = shoppingCart.getShoppingCartItems();
		
		float cost = 0;
		List<OrderProduct> orderProductList = new LinkedList<OrderProduct>();
		for(ShoppingCartItem shoppingCartItem : shoppingCartItemList){
			OrderProduct orderProduct = new OrderProduct();
			Product product = shoppingCartItem.getProduct();
			orderProduct.setProduct(product);
			orderProduct.setOrder(order);
			orderProduct.setNum(shoppingCartItem.getCount());
			orderProductList.add(orderProduct);
			cost+=product.getPrice()*shoppingCartItem.getCount(); //订单总价
		}
		
		order.setOrderProductList(orderProductList);
		order.setCost(cost);
		orderService.saveOrder(order);
		navCode = NavUtil.genNavCode("购物");
		mainPage ="shopping/shopping-result.jsp";
		session.removeAttribute("shoppingCart");  //清空购物车,清空session
		return SUCCESS;
	}
	
	/**
	 * 
	 * @MethodName: findOrder
	 * @Description: 查询个人所有订单
	 * @author jed
	 * @date 2017年4月29日下午4:30:05
	 * @param @return    
	 * @return String    返回类型
	 * @return
	 *
	 */
	public String findOrder(){
		//获取当前用户
		HttpSession session = request.getSession();
		User currentUser = (User) session.getAttribute("currentUser");
		if(order==null){
			order = new Order(); //初始化 
		}
		order.setUser(currentUser);
		orderList = orderService.findOrder(order, null);
		navCode=NavUtil.genNavCode("个人中心 ");
		mainPage="userCenter/orderList.jsp";
		return "orderList";
	}
	
	/**
	 * 
	 * @MethodName: confirmReceive
	 * @Description: 确认收货
	 * @author jed
	 * @date 2017年4月29日下午9:56:50
	 * @param @return    
	 * @return String    返回类型
	 * @return
	 * @throws Exception 
	 *
	 */
	public String confirmReceive() throws Exception{
		orderService.updateOrderStatus(status, orderNo);
		JSONObject result = new JSONObject();
		result.put("success", true);
		ResponseUtil.write(ServletActionContext.getResponse(), result);
		return null;
	}
	
	/**
	 * 
	 * @MethodName: list
	 * @Description: 查询订单
	 * @author jed
	 * @date 2017年5月29日下午3:55:22
	 * @param @return    
	 * @return String    返回类型
	 * @return
	 * @throws Exception 
	 *
	 */
	public String list() throws Exception{
		PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
		List<Order> orderList = orderService.findOrder(order, pageBean);
		long total = orderService.getOrderCount(order);
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setExcludes(new String[]{"orderProductList"}); //去除要排除的属性   前台不展示
		jsonConfig.registerJsonValueProcessor(java.util.Date.class, new DateJsonValueProcessor("yyyy-MM-dd")); //日期处理
		jsonConfig.registerJsonValueProcessor(User.class, new ObjectJsonValueProcessor(new String[]{"id","userName"}, User.class));//用户id和name
		JSONArray rows = JSONArray.fromObject(orderList, jsonConfig);
		JSONObject result = new JSONObject();
		result.put("rows", rows);
		result.put("total", total);
		ResponseUtil.write(ServletActionContext.getResponse(), result);
		return null;
	}
	
	/**
	 * 
	 * @MethodName: findProductListByOrderId
	 * @Description: 根据订单id查询订单详情
	 * @author jed
	 * @date 2017年6月4日上午9:48:47
	 * @param @return    
	 * @return String    返回类型
	 * @return
	 * @throws Exception 
	 *
	 */
	public String findProductListByOrderId() throws Exception{
		if(StringUtil.isEmpty(orderId)){
			return null;
		}
		Order order = orderService.getOrderById(Integer.parseInt(orderId));
		List<OrderProduct> orderProductList = order.getOrderProductList();
		JSONObject result = new JSONObject();
		JSONArray rows = new JSONArray();
		for(OrderProduct orderProduct:orderProductList){
			Product product = orderProduct.getProduct();
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("productName", product.getName());
			jsonObject.put("proPic", product.getProPic());
			jsonObject.put("price", product.getPrice());
			jsonObject.put("num", orderProduct.getNum());
			jsonObject.put("subtotal", product.getPrice()*orderProduct.getNum());
			rows.add(jsonObject);
		}
		result.put("rows", rows);
		result.put("total", rows.size());
		ResponseUtil.write(ServletActionContext.getResponse(), result);
		return null;
	}
	
	/**
	 * 
	 * @MethodName: modifyOrderStatus
	 * @Description: 修改订单状态
	 * @author jed
	 * @date 2017年6月4日下午12:02:46
	 * @param @return    
	 * @return String    返回类型
	 * @return
	 * @throws Exception 
	 *
	 */
	public String modifyOrderStatus() throws Exception{
		String[] orderNoStr = orderNos.split(",");
		for(int i=0;i<orderNoStr.length;i++){
			orderService.updateOrderStatus(status, orderNoStr[i]);
		}
		JSONObject result = new JSONObject();
		result.put("success", true);
		ResponseUtil.write(ServletActionContext.getResponse(), result);
		return null;
	}
	
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
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

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public List<Order> getOrderList() {
		return orderList;
	}

	public void setOrderList(List<Order> orderList) {
		this.orderList = orderList;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
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

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getOrderNos() {
		return orderNos;
	}

	public void setOrderNos(String orderNos) {
		this.orderNos = orderNos;
	}



}
