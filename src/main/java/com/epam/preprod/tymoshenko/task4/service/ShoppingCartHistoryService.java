package com.epam.preprod.tymoshenko.task4.service;

import com.epam.preprod.tymoshenko.task1.part1.Product;
import com.epam.preprod.tymoshenko.task4.entity.ShoppingCartHistory;

public interface ShoppingCartHistoryService<T extends Product>{

    public void add(T product);

    public String toString();

    public ShoppingCartHistory<T> getShoppingCartHistory();

}

