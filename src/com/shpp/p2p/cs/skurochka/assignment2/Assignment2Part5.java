package com.shpp.p2p.cs.skurochka.assignment2;

import acm.graphics.GLine;
import acm.graphics.GOval;
import acm.graphics.GRect;
import com.shpp.cs.a.graphics.WindowProgram;
import java.awt.*;

public class Assignment2Part5 extends WindowProgram {
    /* The number of rows and columns in the grid, respectively. */
    private static final int NUM_ROWS = 6;
    private static final int NUM_COLS = 6;

    /* The width and height of each box. */
    private static final double BOX_SIZE = 40;

    /* The horizontal and vertical spacing between the boxes. */
    private static final double BOX_SPACING = 20;

    // The method of launching the program.
    public void run() {
        // The method of drawing a set of boxes.

        drawSetOfBox();
        GLine vertical = new GLine(getWidth()/2, 0, getWidth()/2 , getHeight());
        vertical.setColor(Color.red);
        add(vertical);

        GLine horizontal = new GLine(0, getHeight()/2 , getWidth(), getHeight()/2);
        horizontal.setColor(Color.red);
        add(horizontal);

        GOval oval = new GOval(getWidth()/2 - 3, getHeight()/2 - 3 ,6 ,6);
        oval.setFilled(true);
        oval.setColor(Color.red);
        add(oval);
    }

    /*
     * The method of drawing a set of boxes.
     * The method first calculates the width and height of all boxes, along with the indents between the boxes.
     * Then the starting position in X and the starting position in Y for the first box are calculated.
     * Then the size of one box with a gap is calculated.
     * Then a loop within a loop is started to search the X and Y positions and draw these boxes accordingly.
     * */
    private void drawSetOfBox() {
        double widthAllBox = (NUM_COLS * BOX_SIZE) + ((NUM_COLS-1) * BOX_SPACING);
        double heightAllBox = (NUM_ROWS * BOX_SIZE) + ((NUM_ROWS-1) * BOX_SPACING);
        double startPositionForX = getWidth() / 2.0 - widthAllBox / 2.0;
        double startPositionForY = getHeight() / 2.0 - heightAllBox / 2.0;
        double sizeOneBoxPlusSpacing = BOX_SPACING + BOX_SIZE;
        for (int i = 0; i < NUM_COLS; i++) {
            for (int j = 0; j < NUM_ROWS; j++) {
                drawOneBox(startPositionForX + sizeOneBoxPlusSpacing * i,
                        startPositionForY + sizeOneBoxPlusSpacing * j);
            }
        }
    }

    /*
     * The method of drawing a single box.
     * The method takes as parameters the starting positions X and Y for drawing a single box.
     * In the middle, a new object of type GRect is created,
     * which accepts the starting parameters X and Y and the dimensions of the box, which are set by constants.
     * The next step is to set the activity of the inner field to allow you to add a color to the box.
     * Then the color of the box is set.
     * The last step is to add the object to the screen layout.
     * */
    private void drawOneBox(double x, double y) {
        GRect rect = new GRect(x, y, BOX_SIZE, BOX_SIZE);
        rect.setFilled(true);
        rect.setColor(Color.BLACK);
        add(rect);
    }
}