package com.epam.preprod.tymoshenko.task5.part2.filter.impl;

import com.epam.preprod.tymoshenko.task5.part2.filter.Filter;

import java.io.File;

public class FilterByExtension extends Filter {
    private String extension;

    public FilterByExtension(Filter nextFilter, String extension) {
        super(nextFilter);
        this.extension = extension;
    }

    @Override
    public boolean filter(File file) {
       return file.getName().endsWith(extension);
    }
}
