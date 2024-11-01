package com.shpp.p2p.cs.azaika.collections.arraylist.test;

import com.shpp.p2p.cs.azaika.collections.FailedTestException;
import com.shpp.p2p.cs.azaika.collections.arraylist.ArrayList;

import java.util.Arrays;
import java.util.Iterator;
import java.lang.reflect.Field;

public class ArrayListTest {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        ArrayListTest arrayListTest = new ArrayListTest();
        arrayListTest.testAddCorrectOrder();
        arrayListTest.testAddByIndex();
        arrayListTest.testRemoveByIndex();
        arrayListTest.testSize();
        arrayListTest.testIsEmpty();
        arrayListTest.testClear();
        arrayListTest.testRemoveFromEmptyList();
        arrayListTest.testAddWithNegativeIndex();
        arrayListTest.testAddBeyondSize();
        arrayListTest.testAddInFullArray();
        arrayListTest.testGet();
        arrayListTest.testGetFromEmptyList();
        arrayListTest.testRemoveLastElement();
        arrayListTest.testResize();
        arrayListTest.testIterator();
    }

    private void testAddCorrectOrder() {
        boolean result;
        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(0);
        arrayList.add(1);
        arrayList.add(3);

        int[] expected = new int[]{0, 1, 3};
        for (int i = 0; i < arrayList.size(); i++) {
            result = expected[i] == arrayList.get(i);
            if (!result) throw new FailedTestException(
                    "\n˟ Test add correct order FAILED!" +
                            "\n Wrong order of number " +
                            "\n Expected : " + Arrays.toString(expected) +
                            "\n Was : " + arrayList);
        }
        System.out.println("✓ Test add correct order PASSED!");
    }

    private void testAddByIndex() {
        boolean result;
        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(0);
        arrayList.add(1);
        arrayList.add(3);

        arrayList.add(2, 2);
        int[] expected = new int[]{0, 1, 2, 3};
        for (int i = 0; i < arrayList.size(); i++) {
            result = expected[i] == arrayList.get(i);
            if (!result) throw new FailedTestException(
                    "\n˟ Test add by index FAILED!" +
                            "\n Wrong order of number " +
                            "\n Expected : " + Arrays.toString(expected) +
                            "\n Was : " + arrayList);
        }
        System.out.println("✓ Test add by index PASSED!");

    }

    private void testRemoveByIndex() {
        boolean result;
        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(0);
        arrayList.add(1);
        arrayList.add(3);

        arrayList.remove(1);
        int[] expected = new int[]{0, 3};
        for (int i = 0; i < arrayList.size(); i++) {
            result = expected[i] == arrayList.get(i);
            if (!result) throw new FailedTestException(
                    "\n˟ Test remove by index FAILED!" +
                            "\n Wrong order of number " +
                            "\n Expected : " + Arrays.toString(expected) +
                            "\n Was : " + arrayList);
        }
        System.out.println("✓ Test remove by index PASSED!");

    }

    private void testSize() {
        boolean result;
        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(0);
        arrayList.add(1);
        arrayList.add(3);

        int expected = 3;
        result = expected == arrayList.size();

        if (!result) throw new FailedTestException(
                "\n˟ Test Size  FAILED!" +
                        "\n Wrong size " +
                        "\n Expected : " + expected +
                        "\n Was : " + arrayList.size());
        System.out.println("✓ Test Size PASSED!");

    }

    private void testIsEmpty() {
        boolean result;
        boolean expected = true;
        ArrayList<Integer> arrayList = new ArrayList<>();
        result = expected == arrayList.isEmpty();
        if (result) {
            expected = false;
            arrayList.add(0);
            arrayList.add(1);
            arrayList.add(3);

            result = expected == arrayList.isEmpty();
        }
        if (!result) throw new FailedTestException(
                "\n˟ Test IsEmpty  FAILED!" +
                        "\n Wrong result " +
                        "\n Expected : " + expected +
                        "\n Was : " + arrayList.isEmpty());
        System.out.println("✓ Test IsEmpty PASSED!");

    }

    private void testClear() {
        boolean result;
        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(0);
        arrayList.add(1);
        arrayList.add(3);

        arrayList.clear();
        int expected = 0;
        result = expected == arrayList.size();

        if (!result) throw new FailedTestException(
                "\n˟ Test Clear  FAILED!" +
                        "\n Wrong size " +
                        "\n Expected : " + expected +
                        "\n Was : " + arrayList.size());
        System.out.println("✓ Test Clear PASSED!");

    }

    private void testRemoveFromEmptyList() {
        ArrayList<Integer> arrayList = new ArrayList<>();
        try {
            arrayList.remove(0);
            throw new FailedTestException("Test remove from empty list FAILED!" +
                    " Expected exception not thrown.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("✓ Test remove from empty list PASSED!");
        }
    }

    private void testAddWithNegativeIndex() {
        ArrayList<Integer> arrayList = new ArrayList<>();
        try {
            arrayList.add(-1, 1);
            throw new FailedTestException("Test add with negative index FAILED! " +
                    "Expected exception not thrown.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("✓ Test add with negative index PASSED!");
        }
    }

    private void testAddBeyondSize() {
        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(0);
        arrayList.add(1);
        arrayList.add(2);

        try {
            arrayList.add(5, 3);
            throw new FailedTestException("Test add beyond size FAILED! " +
                    "Expected exception not thrown.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("✓ Test add beyond size PASSED!");
        }
    }

    private void testAddInFullArray() {
        ArrayList<Integer> arrayList = new ArrayList<>(2);
        arrayList.add(0);
        arrayList.add(1);
        arrayList.add(2);

        int[] expected = new int[]{0, 1, 2};
        for (int i = 0; i < arrayList.size(); i++) {
            if (expected[i] != arrayList.get(i)) {
                throw new FailedTestException("Test add in full array FAILED!" +
                        " Wrong order.");
            }
        }
        System.out.println("✓ Test add in full array PASSED!");
    }


    private void testGet() {
        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(0);
        arrayList.add(1);
        arrayList.add(2);

        int expected = 1;
        int result = arrayList.get(1);

        if (expected != result) throw new FailedTestException(
                "\n˟ Test get FAILED!" +
                        "\n Expected : " + expected +
                        "\n Was : " + result);
        System.out.println("✓ Test get PASSED!");
    }

    private void testGetFromEmptyList() {
        ArrayList<Integer> arrayList = new ArrayList<>();

        try {
            arrayList.get(1);
            throw new FailedTestException("Test get from empty list failed! " +
                    "Expected exception not thrown.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("✓ Test get PASSED!");
        }

    }

    private void testRemoveLastElement() {
        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(0);
        arrayList.add(1);
        arrayList.add(2);

        arrayList.remove(2);
        int expectedSize = 2;
        int resultSize = arrayList.size();

        if (expectedSize != resultSize) throw new FailedTestException(
                "\n˟ Test remove last element FAILED!" +
                        "\n Expected size : " + expectedSize +
                        "\n Was : " + resultSize);
        System.out.println("✓ Test remove last element PASSED!");
    }

    public void testResize() throws NoSuchFieldException, IllegalAccessException {
        ArrayList<Integer> arrayList = new ArrayList<>(2);
        arrayList.add(0);
        arrayList.add(1);
        arrayList.add(2);
        int expectedSize = 4;
        Field dataField = ArrayList.class.getDeclaredField("array");
        dataField.setAccessible(true);
        Object[] innerArray = (Object[]) dataField.get(arrayList);


        int resultSize = innerArray.length;
        if (expectedSize != resultSize) throw new FailedTestException(
                "\n˟ Test resize (Reflection) FAILED!" +
                        "\n Expected size : " + expectedSize +
                        "\n Was : " + resultSize);
        System.out.println("✓ Test resize (Reflection) PASSED!");
    }

    private void testIterator() {
        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(0);
        arrayList.add(1);
        arrayList.add(2);

        Iterator<Integer> iterator = arrayList.iterator();
        StringBuilder result = new StringBuilder();

        while (iterator.hasNext()) {
            result.append(iterator.next()).append(" ");
        }

        String expected = "0 1 2 ";
        if (!expected.equals(result.toString())) throw new FailedTestException(
                "\n˟ Test iterator FAILED!" +
                        "\n Expected : " + expected +
                        "\n Was : " + result.toString());
        System.out.println("✓ Test iterator PASSED!");
    }


}
