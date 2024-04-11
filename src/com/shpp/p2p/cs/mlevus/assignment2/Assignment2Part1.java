package com.shpp.p2p.cs.mlevus.assignment2;

import com.shpp.cs.a.console.TextProgram;

/**
 * The class represents a console program that accepts three double-type numbers (a, b, c) as input
 * and outputs the roots of a quadratic equation.
 */

public class Assignment2Part1 extends TextProgram {
    /**
     * The entry point of the program.
     */
    public void run() {
        while (true) {
            // Read the coefficients
            println("Enter coefficients for the quadratic equation:");
            double a = readDouble("Please enter a: ");
            double b = readDouble("Please enter b: ");
            double c = readDouble("Please enter c: ");

            // Check if any coefficient is zero
            if (a == 0 || b == 0 || c == 0) {
                println("Error: Coefficients cannot be zero. Please enter valid coefficients.");
            } else {
                QuadraticEquationSolver(a, b, c);
                // Exit the loop if valid coefficients are entered
                break;
            }
        }
    }

    /**
     * Solves a quadratic equation of the form ax^2 + bx + c = 0.
     *
     * @param a the coefficient of the quadratic term (ax^2)
     * @param b the coefficient of the linear term (bx)
     * @param c the constant term
     */
    private void QuadraticEquationSolver(double a, double b, double c) {
        // Calculate the discriminant of the quadratic equation
        double discriminant = b * b - 4 * a * c;

        // If the discriminant is greater than 0, the equation has two real roots
        if (discriminant > 0) {
            double root1 = (-b + Math.sqrt(discriminant)) / (2 * a);
            double root2 = (-b - Math.sqrt(discriminant)) / (2 * a);

            println("There are two roots: " + root1 + " and " + root2);

        // If the discriminant is equal to 0, the equation has one real root
        } else if (discriminant == 0) {
            double root = -b / (2 * a);

            println("There is one root: " + root);

        // If the discriminant is less than 0, the equation has no real roots.
        } else {
            println("There are no real roots");
        }
    }
}
