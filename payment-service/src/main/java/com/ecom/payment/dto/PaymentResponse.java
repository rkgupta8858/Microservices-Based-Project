package com.ecom.payment.dto;

public class PaymentResponse {

	private String status;

	public PaymentResponse(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
