/**
 * 
 */
package com.hik.service.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hik.dao.BaseDAO;
import com.hik.entity.PageBean;
import com.hik.entity.Product;
import com.hik.entity.ProductSmallType;
import com.hik.service.ProductService;
import com.hik.util.StringUtil;

/**
 * @ClassName: ProductServiceImpl
 * @Description: TODO
 * @author jed
 * @date 2017年3月5日下午8:32:35
 *
 */
@Service("productService")
public class ProductServiceImpl implements ProductService{
	
	@Resource
	private BaseDAO<Product> baseDao;

	@Override
	public List<Product> findProductList(Product product, PageBean pageBean) {
		List<Object> param = new LinkedList<Object>();
		StringBuffer sb = new StringBuffer("from Product");
		if(product!=null){
			if(product.getBigType()!=null){
				sb.append(" and bigType.id = ?");
				param.add(product.getBigType().getId());
			}
			if(product.getSmallType()!=null){
				sb.append(" and smallType.id =?");
				param.add(product.getSmallType().getId());
			}
			if(StringUtil.isNotEmpty(product.getName())){
				sb.append(" and name like ?");
				param.add("%"+product.getName()+"%");
			}
			if(product.getSpecialPrice()==1){//特价
				sb.append(" and specialPrice=1 order by specialPriceTime desc");
			}
			if(product.getHot()==1){//热卖
				sb.append(" and hot=1 order by hotTime desc");
			}
		}
		if(pageBean!=null){
			return baseDao.find(sb.toString().replaceFirst("and", "where"), param, pageBean);
		}else{
			return null;
		}
	}

	@Override
	public long getProductCount(Product product) {
		List<Object> param = new LinkedList<Object>();
		StringBuffer sb = new StringBuffer("select count(*) from Product"); 
		if(product!=null){
			if(product.getBigType()!=null){
				sb.append(" and bigType.id = ?");
				param.add(product.getBigType().getId());
			}
			if(product.getSmallType()!=null){
				sb.append(" and smallType.id =?");
				param.add(product.getSmallType().getId());
			}
			if(StringUtil.isNotEmpty(product.getName())){
				sb.append(" and name like ?");
				param.add("%"+product.getName()+"%");
			}
		}
		return baseDao.count(sb.toString().replaceFirst("and", "where"), param);
	}

	@Override
	public Product getProductbyId(int productId) {
		return baseDao.get(Product.class, productId);
	}

	@Override
	public void saveProduct(Product product) {
		 baseDao.merge(product);
	}

	@Override
	public void deleteProduct(Product product) {
		baseDao.delete(product);
	}

	@Override
	public void setProductWithHot(int productId) {
		Product product = baseDao.get(Product.class, productId);
		product.setHot(1); //设置为热卖商品 
		product.setHotTime(new Date()); //设置为热卖商品时间
		baseDao.save(product);
	}

	@Override
	public void setProductWithSpecialPrice(int productId) {
		Product product = baseDao.get(Product.class, productId);
		product.setSpecialPrice(1); //设置为特价商品 
		product.setSpecialPriceTime(new Date()); //设置为特价商品时间
		baseDao.save(product);
	}

	@Override
	public boolean existProductWithSmallTypeId(int smallTypeId) {
		boolean existProduct = false;
		StringBuffer hql = new StringBuffer(" from Product where smallTypeId = ?");
		List<Object> param = new LinkedList<Object>();
		param.add(smallTypeId);
		List<Product> productList = baseDao.find(hql.toString(), param);
		if(productList.size()>0){
			existProduct = true;
		}
		return existProduct;
	}

}
