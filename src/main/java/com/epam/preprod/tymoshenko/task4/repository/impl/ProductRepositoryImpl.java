package com.epam.preprod.tymoshenko.task4.repository.impl;

import com.epam.preprod.tymoshenko.task1.part1.Product;
import com.epam.preprod.tymoshenko.task4.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;


public class ProductRepositoryImpl<T extends Product> implements ProductRepository<T> {
    private List<T> products;


    public ProductRepositoryImpl() {
        this.products = new ArrayList<>();
    }

    @Override
    public List<T> getProducts() {
        return products;
    }


    @Override
    public T getProductById(String productId) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId().equals(productId)) {
                return products.get(i);
            }
        }
        return null;
    }


    @Override
    public boolean add(T product) {
        return products.add(product);
    }

    @Override
    public String toString() {
        return products.toString();
    }
}
