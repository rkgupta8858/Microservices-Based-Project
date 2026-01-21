package com.ecom.order.client;

public class PaymentRequest {
	private int orderId;
	private double amount;

	public PaymentRequest(int orderId, double amount) {
		this.orderId = orderId;
		this.amount = amount;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}
}
