package com.ecom.order.client;

public class CartItemResponse {

    private int productId;
    private int quantity;

    public int getProductId() { return productId; }

	public int getQuantity() {
		return quantity;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}

