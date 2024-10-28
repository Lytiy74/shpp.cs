package com.shpp.p2p.cs.azaika.collections.linkedlist;

import java.util.Iterator;
import java.util.Objects;

/**
 * Implementation of LinkedList data structures
 *
 * @param <T> type of elements in the linkedList
 **/
public class LinkedList<T> implements Iterable<T> {

    /**
     * Inner class, node which describes elements in the linked list
     * and saves a reference to the next element in the list.
     *
     * @param <T> The type of elements in the linked list.
     */
    private static class Node<T> {
        /**
         * The element stored in the node.
         */
        T element;

        /**
         * A reference to the next node in the list.
         */
        Node<T> next;

        /**
         * Constructs a new node with the given element.
         *
         * @param element The element to be stored in the node.
         */
        public Node(T element) {
            this.element = element;
        }
    }

    //Amount of elements in list
    private int size;

    //First element in list
    private Node<T> head;

    //Last element in list
    private Node<T> tail;


    /**
     * Creates LinkedList of given elements.
     *
     * @param elements the elements to add to list
     * @return new Linkedlist containing provided elements
     */
    public static <T> LinkedList<T> of(T... elements) {
        LinkedList<T> linkedList = new LinkedList<>();
        for (T element : elements) {
            linkedList.add(element);
        }
        return linkedList;
    }

    /**
     * Add element to end of list
     *
     * @param element provided element to add in list
     */
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

    /**
     * Add element to specified index.
     * If the specified index is 0, the element will be added at the beginning of the list.
     * If the specified index is equal to the size of the list, the element will be added at the end of the list.
     * If the specified index already contains data, the element will be moved one node to the right.
     *
     * @param index   The index where the element should be placed.
     * @param element The element to be added to the list.
     */
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

    /**
     * Removes the element at the specified position in this list.
     * Shifts any elements to the left
     * If the index is out of range (index < 0 || index >= size()),
     * throws an {@code IndexOutOfBoundsException}.
     *
     * @param index the index of the element to be removed
     * @return the element that was removed from the list
     */
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

    /**
     * Get element from specified index
     *
     * @param index index from where get element
     * @return element
     */
    public T get(int index) {
        return getNodeByIndex(index).element;
    }

    /**
     * Replaces the element at the specified position in this list with the specified element.
     * If the index is out of range (index < 0 || index >= size()),
     * throws an {@code IndexOutOfBoundsException}.
     */
    public void set(int index, T element) {
        getNodeByIndex(index).element = element;
    }

    /**
     * Retrieves the node at the specified index in the list.
     *
     * @param index index of the node to retrieve.
     * @return node at the specified index.
     * @throws IndexOutOfBoundsException If the index is out of range (index < 0 || index >= size()).
     */
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

    /**
     * Checks if the list contains the specified element.
     *
     * @param element element to search for in the list.
     * @return {@code true} if the list contains the specified element; {@code false} otherwise.
     */
    public boolean contains(T element) {
        Node<T> current = head;
        for (int i = 0; i < size; i++) {
            if (current.element.equals(element)) return true;
        }
        return false;
    }

    /**
     * Clears the list by removing all elements.
     * After this method is called, the list will be empty.
     * The head and tail references will be set to null, and the size will be set to 0.
     */
    public void clear() {
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

    @Override
    public Iterator<T> iterator() {
        return new LinkedListIterator();
    }

    private class LinkedListIterator implements Iterator<T> {
        private Node<T> current = head;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            T element = current.element;
            current = current.next;
            return element;
        }

    }


}
