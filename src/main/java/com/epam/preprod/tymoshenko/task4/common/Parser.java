package com.epam.preprod.tymoshenko.task4.common;

import java.time.LocalDateTime;

public interface Parser {

    public int parseStringToInt(String string);

    public LocalDateTime parseStringToLocalDateTime(String string);


}
