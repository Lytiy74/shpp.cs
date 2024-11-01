package com.shpp.p2p.cs.azaika.collections.queue;

import com.shpp.p2p.cs.azaika.collections.linkedlist.LinkedList;

import java.util.EmptyStackException;
import java.util.Iterator;

/**
 * A generic implementation of a queue data structure using a linked list.
 *
 * @param <T> the type of elements in this queue
 */
public class Queue <T> implements Iterable<T> {
    private final LinkedList<T> linkedList;

    /**
     * Constructor of empty queue.
     */
    public Queue() {
        this.linkedList = new LinkedList<>();
    }

    /**
     * Adds the specified element to the end of this queue.
     *
     * @param element the element to add
     */
    public void add(T element){
        linkedList.add(element);
    }

    /**
     * Retrieves and removes the head of this queue, or returns {@code null} if this queue is empty.
     *
     * @return the head of this queue, or {@code null} if this queue is empty
     */
    public T poll(){
        if (linkedList.isEmpty()) return null;
        return linkedList.remove(0);
    }
    /**
     * Returns the element at the top of this stack without removing it.
     *
     * @return the element at the top of this stack
     * @throws EmptyStackException if this stack is empty
     */
    public T peek() {
        if (linkedList.isEmpty()) throw new EmptyStackException();
        return linkedList.get(linkedList.size() - 1);
    }

    /**
     * Returns the number of elements in this queue.
     *
     * @return the number of elements in this queue
     */
    public int size(){
        return linkedList.size();
    }

    /**
     * Returns {@code true} if this queue contains no elements.
     *
     * @return {@code true} if this queue contains no elements
     */
    public boolean isEmpty(){
        return linkedList.isEmpty();
    }

    @Override
    public Iterator<T> iterator() {
        return new QueueIterator();
    }
    
    /**
     * iterator for the elements in queue.
     */
    private class QueueIterator implements Iterator<T> {
        private int currentIndex;

        public QueueIterator() {
        }

        @Override
        public T next() {
            return linkedList.get(currentIndex++);
        }

        @Override
        public boolean hasNext() {
            return currentIndex < linkedList.size();
        }
    }
}
