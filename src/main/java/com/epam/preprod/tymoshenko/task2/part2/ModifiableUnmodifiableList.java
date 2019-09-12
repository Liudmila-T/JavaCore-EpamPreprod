package com.epam.preprod.tymoshenko.task2.part2;

import java.util.*;

public class ModifiableUnmodifiableList<T> implements List<T> {

    private List<T> unmodifiableList;
    private List<T> modifiableList;


    public ModifiableUnmodifiableList(List<T> unmodifiableList, List<T> modifiableList) {
        this.unmodifiableList = unmodifiableList;
        this.modifiableList = modifiableList;
    }

    @Override
    public int size() {
        return (modifiableList.size() + unmodifiableList.size());
    }

    @Override
    public boolean isEmpty() {
        return ((modifiableList.size() == 0) && (unmodifiableList.size() == 0));
    }

    @Override
    public boolean contains(Object object) {
        return (modifiableList.contains(object) || unmodifiableList.contains(object));
    }

    @Override
    public Iterator<T> iterator() {
        return new ModifiableUnmodifiableIterator();
    }

    private class ModifiableUnmodifiableIterator implements Iterator<T> {
        private int currentCursor;

        @Override
        public boolean hasNext() {
            return currentCursor < size();
        }

        @Override
        public T next() {
            if (currentCursor == size()) {
                throw new NoSuchElementException();
            }
            if (currentCursor < unmodifiableList.size()) {
                return unmodifiableList.get(currentCursor++);
            } else {
                return (modifiableList.get((currentCursor++) - unmodifiableList.size()));
            }
        }
    }

    @Override
    public Object[] toArray() {
        Object[] result = new Object[size()];
        int startIndex = 0;
        for (int i = 0; i < unmodifiableList.size(); i++) {
            result[i] = unmodifiableList.get(i);
        }
        for (int i = unmodifiableList.size(); i < size(); i++) {
            result[i] = modifiableList.get(startIndex++);
        }
        return result;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean add(Object object) {
        return modifiableList.add((T) object);
    }

    @Override
    public boolean remove(Object object) {
        if (!modifiableList.remove(object)) {
            if (unmodifiableList.contains(object)) {
                throw new UnsupportedOperationException("You can't delete item from unmodifiable list");
            }
        }
        return true;
    }


    @Override
    public boolean addAll(Collection<? extends T> collection) {
        return modifiableList.addAll(collection);
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> collection) {
        checkIndexInUnmodifiableList(index);
        return modifiableList.addAll(index - unmodifiableList.size(), collection);
    }


    @Override
    public void clear() {
        if (unmodifiableList.size() == 0) {
            modifiableList.clear();
        } else {
            throw new UnsupportedOperationException();
        }
    }

    @Override
    public T get(int index) {
        if (index < unmodifiableList.size()) {
            return unmodifiableList.get(index);
        } else {
            return modifiableList.get(index - unmodifiableList.size());
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public T set(int index, Object element) {
        checkIndexInUnmodifiableList(index);
        return modifiableList.set(index - unmodifiableList.size(), (T) element);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void add(int index, Object element) {
        checkIndexInUnmodifiableList(index);
        modifiableList.add(index - unmodifiableList.size(), (T) element);
    }

    @Override
    public T remove(int index) {
        checkIndexInUnmodifiableList(index);
        return modifiableList.remove(index - unmodifiableList.size());
    }

    @Override
    public int indexOf(Object object) {
        if (unmodifiableList.contains(object)) {
            return unmodifiableList.indexOf(object);
        } else {
            return modifiableList.indexOf(object);
        }
    }

    @Override
    public int lastIndexOf(Object object) {
        if (modifiableList.contains(object)) {
            return modifiableList.lastIndexOf(object);
        } else {
            return unmodifiableList.lastIndexOf(object);
        }
    }

    @Override
    public boolean retainAll(Collection collection) {
        return modifiableList.retainAll(collection);
    }

    @Override
    public boolean removeAll(Collection collection) {
        return modifiableList.removeAll(collection);
    }


    @Override
    public boolean containsAll(Collection collection) {
        for (Object elementCollection : collection) {
            if (!unmodifiableList.contains(elementCollection) && !modifiableList.contains(elementCollection)) {
                return false;
            }
        }
        return true;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T[] toArray(T[] newArray) {
        if (newArray == null) {
            throw new NullPointerException();
        }
        if (newArray.length > size()) {
            System.arraycopy(unmodifiableList.toArray(), 0, newArray, 0, unmodifiableList.size());
            System.arraycopy(modifiableList.toArray(), 0, newArray, unmodifiableList.size(), modifiableList.size());
            newArray[size()] = null;
            return newArray;
        } else {
            Object[] array = new Object[size()];
            System.arraycopy(unmodifiableList.toArray(), 0, array, 0, unmodifiableList.size());
            System.arraycopy(modifiableList.toArray(), 0, array, unmodifiableList.size(), modifiableList.size());
            return (T[]) array;
        }
    }

    @Override
    public ListIterator<T> listIterator() {
        throw new UnsupportedOperationException();
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    private void checkIndexInUnmodifiableList(int index) {
        if (index < unmodifiableList.size()) {
            throw new IndexOutOfBoundsException("Index contains unmodifiable list");
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(unmodifiableList);
        sb.append(modifiableList);
        return sb.toString();
    }
}

