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
import com.hik.entity.ProductBigType;
import com.hik.service.ProductBigTypeService;
import com.hik.util.StringUtil;

/**
 * @ClassName: ProductBigTypeServiceImpl
 * @Description: TODO
 * @author jed
 * @date 2017年3月2日下午11:26:35
 *
 */
@Service("productBigTypeService")
public class ProductBigTypeServiceImpl implements ProductBigTypeService{

	@Resource
	private BaseDAO<ProductBigType> baseDao;
	@Override
	public List<ProductBigType> findAllBigTypeList() {
		return baseDao.find("from ProductBigType");
	}
	
	@Override
	public List<ProductBigType> findProductBigTypeList(ProductBigType productBigType, PageBean pageBean) {
		StringBuffer hql = new StringBuffer(" from ProductBigType ");
		List<Object> param = new LinkedList<Object>();
		if(productBigType!=null){
			if(StringUtil.isNotEmpty(productBigType.getName())){
				hql.append(" and name like ?");
				param.add("%"+productBigType.getName()+"%");
			}
		}
		if(pageBean!=null){
			return baseDao.find(hql.toString().replaceFirst("and", "where"), param, pageBean);
		}else{
			return baseDao.find(hql.toString().replaceFirst("and", "where"), param);
		}
	}
	
	@Override
	public Long getProductBigTypeCount(ProductBigType productBigType) {
		StringBuffer hql = new StringBuffer(" select count(*) from ProductBigType ");
		List<Object> param = new LinkedList<Object>();
		if(productBigType!=null){
			if(StringUtil.isNotEmpty(productBigType.getName())){
				hql.append(" and name like ?");
				param.add("%"+productBigType.getName()+"%"); 
			}
		}
		return baseDao.count(hql.toString().replaceFirst("and", "where"), param);
	}

	@Override
	public void saveProductBigType(ProductBigType productBigType) {
		baseDao.merge(productBigType);
	}

	@Override
	public void deleteProductBigType(ProductBigType productBigType) {
		baseDao.delete(productBigType);
	}

	@Override
	public ProductBigType getProductBigTypeById(int id) {
		return baseDao.get(ProductBigType.class, id);
	}

}
