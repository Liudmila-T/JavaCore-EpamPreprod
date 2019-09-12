package com.epam.preprod.tymoshenko.task4.entity;

import com.epam.preprod.tymoshenko.task1.part1.Product;

import java.time.LocalDateTime;
import java.util.NavigableMap;
import java.util.TreeMap;


public class OrdersHistory<T extends Product>  {

    private NavigableMap<LocalDateTime, ShoppingCart<T>> orders;

    public OrdersHistory() {
        this.orders = new TreeMap<>();
    }

    public NavigableMap<LocalDateTime, ShoppingCart<T>> getOrders() {
        return orders;
    }

    public void add (LocalDateTime dateTime, ShoppingCart<T> cart){
        orders.put(dateTime,cart);
    }

    @Override
    public String toString() {
        return orders.toString();
    }
}
