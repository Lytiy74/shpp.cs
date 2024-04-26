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
        StringBuilder resultString = new StringBuilder();
        boolean carry = false;
        char result;
        int i1 = 0;

        if(n1.length() > n2.length()){
            n2 = alignTheStrings(n1, n2);
        } else if (n2.length() > n1.length()) {
            n1 = alignTheStrings(n2, n1);
        }

        char[] n1CharArray = n1.toCharArray();
        char[] n2CharArray = n2.toCharArray();

        for (int i = n1CharArray.length - 1; i >= 0; i--) {
            int num1 = n1CharArray[i] - 48;
            int num2 = n2CharArray[i] - 48;
            i1 = num1 + num2;

            if (carry) {
                i1++;
                carry = false;
            }

            result = (char) ((i1 % 10) + 48);
            resultString.append(result);

            if (i1 > 9) {
                carry = true;
            }
        }

        if (carry) {
            resultString.append('1');
        }

        return resultString.reverse().toString();
    }

    private static String alignTheStrings(String n1, String n2) {
        int difference =  n1.length() - n2.length();
        StringBuilder n2Builder = new StringBuilder(n2);
        for (int i = 0; i < difference; i++) {
            n2Builder.insert(0,0);
        }
        n2 =n2Builder.toString();
        return n2;
    }
}
