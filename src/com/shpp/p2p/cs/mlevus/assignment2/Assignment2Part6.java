package com.shpp.p2p.cs.mlevus.assignment2;

import acm.graphics.GOval;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.Color;

/**
 * This class represents a window program that draws a caterpillar composed of segmented circles.
 * The caterpillar can be drawn at specified locations on the canvas.
 */

public class Assignment2Part6 extends WindowProgram {
    /* The number of segments in the caterpillar */
    private static final int NUM_SEGMENTS = 12;

    /* The radius of each caterpillar segment */
    private static final double SEGMENT_RADIUS = 30;

    /* The width of the border around each segment */
    private static final int SEGMENT_BORDER_WIDTH = 4;

    /* Factor determining the horizontal movement distance between segments,
       approximately equal to 1.5 times the diameter of a segment */
    private static final double HORIZONTAL_MOVE_FACTOR = 1.5;

    /* Factor determining the vertical movement distance between segments,
       approximately equal to three-quarters of the diameter of a segment */
    private static final double VERTICAL_MOVE_FACTOR = 0.75;

    /* The color of the caterpillar segments */
    private static final Color GREEN = Color.GREEN;

    /* The color of the caterpillar segment borders */
    private static final Color RED = Color.RED;


    /* The dimensions of the application window.
       The actual area where your graphics are drawn is smaller than the specified application width and height.
       It's due to the decorations and borders added by the operating system's window manager. */
    public static final int APPLICATION_WIDTH = 750;
    public static final int APPLICATION_HEIGHT = 450;

    /**
     * The entry point of the program.
     */
    public void run() {
        // Initial coordinates of the top-left corner for the first caterpillar segment
        double x = 50;
        double y = 150;

        drawCaterpillar(x, y);
    }

    /**
     * Draws the caterpillar.
     *
     * @param x The x-coordinate of the top-left corner of the first caterpillar segment's bounding box.
     * @param y The y-coordinate of the top-left corner of the first caterpillar segment's bounding box.
     */
    private void drawCaterpillar(double x, double y) {
        // Calculate the center coordinates of the first circle segment
        double xCenter = x + SEGMENT_RADIUS;
        double yCenter = y + SEGMENT_RADIUS;

        // Draw each caterpillar segment
        for (int i = 0; i < NUM_SEGMENTS; i++) {
            drawCircle(xCenter, yCenter, SEGMENT_RADIUS, true, GREEN);
            addBorder(xCenter, yCenter, SEGMENT_RADIUS, RED);
            // Move to the next segment position
            xCenter += SEGMENT_RADIUS * HORIZONTAL_MOVE_FACTOR;
            yCenter += i % 2 == 0 ? -SEGMENT_RADIUS * 0.75 : SEGMENT_RADIUS * VERTICAL_MOVE_FACTOR;
        }
    }

    /**
     * Draws a circle centered at the indicated location.
     *
     * @param xCenter  The x coordinate of the center of the circle.
     * @param yCenter  The y coordinate of the center of the circle.
     * @param radius   The radius of the circle.
     * @param isFilled The value indicating whether the circle should be filled.
     * @param color    The color of the circle.
     */
    private void drawCircle(double xCenter, double yCenter, double radius, boolean isFilled, Color color) {
        double x = xCenter - radius;
        double y = yCenter - radius;
        GOval circle = new GOval(x, y, 2 * radius, 2 * radius);
        circle.setColor(color);
        circle.setFilled(isFilled);
        add(circle);
    }

    /**
     * Adds a border around the circle segment.
     *
     * @param xCenter The x-coordinate of the center of the circle.
     * @param yCenter The y-coordinate of the center of the circle.
     * @param radius  The radius of the circle.
     * @param color   The color of the circle border.
     */
    private void addBorder(double xCenter, double yCenter, double radius, Color color) {
        // Draw the border by drawing multiple circles with increasing radius
        for (int j = 0; j < SEGMENT_BORDER_WIDTH; j++) {
            drawCircle(xCenter, yCenter, radius + j, false, color);
        }
    }
}
