package com.ecom.order.client;

import java.util.List;

public class CartResponse {

	private int id;
	private int userId;
	private List<CartItemResponse> items;

	public int getUserId() {
		return userId;
	}

	public List<CartItemResponse> getItems() {
		return items;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public void setItems(List<CartItemResponse> items) {
		this.items = items;
	}
}
