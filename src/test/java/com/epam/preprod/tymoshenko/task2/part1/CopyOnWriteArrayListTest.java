package com.epam.preprod.tymoshenko.task2.part1;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.List;

public class CopyOnWriteArrayListTest {
    private List<Integer> list;
    private List<Integer> resultList;
    private List<Integer> newList;
    Iterator<Integer> iterator;

    @Before
    public void before() {
        list = new CopyOnWriteArrayList<>();
        resultList = new CopyOnWriteArrayList<>();
        list.add(0);
        list.add(1);
        list.add(2);
        list.add(3);
        iterator = list.iterator();
    }
    @Test
    public void add() {
        list.add(4);
         Assert.assertEquals(list.get(4),new Integer(4));
    }

    @Test
    public void iteratorTestAdd() {
        list.add(4);
        while (iterator.hasNext()) {
            resultList.add(iterator.next());
        }
        Assert.assertFalse(resultList.contains(4));
    }
    @Test
    public void addByIndex() {
        list.add(0, 100);
        Assert.assertEquals(list.get(0), new Integer(100));

    }
    @Test
    public void iteratorTestAddByIndex() {
        list.add(0, 100);
        while (iterator.hasNext()) {
            resultList.add(iterator.next());
        }
        Assert.assertEquals(resultList.get(0), new Integer(0));

    }
    @Test
    public void remove() {
        list.remove(new Integer(2));
        Assert.assertEquals(list.get(2), new Integer(3));
    }

    @Test
    public void iteratorTestRemove() {
        list.remove(new Integer(3));
        while (iterator.hasNext()) {
            resultList.add(iterator.next());
        }
        Assert.assertTrue(resultList.contains(3));
    }

    @Test
    public void removeByIndex() {
        list.remove(0);
        Assert.assertEquals(list.get(0), new Integer(1));
    }

    @Test
    public void iteratorTestRemoveByIndex() {
        list.remove(0);
        while (iterator.hasNext()) {
            resultList.add(iterator.next());
        }
        Assert.assertEquals(resultList.get(0), new Integer(0));
    }

    @Test
    public void addAll() {
        newList = new CopyOnWriteArrayList<>();
        newList.add(4);
        newList.add(5);
        list.addAll(newList);
        Assert.assertTrue(list.containsAll(newList));

    }

    @Test
    public void iteratorTestAddAll() {
        newList = new CopyOnWriteArrayList<>();
        newList.add(4);
        newList.add(5);
        list.addAll(newList);
        while (iterator.hasNext()) {
            resultList.add(iterator.next());
        }
        Assert.assertFalse(resultList.containsAll(newList));

    }

    @Test
    public void addAllByIndex() {
        newList = new CopyOnWriteArrayList<>();
        newList.add(4);
        newList.add(5);
        list.addAll(2,newList);
        Assert.assertTrue(list.containsAll(newList));

    }

    @Test
    public void iteratorTestAddAllByIndex() {
        newList = new CopyOnWriteArrayList<>();
        newList.add(4);
        newList.add(5);
        list.addAll(newList);
        while (iterator.hasNext()) {
            resultList.add(iterator.next());
        }
        Assert.assertFalse(resultList.containsAll(newList));

    }
    @Test
    public void set() {
        list.set(0, 100);
        Assert.assertEquals(list.get(0),new Integer(100));
    }

    @Test
    public void iteratorTestSet() {
        list.set(0,200);
        while (iterator.hasNext()) {
            resultList.add(iterator.next());
        }
        Assert.assertFalse(resultList.contains(200));
    }

    @Test
    public void retainAll() {
        newList = new CopyOnWriteArrayList<>();
        newList.add(2);
        newList.add(3);
        list.retainAll(newList);
        System.out.println(list);
        Assert.assertTrue(list.containsAll(newList));
    }

    @Test
    public void iteratorTestRetainAll() {
        newList = new CopyOnWriteArrayList<>();
        newList.add(4);
        newList.add(5);
        list.addAll(newList);

        Iterator newIterator=list.iterator();

        list.retainAll(newList);

        while (newIterator.hasNext()) {
            resultList.add((Integer) newIterator.next());
        }
        Assert.assertTrue(resultList.containsAll(newList));

    }
    @Test
    public void removeAll() {
        newList = new CopyOnWriteArrayList<>();
        newList.add(1);
        newList.add(2);
        list.removeAll(newList);
        Assert.assertFalse(list.containsAll(newList));

    }

    @Test
    public void iteratorTestRemoveAll() {
        newList = new CopyOnWriteArrayList<>();
        newList.add(4);
        newList.add(5);
        list.addAll(newList);

        Iterator newIterator=list.iterator();

        list.removeAll(newList);

        while (newIterator.hasNext()) {
            resultList.add((Integer) newIterator.next());
        }
        Assert.assertTrue(resultList.containsAll(newList));

    }



}