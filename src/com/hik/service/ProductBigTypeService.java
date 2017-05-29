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
 * @date 2017��3��2������11:22:18
 *
 */
public interface ProductBigTypeService {
	
	/**
	 * 
	 * @MethodName: findAllBigTypeList
	 * @Description: ��ȡ������Ʒ����
	 * @author jed
	 * @date 2017��3��2������11:25:20
	 * @param @return    
	 * @return List<ProductBigType>    ��������
	 * @return
	 *
	 */
	public List<ProductBigType> findAllBigTypeList(); 
	
	/**
	 * 
	 * @MethodName: findProductBigTypeList
	 * @Description: ��ҳ��ѯ��Ʒ����
	 * @author jed
	 * @date 2017��5��19������9:28:30
	 * @param @param productBigType
	 * @param @param pageBean
	 * @param @return    
	 * @return List<ProductBigType>    ��������
	 * @param productBigType
	 * @param pageBean
	 * @return
	 *
	 */
	public List<ProductBigType> findProductBigTypeList(ProductBigType productBigType,PageBean pageBean);
	
	/**
	 * 
	 * @MethodName: getProductBigTypeCount
	 * @Description: ��ѯ��Ʒ��������
	 * @author jed
	 * @date 2017��5��19������9:29:08
	 * @param @param productBigType
	 * @param @return    
	 * @return Long    ��������
	 * @param productBigType
	 * @return
	 *
	 */
	public Long getProductBigTypeCount(ProductBigType productBigType);
	
	/**
	 * 
	 * @MethodName: saveProductBigType
	 * @Description: ������Ʒ����
	 * @author jed
	 * @date 2017��5��21������9:07:43
	 * @param @param productBigType    
	 * @return void    ��������
	 * @param productBigType
	 *
	 */
	public void saveProductBigType(ProductBigType productBigType);
	
	/**
	 * 
	 * @MethodName: deleteProductBigType
	 * @Description: ɾ����Ʒ����
	 * @author jed
	 * @date 2017��5��21������9:08:34
	 * @param @param productBigType    
	 * @return void    ��������
	 * @param productBigType
	 *
	 */
	public void deleteProductBigType(ProductBigType productBigType);
	
	/**
	 * 
	 * @MethodName: getProductBitTypeById
	 * @Description: ��ȡ��Ʒ����
	 * @author jed
	 * @date 2017��5��21������9:11:06
	 * @param @param id
	 * @param @return    
	 * @return ProductBigType    ��������
	 * @param id
	 * @return
	 *
	 */
	public ProductBigType getProductBigTypeById(int id);

}
