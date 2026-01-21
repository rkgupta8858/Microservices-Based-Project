package com.ecom.cart.controller;

import com.ecom.cart.dto.CartRequest;
import com.ecom.cart.entity.Cart;
import com.ecom.cart.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/cart/add")
    @ResponseStatus(HttpStatus.CREATED)
    public Cart addToCart(@RequestBody CartRequest request) {
        return cartService.addToCart(request);
    }

    @DeleteMapping("/cart/remove/{cartItemId}")
    @ResponseStatus(HttpStatus.OK)
    public void removeFromCart(@PathVariable int cartItemId) {
        cartService.removeFromCart(cartItemId);
    }
}
