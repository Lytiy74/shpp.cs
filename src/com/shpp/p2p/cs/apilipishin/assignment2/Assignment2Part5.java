package com.shpp.p2p.cs.apilipishin.assignment2;

import acm.graphics.GLine;
import acm.graphics.GRect;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;

public class Assignment2Part5 extends WindowProgram {

    /* The number of rows and columns in the grid, respectively. */
    private static final int NUM_ROWS = 5;
    private static final int NUM_COLS = 5;

    /* The width and height of each box. */
    private static final double BOX_SIZE = 40;

    /* The horizontal and vertical spacing between the boxes. */
    private static final double BOX_SPACING = 10;

    /* The width and height of matrix */
    public static double height_matrix = (NUM_ROWS * (BOX_SPACING + BOX_SIZE) - BOX_SPACING);
    public static double width_matrix = (NUM_COLS * (BOX_SPACING + BOX_SIZE) - BOX_SPACING);

    public void run() {
        createMatrix(NUM_ROWS, NUM_COLS, BOX_SIZE, BOX_SPACING); //create illusion
    }

    /*
    Create each rectangle in cycle
    i - cols of matrix
    j - rows of matrix
     */
    private void createMatrix(int numRows, int numCols, double boxSize, double boxSpacing) {
        for (int i = 0; i < numCols; i++) {
            for (int j = 0; j < numRows; j++) {
                createRectangle(i, j, boxSize, boxSpacing); // i - cols, j - rows
            }
        }
    }

    /*
    create rectangle and add on the screen
     */
    private void createRectangle(int i, int j, double boxSize, double boxSpacing) {
        // calculate offset depends on width/height matrix and rows/cols(i,j)
        GRect rect = new GRect((getWidth() - width_matrix) / 2 + i * (boxSize + boxSpacing), (getHeight() - height_matrix) / 2 + j * (boxSize + boxSpacing), boxSize, boxSize);
        rect.setColor(Color.BLACK); //set color
        rect.setFilled(true);
        add(rect); //add on the screen
    }
}
