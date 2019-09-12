package com.epam.preprod.tymoshenko.task5.part2;

import com.epam.preprod.tymoshenko.task5.part2.filter.Filter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileFinder {
    private Filter filter;

    public FileFinder(Filter filter) {
        this.filter = filter;
    }

    public List<File> finderByFilter(File pathFile) {
        List<File> result = new ArrayList<>();
        if (pathFile.isDirectory()) {
            File[] listFiles = pathFile.listFiles();

            if (listFiles != null) {
                for (int i = 0; i < listFiles.length; i++) {
                   result.addAll(finderByFilter(listFiles[i]));
                }
            }
        } else if (filter != null && filter.doFilter(pathFile)) {
            result.add(pathFile);
        }
        return result;
    }
}
