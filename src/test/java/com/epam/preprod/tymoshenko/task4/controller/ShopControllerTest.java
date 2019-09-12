package com.epam.preprod.tymoshenko.task4.controller;

import com.epam.preprod.tymoshenko.task1.part1.Doll;
import com.epam.preprod.tymoshenko.task1.part1.Product;
import com.epam.preprod.tymoshenko.task4.entity.OrdersHistory;
import com.epam.preprod.tymoshenko.task4.entity.ShoppingCart;
import com.epam.preprod.tymoshenko.task4.entity.ShoppingCartHistory;
import com.epam.preprod.tymoshenko.task4.repository.ProductRepository;
import com.epam.preprod.tymoshenko.task4.service.OrderHistoryService;
import com.epam.preprod.tymoshenko.task4.service.impl.OrdersHistoryServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static com.epam.preprod.tymoshenko.task4.common.Utils.LINE_SEPARATOR;

@RunWith(MockitoJUnitRunner.class)
public class ShopControllerTest {
    private static final LocalDateTime ORDER_DATE_TIME =
            LocalDateTime.parse("2019-05-26 22:05", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    private Product product1;
    private ShopController shopController;

    @Mock
    ProductRepository<Product> productRepository;

    @Mock
    ShoppingCart<Product> shoppingCart;

    @Mock
    ShoppingCartHistory<Product> shoppingCartHistory;

    @Mock
    OrdersHistory<Product> ordersHistory;

    @Before
    public void before() {
        product1 = new Doll("1", "Barby1", 100, "China", "For girls", "white");

        List<Product> mockedRepository = new ArrayList<>();
        mockedRepository.add(product1);
        Mockito.when(productRepository.getProducts()).thenReturn(mockedRepository);

        shopController = ShopController.getInstance(productRepository);
    }

    @Test
    public void getMenu() {
        String menu = "          Menu:" + LINE_SEPARATOR +
                "0. Exit" + LINE_SEPARATOR +
                "1. Get a list of products" + LINE_SEPARATOR +
                "2. Add product to cart" + LINE_SEPARATOR +
                "3. Get the cart" + LINE_SEPARATOR +
                "4. Make an order" + LINE_SEPARATOR +
                "5. Get 5 latest products added to the cart" + LINE_SEPARATOR +
                "6. Get an order for a predetermined period of time" + LINE_SEPARATOR +
                "7. Get a list of order from the nearest date" + LINE_SEPARATOR;
        Assert.assertEquals(menu, shopController.getMenu());
    }

    @Test
    public void getCartService() {
        Assert.assertEquals(product1, shopController.getCartService().getProductsById(product1.getId()));
    }

    @Test
    public void getProductRepositoryService() {
        List<Product> expectedRepository = new ArrayList<>();
        expectedRepository.add(product1);

        Assert.assertEquals(expectedRepository,
                shopController.getProductRepositoryService().getProductRepository().getProducts());
    }

    @Test
    public void getShoppingCartHistoryService() {
        ShoppingCartHistory<Product> expectedShoppingCartHistory = new ShoppingCartHistory<>();
        expectedShoppingCartHistory.add(product1);

        Assert.assertEquals(expectedShoppingCartHistory,
                shopController.getShoppingCartHistoryService().getShoppingCartHistory().getHistory());
    }

    @Test
    public void getOrdersHistoryService() {

        NavigableMap<LocalDateTime, ShoppingCart<Product>> expectedOrderHistory = new TreeMap<>();
        expectedOrderHistory.put(ORDER_DATE_TIME, shoppingCart);

        shopController.getOrdersHistoryService().getOrdersHistory().add(ORDER_DATE_TIME,shoppingCart);
       Assert.assertEquals(expectedOrderHistory,
                shopController.getOrdersHistoryService().getOrdersHistory().getOrders());
    }
}
