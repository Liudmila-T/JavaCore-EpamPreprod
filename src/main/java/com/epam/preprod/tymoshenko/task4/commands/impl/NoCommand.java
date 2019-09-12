package com.epam.preprod.tymoshenko.task4.commands.impl;

import com.epam.preprod.tymoshenko.task4.commands.Command;
import com.epam.preprod.tymoshenko.task4.controller.ShopController;


public class NoCommand extends Command {

    @Override
    public void execute(ShopController shopController) {
        printer.print("No such command");
    }
}
