package com.epam.preprod.tymoshenko.task4.service.impl;

import com.epam.preprod.tymoshenko.task1.part1.Doll;
import com.epam.preprod.tymoshenko.task1.part1.Product;
import com.epam.preprod.tymoshenko.task4.entity.ShoppingCart;
import com.epam.preprod.tymoshenko.task4.entity.ShoppingCartHistory;
import com.epam.preprod.tymoshenko.task4.service.ShoppingCartHistoryService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@RunWith(MockitoJUnitRunner.class)
public class ShoppingCartHistoryServiceImplTest {

    private Product product1;
    private Product product2;
    private Product product3;

    private Map<String, Product> mockedShoppingCartHistory;
    private Map<String, Product> expectedShoppingCartHistory;

    private ShoppingCartHistoryService<Product> shoppingCartHistoryService;

    @Mock
    ShoppingCartHistory<Product> shoppingCartHistory;

    @Before
    public void before() {
        product1 = new Doll("1", "Barby1", 100, "China", "For girls", "white");
        product2 = new Doll("2", "Barby2", 200, "China", "For girls", "white");
        product3 = new Doll("3", "Barby3", 300, "China", "For girls", "white");

        shoppingCartHistoryService = new ShoppingCartHistoryServiceImpl<>(shoppingCartHistory);

        expectedShoppingCartHistory = new LinkedHashMap<>();
        expectedShoppingCartHistory.put(product1.getId(), product1);
        expectedShoppingCartHistory.put(product2.getId(), product2);
    }

    @Test
    public void getShoppingCartHistory() {
        mockedShoppingCartHistory = new LinkedHashMap<>();
        mockedShoppingCartHistory.put(product1.getId(), product1);
        mockedShoppingCartHistory.put(product2.getId(), product2);

        Mockito.when(shoppingCartHistory.getHistory()).thenReturn(mockedShoppingCartHistory);

        Assert.assertEquals(expectedShoppingCartHistory,
                            shoppingCartHistoryService.getShoppingCartHistory().getHistory());
    }

    @Test
    public void add() {
        mockedShoppingCartHistory = new LinkedHashMap<>();
        mockedShoppingCartHistory.put(product1.getId(), product1);
        mockedShoppingCartHistory.put(product2.getId(), product2);
        mockedShoppingCartHistory.put(product3.getId(), product3);

        Mockito.when(shoppingCartHistory.getHistory()).thenReturn(mockedShoppingCartHistory);

        shoppingCartHistoryService.add(product3);

        Assert.assertTrue(shoppingCartHistoryService.getShoppingCartHistory().getHistory().containsKey(product3.getId()));
    }
}