/**
 * 
 */
package com.hik.service.impl;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hik.dao.BaseDAO;
import com.hik.entity.PageBean;
import com.hik.entity.ProductSmallType;
import com.hik.service.ProductSmallTypeService;
import com.hik.util.StringUtil;

/**
 * @ClassName: ProductSmallTypeServiceImpl
 * @Description: TODO
 * @author jed
 * @date 2017年5月13日上午7:36:41
 *
 */
@Service("productSmallTypeService")
public class ProductSmallTypeServiceImpl implements ProductSmallTypeService{

	@Resource
	private BaseDAO<ProductSmallType> baseDao;
	
	@Override
	public List<ProductSmallType> findProductSmallTypeList(ProductSmallType productSmallType,PageBean pageBean) {
		List<Object> param = new LinkedList<Object>();
		StringBuffer hql = new StringBuffer(" from ProductSmallType ");
		if(productSmallType!=null){
			if(StringUtil.isNotEmpty(productSmallType.getName())){
				hql.append(" and name like ?");
				param.add("%"+productSmallType.getName()+"%"); 
			}
			if(productSmallType.getBigType()!=null && productSmallType.getBigType().getId()!=0){
				hql.append(" and bigType.id=?");
				param.add(productSmallType.getBigType().getId());
			}
		}
		if(pageBean!=null){
			return baseDao.find(hql.toString().replaceFirst("and", "where"), param, pageBean);
		}else{
			return baseDao.find(hql.toString().replaceFirst("and", "where"), param);
		}
		
	}

	@Override
	public boolean existSmallTypeWithBigTypeId(int bigTypeId) {
		boolean existSmallType = false;
		StringBuffer hql = new StringBuffer(" from ProductSmallType where bigTypeId = ?");
		List<Object> param = new LinkedList<Object>();
		param.add(bigTypeId);
		List<ProductSmallType> productSmallTypeList = baseDao.find(hql.toString(), param);
		if(productSmallTypeList.size()>0){
			existSmallType = true;
		}
		return existSmallType;
	}

	@Override
	public Long getProductSmallTypeCount(ProductSmallType productSmallType) {
		StringBuffer hql = new StringBuffer(" select count(*) from ProductSmallType ");
		List<Object> param = new LinkedList<Object>();
		if(productSmallType!=null){
			if(StringUtil.isNotEmpty(productSmallType.getName())){
				hql.append(" and name like ?");
				param.add("%"+productSmallType.getName()+"%"); 
			}
		}
		return baseDao.count(hql.toString().replaceFirst("and", "where"), param);
	}

	@Override
	public void saveProductSmallType(ProductSmallType productSmallType) {
		baseDao.merge(productSmallType);
	}

	@Override
	public void deleteProductSmallType(ProductSmallType productSmallType) {
		baseDao.delete(productSmallType);
	}

	@Override
	public ProductSmallType getProductSmallTypeById(int id) {
		return baseDao.get(ProductSmallType.class, id);
	}

}
