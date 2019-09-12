package com.epam.preprod.tymoshenko.task4.commands.impl;

import com.epam.preprod.tymoshenko.task4.commands.Command;
import com.epam.preprod.tymoshenko.task4.controller.ShopController;

public class AddProductToCartCommand extends Command {

    @Override
    public void execute(ShopController shopController) {

        printer.print("Enter Product ID:");
        shopController.getCartService().
                addProductToCart((reader.read()));
    }
}
