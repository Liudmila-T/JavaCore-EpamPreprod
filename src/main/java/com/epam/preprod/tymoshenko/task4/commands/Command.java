package com.epam.preprod.tymoshenko.task4.commands;

import com.epam.preprod.tymoshenko.task4.common.Parser;
import com.epam.preprod.tymoshenko.task4.common.impl.ConsoleReader;
import com.epam.preprod.tymoshenko.task4.common.impl.ParserImpl;
import com.epam.preprod.tymoshenko.task4.common.Printer;
import com.epam.preprod.tymoshenko.task4.common.Reader;
import com.epam.preprod.tymoshenko.task4.common.impl.ConsolePrinter;
import com.epam.preprod.tymoshenko.task4.controller.ShopController;


public  abstract class Command {
    protected Printer printer;
    protected Reader reader;
    protected Parser parser;

    public Command() {
        this.printer = new ConsolePrinter();
        this.reader=new ConsoleReader();
        this.parser=new ParserImpl();

    }

    public abstract void execute(ShopController shopController);
}
