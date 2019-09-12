package com.epam.preprod.tymoshenko.task5.part2.filter.impl;

import com.epam.preprod.tymoshenko.task5.part2.filter.Filter;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;

public class FilterByExtensionTest {
    private File file = new File("resources/task5.txt");

    @Test
    public void filterByExtensionExpectedTrue() {
        Filter filterByExtension = new FilterByExtension(null, "txt");
        Assert.assertTrue(filterByExtension.filter(file));
    }
    @Test
    public void filterByExtensionExpectedFalse() {
        Filter filterByExtension = new FilterByExtension(null, "jpg");
        Assert.assertFalse(filterByExtension.filter(file));
    }
}