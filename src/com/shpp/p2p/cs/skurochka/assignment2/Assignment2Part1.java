package com.shpp.p2p.cs.skurochka.assignment2;

import com.shpp.cs.a.console.TextProgram;
import java.util.InputMismatchException;

public class Assignment2Part1 extends TextProgram {
    // Method of launching the program.
    public void run() {
        // The method displays a label with information about the program.
        System.out.println("Program to solve a quadratic equation");
        // Wrapping a sequence of actions in a block that can catch errors.
        try {
            // Displaying the inscription on the screen and accepting
            // information from the user and then assigning it to a variable.
            double a = readDouble("Please input a: ");
            // Displaying the inscription on the screen and accepting
            // information from the user and then assigning it to a variable.
            double b = readDouble("Please input b: ");
            // Displaying the inscription on the screen and accepting
            // information from the user and then assigning it to a variable.
            double c = readDouble("Please input c: ");
            //A method of displaying information on the screen.
            // The information is obtained from the getTheRootsOfQuadraticEquation() method,
            // which takes three numbers and calculates the result, which it returns as a string.
            println(getTheRootsOfQuadraticEquation(a, b, c));
            // The block responsible for catching errors when the program is running.
            // The type of error that will be processed is marked in brackets.
        } catch (InputMismatchException exception) {
            // Displays the caption on the screen.
            println("We're sorry, but you have entered invalid data.");
        }
    }

    /**
     * A method for calculating the roots of a quadratic equation.
     * The method takes three values of type double.
     * The first step is to create an empty string.
     * The next step is to calculate the discriminant.
     * Then, checks are performed and if the discriminant is less than zero,
     * the resulting string is assigned the corresponding value.
     * If the condition is not met, then a check is performed to see if the discriminant is greater than zero.
     *  If yes, then two roots are calculated and assigned to the resultant string If the condition is not met,
     * then one root is calculated and assigned to the resultant string.
     * The final action is to return the resulting string.
     */
    private String getTheRootsOfQuadraticEquation(double a, double b, double c) {
        String resultString = "";
        double discriminator = b * b - 4 * a * c;
        if (discriminator < 0) {
            resultString = "There are no real roots";
        } else if (discriminator > 0 && !(a == 0)) {
            double x1 = (-b + Math.sqrt(discriminator)) / (2 * a);
            double x2 = (-b - Math.sqrt(discriminator)) / (2 * a);
            resultString = "There are two roots: " + x1 + " and " + x2;
        } else if (discriminator == 0 && !(a == 0)) {
            double x = -b / (2 * a);
            resultString = "There is one root: " + x;
        } else {
            resultString = "1" +
                    "You have a problem with the data you entered. You have entered an invalid a.";
        }
        return resultString;
    }
}