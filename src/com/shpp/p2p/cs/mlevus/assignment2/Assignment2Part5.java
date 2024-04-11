package com.shpp.p2p.cs.mlevus.assignment2;

import acm.graphics.GLine;
import acm.graphics.GRect;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.Color;

/**
 * This class represents a window program that draws a matrix of boxes separated by "streets".
 */
public class Assignment2Part5 extends WindowProgram {

    /* The number of rows and columns in the grid. */
    private static final int NUM_ROWS = 6;
    private static final int NUM_COLS = 6;

    /* The width and height of each box. */
    private static final double BOX_SIZE = 40;

    /* The horizontal and vertical spacing between the boxes. */
    private static final double BOX_SPACING = 30;

    /* Box color */
    private static final Color BLACK = Color.BLACK;

    /* The dimensions of the application window.
       The actual area where your graphics are drawn is smaller than the specified application width and height.
       It's due to the decorations and borders added by the operating system's window manager. */
    public static final int APPLICATION_WIDTH = 600;
    public static final int APPLICATION_HEIGHT = 500;

    /**
     * The entry point of the program.
     */
    public void run() {
        // Get the dimensions of the drawing canvas
        double canvasWidth = getWidth();
        double canvasHeight = getHeight();

        drawMatrix(canvasWidth, canvasHeight);;
        GLine hor = new GLine(0,getHeight()/2,getWidth(),getHeight()/2);
        add(hor);
        GLine ver = new GLine(getWidth()/2,0,getWidth()/2,getHeight());
        add(ver);
    }

    /**
     * Draws a matrix of boxes.
     *
     * @param canvasWidth  The width of the drawing canvas.
     * @param canvasHeight The height of the drawing canvas.
     */
    private void drawMatrix(double canvasWidth, double canvasHeight) {
        // Calculate the starting coordinates of the top-left point of the matrix, taking centering into account.
        double startX = (canvasWidth - (NUM_COLS * BOX_SIZE + (NUM_COLS - 1) * BOX_SPACING)) / 2.0;
        double startY = (canvasHeight - (NUM_ROWS * BOX_SIZE + (NUM_ROWS - 1) * BOX_SPACING)) / 2.0;

        // Draw each box in the matrix
        for (int row = 0; row < NUM_ROWS; row++) {
            for (int col = 0; col < NUM_COLS; col++) {
                double x = startX + col * (BOX_SIZE + BOX_SPACING);
                double y = startY + row * (BOX_SIZE + BOX_SPACING);
                drawBox(x, y, BLACK);
            }
        }
    }

    /**
     * Draws a box at the specified coordinates.
     *
     * @param x     The x-coordinate of the top-left corner of the box.
     * @param y     The y-coordinate of the top-left corner of the box.
     * @param color The color of the box.
     */
    private void drawBox(double x, double y, Color color) {
        GRect box = new GRect(x, y, BOX_SIZE, BOX_SIZE);
        box.setFilled(true);
        box.setFillColor(color);
        add(box);
    }
}
