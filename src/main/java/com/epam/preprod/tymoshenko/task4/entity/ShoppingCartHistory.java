package com.epam.preprod.tymoshenko.task4.entity;

import com.epam.preprod.tymoshenko.task1.part1.Product;

import java.util.LinkedHashMap;
import java.util.Map;

public class ShoppingCartHistory<T extends Product>  extends LinkedHashMap<String,T>{

    private static final int MAX_QUANTITY_PRODUCTS = 5;

    private Map<String, T> shoppingCartHistory;

    public ShoppingCartHistory() {
        shoppingCartHistory = new LinkedHashMap<String, T>(){
            protected boolean removeEldestEntry(Map.Entry<String, T> eldest)
        {
            return size() > MAX_QUANTITY_PRODUCTS;
        }
    };
    }


    public void add(T product) {
        shoppingCartHistory.put(product.getId(), product);
            }

    public Map<String, T> getHistory() {
        return shoppingCartHistory;
    }

    @Override
    public String toString() {
        return shoppingCartHistory.toString();
    }
}
