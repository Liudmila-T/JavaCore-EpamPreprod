package com.epam.preprod.tymoshenko.task4.service;

import com.epam.preprod.tymoshenko.task1.part1.Product;
import com.epam.preprod.tymoshenko.task4.entity.OrdersHistory;
import com.epam.preprod.tymoshenko.task4.entity.ShoppingCart;

import java.time.LocalDateTime;
import java.util.Map;

public interface OrderHistoryService <T extends Product> {
    public OrdersHistory<T> getOrdersHistory();

    public boolean makeOrder(LocalDateTime date);

    public Map<LocalDateTime, ShoppingCart<T>>  getOrdersForPeriod(LocalDateTime from, LocalDateTime to);

    public Map<LocalDateTime, ShoppingCart<T>> getOrdersByNearestDate(LocalDateTime date);
}
