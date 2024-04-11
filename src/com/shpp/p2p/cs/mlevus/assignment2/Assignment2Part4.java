package com.shpp.p2p.cs.mlevus.assignment2;

import acm.graphics.GLine;
import acm.graphics.GRect;
import acm.graphics.GLabel;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.Color;
import java.awt.Font;

/**
 * The class represents a window program that draws a flag consisting of three vertical multi-colored stripes
 * of equal size, positioned in the center of the window.
 */
public class Assignment2Part4 extends WindowProgram {

    /* Dimensions of the flag */
    private static final double FLAG_WIDTH = 300;
    private static final double FLAG_HEIGHT = 200;

    /* Background color of the window */
    private static final Color BACKGROUND_COLOR = Color.LIGHT_GRAY;

    /* Colors of the flag */
    private static final Color BLUE = new Color(0, 85, 164);
    private static final Color WHITE = new Color(255, 255, 255);
    private static final Color RED = new Color(239, 65, 53);

    /* Margins for positioning the label */
    private static final double LABEL_RIGHT_MARGIN = 140;
    private static final double LABEL_BOTTOM_MARGIN = 20;

    /* Label properties */
    private static final String LABEL_TEXT = "Flag of France";
    private static final String LABEL_FONT_NAME = "Arial";
    private static final int LABEL_FONT_STYLE = Font.BOLD;
    private static final int LABEL_FONT_SIZE = 16;

    /* The dimensions of the application window.
       The actual area where your graphics are drawn is smaller than the specified application width and height.
       It's due to the decorations and borders added by the operating system's window manager. */
    public static final int APPLICATION_WIDTH = 500;
    public static final int APPLICATION_HEIGHT = 500;

    /**
     * The entry point of the program.
     */
    public void run() {
        // Set the background color
        setBackground(BACKGROUND_COLOR);

        // Get the dimensions of the drawing canvas
        double canvasWidth = getWidth();
        double canvasHeight = getHeight();

        // Draw the flag
        drawFlag(canvasWidth, canvasHeight);

        // Add the label
        addFlagLabel(canvasWidth, canvasHeight);
        GLine hor = new GLine(0,getHeight()/2,getWidth(),getHeight()/2);
        add(hor);
        GLine ver = new GLine(getWidth()/2,0,getWidth()/2,getHeight());
        add(ver);
    }

    /**
     * Draws the flag.
     *
     * @param canvasWidth  The width of the drawing canvas.
     * @param canvasHeight The height of the drawing canvas.
     */
    private void drawFlag(double canvasWidth, double canvasHeight) {
        // Calculate the coordinates of the top-left point of the flag, taking centering into account.
        double x = (canvasWidth - FLAG_WIDTH) / 2.0;
        double y = (canvasHeight - FLAG_HEIGHT) / 2.0;

        // Draw the blue stripe
        drawRectangle(x, y, FLAG_WIDTH / 3.0, FLAG_HEIGHT, BLUE);

        // Draw the white stripe
        drawRectangle(x + FLAG_WIDTH / 3.0, y, FLAG_WIDTH / 3.0, FLAG_HEIGHT, WHITE);

        // Draw the red stripe
        drawRectangle(x + (2 * FLAG_WIDTH) / 3.0, y, FLAG_WIDTH / 3.0, FLAG_HEIGHT, RED);
    }

    /**
     * Draws a rectangle with the indicated properties.
     *
     * @param x      The x coordinate of the upper-left corner of the rectangle.
     * @param y      The y coordinate of the upper-left corner of the rectangle.
     * @param width  The width of the rectangle.
     * @param height The height of the rectangle.
     * @param color  The color to use for the rectangle.
     */
    private void drawRectangle(double x, double y, double width, double height, Color color) {
        GRect rect = new GRect(x, y, width, height);
        rect.setFilled(true);
        rect.setColor(color);
        add(rect);
    }

    /**
     * Adds the flag label.
     *
     * @param canvasWidth  The width of the drawing canvas.
     * @param canvasHeight The height of the drawing canvas.
     */
    private void addFlagLabel(double canvasWidth, double canvasHeight) {
        // Calculate the coordinates of the top-left corner of the label
        double x = canvasWidth - LABEL_RIGHT_MARGIN;
        double y = canvasHeight - LABEL_BOTTOM_MARGIN;

        addLabel(LABEL_TEXT, x, y);
    }

    /**
     * Adds a label to the canvas at the specified coordinates.
     *
     * @param text The text to be displayed on the label.
     * @param x    The x-coordinate of the top-left corner of the label.
     * @param y    The y-coordinate of the top-left corner of the label.
     */
    private void addLabel(String text, double x, double y) {
        GLabel label = new GLabel(text, x, y);
        // Set the font for the label
        label.setFont(new Font(LABEL_FONT_NAME, LABEL_FONT_STYLE, LABEL_FONT_SIZE));
        add(label);
    }
}
