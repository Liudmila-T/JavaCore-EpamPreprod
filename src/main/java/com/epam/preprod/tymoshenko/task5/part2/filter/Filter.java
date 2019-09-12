package com.epam.preprod.tymoshenko.task5.part2.filter;

import java.io.File;

public abstract class Filter {
    private Filter filter;

    public Filter(Filter nextFilter) {
        this.filter = nextFilter;
    }

    public abstract boolean filter(File file);

    public boolean doFilter(File file) {
        if (filter != null) {
            return filter(file) && filter.doFilter(file);
        }
        return filter(file);
    }
}
