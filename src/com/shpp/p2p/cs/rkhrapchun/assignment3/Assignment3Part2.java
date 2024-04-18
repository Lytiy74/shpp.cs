package com.shpp.p2p.cs.rkhrapchun.assignment3;

import java.util.Scanner;

public class Assignment3Part2 {

    // Main method to start the program
    public static void main(String[] args) {
        Scanner num = new Scanner(System.in);  // Scanner to read user input
        System.out.print("Enter a number: ");
        double n = num.nextDouble();  // Read a double value from the user

        // Execute the loop until n becomes 1
        while (n != 1) {
            // Check if n is even
            if (n % 2 == 0) {
                n = evenNumber(n);  // Call evenNumber method if n is even
            } else {
                n = oddNumber(n);  // Call oddNumber method if n is odd
            }
        }

        // Print final message when loop ends
        System.out.println("The end.");
    }

    /**
     * Processes an even number by dividing it by two and prints the operation performed.
     *
     * @param number the number to process
     * @return the result of number / 2
     */
    public static double evenNumber(double number) {
        double result = number / 2;  // Calculate half of number
        System.out.println(number + " is even so I take half: " + result);  // Print what happens
        return result;  // Return the new value
    }

    /**
     * Processes an odd number by multiplying it by three and adding one and prints the operation performed.
     *
     * @param number the number to process
     * @return the result of 3 * number + 1
     */
    public static double oddNumber(double number){
        double result = number * 3 + 1;  // Calculate 3n + 1 for the odd number
        System.out.println(number + " is odd so I make 3n + 1: " + result);  // Print what happens
        return result;  // Return the new value
    }
}
