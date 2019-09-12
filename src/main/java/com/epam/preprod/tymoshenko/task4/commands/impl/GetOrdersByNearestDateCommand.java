package com.epam.preprod.tymoshenko.task4.commands.impl;

import com.epam.preprod.tymoshenko.task4.commands.Command;
import com.epam.preprod.tymoshenko.task4.controller.ShopController;

public class GetOrdersByNearestDateCommand extends Command {

    @Override
    public void execute(ShopController shopController) {
        printer.print("Enter date of pattern 'yyyy-MM-dd HH-mm'");
        printer.print(shopController.getOrdersHistoryService()
                .getOrdersByNearestDate(parser.parseStringToLocalDateTime(reader.read())));
    }
}
