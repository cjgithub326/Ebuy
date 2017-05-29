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
 * @Description: 商品接口
 * @author jed
 * @date 2017年3月5日下午8:29:36
 *
 */
public interface ProductService {
	
	/**
	 * 
	 * @MethodName: findProductList
	 * @Description: 获取所有商品
	 * @author jed
	 * @date 2017年3月5日下午8:31:47
	 * @param @param product
	 * @param @param pageBean
	 * @param @return    
	 * @return List<Product>    返回类型
	 * @param product
	 * @param pageBean
	 * @return
	 *
	 */
	public List<Product> findProductList(Product product,PageBean pageBean);
	
	/**
	 * 
	 * @MethodName: getProductCount
	 * @Description: 获取商品数量
	 * @author jed
	 * @date 2017年3月15日下午11:51:48
	 * @param @param s_product
	 * @param @return    
	 * @return long    返回类型
	 * @param s_product
	 * @return
	 *
	 */
	public long getProductCount(Product product);
    
	/**
	 * 
	 * @MethodName: getProductbyId
	 * @Description: 根据id查询商品
	 * @author jed
	 * @date 2017年3月19日下午3:55:07
	 * @param @param productId
	 * @param @return    
	 * @return Product    返回类型
	 * @param productId
	 * @return
	 *
	 */
	public Product getProductbyId(int productId);
	
	/**
	 * 
	 * @MethodName: saveProduct
	 * @Description: 保存商品
	 * @author jed
	 * @date 2017年5月16日下午9:44:43
	 * @param @param product
	 * @param @return    
	 * @return Product    返回类型
	 * @param product
	 * @return
	 *
	 */
	public void saveProduct(Product product);
	
	/**
	 * 
	 * @MethodName: deleteProduct
	 * @Description: 删除商品
	 * @author jed
	 * @date 2017年5月18日下午8:41:04
	 * @param @param product    
	 * @return void    返回类型
	 * @param product
	 *
	 */
	public void deleteProduct(Product product);
	
	/**
	 * 
	 * @MethodName: setProductWithHot
	 * @Description: 设置为热卖商品
	 * @author jed
	 * @date 2017年5月18日下午8:42:12
	 * @param @param productId    
	 * @return void    返回类型
	 * @param productId
	 *
	 */
	public void setProductWithHot(int productId);
	
	/**
	 * 
	 * @MethodName: setProductWithSpecialPrice
	 * @Description: 设置为特价商品
	 * @author jed
	 * @date 2017年5月18日下午8:43:04
	 * @param @param productId    
	 * @return void    返回类型
	 * @param productId
	 *
	 */
	public void setProductWithSpecialPrice(int productId);
	
	/**
	 * 
	 * @MethodName: existProductWithSmallTypeId
	 * @Description: 商品小类下是否有商品 
	 * @author jed
	 * @date 2017年5月28日上午10:12:36
	 * @param @param SmallTypeId
	 * @param @return    
	 * @return Product    返回类型
	 * @param SmallTypeId
	 * @return
	 *
	 */
	public boolean existProductWithSmallTypeId(int smallTypeId);
}
