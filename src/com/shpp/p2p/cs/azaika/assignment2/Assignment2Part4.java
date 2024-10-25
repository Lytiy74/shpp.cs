package com.shpp.p2p.cs.azaika.assignment2;


import acm.graphics.GLabel;
import acm.graphics.GRect;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;

/**
 * Program for drawing the flag by three stripes.
 * <p><b>Precondition:</b> The flag height and flag width must be specified.</p>
 * <p><b>Result:</b> Draws three striped flag and write label on the down right corner.</p>
 */
public class Assignment2Part4 extends WindowProgram {
    private static final double FLAG_HEIGHT = 250;
    private static final double FLAG_WIDTH = 450;
    private static final int QUANTITY_OF_STRIPES = 3;


    @Override
    public void run() {
        drawFlag(FLAG_WIDTH, FLAG_HEIGHT);
        drawLabel();
    }

    /**
     * Method for drawing three striped flags.
     * <p><b>Precondition:</b> The flag height and flag width must be specified..</p>
     * <p><b>Result:</b> Draws flag in the center of window and put label in down-right corner.</p>
     *
     * @param flagWidth  The width of the flag.
     * @param flagHeight The height of the flag.
     */
    private void drawFlag(double flagWidth, double flagHeight) {
        //getting the center of the window by x-axis
        double centerOfXAxis = getWidth() / 2.0;
        //getting the center of the window by y-axis
        double centerOfYAxis = getHeight() / 2.0;
        //getting the height of one stripe of a flag
        double stripeHeight = flagHeight / QUANTITY_OF_STRIPES;

        //create stripes one by one
        double x = centerOfXAxis - flagWidth / 2;
        double y = centerOfYAxis - flagHeight / 2;
        GRect upperStripe = getLineOfFlag(flagWidth, x, y, stripeHeight, Color.BLACK);
        add(upperStripe);
        GRect middle = getLineOfFlag(flagWidth, x, y + stripeHeight , stripeHeight, Color.RED);
        add(middle);
        GRect downStipe = getLineOfFlag(flagWidth, x, y + stripeHeight * 2, stripeHeight, Color.YELLOW);
        add(downStipe);
    }

    /**
     * Method to construct one stripe of flag
     * @param flagWidth width of all flag
     * @param x coordiante to x axis
     * @param y coordiante to y axis
     * @param stripeHeight height of one stripe
     * @param stripeColors color of stripe
     * @return instance of rectangle
     */
    private static GRect getLineOfFlag(double flagWidth, double x, double y, double stripeHeight, Color stripeColors) {
        GRect lineOfFlag = new GRect(x, y, flagWidth, stripeHeight);
        lineOfFlag.setFilled(true);
        lineOfFlag.setFillColor(stripeColors);
        lineOfFlag.setColor(stripeColors);
        return lineOfFlag;
    }

    //Method make label in a down-right corner of a window
    private void drawLabel() {
        GLabel label = new GLabel("Flag of GERMANY");
        label.setLocation(getWidth() - label.getBounds().getWidth(), getHeight() - label.getDescent());
        add(label);
    }
}
