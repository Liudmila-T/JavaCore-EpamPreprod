package com.epam.preprod.tymoshenko.task3.part2;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class StringHashCodeStringLengthTest {
    private Iterator iteratorHashMap;
    private Iterator iteratorLinkedHashMap;

    @Before
    public void before() {
        Map<StringHashCodeSumChar, Object> hashMap = new HashMap<>();
        Map<StringHashCodeSumChar, Object> linkedHashMap = new LinkedHashMap<>();

        StringHashCodeSumChar element1 = new StringHashCodeSumChar("11111234413454354354643656");
        StringHashCodeSumChar element2 = new StringHashCodeSumChar("2222234453256465");
        StringHashCodeSumChar element3 = new StringHashCodeSumChar("333337879809");
        StringHashCodeSumChar element4 = new StringHashCodeSumChar("44444235467768255543");

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
    public void compareIteratorsHashMapAndLinkedHashMap() {
        while (iteratorHashMap.hasNext()) {
            Assert.assertNotEquals(iteratorHashMap.next(), (iteratorLinkedHashMap.next()));
        }
    }


}