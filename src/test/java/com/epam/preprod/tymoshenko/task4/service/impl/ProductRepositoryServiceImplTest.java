package com.epam.preprod.tymoshenko.task4.service.impl;

import com.epam.preprod.tymoshenko.task1.part1.Doll;
import com.epam.preprod.tymoshenko.task1.part1.Product;
import com.epam.preprod.tymoshenko.task4.repository.ProductRepository;
import com.epam.preprod.tymoshenko.task4.service.ProductRepositoryService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;


@RunWith(MockitoJUnitRunner.class)
public class ProductRepositoryServiceImplTest {
    private Product product1;
    private Product product2;
    private List<Product> mockedRepository;
    private List<Product> expectedRepository;
    @Mock
    ProductRepository<Product> repository;

    @Mock
    ProductRepositoryService repositoryService;

    @Before
    public void before() {
        product1 = new Doll("1", "Barby1", 100, "China", "For girls", "white");
        product2 = new Doll("2", "Barby2", 200, "China", "For baby", "black");

        repositoryService = new ProductRepositoryServiceImpl<>(repository);

        expectedRepository = new ArrayList<>();
        expectedRepository.add(product1);
        expectedRepository.add(product2);
    }

    @Test
    public void getProductRepository() {
        mockedRepository = new ArrayList<>();
        mockedRepository.add(product1);
        mockedRepository.add(product2);

        Mockito.when(repository.getProducts()).thenReturn(mockedRepository);

        Assert.assertEquals(expectedRepository, repositoryService.getProductRepository().getProducts());
        Mockito.verify(repository).getProducts();
    }
}