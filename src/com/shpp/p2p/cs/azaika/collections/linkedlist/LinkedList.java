package com.shpp.p2p.cs.azaika.collections.linkedlist;

import java.util.Objects;

public class LinkedList<T> {
    private static class Node<T> {
        T element;
        Node<T> next;

        public Node(T element) {
            this.element = element;
        }
    }

    private int size;
    private Node<T> head;
    private Node<T> tail;

    public static <T> LinkedList<T> of(T... elements) {
        LinkedList<T> linkedList = new LinkedList<>();
        for (T element : elements) {
            linkedList.add(element);
        }
        return linkedList;
    }

    public void add(T element) {
        Node<T> newNode = new Node<>(element);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }

    public void add(int index, T element) {
        Node<T> newNode = new Node<>(element);
        if (index == 0) {
            newNode.next = head;
            head = newNode;
        } else if (index == size) {
            tail.next = newNode;
            tail = newNode;
        } else {
            Node<T> prev = getNodeByIndex(index - 1);
            newNode.next = prev.next;
            prev.next = newNode;
        }
        size++;
    }

    public T remove(int index) {
        T removedElement;
        if (index == 0) {
            removedElement = head.element;
            head = head.next;
        } else {
            Node<T> prev = getNodeByIndex(index - 1);
            removedElement = prev.next.element;
            prev.next = prev.next.next;
            if (index == size - 1) {
                tail = prev;
            }
        }
        size--;
        return removedElement;
    }

    public T get(int index) {
        return getNodeByIndex(index).element;
    }

    public void set(int index, T element) {
        getNodeByIndex(index).element = element;
    }

    private Node<T> getNodeByIndex(int index) {
        Objects.checkIndex(index, size);
        if (index == 0) return head;
        if (index == size) return tail;
        Node<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current;
    }

    public boolean contains(T element) {
        Node<T> current = head;
        for (int i = 0; i < size; i++) {
            if (current.element.equals(element)) return true;
        }
        return false;
    }

    public void clear(){
        head = null;
        tail = null;
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public T getFirst() {
        return head.element;
    }

    public T getLast() {
        return tail.element;
    }

}
