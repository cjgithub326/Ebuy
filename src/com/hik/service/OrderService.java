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

}
