package com.product.service;

import com.product.entity.ProductEntity;

public interface ProductService {
    ProductEntity saveProduct(ProductEntity product);
    ProductEntity getProductById(Long id);
}
