package com.shpp.p2p.cs.azaika.collections.queue.test;

import com.shpp.p2p.cs.azaika.collections.FailedTestException;
import com.shpp.p2p.cs.azaika.collections.queue.Queue;


public class QueueTest {
    public static void main(String[] args) {
        QueueTest queue = new QueueTest();
        queue.testAddAndPoll();
        queue.testSize();
        queue.testIsEmpty();
        queue.testPollFromEmptyQueue();
        queue.testPollOrder();
        queue.testPeek();
        queue.testSizeAfterPoll();
        queue.testIterator();

    }

    private void testAddAndPoll() {
        boolean result;
        Queue<Integer> queue = new Queue<>();
        queue.add(0);
        queue.add(1);
        queue.add(3);
        int[] expected = new int[]{0, 1, 3};
        for (int i = 0; i < queue.size(); i++) {
            Integer polled = queue.poll();
            result = expected[i] == polled;
            if (!result) throw new FailedTestException(
                    "\n˟ Test add correct order FAILED!" +
                            "\n Wrong order of number " +
                            "\n Expected : " + expected[i] +
                            "\n Was : " + polled);
        }
        System.out.println("✓ Test add correct order PASSED!");
    }

    private void testSize() {
        boolean result;
        Queue<Integer> queue = new Queue<>();
        queue.add(0);
        queue.add(1);
        queue.add(3);

        int expected = 3;
        result = expected == queue.size();

        if (!result) throw new FailedTestException(
                "\n˟ Test Size  FAILED!" +
                        "\n Wrong size " +
                        "\n Expected : " + expected +
                        "\n Was : " + queue.size());
        System.out.println("✓ Test Size PASSED!");

    }

    private void testIsEmpty() {
        boolean result;
        boolean expected = true;
        Queue<Integer> queue = new Queue<>();
        result = expected == queue.isEmpty();
        if (result) {
            expected = false;
            queue.add(0);
            queue.add(1);
            queue.add(3);

            result = expected == queue.isEmpty();
        }
        if (!result) throw new FailedTestException(
                "\n˟ Test IsEmpty  FAILED!" +
                        "\n Wrong result " +
                        "\n Expected : " + expected +
                        "\n Was : " + queue.isEmpty());
        System.out.println("✓ Test IsEmpty PASSED!");

    }

    private void testPollFromEmptyQueue() {
        Queue<Integer> queue = new Queue<>();
        Integer result = queue.poll();

        if (result != null) throw new FailedTestException(
                "\n˟ Test poll from empty queue FAILED!" +
                        "\n Expected: null" +
                        "\n Was: " + result);
        System.out.println("✓ Test poll from empty queue PASSED!");
    }

    private void testPollOrder() {
        Queue<Integer> queue = new Queue<>();
        queue.add(0);
        queue.add(1);
        queue.add(2);

        int[] expected = new int[]{0, 1, 2};
        for (int expectedValue : expected) {
            Integer polledValue = queue.poll();
            if (expectedValue != polledValue) throw new FailedTestException(
                    "\n˟ Test poll order FAILED!" +
                            "\n Expected: " + expectedValue +
                            "\n Was: " + polledValue);
        }
        System.out.println("✓ Test poll order PASSED!");
    }

    private void testPeek() {
        Queue<Integer> queue = new Queue<>();
        queue.add(1);
        queue.add(2);
        queue.add(3);

        int expected = 3;
        int actual = queue.peek();
        if (expected != actual) throw new FailedTestException(
                "\n˟ Test peek FAILED!" +
                        "\n Expected: " + expected +
                        "\n Was: " + actual);

        System.out.println("✓ Test peek PASSED!");
    }

    private void testSizeAfterPoll() {
        Queue<Integer> queue = new Queue<>();
        queue.add(0);
        queue.add(1);
        queue.add(2);

        int expectedSizeAfterFirstPoll = 2;
        queue.poll(); // Remove first element
        int actualSize = queue.size();

        if (expectedSizeAfterFirstPoll != actualSize) throw new FailedTestException(
                "\n˟ Test size after poll FAILED!" +
                        "\n Expected size: " + expectedSizeAfterFirstPoll +
                        "\n Was: " + actualSize);
        System.out.println("✓ Test size after poll PASSED!");
    }

    private void testIterator() {
        Queue<Integer> queue = new Queue<>();
        queue.add(0);
        queue.add(1);
        queue.add(2);

        StringBuilder result = new StringBuilder();
        for (Integer value : queue) {
            result.append(value).append(" ");
        }

        String expected = "0 1 2 ";
        if (!expected.equals(result.toString())) throw new FailedTestException(
                "\n˟ Test iterator FAILED!" +
                        "\n Expected: " + expected +
                        "\n Was: " + result.toString());
        System.out.println("✓ Test iterator PASSED!");
    }

}
