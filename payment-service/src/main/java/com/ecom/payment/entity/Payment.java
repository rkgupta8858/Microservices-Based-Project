package com.ecom.payment.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "payments")
public class Payment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private int orderId;
	private double amount;
	private String status; // SUCCESS or FAILED

	public int getId() {
		return id;
	}

	public int getOrderId() {
		return orderId;
	}

	public double getAmount() {
		return amount;
	}

	public String getStatus() {
		return status;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
