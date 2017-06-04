/**
 * 
 */
package com.hik.service;

import java.util.List;

import com.hik.entity.Order;
import com.hik.entity.PageBean;

/**
 * @ClassName: OrderService
 * @Description: TODO
 * @author jed
 * @date 2017年4月25日下午9:29:38
 *
 */
public interface OrderService {
	
	/**
	 * 
	 * @MethodName: saveOrder
	 * @Description: 保存订单
	 * @author jed
	 * @date 2017年4月25日下午9:30:12
	 * @param @param order    
	 * @return void    返回类型
	 * @param order
	 *
	 */
	public void saveOrder(Order order);
	
	/**
	 * 
	 * @MethodName: findOrder
	 * @Description: 分页查询所有订单
	 * @author jed
	 * @date 2017年4月29日下午4:06:39
	 * @param @param order
	 * @param @param pageBean
	 * @param @return    
	 * @return List<Order>    返回类型
	 * @param order
	 * @param pageBean
	 * @return
	 *
	 */
	public List<Order> findOrder(Order order,PageBean pageBean);
	
	/**
	 * 
	 * @MethodName: getOrderCount
	 * @Description: 获取订单数量
	 * @author jed
	 * @date 2017年5月29日下午3:36:45
	 * @param @param order
	 * @param @return    
	 * @return Long    返回类型
	 * @param order
	 * @return
	 *
	 */
	public Long getOrderCount(Order order);
	
	/**
	 * 
	 * @MethodName: updateOrderStatus
	 * @Description: 更新订单状态
	 * @author jed
	 * @date 2017年4月29日下午9:48:30
	 * @param @param status
	 * @param @param orderNo    
	 * @return void    返回类型
	 * @param status
	 * @param orderNo
	 *
	 */
	public void updateOrderStatus(int status,String orderNo); 
	
	/**
	 * 
	 * @MethodName: getOrderById
	 * @Description: 根据订单id获取订单
	 * @author jed
	 * @date 2017年6月4日上午9:21:13
	 * @param @param id
	 * @param @return    
	 * @return Order    返回类型
	 * @param id
	 * @return
	 *
	 */
	public Order getOrderById(int id);

}
