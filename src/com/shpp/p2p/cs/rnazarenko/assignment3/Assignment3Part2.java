package com.shpp.p2p.cs.rnazarenko.assignment3;

import com.shpp.cs.a.console.TextProgram;
/**
 * Assignment3Part2 - Numbers-hails sequence
 */
public class Assignment3Part2 extends TextProgram {
    // variable for storing user entered number
    int userNumber;

    /**
     * Runs the program
     */
    @Override
    public void run() {
        doNumberHailsSequence();
    }

    /**
     * Do Number-Hails Sequence. Asks user for a number that's greater than 0
     * if not then asks again, adding that it should be a positive number.
     * Calculate the Sequence by checking if number odd or even and do
     * calculations based on that. If odd then "3n + 1", if even then "n / 2".
     */
    private void doNumberHailsSequence() {
        askForNumber();
        calcTheSequence();
        println(userNumber + " the end");
    }

    /**
     * Asks user for a number that's greater than 0 if not then asks again,
     * adding that it should be a positive number.
     */
    private void askForNumber() {
        print("Enter a number: ");
        userNumber = readInt();
        while (userNumber <= 0) {
            print("Error: Please, enter positive number: ");
            userNumber = readInt();
        }
    }

    /**
     * Calculates the Sequence by checking if number odd or even and do
     * calculations based on that. If odd then "3n + 1", if even then "n / 2".
     * Displays messages coresponding to the calculation. Does calculation until
     * user number equels 1;
     */
    private void calcTheSequence() {
        do {
            if (userNumber % 2 == 0) {
                doEvenCalc();
            } else {
                doOddCalc();
            }
        } while (userNumber != 1);
    }

    /**
     * Number is even so that means we divide it by two.
     * Display the previous result with the new result
     * and how it was caclulated
     */
    private void doEvenCalc() {
        int prevNumber = userNumber;
        userNumber = userNumber / 2;
        println(prevNumber + " is even so I take half: " + userNumber);
    }

    /**
     * Number is odd, so we multipli number by three and add to it one
     * Display the previous result with the new result
     * and how it was caclulated
     */
    private void doOddCalc() {
        int prevNumber = userNumber;
        userNumber = 3 * userNumber + 1;
        println(prevNumber + " is odd so I make 3n + 1: " + userNumber);
    }
}
