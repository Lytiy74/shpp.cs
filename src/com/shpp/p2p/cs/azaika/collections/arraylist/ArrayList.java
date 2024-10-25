package com.shpp.p2p.cs.azaika.collections.arraylist;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;

public class ArrayList<T> implements Iterator<T> {
    private T[] array;
    private int size;
    private final static int DEFAULT_ARRAY_SIZE = 10;
    private int currentPosition;

    public ArrayList() {
        this.array = (T[]) new Object[DEFAULT_ARRAY_SIZE];
    }

    public ArrayList(int initCapacity) {
        this.array = (T[]) new Object[initCapacity];
    }

    public ArrayList <T> Of(T...elements){
        ArrayList<T> arrayList = new ArrayList<>();
        for (T element : elements) {
            arrayList.add(element);
        }
        return arrayList;
    }

    public void add(T element) {
        increaseArrayIfItFull();
        array[size] = element;
        size++;
    }

    public void add(int index, T element){
        Objects.checkIndex(index,size);
        increaseArrayIfItFull();
        System.arraycopy(array,index,array,index+1,size - index);
        size++;
        array[index] = element;
    }

    private void increaseArrayIfItFull() {
        if (size >= array.length/0.6) {
            T[] resizedArray = (T[]) new Object[array.length * 2];
            System.arraycopy(array, 0, resizedArray, 0, array.length);
            array = resizedArray;
        }
    }


    public T remove(int index){
        Objects.checkIndex(index,size);
        T removedElement = array[index];
        System.arraycopy(array,index+1,array,index,size - index);
        size--;
        return removedElement;
    }

    public T get(int index){
        Objects.checkIndex(index,size);
        return array[index];
    }

    public void clear(){
        array = (T[]) new Object[DEFAULT_ARRAY_SIZE];
        size = 0;
    }


    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    @Override
    public boolean hasNext() {
        return currentPosition < size;
    }

    @Override
    public T next() {
        if (!hasNext()){
            return null;
        }
        T element = array[currentPosition];
        currentPosition++;
        return element;
    }

    @Override
    public String toString() {
        return Arrays.toString(array);
    }
}
