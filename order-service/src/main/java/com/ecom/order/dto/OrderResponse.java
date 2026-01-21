package com.ecom.order.dto;

public class OrderResponse {
	private int orderId;
	private String status;

	public OrderResponse(int orderId, String status) {
		this.orderId = orderId;
		this.status = status;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
