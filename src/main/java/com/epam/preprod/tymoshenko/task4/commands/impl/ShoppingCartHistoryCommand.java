package com.epam.preprod.tymoshenko.task4.commands.impl;

import com.epam.preprod.tymoshenko.task4.commands.Command;
import com.epam.preprod.tymoshenko.task4.controller.ShopController;

public class ShoppingCartHistoryCommand extends Command {

    @Override
    public void execute(ShopController shopController) {
        printer.print(shopController.getShoppingCartHistoryService().getShoppingCartHistory());
    }
}
