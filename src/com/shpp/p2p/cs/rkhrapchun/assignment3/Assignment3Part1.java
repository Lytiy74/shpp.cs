package com.shpp.p2p.cs.rkhrapchun.assignment3;

import java.util.ArrayList;
import java.util.Scanner;

public class Assignment3Part1 {

    // Main method to drive the program
    public static void main(String[] args) {
        int day = 1; // Track the number of days
        Scanner ans = new Scanner(System.in); // Scanner for input
        ArrayList<Integer> aerobics = new ArrayList<>(); // List to store aerobic exercise minutes
        ArrayList<Integer> presure = new ArrayList<>(); // List to store pressure exercise minutes

        // Loop over a week's days to collect exercise data
        while (day <= 7) {
            System.out.print("How many minutes did you do on day " + day + "? ");
            int answer = ans.nextInt(); // Store daily exercise minutes

            // Check for aerobic exercise condition
            if (answer >= 30) {
                aerobics.add(answer); // Add to aerobic list if condition met
            }

            // Check for blood pressure exercise condition
            if (answer >= 40) {
                presure.add(answer); // Add to pressure list if condition met
            }
            day++; // Increment day count
        }

        // Display results for cardiovascular health if any days met the aerobic condition
        if (aerobics.size() > 0) {
            System.out.println("Cardiovascular health:");
            System.out.println(isEnoughTimeForAerobics(aerobics)); // Check and display aerobic activity adequacy
        }

        // Display results for blood pressure if any days met the pressure condition
        if (presure.size() > 0) {
            System.out.println("Blood pressure:");
            System.out.println(isEnoughTimeForPresure(presure)); // Check and display pressure activity adequacy
        }
    }

    // Method to determine if aerobic exercise is adequate
    public static String isEnoughTimeForAerobics(ArrayList<Integer> time) {
        int days = time.size(); // Count the number of days sufficient aerobic activity was done
        String answer;

        // Condition to check if the exercise was enough
        if (days >= 5) {
            answer = "  Great job! You've done enough exercise for cardiovascular health.";
            return answer;
        } else {
            answer = "  You needed to train hard for at least " + (5 - days) + " more day(s) a week!";
        }
        return answer;
    }

    // Method to determine if exercise for blood pressure is adequate
    public static String isEnoughTimeForPresure(ArrayList<Integer> time) {
        int days = time.size(); // Count the number of days sufficient pressure activity was done
        String answer;

        // Condition to check if the exercise was enough
        if (days >= 3) {
            answer = "  Great job! You've done enough exercise to keep a low blood pressure.";
            return answer;
        } else {
            answer = "  You needed to train hard for at least " + (3 - days) + " more day(s) a week!";
        }
        return answer;
    }
}
