package com.shpp.p2p.cs.mtrinko.assignment3;

import java.util.Scanner;

/*
The programm take a number and returns
Exponenta of this number
 */
public class Assignment3Part3 {
    public static void main(String[] args) {
     getExponenta();
    }

    // Exponentiation
    // @param n base for Exponentiation.
    // @param n exponenta.
    private static double raiseToPower(double base, int exponent) {
        if (base == 0 && exponent == 0) {
            return 1;
        }

        double result = 1;
        int counter = exponent > 0 ? exponent : exponent * (-1);

        while (counter > 0) {
            result = result * base;
            counter--;
        }

        return exponent > 0 ? result : 1 / result;
    }

    /*
    This metho interect with user
   it gets a base for exponentiation and an exponenta
     */
    public static void getExponenta() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter please a base for exponentiation:");
        double base = scanner.nextInt();
        System.out.println("Enter please a exponent for exponentiation:");
        int exponent = scanner.nextInt();

        double result = raiseToPower(base, exponent);

        System.out.println("Number " + base+ " to the degree "+ exponent + " is " + result);
    }
}
