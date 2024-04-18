package com.shpp.p2p.cs.rkhrapchun.assignment3;

import java.util.Scanner;

public class Assignment3Part3 {

    // Main method to start the program
    public static void main(String[] args) {
        Scanner numb = new Scanner(System.in);  // Scanner to read the base number from user input
        System.out.print("Enter the base number: ");
        double num = numb.nextDouble();  // Reads a double value from the user for the base

        Scanner power = new Scanner(System.in);  // Scanner to read the exponent from user input
        System.out.print("Enter the exponent: ");
        int n = power.nextInt();  // Reads an integer value from the user for the exponent

        // Outputs the result of raising the base to the power of exponent
        System.out.println(raiseToPower(num, n));
    }

    /**
     * Raises a base to the power of exponent.
     *
     * @param base The base number.
     * @param exponent The exponent to which the base is to be raised.
     * @return The result of base raised to the power of exponent.
     */
    private static double raiseToPower(double base, int exponent) {
        double number = 1; // Initialize result to 1 for multiplication

        // Handle positive exponents
        if (exponent > 0) {
            for (int i = 0; i < exponent; i++) {
                number *= base;  // Multiply the base 'exponent' times
            }
        }

        // Handle the case when exponent is zero
        if (exponent == 0) {
            return 1;  // Any number to the power of 0 is 1
        }

        // Handle negative exponents
        if (exponent < 0) {
            for (int i = exponent; i < 0; i++) {
                number *= base;  // Multiply the base 'exponent' times
            }
            number = 1 / number;  // Take reciprocal to handle negative exponent
        }

        return number;  // Return the calculated power
    }
}
