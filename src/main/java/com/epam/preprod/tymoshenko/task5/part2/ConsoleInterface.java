package com.epam.preprod.tymoshenko.task5.part2;

import com.epam.preprod.tymoshenko.task4.inputOutput.Printer;
import com.epam.preprod.tymoshenko.task4.inputOutput.Reader;
import com.epam.preprod.tymoshenko.task4.inputOutput.impl.ConsolePrinter;
import com.epam.preprod.tymoshenko.task4.inputOutput.impl.ConsoleReader;
import com.epam.preprod.tymoshenko.task4.parser.Parser;
import com.epam.preprod.tymoshenko.task4.parser.ParserImpl;
import com.epam.preprod.tymoshenko.task5.part2.filter.Filter;
import com.epam.preprod.tymoshenko.task5.part2.filter.impl.FilterByExtension;
import com.epam.preprod.tymoshenko.task5.part2.filter.impl.FilterByModifiedDate;
import com.epam.preprod.tymoshenko.task5.part2.filter.impl.FilterByName;
import com.epam.preprod.tymoshenko.task5.part2.filter.impl.FilterBySize;

import java.io.File;
import java.util.Date;
import java.util.List;

public class ConsoleInterface {
    private Parser parser;
    private Reader reader;
    private Printer printer;
    private Filter filter;

    public ConsoleInterface(Parser parser, Reader reader, Printer printer) {
        this.parser = parser;
        this.reader = reader;
        this.printer = printer;
    }

    public void start() {
        File filePath = new File(enterDirName());
        if (filePath.isDirectory()) {
            enterFileName();
            enterFileExtension();
            enterFileSize();
            enterFileDateModified();
            if (filter != null) {
                FileFinder fileFinder = new FileFinder(filter);
                List<File> filesList = fileFinder.finderByFilter(filePath);
                for (File file : filesList) {
                    printer.print(file.getAbsolutePath());
                }
            } else {
                printer.print("No filter specified. Nothing to show");
            }
        }
    }


    private String enterDirName() {
        printer.print("Enter directory for search:");
        return reader.read();
    }

    private void enterFileName() {
        printer.print("Search by file name (0/1)");
        if (parser.parseStringToInt(reader.read()) == 1) {
            printer.print("Enter file name:");
            filter = new FilterByName(filter, reader.read());
        }
    }

    private void enterFileExtension() {
        printer.print("Search by file extension (0/1)");
        if (parser.parseStringToInt(reader.read()) == 1) {
            printer.print("Enter file extension");
            filter = new FilterByExtension(filter, reader.read());
        }
    }

    private void enterFileSize() {
        printer.print("Search by file size range (0/1)");
        if (parser.parseStringToInt(reader.read()) == 1) {
            printer.print("Enter size from:");
            long startSize = parser.parseStringToInt(reader.read());
            printer.print("Enter size to:");
            long endSize = parser.parseStringToInt(reader.read());
            filter = new FilterBySize(filter, startSize, endSize);
        }
    }

    private void enterFileDateModified() {
        printer.print("Search by file date modified (0/1)");
        if (parser.parseStringToInt(reader.read()) == 1) {
            printer.print("Enter date modified from (format yyyy-MM-dd):");
            Date from = parser.parseStringToDate(reader.read());
            printer.print("Enter date modified to (format yyyy-MM-dd):");
            Date to = parser.parseStringToDate(reader.read());
            filter = new FilterByModifiedDate(filter, from, to);
        }
    }
}

