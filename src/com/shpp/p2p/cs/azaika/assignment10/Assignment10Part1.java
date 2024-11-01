package com.shpp.p2p.cs.azaika.assignment10;

import java.util.HashMap;
import java.util.Queue;

/**
 * This class contains a main method that processes command-line arguments to perform mathematical operations.
 * It supports infix to postfix conversion, variable replacement, and calculation of postfix expressions.
 */
public class Assignment10Part1 {
    public static void main(String[] args) {
        HashMap<String, Double> variables = new HashMap<>();
        if (args.length == 0) {
            throw new IllegalArgumentException("No arguments passed");
        }

        String formula = args[0].replaceAll(" ", "");

        if (args.length > 1) {
            variables = VariableReplacer.replaceVariables(args);
        }

        Queue<String> postfix = PostFixConvertor.makePostfix(formula);
        double calculationResult = Calculator.calculatePostfix(postfix, variables);
        System.out.println(calculationResult);
    }


}
