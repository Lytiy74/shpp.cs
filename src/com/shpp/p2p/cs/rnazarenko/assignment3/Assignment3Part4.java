package com.shpp.p2p.cs.rnazarenko.assignment3;

import acm.graphics.GRect;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;

/**
 * Assignment3Part4 - Pyramids
 */
public class Assignment3Part4 extends WindowProgram {
    // constants for changing size of the window
    // use with caution!
    @SuppressWarnings("unused")
    public static final int APPLICATION_WIDTH = 600;
    @SuppressWarnings("unused")
    public static final int APPLICATION_HEIGHT = 400;
    // brick width and height
    private static final double BRICK_WIDTH = 20;
    private static final double BRICK_HEIGHT = 10;
    // bricks in the base level
    private static final int BRICKS_IN_BASE = 26;
    // the number of levels in the pyramid equals to the number of
    // bricks in base
    private static final int LEVELS_OF_PYRAMID = BRICKS_IN_BASE;
    // constant for displaying bricks, taking from acctual image
    private static final Color BRICK_BORDER_COLOR = new Color(191, 123, 35);
    private static final Color BRICK_COLOR = new Color(237, 177, 83);
    // width of the bonding box of the pyramid
    double widthOfPyramidBox = BRICK_WIDTH * BRICKS_IN_BASE;
    // height of the bonding box of the pyramid
    double heightOfPyramidBox = BRICK_HEIGHT * LEVELS_OF_PYRAMID;
    // declaring variables for the future calculation of the coordinates of the pyramid box
    double xPositionOfPyramid;
    double yPositionOfPyramid;

    /**
     * Runs the program
     */
    @Override
    public void run() {
        drawPyramid();
    }

    /**
     * Draws the pyramid by firstly calculating the coordinates of the pyramid,
     * so the pyramid touches the bottom edge of the window.
     * Then draws the pyramid starting from the top level (one brick) and getting to
     * the bottom level (BRICKS_IN_BASE).
     * Draws level by level passing counter from loop which represents both the level
     * and the bricks in that level.
     */
    private void drawPyramid() {
        calculateCoordinatesOfPyramid();
        for (int i = 1; i <= LEVELS_OF_PYRAMID; i++) {
            drawLevel(i);
        }
    }

    /**
     *  Calculates the coordinates of the pyramid, so the pyramid touches
     *  the bottom edge of the window.
     */
    private void calculateCoordinatesOfPyramid() {
        xPositionOfPyramid = (getWidth() - widthOfPyramidBox) / 2.0;
        yPositionOfPyramid = getHeight() - heightOfPyramidBox;
    }

    /**
     * Draws the pyramid level by firstly calculating the width and the height
     * of the current level and then calculating coordinates based on that.
     * Calls drawBrick passing coordinates and bricks at that level
     *
     * @param levelAndBricksInLevel Represents current level and bricks
     *                              at that level
     */
    private void drawLevel(int levelAndBricksInLevel) {
        double widthToTheStartOfLevel = (widthOfPyramidBox - BRICK_WIDTH * levelAndBricksInLevel) / 2.0;
        // substract 1 from level so that it starts from the top
        double heightToTheStartOfLevel = (BRICK_HEIGHT * (levelAndBricksInLevel - 1));

        double xPositionOfLevel = xPositionOfPyramid + widthToTheStartOfLevel;
        double yPositionOfLevel = yPositionOfPyramid + heightToTheStartOfLevel;

        for (int j = 1; j <= levelAndBricksInLevel; j++) {
            drawBrick(xPositionOfLevel, yPositionOfLevel, j);
        }
    }

    /**
     * Draws a brick with specified width and height, calculates x and y of a brick
     * based on which brick in the level it is. Sets color to the border and brick itself.
     * And adds it to the window.
     *
     * @param xPositionOfLevel The x position of the current level
     * @param yPositionOfLevel The y position of the current level
     * @param bricksInLevel The number of bricks in the current level
     */
    private void drawBrick(double xPositionOfLevel, double yPositionOfLevel, int bricksInLevel) {
        GRect brick = new GRect(BRICK_WIDTH, BRICK_HEIGHT);
        brick.setLocation(xPositionOfLevel + BRICK_WIDTH * (bricksInLevel - 1), yPositionOfLevel);
        brick.setColor(BRICK_BORDER_COLOR);
        brick.setFilled(true);
        brick.setFillColor(BRICK_COLOR);
        add(brick);
    }
}
