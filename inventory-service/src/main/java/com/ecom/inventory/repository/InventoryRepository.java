package com.ecom.inventory.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecom.inventory.entity.Inventory;

public interface InventoryRepository extends JpaRepository<Inventory, Integer> {
	Optional<Inventory> findByProductId(int productId);
}
