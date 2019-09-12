package com.epam.preprod.tymoshenko.task2.part1;

import java.util.Iterator;
import java.util.List;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Collection;
import java.util.ListIterator;


public class CopyOnWriteArrayList<T> implements List<T> {

    private static final int DEFAULT_CAPACITY_ARRAY = 10;
    private static final int NEW_LENGTH = 3;

    private Object[] mainArray;
    private Object[] copyArray;
    private int size;


    public CopyOnWriteArrayList() {
        this.mainArray = new Object[DEFAULT_CAPACITY_ARRAY];
    }



    @Override
    public Iterator<T> iterator() {
        return new CopyOnWriteIterator(0, copyArray);
    }

    private class CopyOnWriteIterator implements Iterator<T> {
        private final  Object[] copyArray;
        private int currentCursor;

        public CopyOnWriteIterator(int currentCursor, Object [] copyArray) {
            this.currentCursor = currentCursor;
            this.copyArray=copyArray;
        }

        @Override
        public boolean hasNext() {
            return currentCursor < copyArray.length;
        }

        @SuppressWarnings("unchecked")
        @Override
        public T next() {
            if (currentCursor >= copyArray.length) {
                throw new NoSuchElementException();
            }
            return (T) copyArray[currentCursor++];
        }
    }

    @Override
    public int size() {
        return size;
    }

    private void resize() {
        mainArray = Arrays.copyOf(mainArray, size * NEW_LENGTH);
    }

    private void resize(int quantityNewElements) {
        mainArray = Arrays.copyOf(mainArray, (size + quantityNewElements) * NEW_LENGTH);
    }


    @SuppressWarnings("unchecked")
    private T[] cloneMainArray() {
        return (T[]) mainArray;
    }

