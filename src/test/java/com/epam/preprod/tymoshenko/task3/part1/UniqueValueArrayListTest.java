package com.epam.preprod.tymoshenko.task3.part1;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.*;

public class UniqueValueArrayListTest {
    private List<Integer> list;
    private List<Integer> listForAddAll;

    @Before
    public void before() {
        list = new UniqueValueArrayList<>();
        listForAddAll = new UniqueValueArrayList<>();
        list.add(0);
        list.add(1);
        list.add(2);
        list.add(3);
    }

    @Test
    public void add() {
        list.add(4);
        Assert.assertEquals(list.get(4), new Integer(4));
    }

    @Test(expected = IllegalArgumentException.class)
    public void addNotUniqueElement() {
        list.add(3);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addNull() {
        list.add(null);
    }

    @Test
    public void addByIndex() {
        list.add(2, 4);
        Assert.assertEquals(list.get(2), new Integer(4));
    }

    @Test(expected = IllegalArgumentException.class)
    public void addByIndexNotUniqueElement() {
        list.add(3, 3);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addByIndexNull() {
        list.add(1, null);
    }

    @Test
    public void set() {
        list.set(2, 4);
        Assert.assertEquals(list.get(2), new Integer(4));
    }

    @Test(expected = IllegalArgumentException.class)
    public void setNotUniqueElement() {
        list.set(3, 3);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setNull() {
        list.add(1, null);
    }

    @Test
    public void addAll() {
        listForAddAll.add(100);
        listForAddAll.add(200);
        list.addAll(listForAddAll);
        Assert.assertTrue(list.containsAll(listForAddAll));
    }

    @Test(expected = IllegalArgumentException.class)
    public void addAllNotUniqueElement() {
        listForAddAll.add(1);
        listForAddAll.add(200);
        list.addAll(listForAddAll);
    }

    @Test
    public void addAllByIndex() {
        listForAddAll.add(100);
        listForAddAll.add(200);
        list.addAll(2, listForAddAll);
        Assert.assertTrue(list.containsAll(listForAddAll));
    }

    @Test(expected = IllegalArgumentException.class)
    public void addAllByIndexNotUniqueElement() {
        listForAddAll.add(1);
        listForAddAll.add(200);
        list.addAll(2, listForAddAll);
    }

    @Test
    public void replaceAll() {
        List<Integer> clone=new ArrayList<>(list);
        list.replaceAll((x->x+1));
        for (int i = 0; i < list.size(); i++) {
            Assert.assertEquals((Integer) (clone.get(i)+1), list.get(i));
        }

          }

    @Test(expected = IllegalArgumentException.class)
    public void replaceAllNotUnique() {
        list.replaceAll((x->x=3));
    }
}