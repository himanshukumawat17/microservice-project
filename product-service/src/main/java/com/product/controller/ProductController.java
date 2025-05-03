package com.product.controller;

import com.product.entity.ProductEntity;
import com.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/productsave")
    public ResponseEntity<ProductEntity> saveProduct(@RequestBody ProductEntity product) {
        ProductEntity savedProduct = productService.saveProduct(product);
        return ResponseEntity.ok(savedProduct);
    }

    @GetMapping("/getproducts")
    public ResponseEntity<ProductEntity> getProductById(@RequestParam("id") Long id) {
        ProductEntity product = productService.getProductById(id);
        return ResponseEntity.ok(product);
    }
}
