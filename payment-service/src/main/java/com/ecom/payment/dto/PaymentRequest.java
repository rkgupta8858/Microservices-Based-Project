package com.ecom.payment.dto;

public class PaymentRequest {

    private int orderId;
    private double amount;

    public int getOrderId() {
        return orderId;
    }

    public double getAmount() {
        return amount;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}
}
