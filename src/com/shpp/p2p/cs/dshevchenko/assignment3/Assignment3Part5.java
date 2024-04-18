package com.shpp.p2p.cs.dshevchenko.assignment3;

import com.shpp.cs.a.console.TextProgram;

import java.util.random.RandomGenerator;

public class Assignment3Part5 extends TextProgram {

    public void run() {
        playGame();
    }

    /**
     * Method for playing in the casino
     * where if it comes up heads then
     * opponent double the sum which is on table
     * but if it comes up tails then
     * our character takes all money on the table
     * */
    public void playGame() {
        // for heads or tails random generation
        RandomGenerator random = RandomGenerator.getDefault();

        // total sum per game
        int totalSum = 0;
        // games counter
        int howManyGames = 0;

        while (totalSum < 20) {

            // heads == "eagle"
            boolean isHeads = true;
            int dollars = 1;

            while (isHeads) {
                isHeads = random.nextBoolean();
                if (isHeads) {
                    dollars += dollars;
                }
            }

            totalSum += dollars;
            howManyGames++;

            println("This game, you earned $" + dollars);
            println("Your total is $" + totalSum);
        }
        println("It took " + howManyGames + " games to earn $20");
    }
}
