package com.epam.preprod.tymoshenko.task4.common.impl;

import com.epam.preprod.tymoshenko.task4.common.Printer;

public class ConsolePrinter implements Printer {
    @Override
    public void print(String string) {
        System.out.println(string);
    }

    @Override
    public void print(Object object) {
        System.out.println(object.toString());
    }
}
