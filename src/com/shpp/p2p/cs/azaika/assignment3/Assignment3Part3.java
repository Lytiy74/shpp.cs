package com.shpp.p2p.cs.azaika.assignment3;

public class Assignment3Part3 {
    public static void main(String[] args) {
        Assignment3Part3 assignment3Part3 = new Assignment3Part3();

        System.out.println(assignment3Part3.raiseToPower(0.5,-2));
    }
    private double raiseToPower(double base, int exponent){
        double result = base;
        if (exponent < 0){
            exponent = -exponent;
            for (int i = 0; i < exponent-1; i++) {
                result = result * base;
            }
            return 1/result;
        } else if (exponent == 0) return 1.0;

        for (int i = 0; i < exponent-1; i++) {
            result = result * base;
        }
        return result;
    }

}
