package com.shpp.p2p.cs.azaika.assignment2;

import acm.graphics.GOval;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;
/**
 * This class draws of ovals.
 * The ovals are arranged in a specific pattern defined by the constants oval diameter,
 * quantity of ovals, x coordinate, and y coordinate.
 */
public class Assignment2Part6 extends WindowProgram {
    // Constants for oval dimensions and coordinates
    private static final double OVAL_DIAMETER = 40;
    private static final int QUANTITY_OF_OVALS = 4;
    private static final double STARTING_COORDINATE_X = 80;
    private static final double STARTING_COORDINATE_Y = 150;

    @Override
    public void run() {
        // Draw the pattern
        drawCaterpillar(STARTING_COORDINATE_X, STARTING_COORDINATE_Y, QUANTITY_OF_OVALS);
    }

    /**
     * Draws a pattern of ovals.
     * <p><b>Precondition:</b> The coordinates must be within the window bounds.</p>
     * <p><b>Result:</b> Draws a pattern of ovals at the specified coordinates.</p>
     * @param x               The x-coordinate of the starting point.
     * @param y               The y-coordinate of the starting point.
     * @param quantityOfOvals quantity of segments of paint
     */
    private void drawCaterpillar(double x, double y, int quantityOfOvals) {
        for (int i = 0; i < quantityOfOvals; i++) {
            // Calculate coordinates for each oval
            double ovalX = x + i * (OVAL_DIAMETER + OVAL_DIAMETER / 2);
            double ovalY = y - (i % 2 * (OVAL_DIAMETER / 2));
            // Add the oval to the canvas
            add(getOval(ovalX, ovalY));
        }
    }

    /**
     * Method to make ovals
     * @param ovalX x coordinate to x-axis
     * @param ovalY coordinate to y-axis
     * @return instance of oval object
     */
    private static GOval getOval(double ovalX, double ovalY) {
        // Create and customize the oval
        GOval oval = new GOval(ovalX, ovalY, OVAL_DIAMETER * 2, OVAL_DIAMETER * 2);
        oval.setColor(Color.darkGray);
        oval.setFilled(true);
        oval.setFillColor(Color.green);
        return oval;
    }
}
