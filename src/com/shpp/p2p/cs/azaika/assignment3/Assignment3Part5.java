package com.shpp.p2p.cs.azaika.assignment3;

import java.util.Random;

/*
 * This program simulates a gambling game where the player earns money based on coin flips.
 */
public class Assignment3Part5 {
    /* The message template for displaying the amount earned in a single game. */
    private static final String MESSAGE_YOU_EARN = "This game, you earned $%s\n";
    /* The message template for displaying the total amount earned by the player. */
    private static final String MESSAGE_YOUR_TOTAL = "Your total is $%s\n";
    /* The message template for displaying the number of games it took to reach a certain amount of money. */
    private static final String MESSAGE_IT_TOOK_N_GAMES_TO_EARN = "It took %s games to earn $%s\n";

    public static void main(String[] args) {
        // Create a Random object for generating random outcomes
        Random random = new Random();

        // Initialize variables to keep track of game statistics
        int luckGuyMoney = 0; // Total amount earned by the player
        int moneyOnTable = 1; // Initial bet amount
        int counter = 1; // Counter for the number of games played
        boolean eagle; // Represents the outcome of a coin flip

        // Continue playing games until the total amount earned reaches $20
        while (luckGuyMoney < 20) {
            int currentGameProfit = 0; // Amount earned in the current game

            // Simulate coin flips until tails (false) occurs
            eagle = random.nextBoolean();
            while (eagle) {
                moneyOnTable += moneyOnTable; // Double the bet amount on each successful flip
                eagle = random.nextBoolean();
            }

            // Calculate the amount earned in the current game and update total earnings
            currentGameProfit = moneyOnTable;
            luckGuyMoney += moneyOnTable;

            // Reset the bet amount for the next game
            moneyOnTable = 1;

            // Display the earnings for the current game and the total earnings
            System.out.printf(MESSAGE_YOU_EARN, currentGameProfit);
            System.out.printf(MESSAGE_YOUR_TOTAL, luckGuyMoney);

            // Increment the game counter
            counter++;
        }

        // Display the number of games it took to reach $20
        System.out.printf(MESSAGE_IT_TOOK_N_GAMES_TO_EARN, counter, luckGuyMoney);
    }
}
