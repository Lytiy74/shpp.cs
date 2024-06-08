package com.shpp.p2p.cs.rnazarenko.assignment3;

import com.shpp.cs.a.console.TextProgram;
/**
 * Assignment3Part3 - Raise to the power
 */
public class Assignment3Part3 extends TextProgram {
    // base user number
    double base;
    // exponent that shows to what power base should be taken
    int exponent;
    // variable for storing result
    double result;

    /**
     * Runs the program. Asks user to enter the base of type double
     * and the exponent of type int. Call the raiseToPower method and
     * store the return value in the variable result. Print the
     * result with an equation message
     */
    @Override
    public void run() {
        promptForData();
        result = raiseToPower(base, exponent);
        printResult();
    }

    /**
     * Asks user to enter the base of type double and the exponent of type int.
     */
    private void promptForData() {
        base = readDouble("Enter base number: ");
        exponent = readInt("Enter exponent: ");
    }

    /**
     * Takes the base to the power of the exponent.
     * Checks the exponent if it's 0 then returns 1
     * if positive number then multiplies base by base exponent's number of times
     * if negative number then multiplies base by base exponent's number of times
     * and devides 1 by the result.
     * If the base is 0 and the exponent is negative, then display an explanation.
     *
     * @param base The base number on which calculation would be done
     * @param exponent The exponent to which base number would be taken to the power of
     * @return The result of taking base to the power of the exponent
     */
    private double raiseToPower(double base, int exponent) {
        double result = 1;
        if (base == 0 && exponent < 0) {
            println("Hey! In real world it would be \"undefined\"");
            println("But here it is \"Infinity\", because of type \"double\"");
            println("If it was \"int\" then it would throw an \"Exception\"");
        }
        if (exponent == 0) {
            return result;
        } else if (exponent > 0) {
            for (int i = 0; i < exponent; i++) {
                result *= base;
            }
            return result;
        } else {
            exponent = -1 * exponent;
            for (int i = 0; i < exponent; i++) {
                result *= base;
            }
            return 1 / result;
        }
    }

    /**
     * Print the result with an equation message
     */
    private void printResult() {
        println(base + " to the power of " + exponent + " equals " + result);
    }
}
