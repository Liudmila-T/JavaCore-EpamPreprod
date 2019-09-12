package com.epam.preprod.tymoshenko.task1.part2;


import java.util.Iterator;
import java.util.List;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Collection;
import java.util.ListIterator;
import java.util.function.Predicate;


public class GenericArrayList<T> implements List<T> {

    private static final int DEFAULT_CAPACITY_ARRAY = 10;
    private static final int NEW_LENGTH = 3;

    private Object[] mainArray;
    private int size;

    public GenericArrayList() {
        this.mainArray = new Object[DEFAULT_CAPACITY_ARRAY];
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


    @Override
    public Iterator<T> iterator() {
        return new IteratorImpl();
    }

    private class IteratorImpl implements Iterator<T> {
        private int currentCursor;

        @Override
        public boolean hasNext() {
            return currentCursor < size();
        }

        @SuppressWarnings("unchecked")
        @Override
        public T next() {
            if (currentCursor == size()) {
                throw new NoSuchElementException();
            }
            return (T) mainArray[currentCursor++];
        }
    }


    public Iterator<T> iterator(Predicate<T> predicate) {
        return new IteratorPredicate(predicate);
    }

    private class IteratorPredicate implements Iterator<T> {
        private Predicate<T> predicate;
        private int currentCursor = -1;

        public IteratorPredicate(Predicate<T> predicate) {
            this.predicate = predicate;
        }

        @SuppressWarnings("unchecked")
        @Override
        public boolean hasNext() {
            int nextPos = currentCursor + 1;
            while (nextPos < size && !predicate.test((T) mainArray[nextPos])) {
                nextPos++;
            }
            return (nextPos < size);
        }

        @SuppressWarnings("unchecked")
        @Override
        public T next() {
            do {
                if (size - 1 <= currentCursor) {
                    throw new NoSuchElementException();
                }
            } while (!predicate.test((T) mainArray[++currentCursor]));
            return (T) mainArray[currentCursor];
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public T[] toArray() {
        if (size < mainArray.length) {
            mainArray = Arrays.copyOf(mainArray, size);
        }
        return (T[]) mainArray.clone();
    }

    @Override
    public boolean add(Object object) {
        checkNull(object);
        if ((size + 1) - mainArray.length > 0) {
            resize();
        }
        mainArray[size] = object;
        size++;
        return true;
    }

    @Override
    public boolean remove(Object object) {
        checkNull(object);
        int index = indexOf(object);
        checkIndex(index);
        System.arraycopy(mainArray, index + 1, mainArray, index, size - index - 1);
        size--;
        return true;
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
        System.arraycopy(collection, 0, mainArray, size, collection.length);
        size += collection.length;
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
        mainArray = insertArray(index, newCollection);
        size = mainArray.length;
        return true;
    }

    @SuppressWarnings("unchecked")
    private T[] insertArray(int index, T[] newCollection) {
        Object[] tempArray = new Object[size + newCollection.length];

        System.arraycopy(mainArray, 0, tempArray, 0, index);
        System.arraycopy(newCollection, 0, tempArray, index, newCollection.length);
        System.arraycopy(mainArray, index,
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
        T oldElement = (T) mainArray[index];
        mainArray[index] = element;
        return oldElement;
    }

    @Override
    public void add(int index, T element) {
        checkIndex(index);
        checkNull(element);

        Object[] newArray = new Object[size + 1];
        System.arraycopy(mainArray, 0, newArray, 0, index);
        newArray[index] = element;
        System.arraycopy(mainArray, index, newArray, index + 1, size - index);
        mainArray = Arrays.copyOf(newArray, size + 1);
        size++;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T remove(int index) {
        checkIndex(index);
        T oldElement = (T) mainArray[index];
        Object[] newArray = new Object[size - 1];

        System.arraycopy(mainArray, 0, newArray, 0, index);
        System.arraycopy(mainArray, index + 1, newArray, index, size - index - 1);

        mainArray = Arrays.copyOf(newArray, size - 1);
        size--;
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

    @Override
    public boolean retainAll(Collection c) {
        boolean result = false;
        for (int i = 0; i < mainArray.length; i++) {
            if (!c.contains(mainArray[i])) {
                remove(mainArray[i--]);
                result = true;
            }
        }
        return result;
    }

    @Override
    public boolean removeAll(Collection c) {
        boolean result = false;
        for (int i = 0; i < mainArray.length; i++) {
            if (c.contains(mainArray[i])) {
                remove(mainArray[i--]);
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
        return Arrays.toString(mainArray.clone());
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

}
