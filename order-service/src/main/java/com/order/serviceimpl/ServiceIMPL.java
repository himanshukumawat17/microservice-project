package com.order.serviceimpl;

import com.order.dto.CartResponseDTO;
import com.order.dto.ProductDTO;
import com.order.entity.OrderEntity;
import com.order.feign.ProductServiceFeignClient;
import com.order.repository.OrderRepository;
import com.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ServiceIMPL implements OrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private ProductServiceFeignClient productServiceFeignClient;

	@Override
	public OrderEntity addToCart(OrderEntity order) {
		order.setCreatedTime(LocalDateTime.now());
		return orderRepository.save(order);
	}

	@Override
	public List<CartResponseDTO> getOrdersByUserId(Long userId) {
		List<CartResponseDTO> cartResponseList = new ArrayList<>();
		List<OrderEntity> orderList = orderRepository.findByUserId(userId);
		
		for (OrderEntity orderEntity : orderList) {
			CartResponseDTO cartResponseDTO = new CartResponseDTO();
			cartResponseDTO.setUserId(orderEntity.getUserId());
			cartResponseDTO.setTime(orderEntity.getCreatedTime());
			
			ProductDTO productDTO = productServiceFeignClient.getProductById(orderEntity.getProductId());
			
			cartResponseDTO.setProduct(productDTO);
			cartResponseList.add(cartResponseDTO);
			
		}
		return cartResponseList;
	}
}
