package com.shpp.p2p.cs.rnazarenko.assignment3;

import com.shpp.cs.a.console.TextProgram;

/**
 * Assignment3Part1 - Aerobics (physical exercising)
 */
public class Assignment3Part1 extends TextProgram {
    // constant for TAB symbol
    private static final String TAB = "\t";

    // recommended days per week of doing exercises for good blood pressure
    private static final int DAYS_FOR_LOW_BLOOD_PRESSURE = 3;
    // recommended time of doing exercises in one set for good blood pressure
    private static final int TIME_FOR_LOW_BLOOD_PRESSURE = 40;

    // recommended days per week of doing exercises for good cardiovascular health
    private static final int DAYS_FOR_GOOD_CARDIO_HEALTH = 5;
    // recommended time of doing exercises in one set for good cardiovascular health
    private static final int TIME_FOR_GOOD_CARDIO_HEALTH = 30;

    // array for storing time of exercising
    int[] exercisingPerLast7Days = new int[7];

    // days of doing exercising optimal for good blood pressure
    int optimalDaysDoneForCardioHealth;
    // variable to know if enough exercising for good cardiovascular health is done
    boolean isAerobicsForGoodCardioHealthDone;

    // days of doing exercising optimal for good blood pressure
    int optimalDaysDoneForBloodPressure;
    // variable to know if enough exercising for good blood pressure is done
    boolean isAerobicsForLowBloodDone;

    /**
     * Runs the program
     */
    @Override
    public void run() {
        examinePhysicalActivity();
    }

    /**
     * Asks user to enter how much time he/she was exercising each day past these
     * last 7 days, puts it into array. Processes data and calculate how much full
     * days of exercising was done. Checks data to see if enough exercise is done
     * then puts boolean value in corresponding variables. Displays results if
     * enough was done and if not then how much should have been done.
     */
    private void examinePhysicalActivity() {
        askForData();
        processData();
        checkData();
        showResults();
    }

    /**
     * Asks user to enter how much time he/she was exercising each day past these
     * last 7 days, puts it into array.
     */
    private void askForData() {
        for (int i = 0; i < exercisingPerLast7Days.length; i++) {
            print("How many minutes did you do on day " + (i + 1) + "? ");
            exercisingPerLast7Days[i] = readInt();
        }
    }

    /**
     * Processes data and calculate how much full days of exercising was done.
     */
    private void processData() {
        for (int minutesPerDay : exercisingPerLast7Days) {
            if (minutesPerDay >= TIME_FOR_GOOD_CARDIO_HEALTH) {
                optimalDaysDoneForCardioHealth++;
            }
            if (minutesPerDay >= TIME_FOR_LOW_BLOOD_PRESSURE) {
                optimalDaysDoneForBloodPressure++;
            }
        }
    }

    /**
     * Checks data to see if enough exercise is done
     * then puts boolean value in corresponding variable.
     */
    private void checkData() {
        isAerobicsForGoodCardioHealthDone = optimalDaysDoneForCardioHealth >= DAYS_FOR_GOOD_CARDIO_HEALTH;
        isAerobicsForLowBloodDone = optimalDaysDoneForBloodPressure >= DAYS_FOR_LOW_BLOOD_PRESSURE;
    }

    /**
     * Displays results if enough was done and if not then how much should have been done.
     */
    private void showResults() {
        println("Cardiovascular health:");
        if (isAerobicsForGoodCardioHealthDone) {
            println(TAB + "Great job! You've done enough exercise for cardiovascular health.");
        } else {
            int daysToTrain = DAYS_FOR_GOOD_CARDIO_HEALTH - optimalDaysDoneForCardioHealth;
            println(TAB + "You needed to train hard for at least " + daysToTrain + " more day(s) a week!");
        }
        println("Blood pressure:");
        if (isAerobicsForLowBloodDone) {
            println(TAB + "Great job! You've done enough exercise to keep a low blood pressure.");
        } else {
            int daysTo = DAYS_FOR_LOW_BLOOD_PRESSURE - optimalDaysDoneForBloodPressure;
            println(TAB + "You needed to train hard for at least " + daysTo + " more day(s) a week!");
        }
    }
}
