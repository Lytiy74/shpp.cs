package com.shpp.p2p.cs.azaika.collections.linkedlist.test;

import com.shpp.p2p.cs.azaika.collections.FailedTestException;
import com.shpp.p2p.cs.azaika.collections.linkedlist.LinkedList;


public class LinkedListTest {
    public static void main(String[] args) {
        LinkedListTest linkedListTest = new LinkedListTest();
        linkedListTest.testAddCorrectOrder();
        linkedListTest.testAddByIndex();
        linkedListTest.testRemoveByIndex();
        linkedListTest.testSize();
        linkedListTest.testIsEmpty();
        linkedListTest.testClear();
        linkedListTest.testRemoveFromEmptyList();
        linkedListTest.testGetFromEmptyList();
        linkedListTest.testContains();
    }

    private void testAddCorrectOrder() {
        boolean result;
        LinkedList<Integer> linkedList = new LinkedList<>();
        linkedList.add(0);
        linkedList.add(1);
        linkedList.add(3);

        int[] expected = new int[]{0, 1, 3};
        for (int i = 0; i < linkedList.size(); i++) {
            result = expected[i] == linkedList.get(i);
            if (!result) throw new FailedTestException(
                    "\n˟ Test add correct order FAILED!" +
                            "\n Wrong order of number " +
                            "\n Expected : " + expected[i] +
                            "\n Was : " + linkedList.get(i));
        }
        System.out.println("✓ Test add correct order PASSED!");
    }

    private void testAddByIndex() {
        boolean result;
        LinkedList<Integer> linkedList = new LinkedList<>();
        linkedList.add(0);
        linkedList.add(1);
        linkedList.add(3);

        linkedList.add(2, 2);
        int[] expected = new int[]{0, 1, 2, 3};
        for (int i = 0; i < linkedList.size(); i++) {
            result = expected[i] == linkedList.get(i);
            if (!result) throw new FailedTestException(
                    "\n˟ Test add by index FAILED!" +
                            "\n Wrong order of number " +
                            "\n Expected : " + expected[i] +
                            "\n Was : " + linkedList.get(i));
        }
        System.out.println("✓ Test add by index PASSED!");

    }

    private void testRemoveByIndex() {
        boolean result;
        LinkedList<Integer> linkedList = new LinkedList<>();
        linkedList.add(0);
        linkedList.add(1);
        linkedList.add(3);

        linkedList.remove(1);
        int[] expected = new int[]{0, 3};
        for (int i = 0; i < linkedList.size(); i++) {
            result = expected[i] == linkedList.get(i);
            if (!result) throw new FailedTestException(
                    "\n˟ Test remove by index FAILED!" +
                            "\n Wrong order of number " +
                            "\n Expected : " + expected[i] +
                            "\n Was : " + linkedList.get(i));
        }
        System.out.println("✓ Test remove by index PASSED!");

    }

    private void testSize() {
        boolean result;
        LinkedList<Integer> linkedList = new LinkedList<>();
        linkedList.add(0);
        linkedList.add(1);
        linkedList.add(3);

        int expected = 3;
        result = expected == linkedList.size();

        if (!result) throw new FailedTestException(
                "\n˟ Test Size  FAILED!" +
                        "\n Wrong size " +
                        "\n Expected : " + expected +
                        "\n Was : " + linkedList.size());
        System.out.println("✓ Test Size PASSED!");

    }

    private void testIsEmpty() {
        boolean result;
        boolean expected = true;
        LinkedList<Integer> linkedList = new LinkedList<>();
        result = expected == linkedList.isEmpty();
        if (result) {
            expected = false;
            linkedList.add(0);
            linkedList.add(1);
            linkedList.add(3);

            result = expected == linkedList.isEmpty();
        }
        if (!result) throw new FailedTestException(
                "\n˟ Test IsEmpty  FAILED!" +
                        "\n Wrong result " +
                        "\n Expected : " + expected +
                        "\n Was : " + linkedList.isEmpty());
        System.out.println("✓ Test IsEmpty PASSED!");

    }

    private void testClear() {
        boolean result;
        LinkedList<Integer> linkedList = new LinkedList<>();
        linkedList.add(0);
        linkedList.add(1);
        linkedList.add(3);

        linkedList.clear();
        int expected = 0;
        result = expected == linkedList.size();

        if (!result) throw new FailedTestException(
                "\n˟ Test Clear  FAILED!" +
                        "\n Wrong size " +
                        "\n Expected : " + expected +
                        "\n Was : " + linkedList.size());
        System.out.println("✓ Test Clear PASSED!");

    }

    private void testRemoveFromEmptyList() {
        LinkedList<Integer> linkedList = new LinkedList<>();
        try {
            linkedList.remove(0);
            throw new FailedTestException("Test remove from empty list FAILED! Expected exception was not thrown.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("✓ Test remove from empty list PASSED!");
        }
    }

    private void testGetFromEmptyList() {
        LinkedList<Integer> linkedList = new LinkedList<>();
        try {
            linkedList.get(0);
            throw new FailedTestException("Test get from empty list FAILED! Expected exception was not thrown.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("✓ Test get from empty list PASSED!");
        }
    }

    private void testContains() {
        LinkedList<Integer> linkedList = new LinkedList<>();
        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(3);

        boolean result = linkedList.contains(2);
        if (!result) throw new FailedTestException("Test contains FAILED! Expected true for existing element.");

        result = linkedList.contains(4);
        if (result) throw new FailedTestException("Test contains FAILED! Expected false for non-existing element.");

        System.out.println("✓ Test contains PASSED!");
    }


}
