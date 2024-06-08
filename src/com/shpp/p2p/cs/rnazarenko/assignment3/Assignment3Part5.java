package com.shpp.p2p.cs.rnazarenko.assignment3;

import com.shpp.cs.a.console.TextProgram;

import acm.util.RandomGenerator;

/**
 * Assignment3Part5 - Casino
 */
public class Assignment3Part5 extends TextProgram {
    // border value to know when to stop playing
    private static final int STOP_AMOUNT = 20;
    // remember how many times the user played
    int timesOfPlays = 0;
    // total amount of money won
    int totalWin = 0;
    // create new random generator
    RandomGenerator rgen = RandomGenerator.getInstance();

    /**
     * Runs the program.
     */
    @Override
    public void run() {
        playCasino();
    }

    /**
     * Plays casino until total won money greater or equal to 20,
     * by starting with 1 on a table and flipping the coin,
     * while coin is heads double the bet, if not take money and
     * start the game from 1. Calculates total times of plays.
     * Prints the money earned this game. Calculates total win.
     * Displays total money earned. Prints the total number of plays.
     */
    private void playCasino() {
        while (totalWin <= STOP_AMOUNT) {
            int currentWin = 1;
            while (isHeads()) {
                currentWin *= 2;
            }
            calcTimesOfPlays();
            printWinOfGame(currentWin);
            calcTotalWin(currentWin);
            printTotalMoney();
        }
        printNumberOfPlay();
    }

    /**
     * Checks if flipped coin showed heads.
     *
     * @return true if coin showed heads or false if tails.
     */
    private boolean isHeads() {
        int coin = flipCoin();
        int heads = 1;
        return coin == heads;
    }

    /**
     * Flips the coin by using a random generator.
     *
     * @return 0 for tails or 1 for heads.
     */
    private int flipCoin() {
        return rgen.nextInt(2);
    }

    /**
     * Calculates times of plays by adding one to the
     * variable timesOfPlays.
     */
    private void calcTimesOfPlays() {
        timesOfPlays++;
    }

    /**
     * Prints message showing current win.
     *
     * @param currentWin win of current finished game.
     */
    private void printWinOfGame(int currentWin) {
        println("This game, you earned $" + currentWin);
    }

    /**
     * Caclulate total win by adding to it current win.
     *
     * @param currentWin win of current finished game.
     */
    private void calcTotalWin(int currentWin) {
        totalWin += currentWin;
    }

    /**
     * Prints message showing total win of the game
     */
    private void printTotalMoney() {
        println("Your total is $" + totalWin);
    }

    /**
     * Prints number of plays
     */
    private  void printNumberOfPlay() {
        println("\n"+"It took " + timesOfPlays + " games to earn $20");
    }
}
