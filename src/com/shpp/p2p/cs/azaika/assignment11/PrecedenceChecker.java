package com.shpp.p2p.cs.azaika.assignment11;

public class PrecedenceChecker {
    /**
     * This method checks the precedence of two operators.
     *
     * @param top     The top operator.
     * @param current The current operator.
     * @return True if the precedence of the top operator is greater than or equal to the precedence of the current operator, false otherwise.
     */
    static boolean precedence(char top, char current) {
        if (top == '^' && current == '^') {
            return false;
        }
        return precedence(top) >= precedence(current);
    }

    /**
     * This method calculates the precedence of an operator.
     *
     * @param operator The operator.
     * @return The precedence of the operator.
     */
    static int precedence(char operator) {
        return switch (operator) {
            case '^' -> 3;
            case '*', '/' -> 2;
            case '+', '-' -> 1;
            default -> -1;
        };
    }
}
