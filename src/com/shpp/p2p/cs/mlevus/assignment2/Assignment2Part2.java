package com.shpp.p2p.cs.mlevus.assignment2;

import acm.graphics.GOval;
import acm.graphics.GRect;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.Color;

/**
 * The class represents a window program that creates a rectangle in the center of the window with vertices located
 * at the centers of circles positioned at the corners of the window.
 */

public class Assignment2Part2 extends WindowProgram {

    /* The scale factor for calculating circle radius */
    public static final double CIRCLE_RADIUS_SCALE_FACTOR = 5.3;

    /* Colors for circles and rectangle */
    private static final Color BLACK = Color.BLACK;
    private static final Color WHITE = Color.WHITE;

    /* The dimensions of the application window.
   The actual area where your graphics are drawn is smaller than the specified application width and height.
   It's due to the decorations and borders added by the operating system's window manager. */
    public static final int APPLICATION_WIDTH = 650;
    public static final int APPLICATION_HEIGHT = 600;

    /**
     * The entry point of the program.
     */
    public void run() {
        // Get the dimensions of the drawing canvas
        double canvasWidth = getWidth();
        double canvasHeight = getHeight();

        // Calculate circle radius based on drawing area dimensions
        double circleRadius = calculateCircleRadius(canvasWidth, canvasHeight);

        // Draw four corner circles
        drawFourCornerCircles(canvasWidth, canvasHeight, circleRadius);

        // Draw centered rectangle
        drawCenteredRectangle(canvasWidth, canvasHeight, circleRadius);
    }

    /**
     * Calculates the radius of the circle based on the dimensions of the drawing area.
     *
     * @param areaWidth  The width of the drawing area.
     * @param areaHeight The height of the drawing area.
     * @return The calculated radius of the circle.
     */
    private double calculateCircleRadius(double areaWidth, double areaHeight) {
        return Math.min(areaWidth, areaHeight) / CIRCLE_RADIUS_SCALE_FACTOR;
    }

    /**
     * Draws four circles at the corners of the drawing area.
     *
     * @param canvasWidth  The width of the drawing canvas.
     * @param canvasHeight The height of the drawing canvas.
     * @param circleRadius The radius of the circles.
     */
    private void drawFourCornerCircles(double canvasWidth, double canvasHeight, double circleRadius) {

        drawCircle(circleRadius, circleRadius, circleRadius, BLACK);
        drawCircle(canvasWidth - circleRadius, circleRadius, circleRadius, BLACK);
        drawCircle(circleRadius, canvasHeight - circleRadius, circleRadius, BLACK);
        drawCircle(canvasWidth - circleRadius, canvasHeight - circleRadius, circleRadius, BLACK);
    }

    /**
     * Draws a circle centered at the indicated location.
     *
     * @param xCenter The x coordinate of the center of the circle.
     * @param yCenter The y coordinate of the center of the circle.
     * @param radius  The radius of the circle.
     * @param color   The color of the circle.
     */
    private void drawCircle(double xCenter, double yCenter, double radius, Color color) {
        double x = xCenter - radius;
        double y = yCenter - radius;
        GOval circle = new GOval(x, y, 2 * radius, 2 * radius);
        circle.setColor(color);
        circle.setFilled(true);
        add(circle);
    }

    /**
     * Draws a rectangle in the center of the drawing area with vertices located at the centers of circles
     *
     * @param canvasWidth  The width of the drawing canvas.
     * @param canvasHeight The height of the drawing canvas.
     * @param circleRadius The radius of the circles.
     */

    private void drawCenteredRectangle(double canvasWidth, double canvasHeight, double circleRadius) {
        // Calculate the width and height of the rectangle
        double rectangleWidth = canvasWidth - 2 * circleRadius;
        double rectangleHeight = canvasHeight - 2 * circleRadius;

        // Calculate the coordinates of the top-left corner of the rectangle
        double rectangleX = (canvasWidth - rectangleWidth) / 2;
        double rectangleY = (canvasHeight - rectangleHeight) / 2;

        drawRectangle(rectangleX, rectangleY, rectangleWidth, rectangleHeight, WHITE);
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
}
