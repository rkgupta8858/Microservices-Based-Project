package com.ecom.cart.dto;

public class CartRequest {

    private int userId;
    private int productId;
    private int quantity;

    public int getUserId() {
        return userId;
    }

    public int getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }
}
