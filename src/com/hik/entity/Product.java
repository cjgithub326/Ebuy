/**
 * 
 */
package com.hik.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * @ClassName: Product
 * @Description: TODO
 * @author jed
 * @date 2017��2��26������7:08:59
 *
 */
@Entity
@Table(name="t_product")
public class Product {
	
	private int id;
	private String name; //����
	private int price; //�۸�
	private int stock; //�洢
	private String proPic; //ͼƬ
	private String description; //����
	private int hot; //Ĭ��0������  0:������ ,1������
	private Date hotTime; //����ʱ��
	private int specialPrice;  //�ؼ�0�����ؼ�  0:�����ȼ�,1�����ؼ�
	private Date specialPriceTime; //�ؼ�ʱ��
	
	private ProductBigType bigType; //��Ʒ����Ʒ�����Ƕ��һ��ϵ
	private ProductSmallType smallType; //��Ʒ����ƷС���Ƕ��һ��ϵ
	private List<OrderProduct> orderProductList = new ArrayList<OrderProduct>(); //��Ʒ�Ͷ�����Ʒ��һ�Զ��ϵ
	
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
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public String getProPic() {
		return proPic;
	}
	public void setProPic(String proPic) {
		this.proPic = proPic;
	}
	@Column(length=2000)
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getHot() {
		return hot;
	}
	public void setHot(int hot) {
		this.hot = hot;
	}
	public Date getHotTime() {
		return hotTime;
	}
	public void setHotTime(Date hotTime) {
		this.hotTime = hotTime;
	}
	public int getSpecialPrice() {
		return specialPrice;
	}
	public void setSpecialPrice(int specialPrice) {
		this.specialPrice = specialPrice;
	}
	public Date getSpecialPriceTime() {
		return specialPriceTime;
	}
	public void setSpecialPriceTime(Date specialPriceTime) {
		this.specialPriceTime = specialPriceTime;
	}
	
	@ManyToOne
	@JoinColumn(name="bigTypeId") //�������
	public ProductBigType getBigType() {
		return bigType;
	}
	public void setBigType(ProductBigType bigType) {
		this.bigType = bigType;
	}
	
	@ManyToOne
	@JoinColumn(name="smallTypeId") //�������
	public ProductSmallType getSmallType() {
		return smallType;
	}
	public void setSmallType(ProductSmallType smallType) {
		this.smallType = smallType;
	}
	
	@OneToMany
	@JoinColumn(name="productId")
	public List<OrderProduct> getOrderProductList() {
		return orderProductList;
	}
	public void setOrderProductList(List<OrderProduct> orderProductList) {
		this.orderProductList = orderProductList;
	}
	
	

}
