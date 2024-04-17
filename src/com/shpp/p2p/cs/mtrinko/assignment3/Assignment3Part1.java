package com.shpp.p2p.cs.mtrinko.assignment3;


import com.shpp.cs.a.console.TextProgram;

/*
The programm calculate how much time person have to train per day
 to keep the  cardiovascular health and  to keep a low blood pressure
*/
public class Assignment3Part1 extends TextProgram{
    // Quantity of minutes  must have for cardio training pro day
    public static final int CARDIO_NEED_A_DAY = 30;
    // Minimum quantity of days for cardio training
    public static final int CARDIO_DAYS = 5;
    // Quantity of minutes  must have for blood pressure training pro day
    public static final int BLOOD_PRESSURE_NEED_A_DAY = 40;
    // Minimum quantity of days for blood pressure training
    public static final int BLOOD_PRESSURE_DAYS = 3;
    public static final int WEEK = 7; // days in a week

    public void run() {
        // The method shows user the result
        // how much time user have to train to be healthy
        calculateTraining();
    }

    // This method get data from user:
    // How many minutes user trains per day
    // and stors the data in array
    public  int[] getTimeOfWorkout() {
        int[] workoutList = new int[WEEK];
        for (int j = 0; j < workoutList.length; j++) {
           println("How many minutes did you do on day " + (j + 1));
            try {
                workoutList[j] = readInt();
                if(workoutList[j] < 0){
                    System.out.println("Enter please a positive number");
                    return getTimeOfWorkout() ;
                }
            }catch (Exception e){
                System.out.println("Enter please a number");
                return getTimeOfWorkout() ;
            }
        }
        return workoutList;
    }

    // This method count full days that user trained
    // for any training
    public static int getDifference(int[] array, int trainingADay, int days ) {
        int counter = 0;  //  counter that count how many days was trained
        for (int i = 0; i < array.length; i++) {
            if (array[i] >= trainingADay) {
                counter++;
            }
        }
        return counter <= days ? days - counter : 0;
    }

    // The method shows user the result
    // how much time user have to train to be healthy
    public  void calculateTraining() {
        int[] timeList = getTimeOfWorkout();
        int cardioDifference = getDifference(timeList,  CARDIO_NEED_A_DAY, CARDIO_DAYS);
        int bloodPressureDifference = getDifference(timeList, BLOOD_PRESSURE_NEED_A_DAY, BLOOD_PRESSURE_DAYS);

        if (cardioDifference == 0) {
            println("Cardiovascular health:" + "\n"+
                    "Great job! You've done enough exercise for " +
                    "cardiovascular health.");
        } else {
            println(
                    "Cardiovascular health:" + "\n"+
                            "You needed to train hard for at least "
                            + cardioDifference + " more day(s) a week!");
        }

        if (bloodPressureDifference == 0) {
            println("Blood pressure:" + "\n"+
                    "Great job! You've done enough exercise to keep a low blood pressure.");
        } else {
            println("Blood pressure:" + "\n"+
                    "You needed to train hard for at least "
                    + bloodPressureDifference+ " more day(s) a week!");
        }
    }
}
