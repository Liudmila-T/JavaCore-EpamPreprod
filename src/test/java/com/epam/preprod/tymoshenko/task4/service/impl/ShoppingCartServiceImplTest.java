package com.epam.preprod.tymoshenko.task4.service.impl;

import com.epam.preprod.tymoshenko.task1.part1.Doll;
import com.epam.preprod.tymoshenko.task1.part1.Product;
import com.epam.preprod.tymoshenko.task4.entity.ShoppingCart;
import com.epam.preprod.tymoshenko.task4.entity.ShoppingCartHistory;
import com.epam.preprod.tymoshenko.task4.repository.ProductRepository;
import com.epam.preprod.tymoshenko.task4.service.ShoppingCartService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class ShoppingCartServiceImplTest {
    private Product product1;
    private Map<String, Integer> mockedCart;
    private ShoppingCartService<Product> shoppingCartService;

    @Mock
    ShoppingCart<Product> shoppingCart;

    @Mock
    ProductRepository<Product> productRepository;

    @Mock
    ShoppingCartHistory<Product> shoppingCartHistory;

    @Before
    public void before() {
        product1 = new Doll("1", "Barby1", 100, "China", "For girls", "white");

        List<Product> mockedRepository = new ArrayList<>();
        mockedRepository.add(product1);
        Mockito.when(productRepository.getProducts()).thenReturn(mockedRepository);

        shoppingCartService = new ShoppingCartServiceImpl<>(shoppingCart, productRepository, shoppingCartHistory);
    }

    @Test
    public void isEmpty() {
        mockedCart = new HashMap<>();
        Mockito.when(shoppingCart.getCart()).thenReturn(mockedCart);
        Assert.assertTrue(shoppingCartService.isEmpty());
    }

    @Test
    public void addProductToCart() {
        mockedCart = new HashMap<>();
        mockedCart.put(product1.getId(), 1);
        Mockito.when(shoppingCart.getCart()).thenReturn(mockedCart);

        shoppingCartService.addProductToCart(product1.getId());
        Assert.assertEquals(product1, shoppingCartService.getProductsById("1"));
    }

    @Test
    public void getProductsById() {
        Assert.assertEquals(product1, shoppingCartService.getProductsById("1"));
    }

    @Test
    public void getAmount() {
        mockedCart = new HashMap<>();
        mockedCart.put(product1.getId(), 1);
        Mockito.when(shoppingCart.getCart()).thenReturn(mockedCart);

        Assert.assertEquals(100.0,shoppingCartService.getAmount(),0);
    }
}