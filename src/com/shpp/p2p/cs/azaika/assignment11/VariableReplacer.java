package com.shpp.p2p.cs.azaika.assignment11;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VariableReplacer {
    /**
     * This method replaces variables in the given formula with their corresponding values.
     *
     * @param args The command-line arguments.
     * @return A HashMap containing the variables and their values.
     */
    static HashMap<String, Double> replaceVariables(String[] args) {
        // Create a pattern to match variable assignments in the form "variable = value"
        HashMap<String, Double> variablesMap = new HashMap<>();
        Pattern pattern = Pattern.compile("(?<variableName>[a-z]+)\\s*=\\s*(?<variableValue>-?\\d+(\\.\\d*)?(\\^?-?\\d+)*)");

        for (int i = 1, argsLength = args.length; i < argsLength; i++) {
            String arg = args[i];
            Matcher matcher = pattern.matcher(arg);
            while (matcher.find()) {
                double variableValue = Calculator.calculatePostfix(PostFixConvertor.makePostfix(matcher.group("variableValue")));
                variablesMap.put("v_" + matcher.group("variableName"), variableValue);
            }
        }
        if (variablesMap.size() < args.length - 1) {
            throw new IllegalArgumentException("Illegal variables");
        }
        // Return the HashMap containing the variables and their values
        return variablesMap;
    }
}
