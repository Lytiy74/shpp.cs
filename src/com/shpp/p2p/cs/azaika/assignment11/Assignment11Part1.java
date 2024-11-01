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
            variables = VariableReplacer.replaceVariables(args);
        }

        Queue<String> postfix = PostFixConvertor.makePostfix(formula);
        double calculationResult = Calculator.calculatePostfix(postfix, variables);
        System.out.println(calculationResult);
    }

}
