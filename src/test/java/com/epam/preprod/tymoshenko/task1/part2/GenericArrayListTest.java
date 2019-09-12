package com.epam.preprod.tymoshenko.task1.part2;

import com.epam.preprod.tymoshenko.task1.part1.Doll;
import com.epam.preprod.tymoshenko.task1.part1.Product;
import com.epam.preprod.tymoshenko.task1.part1.Toy;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class GenericArrayListTest {
    private GenericArrayList<Product> products;
    private Toy toy;
    private Toy toy2;
    private Doll doll;
    private Doll doll2;

    @Before
    public void before() {
        products = new GenericArrayList<>();
        toy = new Doll();
        doll = new Doll("1", "Barby", 100, "China", "For girls", "red");
        doll2 = new Doll("2", "Barby2", 300, "China", "For girls", "blue");
        products.add(toy);
        products.add(doll);
        products.add(doll2);

    }


    @Test
    public void add() {
        toy2 = new Doll();
        products.add(toy2);
        Assert.assertTrue(products.contains(toy2));
    }

    @Test
    public void addByIndex() {

        products.add(0, doll);

        Assert.assertEquals(doll, products.get(0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void addTestException() {
        products.add(null);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void addByIndexTestException() {

        products.add(7, doll);
    }

    @Test
    public void get() {

        Assert.assertEquals(doll2, products.get(2));
    }

    @Test
    public void remove() {

        products.remove(doll);
        Assert.assertEquals(2, products.size());
    }

    @Test
    public void removeByObject() {

        products.remove(doll);
        Assert.assertFalse(products.contains(doll));
    }

    @Test
    public void removeByIndex() {

        products.remove(0);
        Assert.assertEquals(doll, products.get(0));

    }

    @Test
    public void hasNext() {
        products = new GenericArrayList<>();
        Iterator<Product> productIterator = products.iterator();
        Assert.assertFalse(productIterator.hasNext());
    }

    @Test(expected = NoSuchElementException.class)
    public void next() {
        products = new GenericArrayList<>();
        Iterator<Product> productIterator = products.iterator();
        productIterator.next();
    }

    @Test
    public void iterator() {
        List<Integer> resultList = new GenericArrayList<>();
        List<Integer> list = new GenericArrayList<>();
        list.add(1);
        list.add(3);
        list.add(7);
        list.add(10);
        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            resultList.add(iterator.next());
        }
        for (int i = 0; i < resultList.size(); i++) {
            Assert.assertEquals(list.get(i), resultList.get(i));
        }
    }

    @Test
    public void testIteratorPredicate() {
        GenericArrayList<Integer> list = new GenericArrayList<>();
        list.add(1);
        list.add(30);
        list.add(7);
        list.add(10);
        List<Integer> resultList = new ArrayList<>();

        list.iterator(integer -> integer < 8).forEachRemaining(resultList::add);

        list.remove(1); //list[1,7,10]
        list.remove(2); //list[1,7]

        for (int i = 0; i < resultList.size(); i++) {
            Assert.assertEquals(list.get(i), resultList.get(i));
        }

    }


}