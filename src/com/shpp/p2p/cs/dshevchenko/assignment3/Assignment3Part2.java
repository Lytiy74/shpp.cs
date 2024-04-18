package com.shpp.p2p.cs.dshevchenko.assignment3;

import com.shpp.cs.a.console.TextProgram;

public class Assignment3Part2 extends TextProgram {

    private int number;

    public void run() {

        pathToOne();
    }

    /**
     * Method for showing how we can move from some number to 1
     */
    private void pathToOne() {
        number = readInt("Please enter the positive number: ");
        checkForCorrectNumber();
        while (number > 1) {
            if (number % 2 == 0) {
                println(number + " - is even so I take half: " + (number /= 2));
            } else {
                println(number + " - is odd so I make 3n + 1: " + (number = number * 3 + 1));
            }
        }
    }

    /**
     * Method for checking whether the number is correct
     * if number is 1, 0, or a negative number then the program
     * will ask another (bigger) number;
     */
    private void checkForCorrectNumber() {
        if (number == 1 || number == 0 || number < 0) {
            while (number < 2) {
                println("Please enter bigger number");
                number = readInt("Please enter the positive number: ");
            }
        }
    }
}
