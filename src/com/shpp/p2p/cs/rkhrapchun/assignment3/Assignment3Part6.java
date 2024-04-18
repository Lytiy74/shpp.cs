package com.shpp.p2p.cs.rkhrapchun.assignment3;

import acm.graphics.GLabel;
import acm.graphics.GOval;
import com.shpp.cs.a.graphics.WindowProgram;
import java.awt.*;

public class Assignment3Part6 extends WindowProgram {
    /* Constants defining the physical characteristics and behavior of the ball and the animation */
    private static final double BALL_SIZE = 50; // Diameter of the ball
    private static final double PAUSE_TIME = 1000.0 / 48; // Pause time between frames, calculated for 48 FPS
    private static final double HORIZONTAL_VELOCITY = 4.0; // Horizontal movement speed of the ball
    private static final double INITIAL_VERTICAL_VELOCITY = -6.0; // Initial vertical velocity for the ball's movement
    private static final double GRAVITY = 0.3; // Gravitational acceleration affecting the ball
    private static final double ELASTICITY = 0.95; // Elasticity of the ball to control bouncing
    private static final Color[] COLORS = {Color.BLUE, Color.RED, Color.GREEN, Color.YELLOW, Color.MAGENTA}; // Array of colors for the ball
    private int currentColorIndex = 0; // Index to track and change the current color of the ball

    /**
     * Main method that sets up and starts the ball bouncing simulation.
     */
    public void run() {
        GOval ball = makeBall(); // Create the initial ball
        GLabel timerLabel = new GLabel("", 10, 20); // Create a label to display elapsed time
        add(timerLabel); // Add the timer label to the window
        add(ball); // Add the ball to the window
        bounceBall(ball, timerLabel); // Start the bouncing simulation
    }

    /**
     * Creates and returns a new GOval object configured as a ball, centered horizontally in the window.
     * @return A filled GOval object representing the ball.
     */
    private GOval makeBall() {
        double startX = (getWidth() - BALL_SIZE) / 2; // Calculate horizontal start position
        double startY = BALL_SIZE; // Set vertical start position
        GOval ball = new GOval(startX, startY, BALL_SIZE, BALL_SIZE); // Create the ball object
        ball.setFilled(true); // Set the ball to be filled
        ball.setColor(COLORS[currentColorIndex]); // Set the initial color of the ball
        return ball; // Return the created ball
    }

    /**
     * Handles the bouncing logic, including gravity, collision with walls and the floor, and color changes.
     * @param ball The ball to bounce.
     * @param timerLabel The label used to show the elapsed time.
     */
    private void bounceBall(GOval ball, GLabel timerLabel) {
        double dy = INITIAL_VERTICAL_VELOCITY; // Set the initial vertical velocity
        double dx = HORIZONTAL_VELOCITY; // Set the horizontal velocity
        long startTime = System.currentTimeMillis(); // Record the start time
        long duration = 5000; // Set the duration of the simulation

        // Loop until the set duration has elapsed
        while (System.currentTimeMillis() - startTime < duration) {
            ball.move(dx, dy); // Move the ball based on current velocities
            dy += GRAVITY; // Apply gravity to vertical velocity

            // Check for collision with the floor and apply bounce effect
            if (ballBelowFloor(ball) && dy > 0) {
                dy *= -ELASTICITY; // Reverse and reduce vertical velocity based on elasticity
                changeBallColor(ball); // Change the color of the ball on collision
            }

            // Check for collision with the right wall
            if (ball.getX() + BALL_SIZE >= getWidth()) {
                dx *= -1; // Reverse horizontal velocity
                changeBallColor(ball); // Change the color of the ball
            }

            // Check for collision with the left wall
            if (ball.getX() <= 0) {
                dx *= -1; // Reverse horizontal velocity
                changeBallColor(ball); // Change the color of the ball
            }

            pause(PAUSE_TIME); // Pause to maintain the frame rate
            timerLabel.setLabel("Time: " + (System.currentTimeMillis() - startTime) + " ms"); // Update the timer label
        }
    }

    /**
     * Changes the color of the ball to the next in the predefined color array.
     * @param ball The ball whose color is to be changed.
     */
    private void changeBallColor(GOval ball) {
        currentColorIndex = (currentColorIndex + 1) % COLORS.length; // Update the color index, cycling through the array
        ball.setColor(COLORS[currentColorIndex]); // Set the new color on the ball
    }

    /**
     * Checks if the ball is below the floor level of the window.
     * @param ball The ball to check.
     * @return true if the ball is below the floor, false otherwise.
     */
    private boolean ballBelowFloor(GOval ball) {
        return ball.getY() + ball.getHeight() >= getHeight(); // Check if the bottom of the ball is below the bottom of the window
    }
}
