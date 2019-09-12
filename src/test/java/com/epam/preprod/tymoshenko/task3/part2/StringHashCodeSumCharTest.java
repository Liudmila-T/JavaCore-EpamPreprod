package com.epam.preprod.tymoshenko.task3.part2;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.*;


public class StringHashCodeSumCharTest {
    private Iterator iteratorHashMap;
    private Iterator iteratorLinkedHashMap;
    private StringHashCodeSumChar element1;
    private StringHashCodeSumChar element2;
    private StringHashCodeSumChar element3;
    private StringHashCodeSumChar element4;
    private StringHashCodeSumChar element5;

    @Before
    public void before() {
        Map<StringHashCodeSumChar, Object> hashMap = new HashMap<>();
        Map<StringHashCodeSumChar, Object> linkedHashMap = new LinkedHashMap<>();

        element1 = new StringHashCodeSumChar("11111");
        element2 = new StringHashCodeSumChar("22222");
        element3 = new StringHashCodeSumChar("33333");
        element4 = new StringHashCodeSumChar("44444");
        element5 = new StringHashCodeSumChar("1");

        hashMap.put(element1, 1);
        hashMap.put(element2, 2);
        hashMap.put(element3, 3);
        hashMap.put(element4, 4);

        linkedHashMap.put(element1, 1);
        linkedHashMap.put(element2, 2);
        linkedHashMap.put(element3, 3);
        linkedHashMap.put(element4, 4);

        iteratorHashMap = hashMap.entrySet().iterator();
        iteratorLinkedHashMap = linkedHashMap.entrySet().iterator();

    }

    @Test
    public void hashCodeForStringLengthMore4 (){
        Assert.assertEquals(element1.hashCode(), 196);
    }
    @Test
    public void hashCodeForStringLengthOne (){
        Assert.assertEquals(element5.hashCode(), 49);
    }

    @Test
    public void compareIteratorsHashMapAndLinkedHashMap () {
        while (iteratorHashMap.hasNext()) {
            Assert.assertNotEquals(iteratorHashMap.next(), (iteratorLinkedHashMap.next()));
        }
    }

}