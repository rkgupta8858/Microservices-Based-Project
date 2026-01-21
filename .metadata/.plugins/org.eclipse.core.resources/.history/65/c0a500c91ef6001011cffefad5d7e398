package com.ecom.order.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "inventory-service", url = "http://localhost:8083")
public interface InventoryClient {

	@GetMapping("/inventory/{productId}")
	InventoryResponse checkStock(@PathVariable int productId);
}