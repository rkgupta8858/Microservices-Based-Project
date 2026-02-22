package com.ecom.order.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "cart-service", url = "http://localhost:8084")
public interface CartClient {

    @GetMapping("/cart/{userId}")
    CartResponse getCart(@PathVariable int userId);

    @DeleteMapping("/cart/clear/{userId}")
    void clearCart(@PathVariable int userId);
}
