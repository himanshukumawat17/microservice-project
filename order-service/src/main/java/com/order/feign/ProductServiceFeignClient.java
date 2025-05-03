package com.order.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.order.dto.ProductDTO;

@FeignClient(name = "product-service", url = "http://localhost:8084")
public interface ProductServiceFeignClient {

	@GetMapping("/api/getproducts")
	ProductDTO getProductById(@RequestParam("id") Long productId);
}
