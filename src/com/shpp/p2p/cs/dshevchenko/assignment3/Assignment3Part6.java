package com.shpp.p2p.cs.dshevchenko.assignment3;


import acm.graphics.GOval;
import acm.graphics.GRect;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;
import java.util.random.RandomGenerator;

public class Assignment3Part6 extends WindowProgram {

    // sun diameter
    private static final int SUN_SIZE = 140;
    // moon diameter
    private static final double MOON_SIZE = SUN_SIZE / 1.3;

    // window size
    public static final int APPLICATION_HEIGHT = SUN_SIZE * 4;
    public static final int APPLICATION_WIDTH = SUN_SIZE * 6;


    // extra colors
    private static final Color DEEP_SKY_BLUE = new Color(0, 191, 255);
    private static final Color LIME = new Color(0, 255, 0);

    // the amount of time to pause between frames
    // in this case: 140 - 140 * 4 / 20 = 112fps

    private static final double PAUSE_TIME = 1000.0 / (SUN_SIZE - APPLICATION_HEIGHT / 20);


    public void run() {

        GOval sun = createCircle(getWidth() / 2 - SUN_SIZE / 2, 1, SUN_SIZE, Color.YELLOW);
        GOval moon = createCircle(0, getHeight() / 2, MOON_SIZE, Color.LIGHT_GRAY);
        GRect sky = createBackground(0, 0, getWidth(), getHeight(), DEEP_SKY_BLUE);
        GRect ground = createBackground(0, getHeight() / 2, getWidth(), getHeight(), LIME);

        add(sky);
        add(moon);
        add(ground);
        add(sun);

        drawAnimation(sun, moon, sky, ground);
    }

    /**
     * Method for drawing the animation in 5 sec
     * (the change from day to night)
     */
    private void drawAnimation(GOval sun, GOval moon, GRect sky, GRect ground) {

        //start necessary rgb values of sky and ground
        double skyG = 191;
        double skyB = 255;
        double groundG = 255;

        // for generating random position of stars (see lower)
        RandomGenerator random = RandomGenerator.getDefault();

        long start = (System.currentTimeMillis());
        while (true) {

            //change colors to darker for evening effect
            if (skyB > 100) {
                skyG -= 0.35;
                skyB -= 0.35;
                groundG -= 0.35;
            }

            // update colors
            Color tempSky = new Color(0, (int) skyG, (int) skyB);
            sky.setColor(tempSky);

            Color tempGround = new Color(0, (int) groundG, 0);
            ground.setColor(tempGround);

            //exit point from loop (when moon is on the top)
            if (moon.getY() < 1) {
                break;
            }

            // sun and moon replace relative to the sun size
            if (SUN_SIZE >= 250) {
                sun.move(1.1, 0.5);
                moon.move(0.8, -0.63);
            } else if (SUN_SIZE >= 200 && SUN_SIZE < 250) {
                sun.move(1.15, 0.5);
                moon.move(0.7, -0.54);
            } else if (SUN_SIZE > 140 && SUN_SIZE < 200) {
                sun.move(1.15, 0.5);
                moon.move(0.68, -0.49);
            } else if (SUN_SIZE >= 100 && SUN_SIZE < 140) {
                sun.move(1, 0.3);
                moon.move(0.7, -0.45);
            } else if (SUN_SIZE < 100) {
                sun.move(1.2, 0.3);
                moon.move(0.62, -0.34);
            } else {
                sun.move(1, 0.4);
                moon.move(0.7, -0.5);
            }

            // creating star with random coordinates
            GOval star = createCircle(random.nextInt(0, getWidth()),
                    random.nextInt(0, getHeight() - SUN_SIZE),
                    1, Color.WHITE);

            //moon line when stars start to appear
            if (moon.getX() > (double) getWidth() / 4) {
                add(star);
            }

            // update all elements for so that
            // stars don`t appear on them
            add(moon);
            add(sun);
            add(ground);

            // remove sun when it goes beyond the edge of the window
            if (sun.getX() > getWidth()) {
                remove(sun);
            }

            pause(PAUSE_TIME);
        }
        long end = (System.currentTimeMillis());
        println("time: " + (end - start));
    }

    /**
     * Method for creating circle
     * set position, size and color to it
     */
    private GOval createCircle(double x, double y, double size, Color cl) {
        GOval circle = new GOval(x, y, size, size);
        circle.setFilled(true);
        circle.setColor(cl);

        return circle;
    }

    /**
     * Method for creating rectangle
     * set position, size and color to it
     */
    public GRect createBackground(double x, double y, double w, double h, Color cl) {
        GRect rect = new GRect(x, y, w, h);
        rect.setFilled(true);
        rect.setColor(cl);

        return rect;
    }
}
