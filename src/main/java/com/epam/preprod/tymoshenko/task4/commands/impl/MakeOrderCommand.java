package com.epam.preprod.tymoshenko.task4.commands.impl;

import com.epam.preprod.tymoshenko.task4.commands.Command;
import com.epam.preprod.tymoshenko.task4.controller.ShopController;

public class MakeOrderCommand extends Command {

    @Override
    public void execute(ShopController shopController) {
        printer.print("Enter order date in the format 'yyyy-MM-dd HH:mm'");
        shopController.getOrdersHistoryService().makeOrder(parser.parseStringToLocalDateTime(reader.read()));
        printer.print("Amount of orders = " + shopController.getCartService().getAmount());

    }
}
