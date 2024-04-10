package com.shpp.p2p.cs.apilipishin.assignment2;

import acm.graphics.GLabel;
import acm.graphics.GRect;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;

public class Assignment2Part4 extends WindowProgram {

    /*
    Constants:
    WIDTH - width of one section of flag
    HEIGHT - flag height
    COUNTRY - name of country
     */
    public static final String COUNTRY = "SomeLend";
    public static final int WIDTH = 40;
    public static final int HEIGHT = 120;

    public void run() {
        drawFlag(); //draw flag
        drawLabel(COUNTRY); //draw label
    }

    /*
    This function draw label in south-east corner
    At first we create label with any position, after we get the height and width of label and put in SE corner with offset
     */
    private void drawLabel(String s) {
        GLabel label = new GLabel("Flag of " + s, 0, 0);
        label.setLocation(getWidth() - label.getWidth() - label.getAscent(), getHeight() - label.getHeight());
        add(label);
    }

    /*
    We draw 3 section on flag int the center this depends on weight and width offset
     */
    private void drawFlag() {
        drawSquare(getWidth() / 2 - WIDTH / 2 - WIDTH, getHeight() / 2 - HEIGHT / 2, WIDTH, HEIGHT, Color.BLACK);
        drawSquare(getWidth() / 2 - WIDTH / 2, getHeight() / 2 - HEIGHT / 2, WIDTH, HEIGHT, Color.RED);
        drawSquare(getWidth() / 2 - WIDTH / 2 + WIDTH, getHeight() / 2 - HEIGHT / 2, WIDTH, HEIGHT, Color.BLUE);
    }

    /*
    This function draw 1 square and add on the screen
    Depends on values
     */
    private void drawSquare(int x, int y, int width, int height, Color color) {
        GRect rect = new GRect(x, y, width, height);
        rect.setColor(color); // set color
        rect.setFilled(true);
        add(rect); // add on the screen
    }
}
