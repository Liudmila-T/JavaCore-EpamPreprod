package com.epam.preprod.tymoshenko.task4.service;

import com.epam.preprod.tymoshenko.task4.repository.ProductRepository;

public interface ProductRepositoryService<T> {

    public ProductRepository<T> getProductRepository();

    public String toString();
}
