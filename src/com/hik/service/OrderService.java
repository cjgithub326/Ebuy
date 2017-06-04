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
 * @date 2017��4��25������9:29:38
 *
 */
public interface OrderService {
	
	/**
	 * 
	 * @MethodName: saveOrder
	 * @Description: ���涩��
	 * @author jed
	 * @date 2017��4��25������9:30:12
	 * @param @param order    
	 * @return void    ��������
	 * @param order
	 *
	 */
	public void saveOrder(Order order);
	
	/**
	 * 
	 * @MethodName: findOrder
	 * @Description: ��ҳ��ѯ���ж���
	 * @author jed
	 * @date 2017��4��29������4:06:39
	 * @param @param order
	 * @param @param pageBean
	 * @param @return    
	 * @return List<Order>    ��������
	 * @param order
	 * @param pageBean
	 * @return
	 *
	 */
	public List<Order> findOrder(Order order,PageBean pageBean);
	
	/**
	 * 
	 * @MethodName: getOrderCount
	 * @Description: ��ȡ��������
	 * @author jed
	 * @date 2017��5��29������3:36:45
	 * @param @param order
	 * @param @return    
	 * @return Long    ��������
	 * @param order
	 * @return
	 *
	 */
	public Long getOrderCount(Order order);
	
	/**
	 * 
	 * @MethodName: updateOrderStatus
	 * @Description: ���¶���״̬
	 * @author jed
	 * @date 2017��4��29������9:48:30
	 * @param @param status
	 * @param @param orderNo    
	 * @return void    ��������
	 * @param status
	 * @param orderNo
	 *
	 */
	public void updateOrderStatus(int status,String orderNo); 
	
	/**
	 * 
	 * @MethodName: getOrderById
	 * @Description: ���ݶ���id��ȡ����
	 * @author jed
	 * @date 2017��6��4������9:21:13
	 * @param @param id
	 * @param @return    
	 * @return Order    ��������
	 * @param id
	 * @return
	 *
	 */
	public Order getOrderById(int id);

}
