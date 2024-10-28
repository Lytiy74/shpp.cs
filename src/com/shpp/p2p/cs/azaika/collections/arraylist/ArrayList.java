package com.shpp.p2p.cs.azaika.collections.arraylist;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;

/**
 * A custom implementation of a dynamic array (ArrayList) that supports generic types.
 *
 * @param <T> the type of elements in this list
 */
public class ArrayList<T> implements Iterable<T> {
    public static final double COEFFICIENT_OF_LOAD_FACTOR = 0.6;
    private T[] array;
    private int size;
    private final static int DEFAULT_ARRAY_SIZE = 10;

    /**
     * Constructs an empty ArrayList with the default initial capacity (10).
     */
    public ArrayList() {
        this.array = (T[]) new Object[DEFAULT_ARRAY_SIZE];
    }

    /**
     * Constructs an empty ArrayList with the specified initial capacity.
     *
     * @param initCapacity the initial capacity of the ArrayList
     */
    public ArrayList(int initCapacity) {
        this.array = (T[]) new Object[initCapacity];
    }

    /**
     * Returns a new ArrayList containing the specified elements.
     *
     * @param elements the elements to be added to the new ArrayList
     * @return a new ArrayList containing the specified elements
     */
    public static <T> ArrayList<T> Of(T... elements) {
        ArrayList<T> arrayList = new ArrayList<>();
        for (T element : elements) {
            arrayList.add(element);
        }
        return arrayList;
    }

    /**
     * Appends the specified element to the end of this list.
     *
     * @param element the element to be appended to this list
     */
    public void add(T element) {
        increaseArrayIfItFull();
        array[size] = element;
        size++;
    }

    /**
     * Inserts the specified element at the specified position in this list.
     *
     * @param index the index at which the specified element is to be inserted
     * @param element the element to be inserted
     */
    public void add(int index, T element) {
        Objects.checkIndex(index, size);
        increaseArrayIfItFull();
        System.arraycopy(array, index, array, index + 1, size - index);
        size++;
        array[index] = element;
    }

    /**
     * Increases the capacity of the array if it is full.
     */
    private void increaseArrayIfItFull() {
        if (size >= array.length / COEFFICIENT_OF_LOAD_FACTOR) {
            T[] resizedArray = (T[]) new Object[array.length * 2];
            System.arraycopy(array, 0, resizedArray, 0, array.length);
            array = resizedArray;
        }
    }

    /**
     * Removes the element at the specified position in this list.
     *
     * @param index the index of the element to be removed
     * @return the element that was removed from the list
     */
    public T remove(int index) {
        Objects.checkIndex(index, size);
        T removedElement = array[index];
        System.arraycopy(array, index + 1, array, index, size - index);
        size--;
        return removedElement;
    }

    /**
     * Returns the element at the specified position in this list.
     *
     * @param index the index of the element to return
     * @return the element at the specified position in this list
     */
    public T get(int index) {
        Objects.checkIndex(index, size);
        return array[index];
    }

    /**
     * Removes all of the elements from this list.
     */
    public void clear() {
        array = (T[]) new Object[DEFAULT_ARRAY_SIZE];
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayListIterator();
    }

    /**
     * An iterator for ArrayList.
     */
    private class ArrayListIterator implements Iterator<T> {
        private int currentPosition;


        @Override
        public boolean hasNext() {
            return currentPosition < size;
        }

        @Override
        public T next() {
            T element = array[currentPosition];
            currentPosition++;
            return element;
        }
    }

    @Override
    public String toString() {
        return Arrays.toString(array);
    }
}
