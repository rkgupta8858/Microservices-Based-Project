package com.ecom.order.client;

public class ReduceStockRequest {

    private int productId;
    private int quantity;

    public ReduceStockRequest(int productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

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
