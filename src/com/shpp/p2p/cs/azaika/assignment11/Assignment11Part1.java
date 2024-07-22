package com.shpp.p2p.cs.azaika.assignment11;


import java.util.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class contains a main method that processes command-line arguments to perform mathematical operations.
 * It supports infix to postfix conversion, variable replacement, and calculation of postfix expressions.
 */
public class Assignment11Part1 {
    public static void main(String[] args) {
        HashMap<String, Double> variables = new HashMap<>();
        if (args.length == 0) {
            throw new IllegalArgumentException("No arguments passed");
        }

        String formula = args[0].replaceAll(" ", "");

        if (args.length > 1) {
            variables = replaceVariables(args);
        }

        Queue<String> postfix = makePostfix(formula);
        System.out.println(postfix);
        double calculationResult = calculatePostfix(postfix, variables);
        System.out.println(calculationResult);
    }

    /**
     * This method replaces variables in the given formula with their corresponding values.
     *
     * @param args The command-line arguments.
     * @return A HashMap containing the variables and their values.
     */
    private static HashMap<String, Double> replaceVariables(String[] args) {
        // Create a pattern to match variable assignments in the form "variable = value"
        HashMap<String, Double> variablesMap = new HashMap<>();
        Pattern pattern = Pattern.compile("(?<variableName>[a-z]+)\\s*=\\s*(?<variableValue>(?>-)?(\\d+)*((\\.\\d*)?)((\\^?)(-?)\\d+))");

        for (int i = 1, argsLength = args.length; i < argsLength; i++) {
            String arg = args[i];
            Matcher matcher = pattern.matcher(arg);
            while (matcher.find()) {
                double variableValue = calculatePostfix(makePostfix(matcher.group("variableValue")));
                variablesMap.put("v_" + matcher.group("variableName"), variableValue);
            }
        }
        if (variablesMap.size() < args.length-1) {throw new IllegalArgumentException("Illegal variables");}
        // Return the HashMap containing the variables and their values
        return variablesMap;
    }

    /**
     * This method calculates the result of a postfix expression.
     *
     * @param postfix The postfix expression as a queue of characters.
     * @param variables A HashMap containing the variables and their values.
     * @return The result of the postfix expression as a double.
     * @throws IllegalArgumentException If the postfix expression contains an invalid character.
     */
    private static double calculatePostfix(Queue<String> postfix, HashMap<String, Double> variables) {
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
     * This method calculates the result of a mathematical function on a single operand.
     *
     * @param v The operand.
     * @param function The mathematical function as a string.
     * @return The result of the function.
     * @throws IllegalArgumentException If the function is not supported.
     */
    private static double calculateFunction(double v, String function) {
        return switch (function) {
            case "sin" -> Math.sin(v);
            case "cos" -> Math.cos(v);
            case "tan" -> Math.tan(v);
            case "atan" -> Math.atan(v);
            case "log2" -> Math.log(v) / Math.log(2);
            case "log10" -> Math.log10(v);
            case "sqrt" -> Math.sqrt(v);
            default -> throw new IllegalArgumentException("Unsupported function: " + function);
        };
    }

    /**
     * This method calculates the result of a postfix expression with default variables.
     *
     * @param postfix The postfix expression as a queue of characters.
     * @return The result of the postfix expression as a double.
     * @throws IllegalArgumentException If the postfix expression contains an invalid character.
     */
    private static double calculatePostfix(Queue<String> postfix) {
        return calculatePostfix(postfix, new HashMap<>());
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
                if (!operatorStack.isEmpty() && isFunction(operatorStack.peek())) {
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
     * This method checks if a given string represents a mathematical function.
     *
     * @param token The string to check.
     * @return True if the string represents a mathematical function, false otherwise.
     */
    private static boolean isFunction(String token) {
        return Arrays.asList("sin", "cos", "tan", "atan", "log10", "log2", "sqrt").contains(token);
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

    /**
     * This method checks the precedence of two operators.
     *
     * @param top The top operator.
     * @param current The current operator.
     * @return True if the precedence of the top operator is greater than or equal to the precedence of the current operator, false otherwise.
     */
    private static boolean precedence(char top, char current) {
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
    private static int precedence(char operator) {
        return switch (operator) {
            case '^' -> 3;
            case '*', '/' -> 2;
            case '+', '-' -> 1;
            default -> -1;
        };
    }
}
