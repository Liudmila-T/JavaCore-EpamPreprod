package com.epam.preprod.tymoshenko.task5.part1;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class FileReaderTest {
    private String nameFile ="src\\test\\resources\\task5.txt";
    private String incorrectNameFile = "src\\test\\resources\\incorrectNameFileTask5part1.txt";

    private FileReader fileReader;

    @Test
    public void hasNext() {
        System.out.println(nameFile);
        fileReader = new FileReader(nameFile);
       Assert.assertTrue(fileReader.iterator().hasNext());
    }

    @Test(expected = RuntimeException.class)
    public void hasNextThrowsRuntimeException() {
        fileReader = new FileReader(incorrectNameFile);
        fileReader.iterator().hasNext();
    }

    @Test
    public void next() {
        fileReader = new FileReader(nameFile);
        Assert.assertEquals("Methods of access", fileReader.iterator().next());
    }

    @Test(expected = RuntimeException.class)
    public void nextThrowsRuntimeException() {
        String emptyFile="src\\test\\resources\\emptyFileTask5.txt";
        fileReader = new FileReader(emptyFile);
        fileReader.iterator().next();
    }

    @Test(expected = RuntimeException.class)
    public void iteratorIncorrectNameFileThrowsRuntimeException() {
        fileReader = new FileReader(incorrectNameFile);
        fileReader.iterator().next();
    }

    @Test
    public void iterator() {
        List<Object> result = new ArrayList<>();
        fileReader = new FileReader(nameFile);
        for (Object string : fileReader) {
            result.add(string);
        }
        List<Object> result2 = new ArrayList<>();
        FileReader fileReader2 = new FileReader(nameFile);
        for (Object string : fileReader2) {
            result2.add(string);
        }
        Assert.assertEquals(result, result2);
    }

}