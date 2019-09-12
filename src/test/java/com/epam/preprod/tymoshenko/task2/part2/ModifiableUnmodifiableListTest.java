package com.epam.preprod.tymoshenko.task2.part2;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ModifiableUnmodifiableListTest {
    private List<Integer> modifiableUnmodifiableList;
    private List<Integer> listForAddAll;

    @Before
    public void before() {
        List<Integer> modifiableList = new ArrayList<>();
        List<Integer> unmodifiableList = new ArrayList<>();
        listForAddAll = new ArrayList<>();
        modifiableUnmodifiableList = new ModifiableUnmodifiableList<>(unmodifiableList, modifiableList);

        unmodifiableList.add(0);
        unmodifiableList.add(1);
        unmodifiableList.add(2);
        unmodifiableList.add(3);
        unmodifiableList.add(4);

        listForAddAll.add(400);
        listForAddAll.add(500);

        modifiableUnmodifiableList.add(10);
        modifiableUnmodifiableList.add(20);
        modifiableUnmodifiableList.add(30);
        modifiableUnmodifiableList.add(40);
    }

    @Test
    public void add() {
        modifiableUnmodifiableList.add(100);
        Assert.assertEquals(modifiableUnmodifiableList.get(9), new Integer(100));
    }

    @Test
    public void addByIndexModifiable() {
        modifiableUnmodifiableList.add(7, 100);
        Assert.assertEquals(modifiableUnmodifiableList.get(7), new Integer(100));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void addByIndexUnmodifiable() {
        modifiableUnmodifiableList.add(2, 100);
    }

    @Test
    public void removeObjectFromModifiableList() {
        modifiableUnmodifiableList.remove(new Integer(10));
        Assert.assertFalse(modifiableUnmodifiableList.contains(10));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void removeObjectFromUnmodifiableList() {
        modifiableUnmodifiableList.remove(new Integer(1));
    }

    @Test
    public void removeByIndexFromModifiableList() {
        modifiableUnmodifiableList.remove(5);
        Assert.assertEquals(modifiableUnmodifiableList.get(5), new Integer(20));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void removeByIndexFromUnModifiableList() {
        modifiableUnmodifiableList.remove(0);
    }

    @Test
    public void addAll() {
        modifiableUnmodifiableList.addAll(listForAddAll);
        Assert.assertTrue(modifiableUnmodifiableList.containsAll(listForAddAll));
    }

    @Test
    public void addAllByIndexFromModifiableList() {
        modifiableUnmodifiableList.addAll(6, listForAddAll);
        Assert.assertTrue(modifiableUnmodifiableList.containsAll(listForAddAll));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void addAllByIndexFromUnModifiableList() {
        modifiableUnmodifiableList.addAll(2, listForAddAll);
    }

    @Test
    public void setFromModifiableList() {
        modifiableUnmodifiableList.set(7, 1000);
        Assert.assertEquals(modifiableUnmodifiableList.get(7), new Integer(1000));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void setFromUnModifiableList() {
        modifiableUnmodifiableList.set(0, 100);
    }

    @Test
    public void retainAll() {
        listForAddAll = new ArrayList<>();
        listForAddAll.add(10);
        listForAddAll.add(20);
        modifiableUnmodifiableList.retainAll(listForAddAll);
        Assert.assertTrue(modifiableUnmodifiableList.containsAll(listForAddAll));
    }

    @Test
    public void removeAll() {
        listForAddAll = new ArrayList<>();
        listForAddAll.add(30);
        listForAddAll.add(40);
        modifiableUnmodifiableList.removeAll(listForAddAll);
        Assert.assertFalse(modifiableUnmodifiableList.containsAll(listForAddAll));
    }

    @Test
    public void iterator() {
        List<Integer> resultList = new ArrayList<>();
        Iterator<Integer> iterator = modifiableUnmodifiableList.iterator();
        while (iterator.hasNext()) {
            resultList.add(iterator.next());
        }
        for (int i = 0; i < modifiableUnmodifiableList.size(); i++) {
            Assert.assertEquals(modifiableUnmodifiableList.get(i), resultList.get(i));
        }
    }
}