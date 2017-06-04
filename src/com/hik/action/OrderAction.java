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
 * @date 2017��4��25������9:35:46
 *
 */
@Controller
public class OrderAction extends ActionSupport implements ServletRequestAware{

	/**
	 * ���к�
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
	 * @Description: �ύ����
	 * @author jed
	 * @date 2017��4��25������10:47:51
	 * @param @return
	 * @param @throws Exception    
	 * @return String    ��������
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
			cost+=product.getPrice()*shoppingCartItem.getCount(); //�����ܼ�
		}
		
		order.setOrderProductList(orderProductList);
		order.setCost(cost);
		orderService.saveOrder(order);
		navCode = NavUtil.genNavCode("����");
		mainPage ="shopping/shopping-result.jsp";
		session.removeAttribute("shoppingCart");  //��չ��ﳵ,���session
		return SUCCESS;
	}
	
	/**
	 * 
	 * @MethodName: findOrder
	 * @Description: ��ѯ�������ж���
	 * @author jed
	 * @date 2017��4��29������4:30:05
	 * @param @return    
	 * @return String    ��������
	 * @return
	 *
	 */
	public String findOrder(){
		//��ȡ��ǰ�û�
		HttpSession session = request.getSession();
		User currentUser = (User) session.getAttribute("currentUser");
		if(order==null){
			order = new Order(); //��ʼ�� 
		}
		order.setUser(currentUser);
		orderList = orderService.findOrder(order, null);
		navCode=NavUtil.genNavCode("�������� ");
		mainPage="userCenter/orderList.jsp";
		return "orderList";
	}
	
	/**
	 * 
	 * @MethodName: confirmReceive
	 * @Description: ȷ���ջ�
	 * @author jed
	 * @date 2017��4��29������9:56:50
	 * @param @return    
	 * @return String    ��������
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
	 * @Description: ��ѯ����
	 * @author jed
	 * @date 2017��5��29������3:55:22
	 * @param @return    
	 * @return String    ��������
	 * @return
	 * @throws Exception 
	 *
	 */
	public String list() throws Exception{
		PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
		List<Order> orderList = orderService.findOrder(order, pageBean);
		long total = orderService.getOrderCount(order);
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setExcludes(new String[]{"orderProductList"}); //ȥ��Ҫ�ų�������   ǰ̨��չʾ
		jsonConfig.registerJsonValueProcessor(java.util.Date.class, new DateJsonValueProcessor("yyyy-MM-dd")); //���ڴ���
		jsonConfig.registerJsonValueProcessor(User.class, new ObjectJsonValueProcessor(new String[]{"id","userName"}, User.class));//�û�id��name
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
	 * @Description: ���ݶ���id��ѯ��������
	 * @author jed
	 * @date 2017��6��4������9:48:47
	 * @param @return    
	 * @return String    ��������
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
	 * @Description: �޸Ķ���״̬
	 * @author jed
	 * @date 2017��6��4������12:02:46
	 * @param @return    
	 * @return String    ��������
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
