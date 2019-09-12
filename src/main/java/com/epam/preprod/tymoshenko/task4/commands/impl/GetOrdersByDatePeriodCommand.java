package com.epam.preprod.tymoshenko.task4.commands.impl;

import com.epam.preprod.tymoshenko.task4.commands.Command;
import com.epam.preprod.tymoshenko.task4.controller.ShopController;

import java.time.LocalDateTime;

public class GetOrdersByDatePeriodCommand extends Command {

      @Override
    public void execute(ShopController shopController) {
        printer.print("Enter date from");
        LocalDateTime from=parser.parseStringToLocalDateTime(reader.read());
          printer.print("Enter date to");
        LocalDateTime to=parser.parseStringToLocalDateTime(reader.read());
        printer.print(shopController.getOrdersHistoryService().
               getOrdersForPeriod(from, to));
    }
}
