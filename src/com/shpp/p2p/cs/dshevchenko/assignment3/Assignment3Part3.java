package com.shpp.p2p.cs.dshevchenko.assignment3;

import com.shpp.cs.a.console.TextProgram;

public class Assignment3Part3 extends TextProgram {

    public void run() {
        double number = readDouble("Please enter a number: ");
        int power = readInt("Please enter a power: ");
        println( "Result: " + raiseToPower(number, power));
    }

    /**
     * Method for raising a number to a power
     * base can be whatever you want
     * power can be only positive number
     */
    private double raiseToPower(double base, int exponent) {
        // variable for saving value of base
        double baseMean = base;

        if (exponent == 0) {
            return 1;
        } else if (exponent > 0) {
            for (int i = 1; i < exponent; i++) {
                base *= baseMean;
            }
        } else {
            // algorithm for inverting number (replace numerator and denominator)
            int numberOfMul = 1;
            while (base % 10 != 0) {
                base *= 10;
                numberOfMul *= 10;
            }
            base = numberOfMul / base;

            // variable for saving value of base
            baseMean = base;
            // power for inverting number
            exponent = -exponent;

            for (int i = 1; i < exponent; i++) {
                base *= baseMean;
            }
        }

        return base;
    }
}
