package com.ecom.order.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.order.client.CartClient;
import com.ecom.order.client.CartItemResponse;
import com.ecom.order.client.CartResponse;
import com.ecom.order.client.InventoryClient;
import com.ecom.order.client.InventoryResponse;
import com.ecom.order.client.PaymentClient;
import com.ecom.order.client.PaymentRequest;
import com.ecom.order.client.PaymentResponse;
import com.ecom.order.client.ProductClient;
import com.ecom.order.client.ProductResponse;
import com.ecom.order.client.ReduceStockRequest;
import com.ecom.order.dto.OrderResponse;
import com.ecom.order.entity.Order;
import com.ecom.order.entity.OrderItem;
import com.ecom.order.exception.OrderNotFoundException;
import com.ecom.order.exception.ProductOutOfStockException;
import com.ecom.order.kafka.OrderProducer;
import com.ecom.order.repository.OrderRepository;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;

@Service
public class OrderService {

    private static final Logger log =
            LoggerFactory.getLogger(OrderService.class);

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private InventoryClient inventoryClient;

    @Autowired
    private PaymentClient paymentClient;

    @Autowired
    private CartClient cartClient;

    @Autowired
    private ProductClient productClient;

    @Autowired
    private OrderProducer orderProducer;

    public OrderResponse checkout(int userId) {

        CartResponse cart = cartClient.getCart(userId);

        if (cart == null || cart.getItems() == null || cart.getItems().isEmpty()) {
            throw new RuntimeException("Cart is empty");
        }

        Order order = new Order();
        order.setUserId(userId);
        order.setStatus("CREATED");

        double total = 0;
        List<OrderItem> orderItems = new ArrayList<>();

        for (CartItemResponse item : cart.getItems()) {

            InventoryResponse stock = checkInventory(item.getProductId());

            if (stock == null || !stock.isInStock()) {
                throw new ProductOutOfStockException(
                        "Product out of stock: " + item.getProductId());
            }

            ProductResponse product = fetchProduct(item.getProductId());

            OrderItem orderItem = new OrderItem();
            orderItem.setProductId(item.getProductId());
            orderItem.setQuantity(item.getQuantity());
            orderItem.setPrice(product.getPrice());
            orderItem.setOrder(order);

            total += product.getPrice() * item.getQuantity();
            orderItems.add(orderItem);
        }

        order.setTotalAmount(total);
        order.setItems(orderItems);

        Order savedOrder = orderRepository.save(order);

        PaymentResponse payment =
                processPayment(savedOrder.getId(), total);

        if (payment != null &&
                "SUCCESS".equalsIgnoreCase(payment.getStatus())) {

            savedOrder.setStatus("PAID");

            for (CartItemResponse item : cart.getItems()) {
                inventoryClient.reduceStock(
                        new ReduceStockRequest(
                                item.getProductId(),
                                item.getQuantity()
                        )
                );
            }

            cartClient.clearCart(userId);
            orderProducer.sendOrderCreated(savedOrder.getId());

        } else {
            savedOrder.setStatus("FAILED");
        }

        orderRepository.save(savedOrder);

        return new OrderResponse(
                savedOrder.getId(),
                savedOrder.getStatus()
        );
    }

    @CircuitBreaker(name = "inventoryService", fallbackMethod = "inventoryFallback")
    public InventoryResponse checkInventory(int productId) {
        return inventoryClient.checkStock(productId);
    }

    public InventoryResponse inventoryFallback(int productId, Exception ex) {
        log.error("Inventory service unavailable for productId: {}", productId);
        InventoryResponse response = new InventoryResponse();
        response.setInStock(false);
        return response;
    }

    @CircuitBreaker(name = "productService", fallbackMethod = "productFallback")
    public ProductResponse fetchProduct(int productId) {
        return productClient.getProduct(productId);
    }

    public ProductResponse productFallback(int productId, Exception ex) {
        log.error("Product service unavailable for productId: {}", productId);
        throw new OrderNotFoundException("Product service is down");
    }

    @CircuitBreaker(name = "paymentService", fallbackMethod = "paymentFallback")
    @Retry(name = "paymentService")
    public PaymentResponse processPayment(int orderId, double total) {
        return paymentClient.makePayment(new PaymentRequest(orderId, total));
    }

    public PaymentResponse paymentFallback(int orderId, double total, Exception ex) {
        log.error("Payment service failed for orderId: {}", orderId);
        PaymentResponse response = new PaymentResponse();
        response.setStatus("FAILED");
        return response;
    }

    public Order getOrderById(int id) {
        return orderRepository.findById(id)
                .orElseThrow(() ->
                        new OrderNotFoundException("Order not found: " + id));
    }
}
