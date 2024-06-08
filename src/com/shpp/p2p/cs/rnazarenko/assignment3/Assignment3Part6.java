package com.shpp.p2p.cs.rnazarenko.assignment3;

import acm.graphics.GLabel;
import com.shpp.cs.a.graphics.WindowProgram;
import acm.util.RandomGenerator;

import java.awt.*;

/**
 * Assignment3Part6 - Animation (sentence that changes colors)
 * Please be careful with the given sentence. May go of the window
 */
public class Assignment3Part6 extends WindowProgram {
    // constants for changing size of the window
    @SuppressWarnings("unused")
    public static final int APPLICATION_WIDTH = 600;
    @SuppressWarnings("unused")
    public static final int APPLICATION_HEIGHT = 400;
    // pause between frames which equals in 46 FPS
    private static final double PAUSE = 1000 / 48.0;
    // string that would be displayed
    private static final String SENTENCE = "Amazing code)";
    // font family
    private static final String FONT_FAMILY = "DroidSans";
    // font size
    private static final int FONT_SIZE = 80;
    // number of chars in the sentence, variable for easier use
    private static final int SENTENCE_LENGTH = SENTENCE.length();
    // number of colors
    private static final int NUMBER_OF_COLORS = 3;
    // array for storing chars of sentence
    GLabel[] chars = new GLabel[SENTENCE_LENGTH];
    // array for storing width of every char
    double[] widthOfChars = new double[SENTENCE_LENGTH];
    // array for string colors
    Color[] colors = new Color[NUMBER_OF_COLORS];
    // height of a sentence
    double heightOfChars = 0;
    // total width of a sentence
    double totalWidthOfChars = 0;
    // x coordinate of bounding box of a sentence
    double startX;
    // y coordinate of bounding box of a sentence
    double startY;
    // indent for positing chars at screen
    double indentFromLeft = 0;
    // start of a timer
    long startTime = System.currentTimeMillis();
    // curent time for a check
    long currentTime = 0;
    // random generator
    RandomGenerator rgen = RandomGenerator.getInstance();

    /**
     * Runs the program.
     * Draws label and animates it
     */
    @Override
    public void run() {
        drawLabel();
        animateLabel();
    }

    /**
     * Draws label. Fills GLabel array with chars from sentence for
     * future animation of every char. Sets font to every char.
     * Fills widthOfChars array for future centering and placing
     * of a label based on current font. Sets height of chars based
     * on current font. Calculate the total width of chars.
     * Sets location to each char. Adds label to window.
     */
    private void drawLabel() {
        fillCharsArray();
        setFontToLabel();
        fillWidthArrays();
        getHeightOfLabel();
        calcTotalWidthOfChars();
        setLocationToLabel();
        addLabel();
    }

    /**
     * Fills the chars' array with chars from sentence for
     * future animation of every char.
     */
    private void fillCharsArray() {
        for (int i = 0; i < SENTENCE_LENGTH; i++) {
            String oneChar = String.valueOf(SENTENCE.charAt(i));
            chars[i] = new GLabel(oneChar);
        }
    }

    /**
     * Sets font and font size to every char in the char array
     */
    private void setFontToLabel() {
        for (GLabel aChar : chars) {
            aChar.setFont(FONT_FAMILY + "-" + FONT_SIZE);
        }
    }

    /**
     * Fills widthOfChars array for future centering and placing
     * of a label based on current font.
     */
    private void fillWidthArrays() {
        for (int i = 0; i < widthOfChars.length; i++) {
            widthOfChars[i] = chars[i].getWidth();
        }
    }

    /**
     * Sets height of chars based on current font.
     */
    private void getHeightOfLabel() {
        heightOfChars = chars[0].getHeight();
    }

    /**
     * Calculate the total width of chars.
     */
    private void calcTotalWidthOfChars() {
        for (double widthOfChar : widthOfChars) {
            totalWidthOfChars += widthOfChar;
        }
    }

    /**
     * Sets location to each char based on starting x and y
     * coordinates and width of every character
     */
    private void setLocationToLabel() {
        calcStartingCoordinates();
        for (int i = 0; i < widthOfChars.length; i++) {
            chars[i].setLocation(startX + indentFromLeft, startY);
            indentFromLeft += widthOfChars[i];
        }
    }

    /**
     * Caclulates starting coordinates of the label
     */
    private void calcStartingCoordinates() {
        startX = (getWidth() - totalWidthOfChars) / 2.0;
        startY = getHeight() / 2.0;
    }

    /**
     * Adds label to the screen/window
     */
    private void addLabel() {
        for (GLabel aChar : chars) {
            add(aChar);
        }
    }

    /**
     * Animates label. Fills color arrays with random colors.
     * Goes over every character and sets their color every
     * eteration.
     */
    private void animateLabel() {
        fillColorsArrays();
        // variable for accessing color in the colors' array
        int j = 0;
        while (currentTime - startTime < 5000) {
            for (GLabel aChar : chars) {
                aChar.setColor(colors[j]);
                pause(PAUSE);
                currentTime = System.currentTimeMillis();
                if (currentTime - startTime > 5000) {
                    break;
                }
            }
            // if j == 2 then assign it to zero
            // so the colors would begin from start
            if (j == 2) {
                j = 0;
            } else {
                j++;
            }
        }
    }

    /**
     * Fills Colors' array with random colors which are
     * generated by Random Generator
     */
    private void fillColorsArrays() {
        for (int i = 0; i < colors.length; i++) {
            Color randomColor = rgen.nextColor();
            colors[i] = randomColor;
        }
    }
}
