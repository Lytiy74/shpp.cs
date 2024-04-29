package com.shpp.p2p.cs.azaika.assignment5;

import com.shpp.cs.a.console.TextProgram;

public class Assignment5Part2 extends TextProgram {
    @Override
    public void run() {
        /* Sit in a loop, reading numbers and adding them. */
        while (true) {
            String n1 = readLine("Enter first number:  ");
            String n2 = readLine("Enter second number: ");
            println(n1 + " + " + n2 + " = " + addNumericStrings(n1, n2));
            println();
        }
    }

    /**
     * Given two string representations of nonnegative integers, adds the
     * numbers represented by those strings and returns the result.
     *
     * @param n1 The first number.
     * @param n2 The second number.
     * @return A String representation of n1 + n2
     */
    private String addNumericStrings(String n1, String n2) {
        // Initialize variables
        StringBuilder resultString = new StringBuilder();
        boolean carry = false;
        char result;
        int i1 = 0;

        // Align strings if necessary
        if (n1.length() > n2.length()) {
            n2 = alignTheStrings(n1, n2);
        } else if (n2.length() > n1.length()) {
            n1 = alignTheStrings(n2, n1);
        }

        // Convert strings to character arrays
        char[] n1CharArray = n1.toCharArray();
        char[] n2CharArray = n2.toCharArray();

        // Iterate over each digit of the numbers, starting from the least significant digit
        for (int i = n1CharArray.length - 1; i >= 0; i--) {
            // Convert characters to integers
            int num1 = n1CharArray[i] - 48;
            int num2 = n2CharArray[i] - 48;
            i1 = num1 + num2;

            // Add carry if necessary
            if (carry) {
                i1++;
                carry = false;
            }

            // Calculate result digit and append to result string
            result = (char) ((i1 % 10) + 48);
            resultString.append(result);

            // Check if carry is needed for the next digit
            if (i1 > 9) {
                carry = true;
            }
        }

        // Add final carry if necessary
        if (carry) {
            resultString.append('1');
        }

        // Reverse the result string and return
        return resultString.reverse().toString();
    }

    /**
     * Aligns two strings by prepending zeros to the shorter string.
     *
     * @param n1 The first number.
     * @param n2 The second number.
     * @return The aligned second number.
     */
    private static String alignTheStrings(String n1, String n2) {
        // Calculate difference in lengths
        int difference = n1.length() - n2.length();
        // Prepend zeros to the shorter string
        StringBuilder n2Builder = new StringBuilder(n2);
        for (int i = 0; i < difference; i++) {
            n2Builder.insert(0, 0);
        }
        // Convert StringBuilder back to string and return
        n2 = n2Builder.toString();
        return n2;
    }
}
