package com.epam.preprod.tymoshenko.task4.commands;

import com.epam.preprod.tymoshenko.task4.commands.impl.*;

import java.util.HashMap;
import java.util.Map;

public class CommandContainer {
    private Map<Integer, Command> commands;

    public CommandContainer() {
        commands = new HashMap<>();
        commands.put(0, new NoCommand());
        commands.put(1, new ViewCatalogCommand());
        commands.put(2, new AddProductToCartCommand());
        commands.put(3, new ViewCartCommand());
        commands.put(4, new MakeOrderCommand());
        commands.put(5, new ShoppingCartHistoryCommand());
        commands.put(6, new GetOrdersByDatePeriodCommand());
        commands.put(7, new GetOrdersByNearestDateCommand());
        commands.put(8, new MainMenuCommand());

    }

    public Command getCommand(int index) {
       return commands.get(index);
    }
}
