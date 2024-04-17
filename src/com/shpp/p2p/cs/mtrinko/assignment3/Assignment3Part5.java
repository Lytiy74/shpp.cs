package com.shpp.p2p.cs.mtrinko.assignment3;

import java.util.Random;

/*
This is a hypothetical casino game with a simple ideology.
Two people play: the lucky one and the sweaty one.
The lucky one leaves the casino when they earn $20 or more.
The sweaty person puts $1 on the table, and the lucky one starts tossing a coin.
If it's an eagle, then the sweaty one adds exactly the same amount to the amount on the table.
If tails, everything on the table goes to the lucky one.
If the lucky player has less than $20 in the end, then the game is repeated.
 */
public class Assignment3Part5 {
    public static void main(String[] args) {
        // The method shows a game between 2 person
        getWin();
    }

    // The method shows a game between 2 person
    public static void getWin() {
        Random random = new Random();
        int total = 0;
        int counter = 0;
        int table = 1;
        while (true) {
            int rand = random.nextInt(2);
            if (rand == 0) {
                table += table;
            } else {
                System.out.println("This game, you earned " + table);
                total += table;
                table = 1;
                counter++;
                System.out.println("Your total is " + total);
                if (total >= 20) {
                    System.out.println("It took " + counter +" games" + " to earn $20");
                    break;
                }
            }
        }
    }
}
