package com.shpp.p2p.cs.azaika.assignment10;


import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Assignment10Part1 {
    public static void main(String[] args) {
        String formula = args[0].replaceAll(" ", "");
        if (args.length > 1) {
            formula = replaceVariables(formula, args);
        }
        Queue<Character> postfix = evaluatePostfix(formula);
        System.out.println(postfix);
        int calculationResult = calculatePostfix(postfix);
        System.out.println(calculationResult);
    }


    private static String replaceVariables(String formula, String[] args) {
        Pattern pattern = Pattern.compile("([a-z])+\\s*=\\s*(\\d)+");
        for (int i = 1; i < args.length; i++) {
            Matcher matcher = pattern.matcher(args[i]);
            if (matcher.matches()) {
                formula = formula.replaceAll(matcher.group(1), matcher.group(2));
            }
        }
        return formula;
    }

    private static int calculatePostfix(Queue<Character> postfix) {
        Stack<Integer> operandStack = new Stack<>();
        for (Character c : postfix) {
            switch (c) {
                case '^', '*', '/', '+', '-': {
                    int v2 = operandStack.pop();
                    int v1 = operandStack.pop();
                    operandStack.add(calculateOperation(v1, v2, c));
                    break;
                }
                default: {
                    if (Character.isDigit(c)) {
                        operandStack.add(Character.getNumericValue(c));
                    } else {
                        throw new IllegalArgumentException();
                    }
                }
            }
        }
        return operandStack.pop();
    }

    private static Integer calculateOperation(int v1, int v2, Character c) {
        return switch (c) {
            case '^' -> (int) Math.pow(v1, v2);
            case '*' -> v1 * v2;
            case '/' -> v1 / v2;
            case '+' -> v1 + v2;
            case '-' -> v1 - v2;
            default -> throw new IllegalArgumentException();
        };
    }

    private static Queue<Character> evaluatePostfix(String formula) {
        Queue<Character> outQueue = new LinkedList<>();
        Stack<Character> operatorStack = new Stack<>();
        char[] chars = formula.toCharArray();
        for (char c : chars) {
            switch (c) {
                case '(', '^', '/', '*': {
                    operatorStack.add(c);
                    break;
                }
                case ')': {
                    while (operatorStack.peek()!= '(') {
                        outQueue.add(operatorStack.pop());
                    }
                    operatorStack.pop(); // Discard the '('
                    break;
                }
                case '+', '-': {
                    if (!operatorStack.isEmpty() && (operatorStack.peek().equals('*') || operatorStack.peek().equals('/') || operatorStack.peek().equals('^'))) {
                        while (!operatorStack.isEmpty() && (operatorStack.peek().equals('*') || operatorStack.peek().equals('/') || operatorStack.peek().equals('^'))) {
                            outQueue.add(operatorStack.pop());
                        }
                    }
                    operatorStack.add(c);
                    break;
                }
                default: {
                    if (Character.isDigit(c)) {
                        outQueue.add(c);
                    } else {
                        throw new IllegalArgumentException();
                    }
                }
            }
        }
        while (!operatorStack.isEmpty()) {
            outQueue.add(operatorStack.pop());
        }
        return outQueue;
    }
}
