package com.shpp.p2p.cs.mtrinko.assignment3;

import acm.graphics.GLabel;
import acm.graphics.GOval;
import acm.util.RandomGenerator;
import com.shpp.cs.a.graphics.WindowProgram;

//This program draws random  circles in 5 min
public class Assignment3Part6 extends WindowProgram {
    // size of ball
    public static final int BALL_SIZE = 150;
    // Pause
    private static final double PAUSE_TIME = 1000.0 / 10;
    // width of window
    public static final int APPLICATION_WIDTH = 900;
    // height of window
    public static final int APPLICATION_HEIGHT = 600;
    GLabel tracker = null;

    public void run() {
        // This function draws show of circles
        // and print final time in concole
      makeShow();
    }

    // Function that shows current time
    private void addLabel() {
        tracker = new GLabel("Time of animation ", 50, 50);
        add(tracker);
    }

    // function create a ball
    private GOval makeBall() {
        GOval ball = new GOval(new RandomGenerator().nextInt(0,
                getWidth() - BALL_SIZE),
                new RandomGenerator().nextInt(0, getHeight() - BALL_SIZE),
                BALL_SIZE,
                BALL_SIZE);
        ball.setFilled(true);
        ball.setColor(RandomGenerator.getInstance().nextColor());
        return ball;
    }

    // This function draws show of circles
    // and print final time in concole
    public void makeShow() {
        addLabel();
        double resultTime;
        double startTime = System.currentTimeMillis();
        while (true) {
            GOval ball = makeBall();
            add(ball);
            ball.move(50, 50);
            double currentTime = System.currentTimeMillis();
            resultTime = currentTime - startTime;
            tracker.setLabel("Time of animation " + (resultTime));

            pause(PAUSE_TIME);
            if (resultTime / 1000 >= 5) {
                break;
            }
        }
        println(resultTime);
    }
}
