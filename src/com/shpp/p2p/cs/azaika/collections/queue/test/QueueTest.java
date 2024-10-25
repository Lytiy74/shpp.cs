package com.shpp.p2p.cs.azaika.collections.queue.test;

import com.shpp.p2p.cs.azaika.collections.FailedTestException;
import com.shpp.p2p.cs.azaika.collections.queue.Queue;


public class QueueTest {
    public static void main(String[] args) {
        QueueTest queue = new QueueTest();
        queue.testAddAndPoll();
        queue.testSize();
        queue.testIsEmpty();

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
}
