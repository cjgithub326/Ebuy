/**
 * 
 */
package com.hik.service;

import java.util.List;

import com.hik.entity.PageBean;
import com.hik.entity.ProductBigType;

/**
 * @ClassName: ProductBigTypeService
 * @Description: TODO
 * @author jed
 * @date 2017年3月2日下午11:22:18
 *
 */
public interface ProductBigTypeService {
	
	/**
	 * 
	 * @MethodName: findAllBigTypeList
	 * @Description: 获取所有商品大类
	 * @author jed
	 * @date 2017年3月2日下午11:25:20
	 * @param @return    
	 * @return List<ProductBigType>    返回类型
	 * @return
	 *
	 */
	public List<ProductBigType> findAllBigTypeList(); 
	
	/**
	 * 
	 * @MethodName: findProductBigTypeList
	 * @Description: 分页查询商品大类
	 * @author jed
	 * @date 2017年5月19日下午9:28:30
	 * @param @param productBigType
	 * @param @param pageBean
	 * @param @return    
	 * @return List<ProductBigType>    返回类型
	 * @param productBigType
	 * @param pageBean
	 * @return
	 *
	 */
	public List<ProductBigType> findProductBigTypeList(ProductBigType productBigType,PageBean pageBean);
	
	/**
	 * 
	 * @MethodName: getProductBigTypeCount
	 * @Description: 查询商品大类数量
	 * @author jed
	 * @date 2017年5月19日下午9:29:08
	 * @param @param productBigType
	 * @param @return    
	 * @return Long    返回类型
	 * @param productBigType
	 * @return
	 *
	 */
	public Long getProductBigTypeCount(ProductBigType productBigType);
	
	/**
	 * 
	 * @MethodName: saveProductBigType
	 * @Description: 保存商品大类
	 * @author jed
	 * @date 2017年5月21日上午9:07:43
	 * @param @param productBigType    
	 * @return void    返回类型
	 * @param productBigType
	 *
	 */
	public void saveProductBigType(ProductBigType productBigType);
	
	/**
	 * 
	 * @MethodName: deleteProductBigType
	 * @Description: 删除商品大类
	 * @author jed
	 * @date 2017年5月21日上午9:08:34
	 * @param @param productBigType    
	 * @return void    返回类型
	 * @param productBigType
	 *
	 */
	public void deleteProductBigType(ProductBigType productBigType);
	
	/**
	 * 
	 * @MethodName: getProductBitTypeById
	 * @Description: 获取商品大类
	 * @author jed
	 * @date 2017年5月21日上午9:11:06
	 * @param @param id
	 * @param @return    
	 * @return ProductBigType    返回类型
	 * @param id
	 * @return
	 *
	 */
	public ProductBigType getProductBigTypeById(int id);

}
