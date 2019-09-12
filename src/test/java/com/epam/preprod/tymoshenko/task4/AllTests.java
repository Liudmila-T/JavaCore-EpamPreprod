package com.epam.preprod.tymoshenko.task4;


import com.epam.preprod.tymoshenko.task4.controller.ShopControllerTest;
import com.epam.preprod.tymoshenko.task4.service.impl.OrdersHistoryServiceImplTest;
import com.epam.preprod.tymoshenko.task4.service.impl.ProductRepositoryServiceImplTest;
import com.epam.preprod.tymoshenko.task4.service.impl.ShoppingCartHistoryServiceImplTest;
import com.epam.preprod.tymoshenko.task4.service.impl.ShoppingCartServiceImplTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({ShopControllerTest.class,
        ProductRepositoryServiceImplTest.class, OrdersHistoryServiceImplTest.class,
        ProductRepositoryServiceImplTest.class, ShoppingCartHistoryServiceImplTest.class,
        ShoppingCartServiceImplTest.class})
public class AllTests {
}
