package com.shpp.p2p.cs.azaika.collections.stack.test;

import com.shpp.p2p.cs.azaika.collections.FailedTestException;
import com.shpp.p2p.cs.azaika.collections.stack.Stack;


public class StackTest {
    public static void main(String[] args) {
        StackTest stackTest = new StackTest();
        stackTest.testPushAndPop();
        stackTest.testSize();
        stackTest.testIsEmpty();

    }

    private void testPushAndPop() {
        boolean result;
        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        stack.push(1);
        stack.push(3);

        int[] expected = new int[]{0, 1, 3};
        for (int i = stack.size()-1; i >= 0; i--) {
            Integer popped = stack.pop();
            result = expected[i] == popped;
            if (!result) throw new FailedTestException(
                    "\n˟ Test add correct order FAILED!" +
                            "\n Wrong order of number " +
                            "\n Expected : " + expected[i] +
                            "\n Was : " + popped);
        }
        System.out.println("✓ Test add correct order PASSED!");
    }

    private void testSize() {
        boolean result;
        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        stack.push(1);
        stack.push(3);

        int expected = 3;
        result = expected == stack.size();

        if (!result) throw new FailedTestException(
                "\n˟ Test Size  FAILED!" +
                        "\n Wrong size " +
                        "\n Expected : " + expected +
                        "\n Was : " + stack.size());
        System.out.println("✓ Test Size PASSED!");

    }

    private void testIsEmpty() {
        boolean result;
        boolean expected = true;
        Stack<Integer> stack = new Stack<>();
        result = expected == stack.isEmpty();
        if (result) {
            expected = false;
            stack.push(0);
            stack.push(1);
            stack.push(3);

            result = expected == stack.isEmpty();
        }
        if (!result) throw new FailedTestException(
                "\n˟ Test IsEmpty  FAILED!" +
                        "\n Wrong result " +
                        "\n Expected : " + expected +
                        "\n Was : " + stack.isEmpty());
        System.out.println("✓ Test IsEmpty PASSED!");

    }

}
