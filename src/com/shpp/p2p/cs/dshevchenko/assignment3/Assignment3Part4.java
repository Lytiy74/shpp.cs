package com.shpp.p2p.cs.dshevchenko.assignment3;

import acm.graphics.GOval;
import acm.graphics.GRect;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;
import java.util.random.RandomGenerator;

public class Assignment3Part4 extends WindowProgram {
    public static final int BRICKS_IN_BASE = 8;

    public static final int BRICK_HEIGHT = 30;
    public static final int BRICK_WIDTH = 55 ;

   // Window size is calculated relative to the brick size and number of elements
    public static final int APPLICATION_HEIGHT = BRICKS_IN_BASE * BRICK_HEIGHT + 100;
    public static final int APPLICATION_WIDTH = BRICKS_IN_BASE * BRICK_WIDTH + 100;


    public void run() {

        // variable for saving value of base
        // it needed for change number of elements in the row (see lower)
        int numberOfRows = BRICKS_IN_BASE;

        // for correct alignment in the center
        final int START_POSITION = (getWidth() - (BRICKS_IN_BASE * BRICK_WIDTH)) / 2;

        for (int i = 0; i < BRICKS_IN_BASE; i++) {
            for (int j = 0; j < numberOfRows; j++) {

                // j * BRICK_WIDTH / 2 needed to do indentation on every row relative to previous
                createBrick(i * BRICK_WIDTH + START_POSITION + (j * BRICK_WIDTH / 2),
                            getHeight() - (j + 1) * BRICK_HEIGHT);

            }

            // every row is reduced on one element
            numberOfRows--;
        }
    }

    /**
     * Method for creating a brick
     * with set fill color, border color and size
     * */
    public void createBrick(int x, int y) {
        GRect brick = new GRect(x, y, BRICK_WIDTH, BRICK_HEIGHT);
        brick.setFilled(true);
        brick.setColor(Color.BLACK);
        brick.setFillColor(Color.ORANGE);
        add(brick);
    }
}
