package com.epam.preprod.tymoshenko.task5.part2.filter.impl;

import com.epam.preprod.tymoshenko.task5.part2.filter.Filter;

import java.io.File;

public class FilterByName extends Filter {
    private String fileName;

    public FilterByName(Filter nextFilter, String fileName) {
        super(nextFilter);
        this.fileName = fileName;
    }

    @Override
    public boolean filter(File file) {
        return file.getName().contains(fileName);
    }
}
