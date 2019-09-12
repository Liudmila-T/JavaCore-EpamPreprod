package com.epam.preprod.tymoshenko.task4.repository;

import java.util.List;

public interface ProductRepository<T> {

    boolean add(T product);

    List<T> getProducts();

    T getProductById(String productId);

    String toString();

}
