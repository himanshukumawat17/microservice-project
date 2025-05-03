package com.order.service;

import com.order.dto.CartResponseDTO;
import com.order.entity.OrderEntity;

import java.util.List;

public interface OrderService {
    OrderEntity addToCart(OrderEntity order);
    List<CartResponseDTO> getOrdersByUserId(Long userId);
}
