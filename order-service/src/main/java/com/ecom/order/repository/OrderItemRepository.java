package com.ecom.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecom.order.entity.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {
}
