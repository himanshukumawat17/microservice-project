package com.order.controller;

import com.order.dto.CartResponseDTO;
import com.order.entity.OrderEntity;
import com.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;


    @PostMapping("/addcart")
    public OrderEntity addToCart(@RequestBody OrderEntity order) {
        return orderService.addToCart(order);
    }

    @GetMapping("/getcart/{userId}")
    public List<CartResponseDTO> getOrdersByUserId(@PathVariable Long userId) {
        return orderService.getOrdersByUserId(userId);
    }
}
