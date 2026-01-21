package com.ecom.order.client;

public class PaymentResponse {
	private String status; // SUCCESS / FAILED

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}
}
