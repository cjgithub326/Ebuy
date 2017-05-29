/**
 * 
 */
package com.hik.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;

/**
 * @ClassName: Order
 * @Description: ����
 * @author jed
 * @date 2017��2��26������7:36:07
 *
 */
@Entity
@Table(name="t_order")
public class Order {
	
	private int id;
	private String orderNo; //������
	private Date createTime; //����ʱ��
	private float cost; //�����ܼ�
	private int status; //����״̬  1��������ˣ����µ� 2�������ͨ�����Ѹ��� 3�������ѷ��� 4��������ջ�
	
	private User user; //�������û��Ƕ��һ��ϵ
	private List<OrderProduct> orderProductList = new ArrayList<OrderProduct>(); //�����Ͷ�����Ʒ��һ�Զ��ϵ
	
	@Id
	@GeneratedValue(generator="_native")
	@GenericGenerator(name="_native",strategy="native")
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public float getCost() {
		return cost;
	}
	public void setCost(float cost) {
		this.cost = cost;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	@ManyToOne
	@JoinColumn(name="userId",updatable=false)
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

	@OneToMany(fetch=FetchType.EAGER)
    @Cascade(value={CascadeType.SAVE_UPDATE})
	@JoinColumn(name="orderId")
	public List<OrderProduct> getOrderProductList() {
		return orderProductList;
	}
	public void setOrderProductList(List<OrderProduct> orderProductList) {
		this.orderProductList = orderProductList;
	}
	
}
