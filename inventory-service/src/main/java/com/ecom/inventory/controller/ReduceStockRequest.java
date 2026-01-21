package com.ecom.inventory.controller;

public class ReduceStockRequest {

    private int productId;
    private int quantity;

    public int getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }
}
