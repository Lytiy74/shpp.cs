package com.shpp.p2p.cs.apilipishin.assignment2;

import acm.graphics.GOval;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;

public class Assignment2Part6 extends WindowProgram {

    private static final int NUM_CIRCLES = 20; // The number of circles caterpillar
    private static final double SIZE = 60.0; // diameter of caterpillar
    private static final Color RING_COLOR = Color.BLACK; // color of ring caterpillar
    private static final Color COLOR = Color.BLUE; // color of caterpillar

    public void run() {
        createCaterpillar(NUM_CIRCLES); // create a caterpillar
    }

    /*
    Function gets num of circles of caterpillar and draw the caterpillar
     */
    private void createCaterpillar(int numCircles) {
        boolean a = true;  // need for offset
        for (int i = 0; i < numCircles; i++) {
            createCircle(i * (SIZE / 2), a); // create a circles
            a = !a; //we change it because we need to have a correct offset
        }
    }

    /*
    This function create 1 circle with correct offset
     */
    private void createCircle(double v, boolean a) {
        double offset = getHeight() / 2.0 + (a ? SIZE / 4 : -SIZE / 4); // offset for Y, if a = true we draw in top else draw in bottom
        GOval oval = new GOval(v, offset, SIZE, SIZE); // create 1 circle with our constants
        oval.setFillColor(COLOR); //get color
        oval.setColor(RING_COLOR); //get ring color
        oval.setFilled(true);
        add(oval); //add on screen
    }
}
