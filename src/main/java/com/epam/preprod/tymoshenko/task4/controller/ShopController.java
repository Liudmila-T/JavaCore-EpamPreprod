package com.epam.preprod.tymoshenko.task4.controller;

import com.epam.preprod.tymoshenko.task1.part1.Product;
import com.epam.preprod.tymoshenko.task4.commands.Command;
import com.epam.preprod.tymoshenko.task4.commands.CommandContainer;
import com.epam.preprod.tymoshenko.task4.common.Reader;
import com.epam.preprod.tymoshenko.task4.common.Parser;
import com.epam.preprod.tymoshenko.task4.common.impl.ConsoleReader;
import com.epam.preprod.tymoshenko.task4.common.impl.ParserImpl;
import com.epam.preprod.tymoshenko.task4.entity.OrdersHistory;
import com.epam.preprod.tymoshenko.task4.entity.ShoppingCart;
import com.epam.preprod.tymoshenko.task4.entity.ShoppingCartHistory;
import com.epam.preprod.tymoshenko.task4.repository.ProductRepository;
import com.epam.preprod.tymoshenko.task4.service.ProductRepositoryService;
import com.epam.preprod.tymoshenko.task4.service.OrderHistoryService;
import com.epam.preprod.tymoshenko.task4.service.ShoppingCartHistoryService;
import com.epam.preprod.tymoshenko.task4.service.ShoppingCartService;
import com.epam.preprod.tymoshenko.task4.service.impl.ProductRepositoryServiceImpl;
import com.epam.preprod.tymoshenko.task4.service.impl.OrdersHistoryServiceImpl;
import com.epam.preprod.tymoshenko.task4.service.impl.ShoppingCartHistoryServiceImpl;
import com.epam.preprod.tymoshenko.task4.service.impl.ShoppingCartServiceImpl;

import static com.epam.preprod.tymoshenko.task4.common.Utils.*;


public class ShopController {
    private static ShopController instance;

    private ShoppingCart<Product> cart;
    private ShoppingCartHistory<Product> shoppingCartHistory;
    private OrdersHistory <Product> order;
    private ProductRepository<Product> productRepository;
    private CommandContainer commandContainer;

    private ShoppingCartService<Product> cartService;
    private ProductRepositoryService<Product> productRepositoryService;
    private ShoppingCartHistoryService<Product> shoppingCartHistoryService;
    private OrderHistoryService<Product> ordersHistoryService;
    private Parser parser;
    private Reader reader;

    private ShopController(ProductRepository<Product> productRepository) {
        this.productRepository = productRepository;
        cart = new ShoppingCart<>();
        order = new OrdersHistory<>();
        shoppingCartHistory = new ShoppingCartHistory<>();
        cartService = new ShoppingCartServiceImpl<>(cart, productRepository, shoppingCartHistory);
        productRepositoryService = new ProductRepositoryServiceImpl<>(productRepository);
        shoppingCartHistoryService = new ShoppingCartHistoryServiceImpl<>(shoppingCartHistory);
        ordersHistoryService = new OrdersHistoryServiceImpl<>(cart, order);
        commandContainer=new CommandContainer();
        parser = new ParserImpl();
        reader = new ConsoleReader();
    }

    public synchronized static ShopController getInstance(ProductRepository<Product> productRepository) {
        if (instance == null) {
            instance = new ShopController(productRepository);
        }
        return instance;
    }

    public void start() {
        int choice = MAIN_MENU;
        while (choice != EXIT) {
            startCommand(choice);
            choice = parser.parseStringToInt(reader.read());
        }
    }

    private void startCommand(Integer actionNum) {
        Command command;
        if (actionNum < EXIT || actionNum > MAIN_MENU) {
            command = commandContainer.getCommand(MAIN_MENU);
            command.execute(instance);
        } else {
            command = commandContainer.getCommand(actionNum);
            command.execute(instance);
        }
        if (actionNum != MAIN_MENU) {
            command = commandContainer.getCommand(MAIN_MENU);
            command.execute(instance);
        }
    }
    public String getMenu() {
        return  "          Menu:" + LINE_SEPARATOR +
                "0. Exit" + LINE_SEPARATOR +
                "1. Get a list of products" + LINE_SEPARATOR +
                "2. Add product to cart" + LINE_SEPARATOR +
                "3. Get the cart" + LINE_SEPARATOR +
                "4. Make an order" + LINE_SEPARATOR +
                "5. Get 5 latest products added to the cart" + LINE_SEPARATOR +
                "6. Get an order for a predetermined period of time" + LINE_SEPARATOR +
                "7. Get a list of order from the nearest date" + LINE_SEPARATOR;
    }

    public ShoppingCartService<Product> getCartService() {
        return cartService;
    }

    public ProductRepositoryService<Product> getProductRepositoryService() {
        return productRepositoryService;
    }

    public ShoppingCartHistoryService<Product> getShoppingCartHistoryService() {
        return shoppingCartHistoryService;
    }

    public OrderHistoryService<Product> getOrdersHistoryService() {
        return ordersHistoryService;
    }
}
