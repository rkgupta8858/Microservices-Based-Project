package com.ecom.inventory.dto;

public class InventoryResponse {

    private int productId;
    private int inStock;

    public InventoryResponse(int productId, int i) {
        this.productId = productId;
        this.inStock = i;
    }

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getInStock() {
		return inStock;
	}

	public void setInStock(int inStock) {
		this.inStock = inStock;
	}

  
}
