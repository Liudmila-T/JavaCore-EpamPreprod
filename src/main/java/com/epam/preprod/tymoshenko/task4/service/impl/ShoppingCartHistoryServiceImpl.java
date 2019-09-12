package com.epam.preprod.tymoshenko.task4.service.impl;

import com.epam.preprod.tymoshenko.task1.part1.Product;
import com.epam.preprod.tymoshenko.task4.entity.ShoppingCartHistory;
import com.epam.preprod.tymoshenko.task4.service.ShoppingCartHistoryService;

import java.util.Map;

public class ShoppingCartHistoryServiceImpl<T extends Product> implements ShoppingCartHistoryService<T> {

    private ShoppingCartHistory<T> shoppingCartHistory;

    public ShoppingCartHistoryServiceImpl(ShoppingCartHistory<T> shoppingCartHistory) {
        this.shoppingCartHistory = shoppingCartHistory;
    }

    public ShoppingCartHistory<T> getShoppingCartHistory() {
        return shoppingCartHistory;
    }

    @Override
    public void add(T product) {
          shoppingCartHistory.add(product);
    }

    @Override
    public String toString() {
        return "Shopping Cart History{" +
                shoppingCartHistory +
                '}';
    }
}
