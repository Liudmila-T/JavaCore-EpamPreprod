package com.epam.preprod.tymoshenko.task5.part2.filter.impl;

import com.epam.preprod.tymoshenko.task4.parser.ParserImpl;
import com.epam.preprod.tymoshenko.task5.part2.filter.Filter;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.util.Date;

public class FilterByModifiedDateTest {
    private File file = new File("src\\test\\resources\\task5.txt");
    private Date fromDate = new ParserImpl().parseStringToDate("2019-05-01");
    private Date toDate = new ParserImpl().parseStringToDate("2019-06-30");
    private Date toDateFalse = new ParserImpl().parseStringToDate("2019-05-02");

    @Test
    public void filterByModifiedDateExpectedTrue() {
        Filter filterModifiedDate = new FilterByModifiedDate(null, fromDate, toDate);
        Assert.assertTrue(filterModifiedDate.filter(file));
    }

    @Test
    public void filterByModifiedDateExpectedFalse() {
        Filter filterModifiedDate = new FilterByModifiedDate(null, fromDate, toDateFalse);
        Assert.assertFalse(filterModifiedDate.filter(file));
    }

}