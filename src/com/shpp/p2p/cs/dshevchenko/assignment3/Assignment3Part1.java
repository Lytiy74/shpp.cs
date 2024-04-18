package com.shpp.p2p.cs.dshevchenko.assignment3;

import com.shpp.cs.a.console.TextProgram;

public class Assignment3Part1 extends TextProgram {
    private static final int DAYS_IN_WEEK = 7;

    // Recommendation training time for heart health
    private static final int NEEDED_MINS_FOR_HEALTH = 30;
    // Recommendation training time for good pressure
    private static final int NEEDED_MINS_FOR_PRESSURE = 40;

    // How many times you need to do training for heart health
    private static final int MIN_TIMES_FOR_HEALTH = 5;
    // How many times you need to do training for good pressure
    private static final int MIN_TIMES_FOR_PRESSURE = 3;

    private int howManyMins = 0;
    private int heartHealth = 0;
    private int bloodPressure = 0;

    public void run() {
        inputtingTrainingTime();
        conclusionAboutHeartHealth(heartHealth);
        conclusionAboutBloodPressure(bloodPressure);
    }

    /**
     * Method for inputting training time for each day
     * */
    private void inputtingTrainingTime() {
        for (int i = 0; i < DAYS_IN_WEEK; i++) {
            howManyMins = readInt("How many minutes did you on day " + (i + 1) + " ? ");
            if (howManyMins >= NEEDED_MINS_FOR_HEALTH) {
                heartHealth++;
            }
            if (howManyMins >= NEEDED_MINS_FOR_PRESSURE) {
                bloodPressure++;
            }
        }
    }

    /**
     * Method for doing conclusions based on time
     * spent training for heart health per one week
     * */
    private void conclusionAboutHeartHealth(int health) {
        println("Cardiovascular health:");
        if (health >= MIN_TIMES_FOR_HEALTH) {
            println("   Great job! You've done enough exercise for cardiovascular health.");
        } else {
            println("   You needed to train hard for at least " + (MIN_TIMES_FOR_HEALTH - health) + " more day(s) a week!");
        }
    }

    /**
     * Method for doing conclusions based on time
     * spent training for good pressure per one week
     * */
    private void conclusionAboutBloodPressure(int pressure) {
        println("Blood pressure:");
        if (pressure >= MIN_TIMES_FOR_PRESSURE) {
            println("   Great job! You've done enough exercise to keep a low blood pressure.");
        } else {
            println("   You needed to train hard for at least " + (MIN_TIMES_FOR_PRESSURE - pressure) + " more day(s) a week!");
        }
    }
}
