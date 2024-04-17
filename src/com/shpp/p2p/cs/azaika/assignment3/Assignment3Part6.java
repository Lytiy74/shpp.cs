package com.shpp.p2p.cs.azaika.assignment3;

import acm.graphics.GLine;
import acm.graphics.GOval;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;

/*
 * This program draws the x and y axes and plots the sine and cosine waves on the canvas.
 */
public class Assignment3Part6 extends WindowProgram {
    private static final int OFFSET_X_AXIS = 30;
    private static final int OFFSET_Y_AXIS = 100;
    private static final int ANIMATION_DURATION_MILLISECONDS = 5000;
    private static final int FRAMES_PER_SECOND = 1000 / 240;

    @Override
    public void run() {
        drawAxes(); // Draw x and y axes
        animateSinAndCos(); // Plot sine and cosine waves
    }

    /**
     * Draws the x and y axes on the canvas.
     */
    private void drawAxes() {
        // Draw x-axis
        GLine xAxis = new GLine(0, getHeight() / 2.0, getWidth(), getHeight() / 2.0);
        add(xAxis);

        // Draw y-axis
        GLine yAxis = new GLine(getWidth() / 2.0, 0, getWidth() / 2.0, getHeight());
        add(yAxis);
    }

    /**
     * Plots the sine and cosine waves on the canvas.
     */
    private void animateSinAndCos() {
        long startTime = System.currentTimeMillis(); // Record the start time
        long endLoopTime = 0;

        // Loop to plot the waves
        for (double i = 0; i < 20; i += 0.02) {
            // Check if 5 seconds have passed since the start of plotting
            if ((endLoopTime - startTime) >= ANIMATION_DURATION_MILLISECONDS) {
                break; // If 5 seconds have passed, exit the loop
            }

            double x = i * OFFSET_X_AXIS; // Calculate x-coordinate
            double ySin = Math.sin(i) * OFFSET_Y_AXIS + getHeight() / 2.0; // Calculate y-coordinate for sine wave
            double yCos = Math.cos(i) * OFFSET_Y_AXIS + getHeight() / 2.0; // Calculate y-coordinate for cosine wave

            // Plot points for sine and cosine waves
            drawOvalAt(x, yCos, Color.RED); // Plot point for cosine wave
            drawOvalAt(x, ySin, Color.BLACK); // Plot point for sine wave

            pause(FRAMES_PER_SECOND); // Pause to control the animation speed
            endLoopTime = System.currentTimeMillis(); // Record the end time of the current loop iteration
        }
    }

    /**
     * Draws an oval at the specified coordinates with the specified color.
     *
     * @param x    the x-coordinate of the center of the oval
     * @param y the y-coordinate of the center of the oval
     * @param color the color of the oval
     */
    private void drawOvalAt(double x, double y, Color color) {
        GOval oval = new GOval(x, y, 4, 4); // Create a new oval
        oval.setFilled(true); // Set oval to be filled
        oval.setColor(color); // Set color of the oval
        add(oval); // Add oval to the canvas
    }
}
