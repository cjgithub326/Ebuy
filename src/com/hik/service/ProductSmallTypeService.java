/**
 * 
 */
package com.hik.service;

import java.util.List;

import com.hik.entity.PageBean;
import com.hik.entity.ProductSmallType;

/**
 * @ClassName: ProductBigTypeService
 * @Description: 商品小类
 * @author jed
 * @date 2017年3月2日下午11:22:18
 *
 */
public interface ProductSmallTypeService {
	
	/**
	 * 
	 * @MethodName: findProductSmallTypeList
	 * @Description: 获取所有商品小类
	 * @author jed
	 * @date 2017年5月13日上午7:38:24
	 * @param @param productSmallType
	 * @param @return    
	 * @return List<ProductSmallType>    返回类型
	 * @param productSmallType
	 * @return
	 *
	 */
	public List<ProductSmallType> findProductSmallTypeList(ProductSmallType productSmallType,PageBean pageBean);
	
	/**
	 * 
	 * @MethodName: existSmallTypeWithBigTypeId
	 * @Description: 商品大类下是否存在商品小类
	 * @author jed
	 * @date 2017年5月21日上午9:46:51
	 * @param @param bigTypeId
	 * @param @return    
	 * @return boolean    返回类型
	 * @param bigTypeId
	 * @return
	 *
	 */
	public boolean existSmallTypeWithBigTypeId(int bigTypeId);
	
	/**
	 * 
	 * @MethodName: getProductSmallTypeCount
	 * @Description: 查询商品小类数量
	 * @author jed
	 * @date 2017年5月28日上午9:40:08
	 * @param @param productSmallType
	 * @param @return    
	 * @return Long    返回类型
	 * @param productSmallType
	 * @return
	 *
	 */
	public Long getProductSmallTypeCount(ProductSmallType productSmallType);
	
	/**
	 * 
	 * @MethodName: saveProductSmallType
	 * @Description: 保存商品小类
	 * @author jed
	 * @date 2017年5月28日上午9:41:35
	 * @param @param productSmallType    
	 * @return void    返回类型
	 * @param productSmallType
	 *
	 */
	public void saveProductSmallType(ProductSmallType productSmallType);
	
	/**
	 * 
	 * @MethodName: deleteProductSmallType
	 * @Description: 删除商品小类
	 * @author jed
	 * @date 2017年5月28日上午9:42:35
	 * @param @param productSmallType    
	 * @return void    返回类型
	 * @param productSmallType
	 *
	 */
	public void deleteProductSmallType(ProductSmallType productSmallType);
	
	/**
	 * 
	 * @MethodName: getProductSmallTypeById
	 * @Description: 获取商品小类
	 * @author jed
	 * @date 2017年5月28日上午9:44:00
	 * @param @param id
	 * @param @return    
	 * @return ProductSmallType    返回类型
	 * @param id
	 * @return
	 *
	 */
	public ProductSmallType getProductSmallTypeById(int id);
}
