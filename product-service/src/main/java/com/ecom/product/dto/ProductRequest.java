package com.ecom.product.dto;

public class ProductRequest {

	private String name;

	private Double price;

	private Integer stock;

	private int categoryId;

	public String getName() {
		return name;
	}

	public Double getPrice() {
		return price;
	}

	public Integer getStock() {
		return stock;
	}

	public int getCategoryId() {
		return categoryId;
	}
}
