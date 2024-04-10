package com.shpp.p2p.cs.skurochka.assignment2;

import acm.graphics.GLabel;
import acm.graphics.GRect;
import com.shpp.cs.a.graphics.WindowProgram;
import java.awt.*;

public class Assignment2Part4 extends WindowProgram {
    // Constants of all colors needed to build a flag. Available for testing.
    private static final Color CARDINAL_RED = new Color(227, 13, 49);
    private static final Color CARDINAL_YELLOW = new Color(250, 250, 0);
    private static final Color CARDINAL_BLACK = new Color(9, 9, 9);

    // The width and height of the flag. You can change it for testing.
    private static final double FLAG_WIDTH = 350;
    private static final double FLAG_HEIGHT = 250;

    // Default flag width and height. No change required for testing.
    private static final double STANDARD_FLAG_WIDTH = 350;
    private static final double STANDARD_FLAG_HEIGHT = 250;

    // Number of parts of the flag. No change required for testing.
    private static final double NUMBER_OF_PARTS_FLAG = 3.0;

    //The text that is written in the inscription under the flag. A change is available for testing.
    private static final String LABEL_STRING = "Flag of Belgium";

    // The font used for the label. A change is available for testing.
    private static final Font FONT_FOR_LABEL = new Font("Serif", Font.BOLD, 20);

    // Indentation for flags whose size does not fit the window size. No change required for testing.
    private static final int INDENTATION_FOR_LARGE_FLAG = 30;

    // Method of launching the program
    public void run() {
        // Calculating the height and width of the flag, which will be further used in the program
        double widthFlag = getWidthOfTheFlag(FLAG_WIDTH), heightFlag = getHeightOfTheFlag(FLAG_HEIGHT);
        // The method of drawing the first part of the flag.
        drawFirstPartOfFlag(widthFlag, heightFlag);
        // The method of drawing the second part of the flag.
        drawSecondPartOfFlag(widthFlag, heightFlag);
        // The method of drawing the third part of the flag.
        drawThirdPartOfFlag(widthFlag, heightFlag);
        // Draw an inscription.
        drawLabel();
    }

    /*
     * The method of drawing the first part of the flag.
     * To draw the first part of the flag, use the drawRectangle() method,
     * which passes the calculated parameters of width, height, and axis dimensions, as well as the color.
     * */
    private void drawFirstPartOfFlag(double widthFlag, double heightFlag) {
        drawRectangle((getWidth() / 2.0) - (widthFlag / NUMBER_OF_PARTS_FLAG / 2.0) + (widthFlag / NUMBER_OF_PARTS_FLAG),
                (getHeight() / 2.0) - (heightFlag / 2.0),
                widthFlag / NUMBER_OF_PARTS_FLAG,
                heightFlag,
                CARDINAL_RED);
    }

    /*
     * The method of drawing the second part of the flag.
     * To draw the second part of the flag, use the drawRectangle() method,
     * which passes the calculated width, height, and axis dimensions and color parameters, respectively.
     * */
    private void drawSecondPartOfFlag(double widthFlag, double heightFlag) {
        drawRectangle((getWidth() / 2.0) - (widthFlag / NUMBER_OF_PARTS_FLAG / 2.0) - (widthFlag / NUMBER_OF_PARTS_FLAG),
                (getHeight() / 2.0) - (heightFlag / 2.0),
                widthFlag / NUMBER_OF_PARTS_FLAG,
                heightFlag, CARDINAL_BLACK);
    }

    /*
     * The method of drawing the third part of the flag.
     * To draw the third part of the flag, use the drawRectangle() method,
     * which passes the calculated width, height, and axis dimensions and color parameters, respectively.
     * */
    private void drawThirdPartOfFlag(double widthFlag, double heightFlag) {
        drawRectangle((getWidth() / 2.0) - (widthFlag / NUMBER_OF_PARTS_FLAG / 2.0),
                (getHeight() / 2.0) - (heightFlag / 2.0),
                widthFlag / NUMBER_OF_PARTS_FLAG,
                heightFlag,
                CARDINAL_YELLOW);
    }

    /*
     * Draw an inscription.
     * First, a created object of type GLabel, with initial position parameters, on the inscription constant.
     * The next step is to set the font of the label.
     * Then you set the calculated position of the text.
     * The last step is to add the text to the screen composition.
     * */
    private void drawLabel() {
        GLabel label = new GLabel(LABEL_STRING, 0, 0);
        label.setFont(FONT_FOR_LABEL);
        label.setLocation(getWidth() - label.getWidth(), getHeight() - label.getDescent());
        add(label);
    }

    /*
     * The method of drawing a rectangle (part of flag).
     * The method takes as input parameters the starting position along the X and Y axes,
     * followed by the size parameters along the X and Y axes, and the last parameter is the color value.
     * In the middle, a new object of type GRect is created, in which set all input parameters.
     * Then the rectangle fill is turned on to allow you to add a fill color.
     * The next step is to set the desired input color.
     * This is then added to the screen composition.
     * */
    private void drawRectangle(double startX, double startY, double sizeX, double sizeY, Color inputColor) {
        GRect rect = new GRect(startX, startY, sizeX, sizeY);
        rect.setFilled(true);
        rect.setColor(inputColor);
        add(rect);
    }

    /*
     * Method for getting the width of a flag.
     * Accepts double values of the flag width, returns a value of the double type.
     * Checks for boundary conditions for flags and returns the value of the flag width.
     * */
    private double getWidthOfTheFlag(double x) {
        if (x <= 0) {
            return STANDARD_FLAG_WIDTH;
        } else if (getWidth() - 2 * INDENTATION_FOR_LARGE_FLAG > x) {
            return x;
        } else {
            return getWidth() - 2 * INDENTATION_FOR_LARGE_FLAG;
        }
    }

    /*
     * Method for getting the height of a flag.
     * Accepts double values of the flag height, returns a value of the double type.
     * Checks for boundary conditions for flags and returns the value of the flag height.
     * */
    private double getHeightOfTheFlag(double y) {
        if (y <= 0) {
            return STANDARD_FLAG_HEIGHT;
        } else if (getHeight() - 2 * INDENTATION_FOR_LARGE_FLAG > y) {
            return y;
        } else {
            return getHeight() - 2 * INDENTATION_FOR_LARGE_FLAG;
        }
    }
}