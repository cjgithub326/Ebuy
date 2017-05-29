/**
 * 
 */
package com.hik.service;

import java.util.List;

import com.hik.entity.PageBean;
import com.hik.entity.ProductSmallType;

/**
 * @ClassName: ProductBigTypeService
 * @Description: ��ƷС��
 * @author jed
 * @date 2017��3��2������11:22:18
 *
 */
public interface ProductSmallTypeService {
	
	/**
	 * 
	 * @MethodName: findProductSmallTypeList
	 * @Description: ��ȡ������ƷС��
	 * @author jed
	 * @date 2017��5��13������7:38:24
	 * @param @param productSmallType
	 * @param @return    
	 * @return List<ProductSmallType>    ��������
	 * @param productSmallType
	 * @return
	 *
	 */
	public List<ProductSmallType> findProductSmallTypeList(ProductSmallType productSmallType,PageBean pageBean);
	
	/**
	 * 
	 * @MethodName: existSmallTypeWithBigTypeId
	 * @Description: ��Ʒ�������Ƿ������ƷС��
	 * @author jed
	 * @date 2017��5��21������9:46:51
	 * @param @param bigTypeId
	 * @param @return    
	 * @return boolean    ��������
	 * @param bigTypeId
	 * @return
	 *
	 */
	public boolean existSmallTypeWithBigTypeId(int bigTypeId);
	
	/**
	 * 
	 * @MethodName: getProductSmallTypeCount
	 * @Description: ��ѯ��ƷС������
	 * @author jed
	 * @date 2017��5��28������9:40:08
	 * @param @param productSmallType
	 * @param @return    
	 * @return Long    ��������
	 * @param productSmallType
	 * @return
	 *
	 */
	public Long getProductSmallTypeCount(ProductSmallType productSmallType);
	
	/**
	 * 
	 * @MethodName: saveProductSmallType
	 * @Description: ������ƷС��
	 * @author jed
	 * @date 2017��5��28������9:41:35
	 * @param @param productSmallType    
	 * @return void    ��������
	 * @param productSmallType
	 *
	 */
	public void saveProductSmallType(ProductSmallType productSmallType);
	
	/**
	 * 
	 * @MethodName: deleteProductSmallType
	 * @Description: ɾ����ƷС��
	 * @author jed
	 * @date 2017��5��28������9:42:35
	 * @param @param productSmallType    
	 * @return void    ��������
	 * @param productSmallType
	 *
	 */
	public void deleteProductSmallType(ProductSmallType productSmallType);
	
	/**
	 * 
	 * @MethodName: getProductSmallTypeById
	 * @Description: ��ȡ��ƷС��
	 * @author jed
	 * @date 2017��5��28������9:44:00
	 * @param @param id
	 * @param @return    
	 * @return ProductSmallType    ��������
	 * @param id
	 * @return
	 *
	 */
	public ProductSmallType getProductSmallTypeById(int id);
}
