package com.shpp.p2p.cs.azaika.assignment10;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Assignment10Part1 {
    public static void main(String[] args) {
        Pattern patternForExpression = Pattern.compile("(\\d|[A-Za-z])*([+\\-*^])*");
        Matcher matcherForExpression = patternForExpression.matcher(args[0]);
        List<String> operands = new ArrayList<>();
        List<String> operators = new ArrayList<>();
        while (matcherForExpression.find()) {
            if(matcherForExpression.group(1) != null) {
                operands.add(matcherForExpression.group(1));
            }
            if(matcherForExpression.group(2) != null) {
                operators.add(matcherForExpression.group(2));
            }
        }
        if (args[1] != null) {
            HashMap<String, Integer> values = new HashMap<>();
            Pattern patternForValues = Pattern.compile("([A-Za-z])*=(\\d)*");
            Matcher matcherForValues = patternForValues.matcher(args[1]);
            while (matcherForValues.find()) {
                values.put(matcherForValues.group(1), Integer.parseInt(matcherForValues.group(2)));
            }
            replaceVariables(operands, values);
        }
        System.out.println(operands);
        System.out.println(operators);
    }

    private static void replaceVariables(List<String> operands, HashMap<String,Integer> values) {
        for (int i = 0; i < operands.size(); i++) {
            if (values.containsKey(operands.get(i))) {
                operands.set(i,values.get(operands.get(i)).toString());
            }
        }
    }

    private double calculate(List<String> operands, List<String> operators) {
        int result = 0;
        
        return 0;
    }
}
