package com.shpp.p2p.cs.azaika.assignment2;

import acm.graphics.GOval;
import acm.graphics.GRect;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;
/*
This program draws illusion with four ovals in each corner and rectangle in the middle
 */

public class Assignment2Part2 extends WindowProgram {
    // Variables for a window and oval dimensions
    public static final int APPLICATION_WIDTH = 500;
    public static final int APPLICATION_HEIGHT = 300;
    private static final int OVAL_SIZE_COEFFICIENT = 3; // Coefficient for oval size
    private static final int OVAL_DIAMETER = Math.min(APPLICATION_WIDTH, APPLICATION_HEIGHT) / OVAL_SIZE_COEFFICIENT;

    /** Main Method
     * <p><b>Precondition:</b> The window must be created and have the appropriate dimensions.</p>
     * <p><b>Result:</b> Draws ovals and a rectangle on the window based on specified parameters creating an illusion.</p>
     */
    public void run() {
        drawOvals(OVAL_DIAMETER);
        drawRectangle(OVAL_DIAMETER/2, OVAL_DIAMETER/2, getWidth() - OVAL_DIAMETER, getHeight() - OVAL_DIAMETER);
    }

    /**
     * Method for drawing the rectangle.
     * <p><b>Precondition:</b> The rectangle size must be specified and match the window dimensions.</p>
     * <p><b>Result:</b> Returns a rectangle with specified coordinates x,y and width,height.</p>
     * @param x coordinate to x-axis
     * @param y coordinate to y-axis
     * @param height height of rectangle in pixels
     * @param width width of rectangle in pixels
     */
    private void drawRectangle(int x, int y, int width, int height) {
        // Creating the rectangle
        GRect rect = new GRect(x, y, width, height);
        // Setting color and filling for the rectangle
        rect.setColor(Color.white);
        rect.setFilled(true);
        rect.setFillColor(Color.white);
        add(rect);
    }

    /**
     * Method for drawing the ovals.
     * <p><b>Precondition:</b> The oval diameter must be specified and match the window dimensions.</p>
     * <p><b>Result:</b> Draws ovals in the corners of window.</p>
     * @param diameter The diameter of the oval.
     */
    private void drawOvals(int diameter) {
        // Drawing ovals
        // Outer cycle draws ovals in vertical, inner draws in horizontal
        for (int row = 0; row <= 1; row++) {
            for (int column = 0; column <= 1; column++) {
                //Make ovals, according to position in cycle
                GOval circleUpperLeft =  new GOval(column * (getWidth() - diameter),
                        row * (getHeight() - diameter), diameter, diameter);
                circleUpperLeft.setFilled(true);
                circleUpperLeft.setFillColor(Color.black);
                add(circleUpperLeft);
            }
        }
    }

}