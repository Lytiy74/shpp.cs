package com.shpp.p2p.cs.azaika.collections.arraylist.test;

import com.shpp.p2p.cs.azaika.collections.arraylist.ArrayList;

import java.util.Arrays;

public class ArrayListTest {
    public static void main(String[] args) {
        ArrayListTest arrayListTest = new ArrayListTest();
        arrayListTest.testAddCorrectOrder();
        arrayListTest.testAddByIndex();
        arrayListTest.testRemoveByIndex();
        arrayListTest.testSize();
        arrayListTest.testIsEmpty();
        arrayListTest.testClear();

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
}
