package com.shpp.p2p.cs.mtrinko.assignment3;

import java.util.Scanner;

/*
Function of the programm:
Take any given whole number and name it n
If n is a guy, then divide it by 2
If n is unpaired, then multiply by 3 and add 1
Continue this process until n not finished 1
 */
public class Assignment3Part2 {
    public static void main(String[] args) {
        getNumberForHail();
    }

    // The programm throw "hail" from numbers
    // @param n positive integer from user.
    public static void throwHail(int n) {
        int i = n;  // I save users data in separate variable, because
        // I dont want to lose users data
        while (true) {
            int value = i;  // first I save number in value, preveios value from last iteration
            if (i % 2 == 0) {
                int hail;
                hail = i / 2; // main algorithm
                System.out.println(value + " is even so I take half: " + hail);
                i = hail; // I save current value for the next cycle
            } else {
                int hail;
                hail = i * 3 + 1; // main algorithm
                System.out.println(value + " is odd so I make 3n + 1: " + hail);
                i = hail;
            }
            if (i == 1) {
                break;
            }
        }
    }

   // this method communicate with user,
    // take a number and from this number begins a programm
    // that throws "hail" from numbers
    public static void getNumberForHail(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter please a positive integer: ");
        int n;
        try {
            n = scanner.nextInt();
            if (n <= 0) {
                System.out.println("Enter please a positive number");
                return;
            }
            throwHail(n);
        } catch (Exception e) {
            System.out.println("Enter please a positive number");
        }
    }
}
