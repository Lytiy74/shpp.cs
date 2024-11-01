package com.shpp.p2p.cs.azaika.assignment10;


import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import static com.shpp.p2p.cs.azaika.assignment10.PrecedenceChecker.precedence;

public class PostFixConvertor {
    /**
     * This method makes from a given infix mathematical formula and converts it to postfix notation.
     *
     * @param formula The infix mathematical formula as a string.
     * @return The postfix notation of the formula as a queue of characters.
     * @throws IllegalArgumentException If the formula contains an invalid character.
     */
    static Queue<String> makePostfix(String formula) {
        Queue<String> outQueue = new LinkedList<>();
        Stack<String> operatorStack = new Stack<>();
        StringBuilder number = new StringBuilder();
        StringBuilder function = new StringBuilder();
        StringBuilder variable = new StringBuilder();

        char[] chars = formula.toCharArray();
        boolean expectNumber = true;

        for (char c : chars) {
            if (Character.isLetter(c)) {
                function.append(c);
                variable.append(c);
                continue;
            }

            if (!function.isEmpty() && c == '(') {
                operatorStack.push(function.toString());
                function.setLength(0);
                variable.setLength(0);
                operatorStack.push(String.valueOf(c));
                expectNumber = true;
                continue;
            }

            if (Character.isDigit(c) || c == '.' || (c == '-' && expectNumber)) {
                if (!function.isEmpty()) {
                    function.append(c);
                    continue;
                }
                number.append(c);
                expectNumber = false;
                continue;
            }

            if (!number.isEmpty()) {
                outQueue.add(number.toString());
                number.setLength(0);
            }

            if (c == '(') {
                operatorStack.push(String.valueOf(c));
            } else if (c == ')') {
                if (!variable.isEmpty()) {
                    outQueue.add("v_" + variable);
                    variable.setLength(0);
                    function.setLength(0);
                }
                while (!operatorStack.isEmpty() && !operatorStack.peek().equals("(")) {
                    outQueue.add(operatorStack.pop());
                }
                operatorStack.pop(); // pop '('
                if (!operatorStack.isEmpty()) {
                    outQueue.add(operatorStack.pop());
                }
            } else if (isOperator(c)) {

                if (!variable.isEmpty()) {
                    outQueue.add("v_" + variable);
                    variable.setLength(0);
                    function.setLength(0);
                }
                while (!operatorStack.isEmpty() && precedence(operatorStack.peek().charAt(0), c)) {
                    outQueue.add(operatorStack.pop());
                }
                operatorStack.push(String.valueOf(c));
                expectNumber = true;
            } else if (!variable.isEmpty()) {
                outQueue.add("v_" + variable);
                function.setLength(0);
                variable.setLength(0);
            } else {
                throw new IllegalArgumentException("Invalid character: " + c);
            }
        }
        if (!variable.isEmpty()) {
            outQueue.add("v_" + variable);
            function.setLength(0);
            variable.setLength(0);
        }

        if (!number.isEmpty()) {
            outQueue.add(number.toString());
        }

        while (!operatorStack.isEmpty()) {
            outQueue.add(operatorStack.pop());
        }

        // Return the postfix notation of the formula
        return outQueue;
    }

    /**
     * This method checks if a given character represents an operator.
     *
     * @param c The character to check.
     * @return True if the character represents an operator, false otherwise.
     */
    private static boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '^';
    }
}
