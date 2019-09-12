package com.epam.preprod.tymoshenko.task4.service;

public interface ShoppingCartService<T>{

    public T getProductsById(String id);

    public void addProductToCart(String productId);

    public double getAmount();

    public boolean isEmpty();

    public String toString();
}
