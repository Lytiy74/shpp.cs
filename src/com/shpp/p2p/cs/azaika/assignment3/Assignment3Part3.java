package com.shpp.p2p.cs.azaika.assignment3;

public class Assignment3Part3 {
    public static void main(String[] args) {
        Assignment3Part3 assignment3Part3 = new Assignment3Part3();

        System.out.println(assignment3Part3.raiseToPower(0.5, -2));
    }

    /**
     * Raises the base to the power of the exponent.
     *
     * @param base     the base number
     * @param exponent the exponent
     * @return the result of raising the base to the power of the exponent
     */
    private double raiseToPower(double base, int exponent) {
        double result = 1.0;

        if (exponent < 0) {
            // If the exponent is negative, convert it to positive and compute the result
            exponent = -exponent;
            for (int i = 0; i < exponent; i++) {
                result *= base;
            }
            // Return the reciprocal of the result
            return 1 / result;
        } else if (exponent == 0) {
            return 1.0; // Anything raised to the power of 0 is 1
        }

        // Compute the result for positive exponents
        for (int i = 0; i < exponent; i++) {
            result *= base;
        }
        return result;
    }
}
