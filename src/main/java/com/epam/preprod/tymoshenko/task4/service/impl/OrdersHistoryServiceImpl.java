package com.epam.preprod.tymoshenko.task4.service.impl;

import com.epam.preprod.tymoshenko.task1.part1.Product;
import com.epam.preprod.tymoshenko.task4.entity.OrdersHistory;
import com.epam.preprod.tymoshenko.task4.entity.ShoppingCart;
import com.epam.preprod.tymoshenko.task4.service.OrderHistoryService;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class OrdersHistoryServiceImpl<T extends Product> implements OrderHistoryService<T> {

    private ShoppingCart<T> cart;
    private OrdersHistory<T> ordersHistory;

    public OrdersHistoryServiceImpl(ShoppingCart<T> cart, OrdersHistory<T> ordersHistory) {
        this.cart = cart;
        this.ordersHistory = ordersHistory;
    }

    public OrdersHistory<T> getOrdersHistory() {
        return ordersHistory;
    }

    @Override
    public boolean makeOrder(LocalDateTime date) {
        ShoppingCart<T> clone = cloneShoppingCart();
        if (!clone.getCart().isEmpty()) {
            ordersHistory.add(date, clone);
            return true;
        }
        return false;
    }

    private ShoppingCart<T> cloneShoppingCart() {
        return new ShoppingCart<>(cart.getCart());
    }

    @Override
    public Map<LocalDateTime, ShoppingCart<T>> getOrdersForPeriod(LocalDateTime from, LocalDateTime to) {
        return ordersHistory.getOrders().subMap(from, true, to, true);
    }

    @Override
    public Map<LocalDateTime, ShoppingCart<T>> getOrdersByNearestDate(LocalDateTime date) {

        if (ordersHistory.getOrders().isEmpty()) {
            return null;
        }
        LocalDateTime lessDate = getLessDate(date);
        LocalDateTime moreDate = getMoreDate(date);

        if (lessDate == null) {
            return getOrdersByDate(moreDate);
        }
        if (moreDate == null) {
            return getOrdersByDate(lessDate);
        }
        return getLocalDateTimeShoppingCartMap(date, lessDate, moreDate);
    }

    private LocalDateTime getLessDate(LocalDateTime date) {
        return ordersHistory.getOrders().lowerKey(date);
    }

    private LocalDateTime getMoreDate(LocalDateTime date) {
        return ordersHistory.getOrders().ceilingKey(date);
    }

    private Map<LocalDateTime, ShoppingCart<T>> getLocalDateTimeShoppingCartMap(LocalDateTime date,
                                                                                LocalDateTime lessDate,
                                                                                LocalDateTime moreDate) {

        long quantityDaysToLess = getQuantityDays(date, lessDate);
        long quantityDaysToMore = getQuantityDays(moreDate, date);

        if (quantityDaysToLess > quantityDaysToMore) {
            return getOrdersByDate(moreDate);
        } else if (quantityDaysToLess == quantityDaysToMore) {
            return getLocalDateTimeShoppingCartMap(lessDate, moreDate);
        }
        return getOrdersByDate(lessDate);
    }

    private Map<LocalDateTime, ShoppingCart<T>> getLocalDateTimeShoppingCartMap(LocalDateTime lessDate,
                                                                                LocalDateTime moreDate) {
        Map<LocalDateTime, ShoppingCart<T>> result = new TreeMap<>();
        result.putAll(getOrdersByDate(moreDate));
        result.putAll(getOrdersByDate(lessDate));
        return result;
    }

    private long getQuantityDays(LocalDateTime moreDate, LocalDateTime lessDate) {
        return moreDate.toLocalDate().toEpochDay() - lessDate.toLocalDate().toEpochDay();
    }

    private Map<LocalDateTime, ShoppingCart<T>> getOrdersByDate(LocalDateTime dateTime) {
        return ordersHistory.getOrders().entrySet().stream()
                .filter(x -> x.getKey().getDayOfYear() == dateTime.getDayOfYear())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    @Override
    public String toString() {
        return "Orders History{" +
                ordersHistory +
                '}';
    }
}
