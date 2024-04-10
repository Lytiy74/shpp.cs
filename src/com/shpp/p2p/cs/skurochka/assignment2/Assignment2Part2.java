package com.shpp.p2p.cs.skurochka.assignment2;

import acm.graphics.GOval;
import acm.graphics.GRect;
import com.shpp.cs.a.graphics.WindowProgram;
import java.awt.*;

public class Assignment2Part2 extends WindowProgram {
    // The constant in which the radius of a circle is stored. Available for testing purposes only.
    private static final double RADIUS_OF_CIRCLE = 0;

    // The line for setting the window width is provided for testing purposes only.
    public static final int APPLICATION_WIDTH = 900;
    // The window height setting line is provided for testing purposes only.
    public static final int APPLICATION_HEIGHT = 900;

    //Constant window sizes for all sizes and the corresponding size of the circle radius.
    //The window dimensions, width, height and radius of the circle for all sizes except the minimum size are available.
    // For the minimum, only the circle radius is available. Changing parameters for testing is not available.
    int WINDOW_MAX_SIZE_WIDTH = 900, WINDOW_MAX_SIZE_HEIGHT = 900, CIRCLE_MAX_SIZE = 100;
    int WINDOW_DEFAULT_SIZE_WIDTH = 700, WINDOW_DEFAULT_SIZE_HEIGHT = 400, CIRCLE_DEFAULT_SIZE = 75;
    int WINDOW_MIDL_SIZE_WIDTH = 300, WINDOW_MIDL_SIZE_HEIGHT = 300, CIRCLE_MIDL_SIZE = 55;
    int CIRCLE_MIN_SIZE = 45;

    // The method of launching the program.
    public void run() {
        // The circle size is calculated from the window measurements.
        double radiusOfCircle = calculateRadiusCircle();
        // A method that draws four circles. Accepts the radius of a circle as a parameter.
        drawAllCircles(radiusOfCircle);
        // A method that draws a rectangle. Accepts the radius of a circle as a parameter.
        drawRectangle(radiusOfCircle);
    }

    /*
     * A method that draws four circles. Accepts the radius of a circle as a parameter.
     * The method calls methods ( drawFirstCircle(), drawSecondCircle(), drawThirdCircle(),
     * drawForthCircle() ) in turn that draw four color-filled circles.
     */
    private void drawAllCircles(double radius) {
        drawFirstCircle(radius);
        drawSecondCircle(radius);
        drawThirdCircle(radius);
        drawForthCircle(radius);
    }

    /*
     * The method of drawing a rectangle. Accepts the radius of the circle as a parameter.
     * In the middle, a new object of type GRect is created, in which the radius is set,
     * which is used as the starting position for building a rectangle, the service function getWidth()/getHeight()
     * is used as the size, from which the diameters of the circle are subtracted.
     * Then the rectangle fill is turned on to allow you to add a fill color.
     * The next step is to set the desired color.
     * This is then added to the screen composition.
     */
    private void drawRectangle(double radius) {
        GRect rect = new GRect(radius, radius, getWidth() - getDiameter(radius), getHeight() - getDiameter(radius));
        rect.setFilled(true);
        rect.setColor(Color.WHITE);
        add(rect);
    }

    /*
     * Method for drawing the first circle. Accepts the radius of the circle as a parameter.
     * The method includes the parameters of the starting point for calculating the circle.
     * Then the method for drawing a circle is called drawOneCircle(), to which all the necessary parameters are passed.
     */
    private void drawFirstCircle(double radius) {
        double startPointX = 0, startPointY = 0;
        drawOneCircle(startPointX, startPointY, radius, radius);
    }

    /*
     * Method for drawing the second circle. Accepts the radius of the circle as a parameter.
     * The method includes the parameters of the starting point for calculating the circle.
     * Then the method for drawing a circle is called drawOneCircle(), to which all the necessary parameters are passed.
     */
    private void drawSecondCircle(double radius) {
        double startPointX = getWidth() - getDiameter(radius), startPointY = 0;
        drawOneCircle(startPointX, startPointY, radius, radius);
    }

    /*
     * Method for drawing the third circle. Accepts the radius of the circle as a parameter.
     * The method includes the parameters of the starting point for calculating the circle.
     * Then the method for drawing a circle is called drawOneCircle(), to which all the necessary parameters are passed.
     */
    private void drawThirdCircle(double radius) {
        double startPointX = 0, startPointY = getHeight() - getDiameter(radius);
        drawOneCircle(startPointX, startPointY, radius, radius);
    }

    /*
     * Method for drawing the forth circle. Accepts the radius of the circle as a parameter.
     * The method includes the parameters of the starting point for calculating the circle.
     * Then the method for drawing a circle is called drawOneCircle(), to which all the necessary parameters are passed.
     */
    private void drawForthCircle(double radius) {
        double startPointX = getWidth() - getDiameter(radius), startPointY = getHeight() - getDiameter(radius);
        drawOneCircle(startPointX, startPointY, radius, radius);
    }

    /*
     * The method of drawing a single circle.
     * The method takes four parameters, the first parameter is the X position,
     * the second parameter is the Y position, the third parameter is the radius in the X coordinate direction,
     * and the last parameter is the radius in the Y coordinate direction.
     * Inside the method, an object of type GOval is created,
     * into which the input parameters of the starting positions are passed,
     * and the diameters calculated from the input radii are passed accordingly.
     * Then the shape fill is turned on to allow you to add a fill color.
     * The next step is to set the desired color.
     * This is then added to the screen composition.
     */
    private void drawOneCircle(double startX, double startY, double radiusX, double radiusY) {
        GOval circle = new GOval(startX, startY, getDiameter(radiusX), getDiameter(radiusY));
        circle.setFilled(true);
        circle.setColor(Color.BLACK);
        add(circle);
    }

    /*
     *The method is used to calculate the diameter.
     * Takes a radius as a parameter and returns a double value.
     * Returns the radius multiplied by two.
     */
    private double getDiameter(double x) {
        return x * 2.0;
    }

    /*
     * The circle size is calculated from the window measurements.
     * The method using the main window sizes, and depending on the actual window size,
     * the window value is returned, which is also stored in the method for each size.
     * The program returns a value of type int.
     */
    private double calculateRadiusCircle() {
        double resultCircleRadius;
        int windowWidth = getWidth(),
                windowHeight = getHeight();
        if (RADIUS_OF_CIRCLE > 0) {
            resultCircleRadius = RADIUS_OF_CIRCLE;
        } else if (WINDOW_MAX_SIZE_WIDTH < windowWidth && WINDOW_MAX_SIZE_HEIGHT < windowHeight) {
            resultCircleRadius = CIRCLE_MAX_SIZE;
        } else if (WINDOW_DEFAULT_SIZE_WIDTH < windowWidth && WINDOW_DEFAULT_SIZE_HEIGHT < windowHeight) {
            resultCircleRadius = CIRCLE_DEFAULT_SIZE;
        } else if (WINDOW_MIDL_SIZE_WIDTH < windowWidth && WINDOW_MIDL_SIZE_HEIGHT < windowHeight) {
            resultCircleRadius = CIRCLE_MIDL_SIZE;
        } else {
            resultCircleRadius = CIRCLE_MIN_SIZE;
        }
        return resultCircleRadius;
    }
}