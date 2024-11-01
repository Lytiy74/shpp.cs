package com.shpp.p2p.cs.azaika.collections.stack;

import com.shpp.p2p.cs.azaika.collections.linkedlist.LinkedList;

import java.util.EmptyStackException;
import java.util.Iterator;

/**
 * implementation of stack data structure using linked list.
 *
 * @param <T> the type of elements in this stack
 */
public class Stack<T> implements Iterable<T> {
    private final LinkedList<T> linkedList;

    /**
     * Constructor of empty stack.
     */
    public Stack() {
        this.linkedList = new LinkedList<>();
    }

    /**
     * Creates a new stack containing the given elements.
     *
     * @param elements the elements to add to the stack
     * @return new stack containing the given elements
     */
    @SafeVarargs
    public static <T> Stack<T> of(T... elements) {
        Stack<T> stack = new Stack<>();
        for (T element : elements) {
            stack.push(element);
        }
        return stack;
    }

    /**
     * Adds an element to the top of this stack.
     *
     * @param element the element to add
     */
    public void push(T element) {
        linkedList.add(element);
    }

    /**
     * Removes and returns the element at the top of this stack.
     *
     * @return the element at the top of this stack
     * @throws EmptyStackException if this stack is empty
     */
    public T pop() {
        if (linkedList.isEmpty()) throw new EmptyStackException();
        return linkedList.remove(linkedList.size()-1);
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
     * Returns the size
     *
     * @return the number of elements in this stack
     */
    public int size() {
        return linkedList.size();
    }

    /**
     * Returns boolean value if stack is empty
     *
     * @return {@code true} if this stack is empty; {@code false} otherwise
     */
    public boolean isEmpty() {
        return linkedList.isEmpty();
    }

    @Override
    public Iterator<T> iterator() {
        return new StackIterator();
    }

    private class StackIterator implements Iterator<T> {
        private int currentIndex;

        public StackIterator() {
            currentIndex = linkedList.size();
        }

        @Override
        public boolean hasNext() {
            return currentIndex > 0;
        }

        @Override
        public T next() {
            return linkedList.get(--currentIndex);
        }
    }
    
}
