package com.epam.preprod.tymoshenko.task4.service.impl;

import com.epam.preprod.tymoshenko.task1.part1.Product;
import com.epam.preprod.tymoshenko.task4.entity.ShoppingCartHistory;
import com.epam.preprod.tymoshenko.task4.repository.ProductRepository;
import com.epam.preprod.tymoshenko.task4.entity.ShoppingCart;
import com.epam.preprod.tymoshenko.task4.service.ShoppingCartService;

import java.util.Map;

public class ShoppingCartServiceImpl<T extends Product> implements ShoppingCartService<T> {

    private static final int MIN_QUANTITY = 1;
    private static final String EXCEPTION_PRODUCT_MUST_BE_IN_THE_CATALOG = "The Product must be in the Catalog Product";

    private ShoppingCart<T> cart;
    private ProductRepository<T> productRepository;
    private ShoppingCartHistory<T> shoppingCartHistory;

    public ShoppingCartServiceImpl(ShoppingCart<T> cart, ProductRepository<T> productRepository,
                                   ShoppingCartHistory<T> shoppingCartHistory) {
        this.cart = cart;
        this.productRepository = productRepository;
        this.shoppingCartHistory = shoppingCartHistory;
    }

    @Override
    public boolean isEmpty() {
        return cart.getCart().isEmpty();
    }

    @Override
    public void addProductToCart(String productId) {
        checkIdProduct(productId);
        if (!updateQuantityIfCartContainsProductId(productId)) {
            cart.addProductToCart(productId, MIN_QUANTITY);
        }
        shoppingCartHistory.add(productRepository.getProductById(productId));
           }


    private boolean updateQuantityIfCartContainsProductId(String productId) {
        for (Map.Entry<String, Integer> elementCart : cart.getCart().entrySet()) {
            if (elementCart.getKey().equals(productId)) {
                elementCart.setValue(elementCart.getValue() + MIN_QUANTITY);
                return true;
            }
        }
        return false;
    }

    @Override
    public T getProductsById(String productId) {
        for (T product : productRepository.getProducts()) {
            if (product.getId().equals(productId)) {
                return product;
            }
        }
        return null;
    }

    @Override
    public double getAmount() {
        double amount = cart.getCart().entrySet().stream()
                .mapToDouble(x -> (getProductsById(x.getKey()).getPrice() * x.getValue()))
                .sum();
        cart = new ShoppingCart<>();
        return amount;
    }


    private void checkIdProduct(String productId) throws IllegalArgumentException {
        if (!productRepository.getProducts().contains(getProductsById(productId))) {
            throw new IllegalArgumentException(EXCEPTION_PRODUCT_MUST_BE_IN_THE_CATALOG);
        }
    }

    @Override
    public String toString() {
        return "Shopping Cart{" +
                cart +
                '}';
    }
}
