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
    private static final double BALL_SIZE = 10.0;

    // Frames per second
    private static final double PAUSE_TIME = 1000.0 / 60;

    // Width of the rocket
    private static final double ROCKET_WIDTH = 50.0;

    // Height of the rocket
    private static final double ROCKET_HEIGHT = 10.0;

    // Offset of the rocket from the top of the window
    private static final int ROCKET_OFFSET_Y_AXIS = 50;

    // Width of the application window
    public static final int APPLICATION_WIDTH = 400;

    // Height of the application window
    public static final int APPLICATION_HEIGHT = 600;

    // Width of each brick
    private static final int BRICK_WIDTH = 30;


    // Height of each brick
    private static final int BRICK_HEIGHT = 8;

    // Spacing between bricks
    private static final int BRICK_SPACING = 4;
    private static final double BRICKS_OFFSET_FROM_Y = 50;

    // Number of columns of bricks
    private static final int COLUMNS_QUANTITY = 10;

    // Number of rows of bricks
    private static final int ROWS_QUANTITY = 10;

    // Array of colors for bricks
    private static final Color[] colorsArray = new Color[]{Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.CYAN};

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
        initRocket();
        initBall();
        initBricks(colorsArray);
        addMouseListeners();

        startGame();
    }

    /**
     * Method initializes bricks with specified row and columns quantity and adds all references to the bricks list.
     *
     * @param colors Array of colors for bricks
     */
    private void initBricks(Color[] colors) {
        double brickWidth = (double) (getWidth() - (COLUMNS_QUANTITY - 1) * BRICK_SPACING) / COLUMNS_QUANTITY;
        double brickHeight = BRICK_HEIGHT;
        // Calculate the size of rows and columns to find start coordinates in X and Y axis
        double rowSizeInPixels = (brickWidth + BRICK_SPACING) * (ROWS_QUANTITY - 1);
        double columnSizeInPixels = (brickWidth + BRICK_SPACING) * (COLUMNS_QUANTITY - 1);
        double middleOfRow = rowSizeInPixels / 2;
        double middleOfColumn = columnSizeInPixels / 2;
        int colorIndex = 0;

        for (int i = 0; i < ROWS_QUANTITY; i++, colorIndex++) {
            for (int j = 0; j < COLUMNS_QUANTITY; j++) {
                // Calculate x and y coordinates for brick
                double x = (getWidth() / 2.0 - brickWidth / 2.0) - middleOfColumn + j * (brickWidth + BRICK_SPACING);
                double y = BRICKS_OFFSET_FROM_Y + i * (BRICK_HEIGHT + BRICK_SPACING);
                Color color = colors[colorIndex / 2]; // Alternate colors for rows
                // Create brick and add to canvas and List of bricks
                GRect brick = getBrick(color, x, y, brickWidth, brickHeight);
                bricks.add(brick);
                add(brick);
            }
            if (colorIndex >= colorsArray.length * 2 - 1) colorIndex = 0;
        }
    }

    /**
     * Creates bricks with specified parameters and returns an instance of GRect
     *
     * @param color  color to paint brick
     * @param x      coordinate on the x-axis
     * @param y      coordinate on the y-axis
     * @param width
     * @param height
     * @return instance of GRect which is used in the game field.
     */
    private GRect getBrick(Color color, double x, double y, double width, double height) {
        GRect brick = new GRect(x, y, width, height);
        brick.setFilled(true);
        brick.setColor(color);
        return brick;
    }

    /**
     * Initializes a game object Ball and adds it to the canvas
     */
    private void initBall() {
        if (ball != null) {
            remove(ball);
        }
        this.ball = new GOval(getWidth() / 2.0, getHeight() / 2.0, BALL_SIZE, BALL_SIZE);
        this.ball.setFilled(true);
        add(this.ball);
    }

    /**
     * Initializes the game rocket
     */
    private void initRocket() {
        if (this.rocket != null) {
            remove(this.rocket);
        }
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

        if (dx == 0) dx++;
        waitForClick();
         // Wait for user click to start the game
        while (!bricks.isEmpty() && LIVES != 0) {
            if ((ball.getY() >= getHeight())) { // If ball goes below the canvas
                LIVES--; // Decrement lives
                initBall(); // Reset the ball
                waitForClick();
            }
            for (int x = 0; x < Math.abs(dx); x++) {
                for (int y = 0; y < Math.abs(dy); y++) {
                    ball.move(Math.signum(dx), Math.signum(dy)); // Move the ball
                    if (ballHitRocket(ball) || ballHitWallY(ball) || ballHitBrick(ball, bricks)) {
                        dy *= -1; // Reverse vertical direction on collision
                    } else if (ballHitWallX(ball)) {
                        dx *= -1; // Reverse horizontal direction on collision with walls
                    }
                }
                pause(PAUSE_TIME); // Pause for smooth animation
            }
        }
    }

    /**
     * Check if the ball hits any of the bricks
     *
     * @param ball   the ball object
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
     *
     * @param ball the ball object
     * @return true if ball hits the top wall, false otherwise
     */
    private boolean ballHitWallY(GOval ball) {
        return (ball.getY() <= 0);
    }

    /**
     * Check if the ball hits either side walls
     *
     * @param ball the ball object
     * @return true if ball hits either side wall, false otherwise
     */
    private boolean ballHitWallX(GOval ball) {
        return ball.getX() <= 0 || ball.getX() + ball.getWidth() >= getWidth();
    }

    /**
     * Check if the ball hits the rocket
     *
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
     *
     * @param event mouse event
     */
    public void mouseMoved(MouseEvent event) {
        if ((event.getX() + ROCKET_WIDTH / 2) <= getWidth() && (event.getX() - ROCKET_WIDTH / 2 >= 0)) {
            rocket.setLocation(event.getX() - ROCKET_WIDTH / 2, getHeight() - ROCKET_OFFSET_Y_AXIS);
        }
    }
}