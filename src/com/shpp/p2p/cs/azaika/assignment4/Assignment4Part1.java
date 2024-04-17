package com.shpp.p2p.cs.azaika.assignment4;

import acm.graphics.GOval;
import acm.graphics.GRect;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.event.MouseEvent;

public class Assignment4Part1 extends WindowProgram {
    private static final double BALL_SIZE = 10.0;
    private static final double PAUSE_TIME = 1000.0 / 30;
    private static final double HORIZONTAL_VELOCITY = 1.0;
    private static final double ROCKET_WIDTH = 50.0;
    private static final double ROCKET_HEIGHT = 10.0;
    public static final int APPLICATION_WIDTH = 300;
    public static final int APPLICATION_HEIGHT = 600;
    private static final int ROCKET_OFFSET_Y_AXIS = 50;
    private GOval ball;
    private GRect rocket;
    @Override
    public void run() {
        initGame();
    }

    private void initGame() {
        addRocket(rocket);
        addBall(ball);
        addMouseListeners();
        startGame();
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
        double dy = 5 ;
        while(true){
            ball.move(0,dy);

            if (ballHitRocket(ball) || ballHitWall()){
                dy*= -1;
            }
            pause(PAUSE_TIME);
        }
    }

    private boolean ballHitWall() {
        return true;
    }

    private boolean ballHitRocket(GOval ball) {
        return ((ball.getY() + ball.getHeight()) >= rocket.getY())
                && ((ball.getX() + ball.getHeight()) >= rocket.getX()
                && (ball.getX() <= rocket.getX()+ rocket.getWidth()));
    }

    public void mouseMoved(MouseEvent event) {
        if ((event.getX() + ROCKET_WIDTH/2) <= getWidth() && (event.getX() - ROCKET_WIDTH/2 >= 0)){
            rocket.setLocation(event.getX() - ROCKET_WIDTH/2, getHeight() - ROCKET_OFFSET_Y_AXIS);}
    }
}
