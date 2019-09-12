package com.epam.preprod.tymoshenko.task3.part1;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.UnaryOperator;

public class UniqueValueArrayList <T> extends ArrayList<T> {

    public UniqueValueArrayList() {
    }

    public UniqueValueArrayList(int initialCapacity) {
        super(initialCapacity);
    }

    public UniqueValueArrayList(Collection<? extends T> c) {
        super(c);
    }

    @Override
    public boolean add(T element) {
        checkNull(element);
        checkUniqueness(element);
        return super.add(element);
    }

    @Override
    public void add(int index, T element) {
        checkNull(element);
        checkUniqueness(element);
        super.add(index, element);
    }

    @Override
    public T set(int index, T element) {
        checkNull(element);
        checkUniqueness(element);
        return super.set(index, element);
    }

    @Override
    public boolean addAll(Collection<? extends T> collection) {
        for (T elementCollection : collection) {
            checkNull(elementCollection);
            checkUniqueness(elementCollection);
        }
        return super.addAll(collection);
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> collection) {
        for (T elementCollection : collection) {
            checkNull(elementCollection);
            checkUniqueness(elementCollection);
        }
        return super.addAll(index, collection);
    }

    @Override
    public void replaceAll(UnaryOperator<T> operator) {
        checkNull(operator);
        if (stream().map(operator).distinct().count() == size()) {
            super.replaceAll(operator);
        } else {
            throw new IllegalArgumentException("List items must be unique");
        }
    }

    private void checkUniqueness(T element) {
        if (contains(element)) {
            throw new IllegalArgumentException("This item is already in the collection");
        }
    }

    private void checkNull(Object object) {
        if (object == null) {
            throw new IllegalArgumentException("The argument cannot be 'null'");
        }
    }
}
