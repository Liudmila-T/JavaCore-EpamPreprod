package com.epam.preprod.tymoshenko.task5.part2.filter.impl;

import com.epam.preprod.tymoshenko.task5.part2.filter.Filter;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

public class FilterByNameTest {
    private File file = new File("resources/task5.txt");

    @Test
    public void filterByNameExpectedTrue() {
        Filter filterByName = new FilterByName(null, "task5");
        Assert.assertTrue(filterByName.filter(file));
    }
    @Test
    public void filterByNameExpectedFalse() {
        Filter filterByName = new FilterByName(null, "incorrectNameTask5");
        Assert.assertFalse(filterByName.filter(file));
    }

}