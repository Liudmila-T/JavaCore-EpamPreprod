package com.epam.preprod.tymoshenko.task4.common.impl;

import com.epam.preprod.tymoshenko.task4.common.Parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ParserImpl implements Parser {
    private static final String PATTERN_DATE_TIME = "yyyy-MM-dd HH:mm";

    @Override
    public  int parseStringToInt(String string)  {
        return Integer.parseInt(string);
    }

    @Override
    public LocalDateTime parseStringToLocalDateTime(String string)  {
        return LocalDateTime.parse(string, DateTimeFormatter.ofPattern(PATTERN_DATE_TIME));
    }
}
