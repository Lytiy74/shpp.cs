package com.shpp.p2p.cs.apilipishin.assignment2;

import acm.graphics.GOval;
import acm.graphics.GRect;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;

public class Assignment2Part2 extends WindowProgram {

    /*
            change size if u want
     */
    public static final int APPLICATION_WIDTH = 300;
    public static final int APPLICATION_HEIGHT = 300;

    private static final int DIAMETER = 100; //our diameter

    /*
    At first create 4 circles, after we create 1 rectangle atop
     */
    public void run() {
        createCircles();  //create 4 circles in different corner
        createRectangle(); // create rectangle
    }

    /*
    This function create rectangle and put int the center, depend on diameter and weight/height screen
     */
    private void createRectangle() {
        //create a new rect in the middle with correct offset
        GRect g = new GRect(DIAMETER / 2.0, DIAMETER / 2.0, getWidth() - DIAMETER, getHeight() - DIAMETER);
        g.setColor(Color.WHITE); //set color
        g.setFilled(true);
        add(g); // add rectangle on a screen
    }

    /*
    This function create 4 circles with different offsets
     */
    private void createCircles() {
        createCircle(0, 0);  // NW corner
        createCircle(getWidth() - DIAMETER, 0); // NE corner
        createCircle(0, getHeight() - DIAMETER); // SW corner
        createCircle(getWidth() - DIAMETER, getHeight() - DIAMETER); // SE corner
    }

    /*
        This function create black 1 circle depends on values
     */
    private void createCircle(int x, int y) {
        GOval o = new GOval(x, y, DIAMETER, DIAMETER); //x,y - offset
        o.setColor(Color.BLACK);
        o.setFilled(true);
        add(o); // add on the screen
    }
}
