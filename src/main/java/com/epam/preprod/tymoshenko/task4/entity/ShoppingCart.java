package com.epam.preprod.tymoshenko.task4.entity;

import com.epam.preprod.tymoshenko.task1.part1.Product;

import java.util.HashMap;
import java.util.Map;

public class ShoppingCart<T extends Product> {

    private Map<String, Integer> cart;

    public ShoppingCart() {
        cart = new HashMap<>();
    }

    public ShoppingCart(Map<String, Integer> cart) {
        this.cart = cart;
    }

    public Map<String, Integer> getCart() {
        return cart;
    }

    public void addProductToCart(String productId, int quantity) {
        cart.put(productId, quantity);
    }

    @Override
    public String toString() {
        return cart.toString();
    }
}



