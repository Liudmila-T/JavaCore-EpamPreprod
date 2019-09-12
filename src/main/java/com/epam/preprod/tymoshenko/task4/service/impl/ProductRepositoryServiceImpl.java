package com.epam.preprod.tymoshenko.task4.service.impl;

import com.epam.preprod.tymoshenko.task4.repository.ProductRepository;
import com.epam.preprod.tymoshenko.task4.service.ProductRepositoryService;

public class ProductRepositoryServiceImpl<T> implements ProductRepositoryService<T> {

    private ProductRepository<T> productRepository;

    public ProductRepositoryServiceImpl(ProductRepository<T> productRepository) {
        this.productRepository = productRepository;
    }

    public ProductRepository<T> getProductRepository() {
        return productRepository;
    }


    @Override
    public String toString() {
        return "Product Repository{" +
                productRepository +
                '}';
    }
}
