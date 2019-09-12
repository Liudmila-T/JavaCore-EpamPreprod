package com.epam.preprod.tymoshenko.task5.part2.filter.impl;

import com.epam.preprod.tymoshenko.task5.part2.filter.Filter;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;

public class FilterBySizeTest {
    private File file = new File("src\\test\\resources\\task5.txt");
    private long sizeFrom = 0;
    private long sizeTo =file.getTotalSpace();

    @Test
    public void filterBySizeExpectedTrue() {
        Filter filterSize = new FilterBySize(null, sizeFrom, sizeTo);
        Assert.assertTrue(filterSize.filter(file));
    }

    @Test
    public void filterBySizeExpectedFalse() {
        long sizeToFalse = 1;
        Filter filterSize = new FilterBySize(null, sizeFrom, sizeToFalse);
        Assert.assertFalse(filterSize.filter(file));
    }

}