package com.shpp.p2p.cs.azaika.assignment4;

import acm.graphics.GOval;
import acm.graphics.GRect;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Assignment4Part1 extends WindowProgram {
    // Size of the ball
    private static final double BALL_SIZE = 5.0;

    // Frames per second
    private static final double PAUSE_TIME = 1000.0 / 60;

    // Width of the rocket
    private static final double ROCKET_WIDTH = 50.0;

    // Height of the rocket
    private static final double ROCKET_HEIGHT = 10.0;

    // Offset of the rocket from the top of the window
    private static final int ROCKET_OFFSET_Y_AXIS = 50;

    // Width of the application window
    public static final int APPLICATION_WIDTH = 300;

    // Height of the application window
    public static final int APPLICATION_HEIGHT = 600;

    // Width of each brick
    private static final int BRICK_WIDTH = 30;

    // Height of each brick
    private static final int BRICK_HEIGHT = 10;

    // Spacing between bricks
    private static final int BRICK_SPACING = 4;

    // Number of columns of bricks
    private static final int COLUMNS_QUANTITY = 8;

    // Number of rows of bricks
    private static final int ROWS_QUANTITY = 8;

    // Array of colors for bricks
    private static final Color[] colorsArray = new Color[]{Color.RED, Color.ORANGE, Color.GREEN, Color.CYAN};
    private static int LIVES = 3;
    private GOval ball;
    private GRect rocket;
    private final List<GRect> bricks = new ArrayList<>();


    @Override
    public void run() {
        // Initialize the game
        initGame();
    }

    /**
     * Initialization of the game: initializes the rocket, ball, bricks, adds mouse listener, and starts the game.
     */
    private void initGame() {
        initRocket(rocket);
        initBall(ball);
        initBricks(bricks, colorsArray);
        addMouseListeners();

        startGame();
    }

    /**
     * Method initializes bricks with specified row and columns quantity and adds all references to the bricks list.
     * @param bricks List where all references of initialized bricks are saved
     * @param colors Array of colors for bricks
     */
    private void initBricks(List<GRect> bricks, Color[] colors) {
        // Calculate the size of rows and columns to find start coordinates in X and Y axis
        double rowSizeInPixels = (BRICK_WIDTH + BRICK_SPACING) * (ROWS_QUANTITY - 1);
        double columnSizeInPixels = (BRICK_WIDTH + BRICK_SPACING) * (COLUMNS_QUANTITY - 1);
        double middleOfRow = rowSizeInPixels / 2;
        double middleOfColumn = columnSizeInPixels / 2;

        for (int i = 0; i < ROWS_QUANTITY; i++) {
            for (int j = 0; j < COLUMNS_QUANTITY; j++) {
                // Calculate x and y coordinates for brick
                double x = (getWidth() / 2.0 - BRICK_WIDTH / 2.0) - middleOfColumn + j * (BRICK_WIDTH + BRICK_SPACING);
                double y = (getHeight() / 2.0 - BRICK_HEIGHT / 2.0) - middleOfRow + i * (BRICK_HEIGHT + BRICK_SPACING);
                Color color = colors[i/2]; // Alternate colors for rows
                // Create brick and add to canvas and List of bricks
                GRect brick = getBrick(color, x, y);
                bricks.add(brick);
                add(brick);
            }
        }
    }

    /**
     * Creates bricks with specified parameters and returns an instance of GRect
     * @param color color to paint brick
     * @param x coordinate on the x-axis
     * @param y coordinate on the y-axis
     * @return instance of GRect which is used in the game field.
     */
    private GRect getBrick(Color color, double x, double y) {
        GRect brick = new GRect(x, y, BRICK_WIDTH, BRICK_HEIGHT);
        brick.setFilled(true);
        brick.setColor(color);
        return brick;
    }

    /**
     * Initializes a game object Ball and adds it to the canvas
     * @param ball variable where to save ball reference
     */
    private void initBall(GOval ball) {
        if (ball != null) {
            remove(ball);
        }
        this.ball = new GOval(getWidth()/2.0,getHeight()/2.0, BALL_SIZE, BALL_SIZE);
        this.ball.setFilled(true);
        add(this.ball);
    }

    /**
     * Initializes the game rocket
     * @param rocket variable where to save rocket reference
     */
    private void initRocket(GRect rocket) {
        double x = getWidth() / 2.0 - ROCKET_WIDTH / 2.0;
        double y = getHeight() - ROCKET_OFFSET_Y_AXIS;
        this.rocket = new GRect(x, y, ROCKET_WIDTH, ROCKET_HEIGHT);
        this.rocket.setFilled(true);
        add(this.rocket);
    }

    /**
     * Starts the game loop
     */
    private void startGame() {
        Random random = new Random();
        double dy = 5; // Initial vertical speed
        double dx = random.nextInt(11) - 5; // Initial horizontal speed (-5 to 5)
        waitForClick(); // Wait for user click to start the game
        while (true) {
            if ((ball.getY() >= getHeight())) { // If ball goes below the canvas
                LIVES--; // Decrement lives
                initBall(ball); // Reset the ball
            } else if (bricks.isEmpty() || LIVES == 0) { // If there are no bricks left or lives are exhausted
                break; // End the game loop
            }
            ball.move(dx, dy); // Move the ball

            // Check for collisions
            if (ballHitRocket(ball) || ballHitWallY(ball) || ballHitBrick(ball, bricks)) {
                dy *= -1; // Reverse vertical direction on collision
            } else if (ballHitWallX(ball)) {
                dx *= -1; // Reverse horizontal direction on collision with walls
            }
            pause(PAUSE_TIME); // Pause for smooth animation
        }
    }

    /**
     * Check if the ball hits any of the bricks
     * @param ball the ball object
     * @param bricks list of bricks
     * @return true if ball hits any brick, false otherwise
     */
    private boolean ballHitBrick(GOval ball, List<GRect> bricks) {
        for (GRect brick : bricks) {
            if (getElementAt(ball.getX(), ball.getY()) == brick
                    || getElementAt(ball.getX() + ball.getWidth(), ball.getY()) == brick
                    || getElementAt(ball.getX(), ball.getY() + ball.getHeight()) == brick
                    || getElementAt(ball.getX() + ball.getWidth(), ball.getY() + ball.getHeight()) == brick) {
                remove(brick); // Remove the brick from canvas
                bricks.remove(brick); // Remove the brick from the list
                return true;
            }
        }
        return false;
    }

    /**
     * Check if the ball hits the top wall
     * @param ball the ball object
     * @return true if ball hits the top wall, false otherwise
     */
    private boolean ballHitWallY(GOval ball) {
        return (ball.getY() <= 0);
    }

    /**
     * Check if the ball hits either side walls
     * @param ball the ball object
     * @return true if ball hits either side wall, false otherwise
     */
    private boolean ballHitWallX(GOval ball) {
        return ball.getX() <= 0 || ball.getX() + ball.getWidth() >= getWidth();
    }

    /**
     * Check if the ball hits the rocket
     * @param ball the ball object
     * @return true if ball hits the rocket, false otherwise
     */
    private boolean ballHitRocket(GOval ball) {
        return ((ball.getY() + ball.getHeight()) >= rocket.getY() &&
                ball.getY() + ball.getHeight() <= rocket.getY() + rocket.getHeight())
                && (ball.getX() >= rocket.getX()
                && (ball.getX() <= rocket.getX() + rocket.getWidth()));
    }

    /**
     * Mouse listener method to move the rocket along with mouse movement
     * @param event mouse event
     */
    public void mouseMoved(MouseEvent event) {
        if ((event.getX() + ROCKET_WIDTH / 2) <= getWidth() && (event.getX() - ROCKET_WIDTH / 2 >= 0)) {
            rocket.setLocation(event.getX() - ROCKET_WIDTH / 2, getHeight() - ROCKET_OFFSET_Y_AXIS);
        }
    }
}