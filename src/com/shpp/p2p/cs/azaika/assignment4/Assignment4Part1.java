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
    private static final double BALL_SIZE = 5.0;
    private static final double PAUSE_TIME = 1000.0 / 60;
    private static final double ROCKET_WIDTH = 50.0;
    private static final double ROCKET_HEIGHT = 10.0;
    private static final int ROCKET_OFFSET_Y_AXIS = 50;
    public static final int APPLICATION_WIDTH = 300;
    public static final int APPLICATION_HEIGHT = 600;
    private static final int BRICK_WIDTH = 30;
    private static final int BRICK_HEIGHT = 10;
    private static final int BRICK_SPACING = 4;
    private static final int COLUMNS_QUANTITY = 8;
    private static final int ROWS_QUANTITY = 8;
    private static final Color[] colorsArray = new Color[]{Color.RED, Color.ORANGE, Color.GREEN, Color.CYAN};

    private GOval ball;
    private GRect rocket;
    private final List<GRect> bricks = new ArrayList<>();
    private int lives = 3;

    @Override
    public void run() {
        initGame();
    }

    private void initGame() {
        initRocket(rocket);
        initBall(ball);
        initBricks(bricks, colorsArray);
        addMouseListeners();

        startGame();
    }

    private void initBricks(List<GRect> bricks, Color[] colors) {
        double rowSizeInPixels = (BRICK_WIDTH + BRICK_SPACING) * (ROWS_QUANTITY - 1);
        double columnSizeInPixels = (BRICK_WIDTH + BRICK_SPACING) * (COLUMNS_QUANTITY - 1);
        double middleOfRow = rowSizeInPixels / 2;
        double middleOfColumn = columnSizeInPixels / 2;

        for (int i = 0; i < ROWS_QUANTITY; i++) {
            for (int j = 0; j < COLUMNS_QUANTITY; j++) {
                double x = (getWidth() / 2.0 - BRICK_WIDTH / 2.0) - middleOfColumn + j * (BRICK_WIDTH + BRICK_SPACING);
                double y = (getHeight() / 2.0 - BRICK_HEIGHT / 2.0) - middleOfRow + i * (BRICK_HEIGHT + BRICK_SPACING);
                GRect brick = getBrick(colors, x, y, i);
                bricks.add(brick);
                add(brick);
            }
        }
    }

    private GRect getBrick(Color[] colors, double x, double y, int i) {
        GRect brick = new GRect(x, y, BRICK_WIDTH, BRICK_HEIGHT);
        brick.setFilled(true);
        brick.setColor(colors[i / 2]);
        return brick;
    }

    private void initBall(GOval ball) {
        if (ball != null) {
            remove(ball);
        }
        this.ball = new GOval(rocket.getX() + ROCKET_WIDTH / 2, rocket.getY() - 150, BALL_SIZE, BALL_SIZE);
        this.ball.setFilled(true);
        add(this.ball);
    }

    private void initRocket(GRect rocket) {
        double x = getWidth() / 2.0 - ROCKET_WIDTH / 2.0;
        double y = getHeight() - ROCKET_OFFSET_Y_AXIS;
        this.rocket = new GRect(x, y, ROCKET_WIDTH, ROCKET_HEIGHT);
        this.rocket.setFilled(true);
        add(this.rocket);
    }

    private void startGame() {
        Random random = new Random();
        double dy = 5;
        double dx = random.nextInt(11) - 5;
        while (true) {
            if ((ball.getY() >= getHeight())) {
                lives--;
                initBall(ball);
                startGame();
            } else if (bricks.isEmpty() || lives == 0) {
                break;
            }
            ball.move(dx, dy);

            if (ballHitRocket(ball) || ballHitWallY(ball) || ballHitBrick(ball, bricks)) {
                dy *= -1;
            } else if (ballHitWallX(ball)) {
                dx *= -1;
            }
            pause(PAUSE_TIME);
        }
    }

    private boolean ballHitBrick(GOval ball, List<GRect> bricks) {
        for (GRect brick : bricks) {
            if (getElementAt(ball.getX(), getY()) == brick
                    || getElementAt(ball.getX() + ball.getWidth(), ball.getY()) == brick
                    || getElementAt(ball.getX(), ball.getY() + ball.getHeight()) == brick
                    || getElementAt(ball.getX() + ball.getWidth(), ball.getY() + ball.getHeight()) == brick) {
                remove(brick);
                bricks.remove(brick);
                return true;
            }
        }
        return false;
    }

    private boolean ballHitWallY(GOval ball) {
        return (ball.getY() <= 0);
    }

    private boolean ballHitWallX(GOval ball) {
        return ball.getX() <= 0 || ball.getX() + ball.getWidth() >= getWidth();
    }

    private boolean ballHitRocket(GOval ball) {
        return ((ball.getY() + ball.getHeight()) >= rocket.getY() &&
                ball.getY() + ball.getHeight() <= rocket.getY() + rocket.getHeight())
                && (ball.getX() >= rocket.getX()
                && (ball.getX() <= rocket.getX() + rocket.getWidth()));
    }

    public void mouseMoved(MouseEvent event) {
        if ((event.getX() + ROCKET_WIDTH / 2) <= getWidth() && (event.getX() - ROCKET_WIDTH / 2 >= 0)) {
            rocket.setLocation(event.getX() - ROCKET_WIDTH / 2, getHeight() - ROCKET_OFFSET_Y_AXIS);
        }
    }

}
