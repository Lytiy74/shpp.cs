package com.shpp.p2p.cs.azaika.assignment11;


import java.util.HashMap;
import java.util.Queue;
import java.util.Stack;

public class Calculator {
    /**
     * This method calculates the result of a postfix expression.
     *
     * @param postfix The postfix expression as a queue of characters.
     * @param variables A HashMap containing the variables and their values.
     * @return The result of the postfix expression as a double.
     * @throws IllegalArgumentException If the postfix expression contains an invalid character.
     */
    static double calculatePostfix(Queue<String> postfix, HashMap<String, Double> variables) {
        Stack<Double> operandStack = new Stack<>();

        for (String token : postfix) {
            if (variables.containsKey(token)) {
                token = variables.get(token).toString();
            }
            switch (token) {
                case "^", "*", "/", "+", "-" -> {
                    if (operandStack.size() >= 2) {
                        double v2 = operandStack.pop();
                        double v1 = operandStack.pop();
                        operandStack.add(calculateOperation(v1, v2, token.charAt(0)));
                    } else if (operandStack.size() == 1 && token.equals("-")) {
                        double v1 = operandStack.pop();
                        operandStack.add(-v1);
                    }
                }
                case "sin", "cos", "tan", "atan", "log2", "log10", "sqrt" -> {
                    if (!operandStack.isEmpty()) {
                        double v = operandStack.pop();
                        operandStack.add(calculateFunction(v, token));
                    }
                }
                default -> {
                    if (!token.matches("-?\\d+(\\.\\d+)?")) {
                        throw new IllegalArgumentException("Invalid character: " + token.split("_")[1]);
                    }
                    operandStack.add(Double.parseDouble(token));
                }
            }
        }

        // Return the result of the postfix expression
        return operandStack.pop();
    }

    /**
     * This method calculates the result of a postfix expression with default variables.
     *
     * @param postfix The postfix expression as a queue of characters.
     * @return The result of the postfix expression as a double.
     * @throws IllegalArgumentException If the postfix expression contains an invalid character.
     */
    static double calculatePostfix(Queue<String> postfix) {
        return calculatePostfix(postfix, new HashMap<>());
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
            case '^' -> Math.pow(v1, v2);
            case '*' -> v1 * v2;
            case '/' -> {
                if (v2 == 0) {
                    throw new IllegalArgumentException("Division by zero");
                }
                yield v1 / v2;
            }
            case '+' -> v1 + v2;
            case '-' -> v1 - v2;
            default -> throw new IllegalArgumentException("Invalid operator in formula");
        };
    }
    /**
     * This method calculates the result of a mathematical function on a single operand.
     *
     * @param v The operand.
     * @param function The mathematical function as a string.
     * @return The result of the function.
     * @throws IllegalArgumentException If the function is not supported.
     */
    private static double calculateFunction(double v, String function) {
        return switch (function) {
            case "sin" -> Math.sin(Math.toRadians(v));
            case "cos" -> Math.cos(Math.toRadians(v));
            case "tan" -> Math.tan(Math.toRadians(v));
            case "atan" -> Math.atan(Math.toRadians(v));
            case "log2" -> Math.log(v) / Math.log(2);
            case "log10" -> Math.log10(v);
            case "sqrt" -> Math.sqrt(v);
            default -> throw new IllegalArgumentException("Unsupported function: " + function);
        };
    }

}
