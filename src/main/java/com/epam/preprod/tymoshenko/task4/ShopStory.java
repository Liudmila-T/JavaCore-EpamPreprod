package com.epam.preprod.tymoshenko.task4;

import com.epam.preprod.tymoshenko.task1.part1.Doll;
import com.epam.preprod.tymoshenko.task1.part1.Product;
import com.epam.preprod.tymoshenko.task4.controller.ShopController;
import com.epam.preprod.tymoshenko.task4.repository.ProductRepository;
import com.epam.preprod.tymoshenko.task4.repository.impl.ProductRepositoryImpl;

public class ShopStory {

    public static void main(String[] args) {

        ProductRepository<Product> productRepository =initShopRepository();

        ShopController shopController = ShopController.getInstance(productRepository);
        shopController.start();
    }


    private static ProductRepository<Product> initShopRepository() {
        ProductRepository<Product> productRepository = new ProductRepositoryImpl<>();
        productRepository.add(new Doll("1", "Barby1", 100, "China", "For girls", "white"));
        productRepository.add(new Doll("2", "Barby2", 200, "China", "For baby", "black"));
        productRepository.add(new Doll("3", "Barby3", 300, "China", "For girls", "blue"));
        productRepository.add(new Doll("4", "Barby4", 400, "China", "For baby", "red"));
        productRepository.add(new Doll("5", "Barby5", 500, "China", "For girls", "yellow"));
        productRepository.add(new Doll("6", "Barby6", 600, "China", "For baby", "green"));
        productRepository.add(new Doll("7", "Barby7", 700, "China", "For girls", "white"));
        productRepository.add(new Doll("8", "Barby8", 800, "China", "For girls", "white"));
        return productRepository;
    }

}
