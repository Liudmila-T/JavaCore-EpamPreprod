package com.epam.preprod.tymoshenko.task5.part2.filter.impl;

import com.epam.preprod.tymoshenko.task5.part2.filter.Filter;

import java.io.File;

public class FilterBySize extends Filter {
    private long startSize;
    private long endSize;

    public FilterBySize(Filter nextFilter, long startSize, long endSize) {
        super(nextFilter);
        this.startSize = startSize;
        this.endSize = endSize;
    }

    @Override
    public boolean filter(File file) {
        long size = file.length();
        return startSize <= size && size <= endSize;
    }
}
