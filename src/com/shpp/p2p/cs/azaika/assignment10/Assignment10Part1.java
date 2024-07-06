package com.shpp.p2p.cs.azaika.assignment10;


import java.util.LinkedList;

import java.util.Queue;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Assignment10Part1 {
    public static void main(String[] args) {
        try {
            if (args.length == 0) throw new IllegalArgumentException("No arguments passed");

            String formula = args[0].replaceAll(" ", "");

            if (args.length > 1) formula = replaceVariables(formula, args);

            Queue<String> postfix = makePostfix(formula);
            System.out.println(postfix);
            double calculationResult = calculatePostfix(postfix);
            System.out.println(calculationResult);

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }


    /**
     * This method replaces variables in the given formula with their corresponding values.
     *
     * @param formula The original formula string.
     * @param args    The command-line arguments.
     * @return The formula string with variables replaced by their values.
     */
    private static String replaceVariables(String formula, String[] args) {
        // Create a pattern to match variable assignments in the form "variable = value"
        Pattern pattern = Pattern.compile("([a-z])+\\s*=\\s*((-{1}){0,1}(\\d+)*((.{1})\\d+){0,1}((\\^{0,1})(-{0,1})\\d+))");

        // Iterate through the command-line arguments starting from the second one
        for (int i = 1; i < args.length; i++) {
            // Create a matcher for the current argument
            Matcher matcher = pattern.matcher(args[i]);

            // If the argument matches the pattern, replace the variable in the formula with its value
            if (matcher.matches()) {
                formula = formula.replaceAll(matcher.group(1), matcher.group(2));
            }
        }

        // Return the formula string with variables replaced by their values
        return formula;
    }

    /**
     * This method calculates the result of a postfix expression.
     *
     * @param postfix The postfix expression as a queue of characters.
     * @return The result of the postfix expression as a double.
     * @throws IllegalArgumentException If the postfix expression contains an invalid character.
     */
    private static double calculatePostfix(Queue<String> postfix) {
        // Create a stack to store operands
        Stack<Double> operandStack = new Stack<>();

        // Iterate through the postfix expression
        for (String token : postfix) {
            switch (token) {
                case "^", "*", "/", "+", "-": {
                    // If there are at least two operands on the stack, perform the operation
                    if (operandStack.size() >= 2) {
                        double v2 = operandStack.pop();
                        double v1 = operandStack.pop();
                        operandStack.add(calculateOperation(v1, v2, token.charAt(0)));
                    }
                    // If there is only one operand and the operation is unary minus, negate the operand
                    else if (operandStack.size() == 1 && token.equals("-")) {
                        double v1 = operandStack.pop();
                        operandStack.add(-v1);
                    }
                    break;
                }
                default: {
                    // If the character is a digit, push it onto the stack
                    operandStack.add(Double.parseDouble(token));
                }
            }
        }

        // After processing the entire postfix expression, the result should be on the top of the stack
        return operandStack.pop();
    }

    /**
     * This method performs a mathematical operation on two operands based on the given operator.
     *
     * @param v1 The first operand.
     * @param v2 The second operand.
     * @param c  The operator character. It can be one of the following: '+', '-', '*', '/', '^'.
     * @return The result of the operation.
     * @throws IllegalArgumentException If the operator is not one of the valid characters.
     */
    private static double calculateOperation(double v1, double v2, Character c) {
        return switch (c) {
            case '^' -> Math.pow(v1, v2); // Raise v1 to the power of v2
            case '*' -> v1 * v2; // Multiply v1 and v2
            case '/' -> v1 / v2; // Divide v1 by v2
            case '+' -> v1 + v2; // Add v1 and v2
            case '-' -> v1 - v2; // Subtract v2 from v1
            default ->
                    throw new IllegalArgumentException("Invalid operator in formula"); // Throw an exception if the operator is not valid
        };
    }

    /**
     * This method makes from a given infix mathematical formula and converts it to postfix notation.
     *
     * @param formula The infix mathematical formula as a string.
     * @return The postfix notation of the formula as a queue of characters.
     * @throws IllegalArgumentException If the formula contains an invalid character.
     */
    private static Queue<String> makePostfix(String formula) {
        // Queue to store the postfix notation
        Queue<String> outQueue = new LinkedList<>();

        // Stack to store operators
        Stack<Character> operatorStack = new Stack<>();

        // StringBuilder to build numbers from digits
        StringBuilder number = new StringBuilder();

        // Convert the formula string to a character array
        char[] chars = formula.toCharArray();

        boolean expectNumber = true;

        // Iterate through each character in the formula
        for (char c : chars) {
            // If the character is a digit or a decimal point, append it to the number
            if (Character.isDigit(c) || c == '.' || (c == '-' && expectNumber)) {
                number.append(c);
                expectNumber = false;
                continue;
            }
            // If the number is not empty, add it to the output queue and clear the number
            if (!number.isEmpty()) {
                outQueue.add(number.toString());
                number.setLength(0);
            }

            // If the character is an opening parenthesis, push it onto the operator stack
            if (c == '(') {
                operatorStack.add(c);
            }
            // If the character is a closing parenthesis, pop operators from the stack
            // and add them to the output queue until an opening parenthesis is encountered
            if (c == ')') {
                while (!operatorStack.isEmpty() && operatorStack.peek() != '(') {
                    outQueue.add(String.valueOf(operatorStack.pop()));
                }
                // Pop the opening parenthesis from the stack
                operatorStack.pop();
            }
            // If the character is an operator, pop operators from the stack
            // and add them to the output queue until an operator with lower precedence is encountered or the stack is empty
            if (c == '+' || c == '-' || c == '*' || c == '/' || c == '^') {
                while (!operatorStack.isEmpty() && precedence(operatorStack.peek(), c)) {
                    outQueue.add(String.valueOf(operatorStack.pop()));
                }
                operatorStack.add(c);
                expectNumber = true;
            }
            // If the character is an invalid character, throw an IllegalArgumentException
            else {
                throw new IllegalArgumentException("Invalid character: " + c);
            }
        }

        // If the number is not empty, add it to the output queue
        if (!number.isEmpty()) {
            outQueue.add(number.toString());
        }

        // Pop any remaining operators from the stack and add them to the output queue
        while (!operatorStack.isEmpty()) {
            outQueue.add(String.valueOf(operatorStack.pop()));
        }

        // Return the postfix notation of the formula
        return outQueue;
    }

    /**
     * This method checks the precedence of two operators.
     *
     * @param top     The operator on the top of the stack.
     * @param current The current operator being processed.
     * @return True if the precedence of the top operator is greater than or equal to the current operator.
     * False if the precedence of the top operator is less than the current operator, or if the current operator is right associative '^'.
     */
    private static boolean precedence(char top, char current) {
        // If both operators are '^' (right associative), return false
        if (top == '^' && current == '^') {
            return false;
        }
        // Compare the precedence of the top operator with the current operator
        // Return true if the precedence of the top operator is greater than or equal to the current operator
        // Return false otherwise
        return precedence(top) >= precedence(current);
    }

    private static int precedence(char operator) {
        return switch (operator) {
            case '^' -> 3;
            case '*', '/' -> 2;
            case '+', '-' -> 1;
            default -> -1;
        };
    }
}
