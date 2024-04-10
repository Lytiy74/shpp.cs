package com.shpp.p2p.cs.apilipishin.assignment2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
In this program we enter 3 numbers from keyboard and find the roots
 */
public class Assignment2Part1 {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in)); //open reader for read numbers from keyboard
    static double a, b, c, d, x1, x2; // our variables

    public static void main(String[] args) throws Exception {
        a = enterNumber("first");
        b = enterNumber("second");
        c = enterNumber("third");
        findRoots(a, b, c); //transmit 3 numbers to our function
    }

    /*
    This function show on screen message, reads number from keyboard and return number(double type)
     */
    private static double enterNumber(String s) throws IOException {
        System.out.println("Please enter " + s + " number: ");  //show message
        return Double.parseDouble(reader.readLine());  //read 1 number
    }

    /*
    This function accept 3 values, find roots of quadratic and show on screen
     */
    private static void findRoots(double a, double b, double c) {
        d = (b * b - 4 * a * c); //find discriminant
        //We have 2 roots if d>0
        if (d > 0) {
            x1 = (-b + Math.sqrt(d)) / (2 * a);
            x2 = (-b - Math.sqrt(d)) / (2 * a);
            System.out.println("There are two roots:" + x1 + " " + x2); // display
        }

        //We have 1 roots if d=0
        if (d == 0) {
            System.out.println("There is one root: " + (-b / (2 * a))); // display
        }

        //We have no roots if d<0
        if (d < 0) {
            System.out.println("There are no real roots"); // display
        }
    }
}
