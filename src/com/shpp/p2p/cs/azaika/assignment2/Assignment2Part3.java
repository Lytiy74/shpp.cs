package com.shpp.p2p.cs.azaika.assignment2;

import acm.graphics.GOval;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;

/**
 * Program for drawing two pawprints.
 * <p><b>Precondition:</b> The constants must be specified.</p>
 * <p><b>Result:</b> Draws two pawprints.</p>
 */
public class Assignment2Part3 extends WindowProgram {
        /* Constants controlling the relative positions of the
         * three toes to the upper-left corner of the pawprint.
         */
        private static final double FIRST_TOE_OFFSET_X = 0;
        private static final double FIRST_TOE_OFFSET_Y = 20;
        private static final double SECOND_TOE_OFFSET_X = 30;
        private static final double SECOND_TOE_OFFSET_Y = 0;
        private static final double THIRD_TOE_OFFSET_X = 60;
        private static final double THIRD_TOE_OFFSET_Y = 20;

        /* The position of the heel relative to the upper-left
         * corner of the pawprint.
         */
        private static final double HEEL_OFFSET_X = 20;
        private static final double HEEL_OFFSET_Y = 40;

        /* Each toe is an oval with this width and height. */
        private static final double TOE_WIDTH = 20;
        private static final double TOE_HEIGHT = 30;

        /* The heel is an oval with this width and height. */
        private static final double HEEL_WIDTH = 40;
        private static final double HEEL_HEIGHT = 60;

        /* The default width and height of the window. These constants will tell Java to
         * create a window whose size is *approximately* given by these dimensions. You should
         * not directly use these constants in your program; instead, use getWidth() and
         * getHeight(), which return the *exact* width and height of the window.
         */
        public static final int APPLICATION_WIDTH = 270;
        public static final int APPLICATION_HEIGHT = 220;

        public void run() {
            drawPawprint(20, 20);
            drawPawprint(180, 70);
        }

        /**
         * Draws a pawprint. The parameters should specify the upper-left corner of the
         * bounding box containing that pawprint.
         *
         * @param x The x coordinate of the upper-left corner of the bounding box for the pawprint.
         * @param y The y coordinate of the upper-left corner of the bounding box for the pawprint.
         */
        private void drawPawprint(double x, double y) {
            drawHeel(x, y);
            drawToes(x, y);
        }

    /**
     * Draws toes in window
     * <p><b>Precondition: x,y must be specified. Order in arrays must be followed </b></p>
     * <p><b>Result: draws toes in window in specified coordinates</b></p>
     * @param x coordinate in x-axis
     * @param y coordinate in y-axis
     */
    private void drawToes(double x, double y) {
        //Collect all offsets of X and Y axis in one array to iterate in cycle.
        //IMPORTANT TO SAVE THE CORRECT ORDER IN ARRAYS LIKE FIRST_TOE_OFFSET_X = FIRST_TOE_OFFSET_Y
        double[] offsetsX = new double[]{FIRST_TOE_OFFSET_X, SECOND_TOE_OFFSET_X, THIRD_TOE_OFFSET_X};
        double[] offsetsY = new double[]{FIRST_TOE_OFFSET_Y, SECOND_TOE_OFFSET_Y, THIRD_TOE_OFFSET_Y};

        //Iterate in arrays and substitute values
        for (int i = 0; i < offsetsX.length; i++) {
            GOval toe = new GOval(x + offsetsX[i]  , y + offsetsY[i] , TOE_WIDTH, TOE_HEIGHT );
            toe.setFilled(true);
            toe.setFillColor(Color.black);
            add(toe);
        }
    }
    /**
     * Draws Heel in window
     * <p><b>Precondition: x,y must be specified.</b></p>
     * <p><b>Result: draws heel in window in specified coordinates</b></p>
     * @param x coordinate in x-axis
     * @param y coordinate in y-axis
     */
    private void drawHeel(double x, double y) {
        GOval heel = new GOval(x + HEEL_OFFSET_X, y + HEEL_OFFSET_Y ,HEEL_WIDTH, HEEL_HEIGHT);
        heel.setFilled(true);
        heel.setFillColor(Color.black);
        add(heel);
    }
}
