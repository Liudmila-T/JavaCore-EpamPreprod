package com.epam.preprod.tymoshenko.task5.part1;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class FileReader implements Iterable {
    private static final String CHARSET = "UTF-8";
    private static final String MESSAGE_FILE_NOT_FOUND = "File not found";
    private static final String MESSAGE_NO_LINES_TO_READ = "No lines to read";
    private String nameFile;
    private BufferedReader bufferedReader;

    public FileReader(String nameFile) {
        this.nameFile = nameFile;
    }


    @Override
    public Iterator iterator() {
        try {
            bufferedReader = Files.newBufferedReader(Paths.get(nameFile), Charset.forName(CHARSET));
        } catch (IOException e) {
            throw new RuntimeException(MESSAGE_FILE_NOT_FOUND);
        }
        return new FileIterator();
    }

    public class FileIterator implements Iterator {
        @Override
        public boolean hasNext() {
            try {
                return bufferedReader.ready();
            } catch (IOException ex) {
                throw new RuntimeException();
            }
        }

        @Override
        public String next() {
            if (hasNext()) {
                try {
                    return bufferedReader.readLine();
                } catch (IOException ex) {
                    ex.printStackTrace();
                    throw new RuntimeException();
                }
            }
            throw new NoSuchElementException(MESSAGE_NO_LINES_TO_READ);
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
