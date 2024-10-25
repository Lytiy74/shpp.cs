package com.shpp.p2p.cs.azaika.collections.stack;

import com.shpp.p2p.cs.azaika.collections.linkedlist.LinkedList;

import java.util.EmptyStackException;

public class Stack<T> {
    private final LinkedList<T> linkedList;

    public Stack() {
        this.linkedList = new LinkedList<>();
    }

    public static <T> Stack<T> of(T... elements) {
        Stack<T> stack = new Stack<>();
        for (T element : elements) {
            stack.push(element);
        }
        return stack;
    }

    public void push(T element) {
        linkedList.add(element);
    }

    public T pop() {
        if (linkedList.isEmpty()) throw new EmptyStackException();
        return linkedList.remove(linkedList.size()-1);
    }

    public int size() {
        return linkedList.size();
    }

    public boolean isEmpty() {
        return linkedList.isEmpty();
    }
}
