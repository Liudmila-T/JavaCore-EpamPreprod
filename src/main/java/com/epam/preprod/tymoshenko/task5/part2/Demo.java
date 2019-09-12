package com.epam.preprod.tymoshenko.task5.part2;

import com.epam.preprod.tymoshenko.task4.inputOutput.impl.ConsolePrinter;
import com.epam.preprod.tymoshenko.task4.inputOutput.impl.ConsoleReader;
import com.epam.preprod.tymoshenko.task4.parser.ParserImpl;

import java.io.File;

public class Demo {

    public static void main(String[] args) {

        ConsoleInterface consoleInterface =
                new ConsoleInterface(new ParserImpl(), new ConsoleReader(), new ConsolePrinter());
        consoleInterface.start();
    }
}
