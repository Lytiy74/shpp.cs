package com.shpp.p2p.cs.mtrinko.assignment3;

import acm.graphics.GRect;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;

/*
This programm draws a piramide
from bricks int the middle of the window
 */
public class Assignment3Part4 extends WindowProgram {
    // The height of brick
    public static final int BRICK_HEIGHT = 50;
    // The width of the brick
    public static final int BRICK_WIDTH = 100;
    // How many brick is there in the base
    public static final int BRICKS_IN_BASE = 6;
    //  Width of the window
    public static final int APPLICATION_WIDTH = 900;
    //  Height of the window
    public static final int APPLICATION_HEIGHT = 500;

    public void run() {
        drawPiramide();
    }

    // This is a fabric method for creating a rectangle
    // @param x start coordinate x.
    // @param y start coordinate y.
    // @param w width of rectangle.
    // @param h height of rectangle.
    private GRect createRect(double x, double y, double w, double h) {
        GRect rect = new GRect(x, y, w, h);
        rect.setFillColor(Color.cyan);
        rect.setFilled(true);
        rect.setColor(Color.BLUE);

        return rect;
    }

    // this method draws a piramide in the middle of the window
    public void drawPiramide(){
        int lengthOfFirstLine = BRICK_WIDTH * BRICKS_IN_BASE;
        int rest = (getWidth() - lengthOfFirstLine) / 2;

        int startCoordinateX = rest;
        int startCoordinateY = getHeight() - BRICK_HEIGHT;
        int zero = 0;
        int bricks = BRICKS_IN_BASE;

        while (zero < bricks) {
            for (int i = 0; i < bricks; i++) {

                GRect rect = createRect(startCoordinateX + BRICK_WIDTH * i,
                        startCoordinateY,
                        BRICK_WIDTH,
                        BRICK_HEIGHT);
                add(rect);
            }
            startCoordinateY -= BRICK_HEIGHT;
            startCoordinateX += BRICK_WIDTH / 2;
            bricks--;
        }
    }
}
