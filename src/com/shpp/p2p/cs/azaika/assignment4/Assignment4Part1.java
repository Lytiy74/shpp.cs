package com.shpp.p2p.cs.azaika.assignment4;

import acm.graphics.GOval;
import acm.graphics.GRect;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.event.MouseEvent;
import java.util.Random;

public class Assignment4Part1 extends WindowProgram {
    private static final double BALL_SIZE = 10.0;
    private static final double PAUSE_TIME = 1000.0 / 30;
    private static final double HORIZONTAL_VELOCITY = 1.0;
    private static final double ROCKET_WIDTH = 50.0;
    private static final double ROCKET_HEIGHT = 10.0;
    private static final int ROCKET_OFFSET_Y_AXIS = 50;

    public static final int APPLICATION_WIDTH = 300;
    public static final int APPLICATION_HEIGHT = 600;

    private static final int BRICK_WIDTH = 50;
    private static final int BRICK_HEIGHT = 10;
    private static final int BRICK_SPACING = 5;
    private static final int BRICK_START_X_AXIS = 0;
    private static final int COLUMNS_QUANTITY = 1;
    private static final int ROWS_QUANTITY = 1;

    private GOval ball;
    private GRect rocket;
    @Override
    public void run() {
        initGame();
    }

    private void initGame() {
        addRocket(rocket);
        addBall(ball);
        drawBricks();
        addMouseListeners();
        startGame();
    }

    private void drawBricks() {
        double rowSizeInPixels = (BRICK_WIDTH+BRICK_SPACING) * (ROWS_QUANTITY-1);
        double columnSizeInPixels = (BRICK_WIDTH+BRICK_SPACING) * (COLUMNS_QUANTITY-1);
        double middleOfRow = rowSizeInPixels / 2;
        double middleOfColumn = columnSizeInPixels / 2;

        for (int i = 0; i < ROWS_QUANTITY; i++) {
            for (int j = 0; j < COLUMNS_QUANTITY; j++) {
                double x = getWidth()/2 - middleOfRow;
                double y = getHeight()/2 - middleOfColumn;
                GRect brick = new GRect(x,y, BRICK_WIDTH,BRICK_HEIGHT);
                add(brick);
            }
        }
    }

    private void addBall(GOval ball) {
        this.ball = new GOval(rocket.getX() + ROCKET_WIDTH/2, rocket.getY() - 80, BALL_SIZE, BALL_SIZE);
        this.ball.setFilled(true);
        add(this.ball);
    }

    private void addRocket(GRect rocket) {
        double x = getWidth()/2 - ROCKET_WIDTH/2;
        double y = getHeight() - ROCKET_OFFSET_Y_AXIS;
        this.rocket = new GRect(x,y,ROCKET_WIDTH,ROCKET_HEIGHT);
        this.rocket.setFilled(true);
        add(this.rocket);
    }

    private void startGame() {
        Random random = new Random();
        double dy = 5 ;
        double dx = random.nextInt(11)-5 ;
        while(true){
            ball.move(dx,dy);

            if (ballHitRocket(ball) || ballHitWallY(ball)){
                dy*= -1;
            }else if (ballHitWallX(ball)){
                dx*= -1;
            }
            pause(PAUSE_TIME);
        }
    }

    private boolean ballHitWallY(GOval ball) {
        return (ball.getY() <= 0) || (ball.getY() >= getHeight());
    }

    private boolean ballHitWallX(GOval ball) {
        return ball.getX() <= 0 || ball.getX() + ball.getWidth() >= getWidth();
    }

    private boolean ballHitRocket(GOval ball) {
        return ((ball.getY() + ball.getHeight()) >= rocket.getY() &&
                ball.getY() + ball.getHeight() <= rocket.getY() + rocket.getHeight())
                && (ball.getX() >= rocket.getX()
                && (ball.getX() <= rocket.getX()+ rocket.getWidth()));
//        if (getElementAt(ball.getX(), ball.getY() + ball.getHeight()) != null
//                || getElementAt(ball.getX() + ball.getHeight(), ball.getY() + ball.getHeight()) != null
//                || getElementAt(ball.getX() + ball.getWidth(), ball.getY()) != null
//                || getElementAt(ball.getX(), ball.getY()) != null) {
//            return rocket == getElementAt(ball.getX(), ball.getY() + ball.getHeight())
//                    || rocket == getElementAt(ball.getX() + ball.getHeight(), ball.getY() + ball.getHeight())
//                    || rocket == getElementAt(ball.getX() + ball.getWidth(), ball.getY())
//                    || rocket == getElementAt(ball.getX(), ball.getY());
//        }
//        return false;
    }

    public void mouseMoved(MouseEvent event) {
        if ((event.getX() + ROCKET_WIDTH/2) <= getWidth() && (event.getX() - ROCKET_WIDTH/2 >= 0)){
            rocket.setLocation(event.getX() - ROCKET_WIDTH/2, getHeight() - ROCKET_OFFSET_Y_AXIS);}
    }
}
