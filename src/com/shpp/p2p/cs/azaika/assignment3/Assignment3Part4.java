package com.shpp.p2p.cs.azaika.assignment3;

import acm.graphics.GRect;
import com.shpp.cs.a.graphics.WindowProgram;

/*
 * This program draws a pyramid made of bricks on the canvas.
 */
public class Assignment3Part4 extends WindowProgram {
    /* The height of each brick. */
    private static final double BRICK_HEIGHT = 20;
    /* The width of each brick. */
    private static final double BRICK_WIDTH = 40;
    /* The number of bricks in the base of the pyramid. */
    private static final int BRICKS_IN_BASE = 15;

    @Override
    public void run() {
        drawPyramid(BRICKS_IN_BASE, BRICK_WIDTH, BRICK_HEIGHT);
    }

    /**
     * Draws a pyramid made of bricks with the specified parameters.
     *
     * @param bricksInBase the number of bricks in the base of the pyramid. Precondition: bricksInBase > 0
     * @param brickWidth the width of each brick. Precondition: brickWidth > 0
     * @param brickHeight the height of each brick. Precondition: brickHeight > 0
     * Result: A pyramid made of bricks is drawn on the canvas.
     */
    private void drawPyramid(int bricksInBase, double brickWidth, double brickHeight) {
        int amountOfBricksInRow = bricksInBase;

        for (int row = 0; row <= bricksInBase; row++) {
            for (int column = 0; column < amountOfBricksInRow; column++) {
                double middleOfRow = amountOfBricksInRow * brickWidth / 2;
                double x = (getWidth() / 2.0 - middleOfRow) + (column * brickWidth);
                double y = getHeight() - brickHeight - (row * brickHeight);
                drawBrick(x, y, brickWidth, brickHeight);
            }
            amountOfBricksInRow--;
        }
    }

    /**
     * Draws a brick at the specified position with the specified size.
     *
     * @param x the x-coordinate of the top-left corner of the brick
     * @param y the y-coordinate of the top-left corner of the brick
     * @param brickWidth the width of the brick
     * @param brickHeight the height of the brick
     */
    private void drawBrick(double x, double y, double brickWidth, double brickHeight) {
        GRect rect = new GRect(x, y, brickWidth, brickHeight);
        add(rect);
    }
}
