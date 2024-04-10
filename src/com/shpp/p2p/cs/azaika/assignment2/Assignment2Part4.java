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
     * @param flagWidth The width of the flag.
     * @param flagHeight The height of the flag.
     */
    private void drawFlag(double flagWidth, double flagHeight) {
        //getting the center of the window by x-axis
         double centerOfXAxis = getWidth() / 2.0;
        //getting the center of the window by y-axis
         double centerOfYAxis = getHeight() / 2.0;
        //getting the height of one stripe of a flag
         double stripeHeight = flagHeight/ QUANTITY_OF_STRIPES;

        //initialize the array where stored colors of the flag to iterate in cycle.
        Color[] stripeColors = {Color.black, Color.red, Color.YELLOW};

        //create stripes one by one, by iterating in a color array
        for (int i = 0; i < stripeColors.length; i++) {
            GRect lineOfFlag = new GRect(centerOfXAxis - flagWidth / 2,
                    centerOfYAxis - flagHeight / 2 + i * stripeHeight ,
                    flagWidth, stripeHeight);
            lineOfFlag.setFilled(true);
            lineOfFlag.setFillColor(stripeColors[i]);
            lineOfFlag.setColor(stripeColors[i]);
            add(lineOfFlag);
        }
    }

    //Method make label in a down-right corner of a window
    private void drawLabel() {
        GLabel label = new GLabel("Flag of GERMANY");
        label.setLocation(getWidth()- label.getBounds().getWidth(),getHeight()-label.getDescent());
        add(label);
    }
}
