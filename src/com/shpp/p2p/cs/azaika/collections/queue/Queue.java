package com.shpp.p2p.cs.azaika.collections.queue;

import com.shpp.p2p.cs.azaika.collections.linkedlist.LinkedList;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Queue <T> implements Iterator<T> {
    private final LinkedList<T> linkedList;

    public Queue() {
        this.linkedList = new LinkedList<>();
    }

    public void add(T element){
        linkedList.add(element);
    }

    public T poll(){
        if (linkedList.isEmpty()) return null;
        return linkedList.remove(0);
    }

    public int size(){
        return linkedList.size();
    }

    public boolean isEmpty(){
        return linkedList.isEmpty();
    }

    @Override
    public boolean hasNext() {
       return linkedList.size() != 0;
    }

    @Override
    public T next() {
        if (hasNext()){
            return poll();
        }
        throw new NoSuchElementException();
    }
}
