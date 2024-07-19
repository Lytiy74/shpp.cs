package com.shpp.p2p.cs.azaika.assignment10;


import java.util.HashMap;
import java.util.LinkedList;

import java.util.Queue;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Assignment10Part1 {
    public static void main(String[] args) {
        try {
            HashMap<String,Double> variables = new HashMap<>();
            if (args.length == 0) throw new IllegalArgumentException("No arguments passed");

            String formula = args[0].replaceAll(" ", "");

            if (args.length > 1)  variables = replaceVariables(args);

            Queue<String> postfix = makePostfix(formula);
            System.out.println(postfix);
            double calculationResult = calculatePostfix(postfix, variables);
            System.out.println(calculationResult);

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }


    /**
     * This method replaces variables in the given formula with their corresponding values.
     *
     * @param args    The command-line arguments.
     * @return The formula string with variables replaced by their values.
     */
    private static HashMap replaceVariables(String[] args) {
        // Create a pattern to match variable assignments in the form "variable = value"
        HashMap<String, Double> variablesMap = new HashMap<>();
        Pattern pattern = Pattern.compile("(?<variableName>[a-z])+\\s*=\\s*(?<variableValue>(?>-{1})?(\\d+)*((.{1})\\d+)?((\\^?)(-?)\\d+))");
        for (String arg : args) {
            Matcher matcher = pattern.matcher(arg);
            while (matcher.find()){
                double variableValue = calculatePostfix(makePostfix(matcher.group("variableValue")));
                variablesMap.put(matcher.group("variableName"), variableValue);
            }
        }

        // Return the formula string with variables replaced by their values
        return variablesMap;
    }

    /**
     * This method calculates the result of a postfix expression.
     *
     * @param postfix The postfix expression as a queue of characters.
     * @return The result of the postfix expression as a double.
     * @throws IllegalArgumentException If the postfix expression contains an invalid character.
     */
    private static double calculatePostfix(Queue<String> postfix, HashMap<String, Double> variables) {
        Stack<Double> operandStack = new Stack<>();

        for (String token : postfix) {
            if (variables.containsKey(token)) token = variables.get(token).toString();
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
                case "sin", "cos" -> {
                    if (operandStack.size() >= 1) {
                        double v = operandStack.pop();
                        operandStack.add(calculateFunction(v, token));
                    }
                }
                default -> {
                    operandStack.add(Double.parseDouble(token));
                }
            }
        }

        return operandStack.pop();
    }

    private static double calculateFunction(double v, String function) {
        return switch (function) {
            case "sin" -> Math.sin(v);
            case "cos" -> Math.cos(v);
            default -> throw new IllegalArgumentException("Unsupported function: " + function);
        };
    }

    private static double calculatePostfix(Queue<String> postfix){
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
        Queue<String> outQueue = new LinkedList<>();
        Stack<String> operatorStack = new Stack<>();
        StringBuilder number = new StringBuilder();
        StringBuilder function = new StringBuilder();

        char[] chars = formula.toCharArray();
        boolean expectNumber = true;

        for (char c : chars) {
            if (Character.isLetter(c)) {
                function.append(c);
                continue;
            }

            if (!function.isEmpty() && (Character.isDigit(c) || c == '(')) {
                operatorStack.push(function.toString());
                function.setLength(0);
            }

            if (Character.isDigit(c) || c == '.' || (c == '-' && expectNumber)) {
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
                while (!operatorStack.isEmpty() && !operatorStack.peek().equals("(")) {
                    outQueue.add(operatorStack.pop());
                }
                operatorStack.pop();
                if (!operatorStack.isEmpty() && isFunction(operatorStack.peek())) {
                    outQueue.add(operatorStack.pop());
                }
            } else if (c == '+' || c == '-' || c == '*' || c == '/' || c == '^') {
                while (!operatorStack.isEmpty() && precedence(operatorStack.peek().charAt(0), c)) {
                    outQueue.add(operatorStack.pop());
                }
                operatorStack.push(String.valueOf(c));
                expectNumber = true;
            } else {
                throw new IllegalArgumentException("Invalid character: " + c);
            }
        }

        if (!number.isEmpty()) {
            outQueue.add(number.toString());
        }

        while (!operatorStack.isEmpty()) {
            outQueue.add(operatorStack.pop());
        }

        return outQueue;
    }

    private static boolean isFunction(String token) {
        return token.equals("sin") || token.equals("cos");
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
