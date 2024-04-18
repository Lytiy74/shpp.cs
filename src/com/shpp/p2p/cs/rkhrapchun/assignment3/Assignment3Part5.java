package com.shpp.p2p.cs.rkhrapchun.assignment3;

import java.util.Random;

public class Assignment3Part5 {
    // Constants defining game rules
    private static final int WIN_THRESHOLD = 20;  // Threshold for total earnings to win the game
    private static final int INITIAL_BET = 1;    // Initial bet for each new game
    private static Random random = new Random(); // Random object to simulate coin flips

    /**
     * Main method that runs the coin flip game.
     * It accumulates earnings until the total is at least $20.
     */
    public static void main(String[] args) {
        int totalEarnings = 0;  // Total earnings initialized to 0
        int numberOfGames = 0;  // Counter for the number of games played

        // Continue playing until total earnings reach the win threshold
        while (totalEarnings < WIN_THRESHOLD) {
            int pot = INITIAL_BET;  // The current game's earnings potential starts at $1
            while (true) {  // Loop indefinitely for each game
                numberOfGames++;  // Increment games played count
                if (flipCoin()) {  // If the coin flip returns heads, double the pot
                    pot *= 2;
                } else {  // If the coin flip returns tails, add the pot to total earnings
                    totalEarnings += pot;
                    System.out.println("This game, you earned $" + pot);
                    System.out.println("Your total is $" + totalEarnings);
                    break;  // Break the loop since the game ends when tails comes up
                }
            }
        }

        // Output the total number of games needed to reach or exceed the earnings threshold
        System.out.println("It took " + numberOfGames + " games to earn $20");
    }

    /**
     * Simulates flipping a coin.
     * @return boolean - returns true if the result is heads, and false if tails
     */
    private static boolean flipCoin() {
        return random.nextBoolean();  // Randomly returns true (heads) or false (tails)
    }
}
