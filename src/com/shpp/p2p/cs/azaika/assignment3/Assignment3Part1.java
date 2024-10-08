package com.shpp.p2p.cs.azaika.assignment3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * This program analysis how much user is spent time for activity
 * Result of this program is report. About if it's enough to do activity or needs more
 */
public class Assignment3Part1 {
    /*
    Constants for program. Some of them can be changed.
     */
    private final static int DAYS_OF_THE_WEEK = 7;
    private final static int REQUIRED_DAYS_FOR_CARDIO = 5;
    private final static int REQUIRED_DAYS_FOR_BLOOD_PRESSURE = 3;
    private final static int REQUIRED_MINUTES_PER_DAY_FOR_CARDIO = 30;
    private final static int REQUIRED_MINUTES_PER_DAY_FOR_BLOOD_PRESSURE = 40;

    /*
    Constants of prepared messages with placeholders which will replaced in a report
     */
    private final static String GOOD_CARDIOVASCULAR_MESSAGE = "Cardiovascular health:\n" +
            "  Great job! You've done enough exercise for cardiovascular health.\n";
    private final static String BAD_CARDIOVASCULAR_MESSAGE = "Cardiovascular health:\n" +
            "  You needed to train hard for at least %s more day(s) a week!\n";
    private final static String GOOD_BLOOD_PRESSURE_MESSAGE = "Blood pressure:\n" +
            "  Great job! You've done enough exercise to keep a low blood pressure";

    private final static String BAD_BLOOD_PRESSURE_MESSAGE = "Blood pressure:\n" +
            "  You needed to train hard for at least %s more day(s) a week!";

    public static void main(String[] args) {
        Assignment3Part1 aerobicAnalyse = new Assignment3Part1();
        System.out.println(aerobicAnalyse.analysAerobicCondition());
    }

    /**
     * Collecting user data and then analysis.
     * This method uses Stream API to filter and count
     * how much user spends time for activity.
     *
     * @return prepared report with replaced placeholders
     */
    private String analysAerobicCondition() {
        //make array to store user data.
        int[] userData = collectUserDataToIntArray(new int[DAYS_OF_THE_WEEK]);
        return analysAerobicCondition(userData);
    }

    /**
     * Overloaded method of analysAerobicCondition
     *
     * @param userData - if we have a prepared array with data
     * @return prepared report with replaced placeholders
     */
    private String analysAerobicCondition(int[] userData) {
        //Count with stream API
        int minutesForCardio = (int) Arrays.stream(userData)
                .filter(x -> x >= REQUIRED_MINUTES_PER_DAY_FOR_CARDIO)
                .count();
        int minutesForBloodPressure = (int) Arrays.stream(userData)
                .filter(x -> x >= REQUIRED_MINUTES_PER_DAY_FOR_BLOOD_PRESSURE)
                .count();

        return buildAndGetMessage(minutesForCardio, minutesForBloodPressure);
    }

    /**
     * <p><b>Precondition:</b></p> array userData must be initialized and passed as argument,
     * user must pass the correct number to array, if user passes the wrong type the
     * exception will be caught.
     * <p><b>Result:</b></p> will return a filled integer array;
     *
     * @param userData initialized array where will be recorded data from user;
     * @return filled integer array
     */
    private int[] collectUserDataToIntArray(int[] userData) {
        //Make instance of buffer reader to read user input in console
        try (BufferedReader bf = new BufferedReader(new InputStreamReader(System.in))) {
            //Iterate over an array to read and record data from console
            for (int i = 0; i < userData.length; i++) {
                //Surround it to try/catch construction to catch an exception if a user passes a wrong type of data.
                try {
                    System.out.format("How many minutes did you do on day %s? ", i + 1);
                    int inputValue = Integer.parseInt(bf.readLine());

                    if (inputValue >= 0) {
                        userData[i] = inputValue;
                    } else throw new NumberFormatException();

                } catch (NumberFormatException e) {
                    System.out.println("Ooops! Wrong input \n" +
                            "Try again with correct number!");
                    i--;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return userData;
    }

    /**
     * Method to build a report message
     *
     * @param daysForCardio - must be specified in analysAerobicCondition method
     * @param daysForBlood  - must be specified in analysAerobicCondition method
     * @return String with a report of aerobic condition
     */
    private static String buildAndGetMessage(int daysForCardio, int daysForBlood) {
        String result;
        if (daysForCardio >= REQUIRED_DAYS_FOR_CARDIO) {
            result = GOOD_CARDIOVASCULAR_MESSAGE;
        } else {
            result = String.format(BAD_CARDIOVASCULAR_MESSAGE, REQUIRED_DAYS_FOR_CARDIO - daysForCardio);
        }

        if (daysForBlood >= REQUIRED_DAYS_FOR_BLOOD_PRESSURE) {
            result += GOOD_BLOOD_PRESSURE_MESSAGE;
        } else {
            result += String.format(BAD_BLOOD_PRESSURE_MESSAGE, REQUIRED_DAYS_FOR_BLOOD_PRESSURE - daysForBlood);
        }
        return result;
    }
}
