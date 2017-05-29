/**
 * 
 */
package com.hik.service;

import java.util.List;

import com.hik.entity.PageBean;
import com.hik.entity.Product;
import com.sun.org.apache.xml.internal.resolver.helpers.PublicId;

/**
 * @ClassName: ProductService
 * @Description: ��Ʒ�ӿ�
 * @author jed
 * @date 2017��3��5������8:29:36
 *
 */
public interface ProductService {
	
	/**
	 * 
	 * @MethodName: findProductList
	 * @Description: ��ȡ������Ʒ
	 * @author jed
	 * @date 2017��3��5������8:31:47
	 * @param @param product
	 * @param @param pageBean
	 * @param @return    
	 * @return List<Product>    ��������
	 * @param product
	 * @param pageBean
	 * @return
	 *
	 */
	public List<Product> findProductList(Product product,PageBean pageBean);
	
	/**
	 * 
	 * @MethodName: getProductCount
	 * @Description: ��ȡ��Ʒ����
	 * @author jed
	 * @date 2017��3��15������11:51:48
	 * @param @param s_product
	 * @param @return    
	 * @return long    ��������
	 * @param s_product
	 * @return
	 *
	 */
	public long getProductCount(Product product);
    
	/**
	 * 
	 * @MethodName: getProductbyId
	 * @Description: ����id��ѯ��Ʒ
	 * @author jed
	 * @date 2017��3��19������3:55:07
	 * @param @param productId
	 * @param @return    
	 * @return Product    ��������
	 * @param productId
	 * @return
	 *
	 */
	public Product getProductbyId(int productId);
	
	/**
	 * 
	 * @MethodName: saveProduct
	 * @Description: ������Ʒ
	 * @author jed
	 * @date 2017��5��16������9:44:43
	 * @param @param product
	 * @param @return    
	 * @return Product    ��������
	 * @param product
	 * @return
	 *
	 */
	public void saveProduct(Product product);
	
	/**
	 * 
	 * @MethodName: deleteProduct
	 * @Description: ɾ����Ʒ
	 * @author jed
	 * @date 2017��5��18������8:41:04
	 * @param @param product    
	 * @return void    ��������
	 * @param product
	 *
	 */
	public void deleteProduct(Product product);
	
	/**
	 * 
	 * @MethodName: setProductWithHot
	 * @Description: ����Ϊ������Ʒ
	 * @author jed
	 * @date 2017��5��18������8:42:12
	 * @param @param productId    
	 * @return void    ��������
	 * @param productId
	 *
	 */
	public void setProductWithHot(int productId);
	
	/**
	 * 
	 * @MethodName: setProductWithSpecialPrice
	 * @Description: ����Ϊ�ؼ���Ʒ
	 * @author jed
	 * @date 2017��5��18������8:43:04
	 * @param @param productId    
	 * @return void    ��������
	 * @param productId
	 *
	 */
	public void setProductWithSpecialPrice(int productId);
	
	/**
	 * 
	 * @MethodName: existProductWithSmallTypeId
	 * @Description: ��ƷС�����Ƿ�����Ʒ 
	 * @author jed
	 * @date 2017��5��28������10:12:36
	 * @param @param SmallTypeId
	 * @param @return    
	 * @return Product    ��������
	 * @param SmallTypeId
	 * @return
	 *
	 */
	public boolean existProductWithSmallTypeId(int smallTypeId);
}
