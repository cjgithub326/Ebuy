/**
 * 
 */
package com.hik.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: ShoppingCart
 * @Description: 购物车实体类
 * @author jed
 * @date 2017年4月2日上午10:50:33
 *
 */
public class ShoppingCart {

	private int userId; //用户id
	private List<ShoppingCartItem> shoppingCartItems = new ArrayList<ShoppingCartItem>(); //购物车条目
	
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