    private void setCopyInMainArray(Object[] copyArray) {
        mainArray = Arrays.copyOf(copyArray, copyArray.length);
        this.copyArray=toArray();
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public boolean contains(Object object) {
        checkNull(object);
        boolean result = false;
        for (Object element : mainArray) {
            if (object.equals(element)) {
                result = true;
            }
        }
        return result;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T[] toArray() {
        return (T[]) Arrays.copyOf(mainArray, size);
    }

    @Override
    public boolean add(Object object) {
        checkNull(object);
        if ((size + 1) - mainArray.length > 0) {
            resize();
        }
        copyArray = cloneMainArray();
        copyArray[size] = object;
        size++;
        setCopyInMainArray(copyArray);
        return true;
    }

    @Override
    public boolean remove(Object object) {
        checkNull(object);
        if (contains(object)) {
            copyArray = cloneMainArray();
            int index = indexOf(object);
            System.arraycopy(copyArray, index + 1, copyArray, index, size - index - 1);
            size--;
            setCopyInMainArray(copyArray);
            return true;
        }
        return false;
    }


    @Override
    public boolean addAll(Collection<? extends T> newCollection) {
        Object[] collection = newCollection.toArray();

        for (Object obj : collection) {
            checkNull(obj);
        }
        if (size + collection.length >= mainArray.length) {
            resize(collection.length);
        }
        copyArray = cloneMainArray();
        System.arraycopy(collection, 0, copyArray, size, collection.length);
        size += collection.length;
        setCopyInMainArray(copyArray);
        return true;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean addAll(int index, Collection<? extends T> collection) {
        checkIndex(index);
        T[] newCollection = (T[]) collection.toArray();

        for (T elementNewCollection : newCollection) {
            checkNull(elementNewCollection);
        }
        copyArray= cloneMainArray();
        copyArray = insertArray(index, newCollection);
        size = copyArray.length;
        setCopyInMainArray(copyArray);
        return true;
    }

    @SuppressWarnings("unchecked")
    private T[] insertArray(int index, T[] newCollection) {
        Object[] tempArray = new Object[size + newCollection.length];

        System.arraycopy(copyArray, 0, tempArray, 0, index);
        System.arraycopy(newCollection, 0, tempArray, index, newCollection.length);
        System.arraycopy(copyArray, index,
                tempArray, index + newCollection.length, size - index);

        return (T[]) tempArray;
    }

    @Override
    public void clear() {
        mainArray = new Object[DEFAULT_CAPACITY_ARRAY];
        size = 0;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T get(int index) {
        checkIndex(index);
        return (T) mainArray[index];

    }

    @SuppressWarnings("unchecked")
    @Override
    public T set(int index, T element) {
        checkIndex(index);
        checkNull(element);
        copyArray= cloneMainArray();
        T oldElement = (T) copyArray[index];
        copyArray[index] = element;
        setCopyInMainArray(copyArray);
        return oldElement;
    }

    @Override
    public void add(int index, T element) {
        checkIndex(index);
        checkNull(element);
        copyArray= cloneMainArray();
        Object[] newArray = new Object[size + 1];
        System.arraycopy(copyArray, 0, newArray, 0, index);
        newArray[index] = element;
        System.arraycopy(copyArray, index, newArray, index + 1, size - index);
        copyArray = Arrays.copyOf(newArray, size + 1);
        size++;
        setCopyInMainArray(copyArray);
    }

    @SuppressWarnings("unchecked")
    @Override
    public T remove(int index) {
        checkIndex(index);
        copyArray= cloneMainArray();
        T oldElement = (T) copyArray[index];
        Object[] newArray = new Object[size - 1];

        System.arraycopy(copyArray, 0, newArray, 0, index);
        System.arraycopy(copyArray, index + 1, newArray, index, size - index - 1);

        copyArray = Arrays.copyOf(newArray, size - 1);
        size--;
        setCopyInMainArray(copyArray);
        return oldElement;
    }

    @Override
    public int indexOf(Object object) {
        checkNull(object);
        for (int i = 0; i < size; i++) {
            if (object.equals(mainArray[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object object) {
        checkNull(object);
        for (int i = size - 1; i >= 0; i--) {
            if (object.equals(mainArray[i])) {
                return i;
            }
        }
        return -1;
    }


    @Override
    public boolean retainAll(Collection collection) {
        boolean result = false;
        copyArray= cloneMainArray();
        for (int i = 0; i < copyArray.length; i++) {
            if (!collection.contains(copyArray[i])) {
                remove(copyArray[i--]);
                setCopyInMainArray(copyArray);
                result = true;
            }
        }
        return result;
    }

    @Override
    public boolean removeAll(Collection c) {
        boolean result = false;
        copyArray= cloneMainArray();
        for (int i = 0; i < copyArray.length; i++) {
            if (c.contains(copyArray[i])) {
                remove(copyArray[i--]);
                setCopyInMainArray(copyArray);
                result = true;
            }
        }
        return result;
    }

    @Override
    public boolean containsAll(Collection collection) {
        boolean result = false;
        Object[] newCollection = collection.toArray();
        for (Object newCollectionElement : newCollection) {
            for (Object mainArrayElement : mainArray) {
                if (newCollectionElement.equals(mainArrayElement)) {
                    result = true;
                    break;
                } else {
                    result = false;
                }
            }
        }
        return result;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T[] toArray(Object[] newArray) {
        if (newArray.length < size) {
            return (T[]) Arrays.copyOf(mainArray, size, newArray.getClass());
        }
        System.arraycopy(mainArray, 0, newArray, 0, size);
        if (newArray.length > size) {
            newArray[size] = null;
        }
        return (T[]) newArray;
    }

    @Override
    public String toString() {
        return Arrays.toString(mainArray);
    }

    private void checkIndex(int index) {
        if (index < 0 || index > size()) {
            throw new IndexOutOfBoundsException("Index must be in range [0, " + size() + "]");
        }
    }

    private void checkNull(Object object) {
        if (object == null) {
            throw new IllegalArgumentException("The argument cannot be 'null'");
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

}

