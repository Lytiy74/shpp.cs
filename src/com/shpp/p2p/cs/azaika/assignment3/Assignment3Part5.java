package com.shpp.p2p.cs.azaika.assignment3;

import java.util.Random;

public class Assignment3Part5 {
    private static final String MESSAGE_YOU_EARN = "This game, you earned $%s\n";
    private static final String MESSAGE_YOUR_TOTAL = "Your total is $%s\n";
    private static final String MESSAGE_IT_TOOK_N_GAMES_TO_EARN = "It took %s games to earn $%s\n";

    public static void main(String[] args) {
        Random random = new Random();
        int luckGuyMoney = 0;
        int moneyOnTable = 1;
        int counter = 1;
        boolean eagle;
        while (luckGuyMoney < 20) {
            int currentGameProfit = 0;
            eagle = random.nextBoolean();
            while (eagle) {
                moneyOnTable += moneyOnTable;
                eagle = random.nextBoolean();
            }
            currentGameProfit = moneyOnTable;
            luckGuyMoney += moneyOnTable;
            moneyOnTable = 1;
            System.out.printf(MESSAGE_YOU_EARN, currentGameProfit);
            System.out.printf(MESSAGE_YOUR_TOTAL, luckGuyMoney);
            counter++;
        }
        System.out.printf(MESSAGE_IT_TOOK_N_GAMES_TO_EARN, counter, luckGuyMoney);
    }
}
