package com.shpp.p2p.cs.azaika.assignment3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/*
This program calculates and prints to console Collatz conjecture
 */
public class Assignment3Part2 {
    //Constants for messages to format
    private static final String MESSAGE_ODD_NUMBER = "%s is odd so i make 3n + 1: %s\n";
    private static final String MESSAGE_EVEN_NUMBER = "%s is even so I take half: %s\n";

    public static void main(String[] args) {
        Assignment3Part2 ap = new Assignment3Part2();
        ap.conjectureCollatz();
    }

    /**
     * Method reads and checks console input;
     * @return int number which user prints in console
     */
    private static int readIntFromConsole() {
        try (BufferedReader bf = new BufferedReader(new InputStreamReader(System.in))) {
                return Integer.parseInt(bf.readLine());
        } catch (NumberFormatException e) {
            System.out.println("Oops! Invalid input. Please enter an integer.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return 1;
    }

    /**
     * Method calculates Collatz conjecture
     * @param n number to calculate Collatz conjecture
     */
    private void conjectureCollatz(int n){
        if (n <= 0) {
            System.out.println("Oops! n must be greater than 0!");
            return;
        }
        while (n != 1) {
            int temp = n;
            if (n % 2 == 0) {
                n /= 2;
                System.out.format(MESSAGE_EVEN_NUMBER, temp, n);
            } else {
                n = 3 * n + 1;
                System.out.format(MESSAGE_ODD_NUMBER, temp, n);
            }
        }
    }

    private void conjectureCollatz() {
        conjectureCollatz(readIntFromConsole());
    }
}
