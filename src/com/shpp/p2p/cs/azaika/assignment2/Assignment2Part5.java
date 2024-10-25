package com.shpp.p2p.cs.azaika.assignment2;

import acm.graphics.GRect;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;
/**
 * Program for drawing the illusion with black squares.
 * <p><b>Precondition:</b> The number of row,columns and size of squares ann space between of square must be specified.</p>
 * <p><b>Result:</b> Draws black squares with space between them and center it.</p>
 */
public class Assignment2Part5 extends WindowProgram {
    // The number of rows and columns in the grid, respectively.
    private static final int NUM_ROWS = 6;
    private static final int NUM_COLS = 6;

    // The width and height of each box.
    private static final double BOX_SIZE = 40.0;

    // The horizontal and vertical spacing between the boxes.
    private static final double BOX_SPACING = 10.0;

    @Override
    public void run() {
        drawIllusion();
    }
    /**
     * <p><b>Precondition:</b> The number of row,columns and size of squares ann space between of square must be specified.</p>
     * <p><b>Result:</b> Draws black squares with space between them and center it.</p>
     */
    private void drawIllusion() {
        double sizeRowInPixels = (BOX_SIZE + BOX_SPACING) * (NUM_COLS-1);
        double middleOfRow = sizeRowInPixels/2.0;
        double sizeColumnInPixels = (BOX_SIZE + BOX_SPACING) * (NUM_ROWS-1);
        double middleOfColumn = sizeColumnInPixels/2.0;

        // Iterate through rows and columns to draw squares
        drawRowsAndColumns(middleOfRow, middleOfColumn);
    }
    /**
     * <p><b>Precondition:</b> The middle of row and middle of column must be specified</p>
     * <p><b>Result:</b> Draws black squares with space between them and center it.</p>
     * @param middleOfRow middle of row in pixels must be specified in drawIllusion()
     * @param middleOfColumn middle of column in pixels must be specified in drawIllusion()
     */
    private void drawRowsAndColumns(double middleOfRow, double middleOfColumn) {
        for (int i = 0; i < NUM_ROWS; i++) {
            for (int j = 0; j < NUM_COLS; j++) {
                double x = (getWidth() / 2.0 - BOX_SIZE / 2.0) - middleOfRow + j * (BOX_SPACING + BOX_SIZE);
                double y = (getHeight() / 2.0 - BOX_SIZE / 2.0) - middleOfColumn + i * (BOX_SPACING + BOX_SIZE);
                add(getRect(x, y));
            }
        }
    }

    /**
     * Method make a rectangle
     * @param x coordinate to x axis
     * @param y coordinate to y axis
     * @return instance of rectangle
     */
    private static GRect getRect(double x, double y) {
        GRect gRect = new GRect(x, y, BOX_SIZE, BOX_SIZE);
        gRect.setColor(Color.black);
        gRect.setFilled(true);
        gRect.setColor(Color.black);
        return gRect;
    }
}
