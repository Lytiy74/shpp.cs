package com.shpp.p2p.cs.azaika.collections.stack.test;

import com.shpp.p2p.cs.azaika.collections.FailedTestException;
import com.shpp.p2p.cs.azaika.collections.stack.Stack;

import java.util.EmptyStackException;


public class StackTest {
    public static void main(String[] args) {
        StackTest stackTest = new StackTest();
        stackTest.testPushAndPop();
        stackTest.testSize();
        stackTest.testIsEmpty();
        stackTest.testPopFromEmptyStack();
        stackTest.testPeekOnEmptyStack();
        stackTest.testPeek();
        stackTest.testSizeAfterPushAndPop();
        stackTest.testIterator();

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

    private void testPeek() {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);

        int expected = 3;
        int actual = stack.peek();
        if (expected != actual) throw new FailedTestException(
                "\n˟ Test peek FAILED!" +
                        "\n Expected: " + expected +
                        "\n Was: " + actual);

        System.out.println("✓ Test peek PASSED!");
    }


    private void testSizeAfterPushAndPop() {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.pop(); // Should remove 3

        int expectedSize = 2; // Should be 2 after one pop
        if (stack.size() != expectedSize) throw new FailedTestException(
                "\n˟ Test size after push and pop FAILED!" +
                        "\n Expected: " + expectedSize +
                        "\n Was: " + stack.size());

        System.out.println("✓ Test size after push and pop PASSED!");
    }

    private void testPopFromEmptyStack() {
        Stack<Integer> stack = new Stack<>();
        try {
            stack.pop();
            throw new FailedTestException("Test pop from empty stack FAILED! Expected exception was not thrown.");
        } catch (EmptyStackException e) {
            System.out.println("✓ Test pop from empty stack PASSED!");
        }
    }

    private void testPeekOnEmptyStack() {
        Stack<Integer> stack = new Stack<>();
        try {
            stack.peek();
            throw new FailedTestException("Test peek on empty stack FAILED! Expected exception was not thrown.");
        } catch (EmptyStackException e) {
            System.out.println("✓ Test peek on empty stack PASSED!");
        }
    }

    private void testIterator() {
        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        stack.push(1);
        stack.push(2);

        StringBuilder result = new StringBuilder();
        for (Integer value : stack) {
            result.append(value).append(" ");
        }

        String expected = "2 1 0 ";
        if (!expected.equals(result.toString())) throw new FailedTestException(
                "\n˟ Test iterator FAILED!" +
                        "\n Expected: " + expected +
                        "\n Was: " + result.toString());
        System.out.println("✓ Test iterator PASSED!");
    }
}
