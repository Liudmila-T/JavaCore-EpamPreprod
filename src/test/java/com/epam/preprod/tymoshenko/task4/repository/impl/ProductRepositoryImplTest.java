package com.epam.preprod.tymoshenko.task4.repository.impl;

import com.epam.preprod.tymoshenko.task1.part1.Doll;
import com.epam.preprod.tymoshenko.task1.part1.Product;
import com.epam.preprod.tymoshenko.task4.repository.ProductRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ProductRepositoryImplTest {

    private Product product1;
    private Product product2;
    private ProductRepository <Product> repository;

    @Before
    public void before() {
        product1 = new Doll("1", "Barby1", 100, "China", "For girls", "white");
        product2 = new Doll("2", "Barby2", 200, "China", "For baby", "black");

        repository = new ProductRepositoryImpl<>();
        repository.add(product1);
    }

    @Test
    public void getProducts() {
        Assert.assertEquals(product1,repository.getProducts().get(0));
    }

    @Test
    public void getProductById() {
        Assert.assertEquals(product1,repository.getProductById("1"));
    }

    @Test
    public void add() {
        Assert.assertTrue(repository.add(product2));
    }
}