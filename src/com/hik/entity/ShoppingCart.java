/**
 * 
 */
package com.hik.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: ShoppingCart
 * @Description: ���ﳵʵ����
 * @author jed
 * @date 2017��4��2������10:50:33
 *
 */
public class ShoppingCart {

	private int userId; //�û�id
	private List<ShoppingCartItem> shoppingCartItems = new ArrayList<ShoppingCartItem>(); //���ﳵ��Ŀ
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public List<ShoppingCartItem> getShoppingCartItems() {
		return shoppingCartItems;
	}
	public void setShoppingCartItems(List<ShoppingCartItem> shoppingCartItems) {
		this.shoppingCartItems = shoppingCartItems;
	}
	
	
	
}
