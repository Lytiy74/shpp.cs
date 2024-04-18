package com.shpp.p2p.cs.rkhrapchun.assignment3;

import acm.graphics.GRect; // Importing GRect class for using rectangle shapes
import com.shpp.cs.a.graphics.WindowProgram; // Importing WindowProgram for creating a window application

import java.awt.*; // Importing AWT for using Color and other graphic utilities

public class Assignment3Part4 extends WindowProgram {
    // Constants
    public static final int APPLICATION_WIDTH = 800; // Width of the application window
    public static final int APPLICATION_HEIGHT = 800; // Height of the application window
    private static final int BRICK_WIDTH = 50; // Width of each brick in the pyramid
    private static final int BRICK_HEIGHT = 30; // Height of each brick in the pyramid
    private static final int BRICKS_IN_BASE = 11; // Number of bricks at the base of the pyramid

    // Method to execute the drawing
    public void run() {
        // Calculate the starting X and Y positions to center the pyramid in the window
        int startX = (getWidth() - BRICKS_IN_BASE * BRICK_WIDTH) / 2;
        int startY = getHeight() - BRICK_HEIGHT;

        // Create and add vertical line dividing the window into left and right halves
        GRect l1 = new GRect(getWidth() / 2, 0, 0, getHeight());
        l1.setColor(Color.RED); // Set color of the line to red
        add(l1); // Add the line to the window

        // Create and add horizontal line dividing the window into top and bottom halves
        GRect l2 = new GRect(0, getHeight() / 2, getWidth(), 0);
        l2.setColor(Color.RED); // Set color of the line to red
        add(l2); // Add the line to the window

        // Draw the pyramid layer by layer from top to base
        for (int row = 0; row < BRICKS_IN_BASE; row++) {
            for (int col = 0; col < BRICKS_IN_BASE - row; col++) {
                int x = startX + col * BRICK_WIDTH + row * BRICK_WIDTH / 2; // Adjusting X to center the row and stagger the bricks
                int y = startY - row * BRICK_HEIGHT; // Calculate Y position for each row
                GRect brick = new GRect(x, y, BRICK_WIDTH, BRICK_HEIGHT); // Create a rectangle to represent the brick
                add(brick); // Add the brick to the window
            }
        }
    }
}
