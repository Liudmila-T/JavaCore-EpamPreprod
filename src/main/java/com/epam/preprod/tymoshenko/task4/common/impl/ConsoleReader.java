package com.epam.preprod.tymoshenko.task4.common.impl;

import com.epam.preprod.tymoshenko.task4.common.Reader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleReader implements Reader {

    @Override
    public String read() {
        String input = "";
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try{
            input = br.readLine();
        } catch(IOException e) {
            e.printStackTrace();
        }
        return input;
    }

}
