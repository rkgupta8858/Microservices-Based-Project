package com.ecom.inventory.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.inventory.dto.InventoryResponse;
import com.ecom.inventory.entity.Inventory;
import com.ecom.inventory.exception.InventoryNotFoundException;
import com.ecom.inventory.repository.InventoryRepository;

@Service
public class InventoryService {

	@Autowired
	private InventoryRepository inventoryRepository;
 
	public InventoryResponse getInventoryByProductId(int productId) {

		Inventory inventory = inventoryRepository.findByProductId(productId)
				.orElseThrow(() -> new InventoryNotFoundException("Inventory not found for productId: " + productId));

		return new InventoryResponse(inventory.getProductId(), inventory.getQuantity());
	}

	public InventoryResponse reduceStock(int productId, int quantity) {

		Inventory inventory = inventoryRepository.findByProductId(productId)
				.orElseThrow(() -> new InventoryNotFoundException("Inventory not found for productId: " + productId));

		if (inventory.getQuantity() < quantity) {
			throw new RuntimeException("Not enough stock available");
		}

		inventory.setQuantity(inventory.getQuantity() - quantity);
		Inventory saved = inventoryRepository.save(inventory);

		return new InventoryResponse(saved.getProductId(), saved.getQuantity());
	}
}
