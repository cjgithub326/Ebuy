/**
 * 
 */
package com.hik.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * @ClassName: ProductBigType
 * @Description: TODO
 * @author jed
 * @date 2017��2��26������7:27:34
 *
 */
@Entity
@Table(name="t_bigType")
public class ProductBigType {

	private int id;
	private String name; //����
	private String remarks; //���
	
	private List<Product> productList = new ArrayList<Product>(); //��Ʒ�������Ʒ��һ�Զ��ϵ
	private List<ProductSmallType> productSmallList = new ArrayList<ProductSmallType>(); //��Ʒ�������ƷС����һ�Զ��ϵ
	
	@Id
	@GeneratedValue(generator="_native")
	@GenericGenerator(name="_native",strategy="native")
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Column(length=50)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	@OneToMany(mappedBy="bigType")
	public List<Product> getProductList() {
		return productList;
	}
	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}
	
	@OneToMany(mappedBy="bigType",fetch=FetchType.EAGER)
	public List<ProductSmallType> getProductSmallList() {
		return productSmallList;
	}
	public void setProductSmallList(List<ProductSmallType> productSmallList) {
		this.productSmallList = productSmallList;
	}
	
	
	
	
}
