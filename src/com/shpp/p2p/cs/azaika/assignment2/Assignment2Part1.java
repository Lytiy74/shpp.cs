package com.shpp.p2p.cs.azaika.assignment2;

import com.shpp.cs.a.console.TextProgram;

/**
 *  This program calculate a quadratic equation.
 */
public class Assignment2Part1 extends TextProgram {
    /**
     * <p><b>Precondition:</b> The user must enter valid coefficients for a quadratic equation.</p>
     */
    public void run(){
        // Prompt the user to enter coefficients for the quadratic equation.
        getRoots(readDouble("Enter coefficient a: "), readDouble("Enter coefficient b: "), readDouble("Enter coefficient c: "));
    }

    /**
     * Calculate and display the roots of the quadratic equation.
     * @param a The coefficient of x^2.
     * @param b The coefficient of x.
     * @param c The constant term.
     * <p><b>Precondition:</b> The coefficients 'a', 'b', and 'c' must be real numbers.</p>
     * <p><b>Result:</b> Prints the roots of the quadratic equation if they exist.</p>
     */
    private void getRoots(double a, double b, double c) {
        if (a <= 0){
            System.out.println("a must be greater then 0");
            return;
        }
        double discriminant = calculateDiscriminant(a, b, c);

        // If the discriminant is negative, the equation has no real roots.
        if (discriminant < 0) {
            System.out.println("The equation has no real roots.");
        }
        // If the discriminant is zero, the equation has one real root.
        else if (discriminant == 0) {
            double root = -b  / (2 * a);
            System.out.println("The equation has one root: " + root);
        }
        // If the discriminant is positive, the equation has two real roots.
        else {
            double root1 = (-b + Math.sqrt(discriminant)) / (2 * a);
            double root2 = (-b - Math.sqrt(discriminant)) / (2 * a);
            System.out.println("The equation has two real roots: " + root1 + " and " + root2);
        }
    }

    /**
     * Calculate the discriminant of the quadratic equation.
     * <p><b>Precondition:</b> The coefficients 'a', 'b', and 'c' must be real numbers.</p>
     * <p><b>Result:</b> Returns the discriminant value of the quadratic equation.</p>
     * @param a The coefficient of x^2.
     * @param b The coefficient of x.
     * @param c The constant term.
     * @return The discriminant value.
     */
    private double calculateDiscriminant(double a, double b, double c) {
        return b * b - 4 * a * c;
    }
}
