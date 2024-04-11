package com.shpp.p2p.cs.azaika.assignment3;

import acm.graphics.GLine;
import acm.graphics.GRect;
import com.shpp.cs.a.graphics.WindowProgram;

public class Assignment3Part4 extends WindowProgram {
    private static final double BRICK_HEIGHT = 20;
    private static final double BRICK_WIDTH = 40;
    private static final int BRICKS_IN_BASE = 15;

    @Override
    public void run() {
        drawPyramid(BRICKS_IN_BASE, BRICK_WIDTH, BRICK_HEIGHT);
    }

    private void drawPyramid(int bricksInBase, double brickWidth , double brickHeight) {
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

    private void drawBrick(double x, double y, double brickWidth, double brickHeight) {
        GRect rect = new GRect(x, y, brickWidth, brickHeight);
        add(rect);
    }
}
