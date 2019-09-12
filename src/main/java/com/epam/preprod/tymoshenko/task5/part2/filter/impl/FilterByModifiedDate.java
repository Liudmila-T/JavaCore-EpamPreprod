package com.epam.preprod.tymoshenko.task5.part2.filter.impl;

import com.epam.preprod.tymoshenko.task5.part2.filter.Filter;

import java.io.File;
import java.util.Date;


public class FilterByModifiedDate extends Filter {
    private Date dateFrom;
    private Date dateTo;

    public FilterByModifiedDate(Filter nextFilter, Date dateFrom, Date dateTo) {
        super(nextFilter);
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }

    @Override
    public boolean filter(File file) {
        long dateTime = file.lastModified();
        return dateFrom.getTime() <= dateTime && dateTime <= dateTo.getTime();
    }
}
