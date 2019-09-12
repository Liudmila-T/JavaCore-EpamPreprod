package com.epam.preprod.tymoshenko.task4.service.impl;

import com.epam.preprod.tymoshenko.task1.part1.Doll;
import com.epam.preprod.tymoshenko.task1.part1.Product;
import com.epam.preprod.tymoshenko.task4.common.impl.ParserImpl;
import com.epam.preprod.tymoshenko.task4.entity.OrdersHistory;
import com.epam.preprod.tymoshenko.task4.entity.ShoppingCart;
import com.epam.preprod.tymoshenko.task4.service.OrderHistoryService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class OrdersHistoryServiceImplTest {
    private Product product1;
    private Product product2;
    private Map<String, Integer> mockedCart;
    private NavigableMap<LocalDateTime, ShoppingCart<Product>> mockedOrderHistory;

    private OrderHistoryService<Product> orderHistoryService;

    @Mock
    ShoppingCart<Product> shoppingCart;

    @Mock
    OrdersHistory<Product> ordersHistory;


    @Before
    public void before() {
        product1 = new Doll("1", "Barby1", 100, "China", "For girls", "white");
        product2 = new Doll("2", "Barby2", 200, "China", "For girls", "white");
        orderHistoryService= new OrdersHistoryServiceImpl<>(shoppingCart, ordersHistory);

    }

    @Test
    public void makeOrderExpectedTrue() {
        mockedCart = new HashMap<>();
        mockedCart.put(product1.getId(), 1);
        mockedCart.put(product2.getId(), 1);
        Mockito.when(shoppingCart.getCart()).thenReturn(mockedCart);

        Assert.assertTrue(orderHistoryService.makeOrder(LocalDateTime.now()));
        Mockito.verify(shoppingCart).getCart();
    }

    @Test
    public void makeOrderExpectedFalse() {
        mockedCart = new HashMap<>();
        Mockito.when(shoppingCart.getCart()).thenReturn(mockedCart);

        Assert.assertFalse(orderHistoryService.makeOrder(LocalDateTime.now()));
        Mockito.verify(shoppingCart).getCart();
    }

    @Test
    public void getOrdersForPeriod() {
        LocalDateTime dateOrder1=new ParserImpl().parseStringToLocalDateTime("2012-12-12 12:12");
        LocalDateTime dateOrder2=new ParserImpl().parseStringToLocalDateTime("2012-12-13 12:12");
        LocalDateTime dateOrder3=new ParserImpl().parseStringToLocalDateTime("2012-12-14 12:12");
        LocalDateTime dateOrder4=new ParserImpl().parseStringToLocalDateTime("2012-12-15 12:12");

        mockedOrderHistory=new TreeMap<>();
        mockedOrderHistory.put(dateOrder1, shoppingCart);
        mockedOrderHistory.put(dateOrder2, shoppingCart);
        mockedOrderHistory.put(dateOrder3, shoppingCart);
        mockedOrderHistory.put(dateOrder4, shoppingCart);
        Mockito.when(ordersHistory.getOrders()).thenReturn(mockedOrderHistory);

        Assert.assertEquals(3, orderHistoryService.getOrdersForPeriod(dateOrder2,dateOrder4).size());
    }

    @Test
    public void getOrdersByNearestDate() {
            LocalDateTime dateOrder1=new ParserImpl().parseStringToLocalDateTime("2012-12-12 12:12");
            LocalDateTime dateOrder2=new ParserImpl().parseStringToLocalDateTime("2012-12-13 12:12");
            LocalDateTime dateOrder3=new ParserImpl().parseStringToLocalDateTime("2012-12-13 00:00");
            LocalDateTime dateOrder4=new ParserImpl().parseStringToLocalDateTime("2012-12-15 12:12");

            mockedOrderHistory=new TreeMap<>();
            mockedOrderHistory.put(dateOrder1, shoppingCart);
            mockedOrderHistory.put(dateOrder2, shoppingCart);
            mockedOrderHistory.put(dateOrder3, shoppingCart);
            mockedOrderHistory.put(dateOrder4, shoppingCart);
            Mockito.when(ordersHistory.getOrders()).thenReturn(mockedOrderHistory);

            Assert.assertEquals(2, orderHistoryService.getOrdersByNearestDate(dateOrder2).size());
    }
}