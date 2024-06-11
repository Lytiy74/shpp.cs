package com.shpp.p2p.cs.azaika.assignment8;

import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.ArrayList;

/**
 * This class creates a graphical window with moving rectangles.
 * The rectangles can be activated or deactivated by mouse clicks,
 * and their movement toggles accordingly.
 */
public class Assignment8Part1 extends WindowProgram {
    private static final double RECTANGLE_SIZE = 20.0;
    private static final Color RECTANGLE_COLOR = Color.BLACK;
    private static final int RECTANGLES_QUANTITY = 10;
    private List<ExtRect> rectangles = new ArrayList<>();
    private static final double PAUSE_TIME = 1000.0 / 30;

    /**
     * Sets up the initial state by drawing rectangles, adding mouse listeners,
     * and starting the animation.
     */
    @Override
    public void run() {
        drawRectangles(RECTANGLE_SIZE, RECTANGLES_QUANTITY, RECTANGLE_COLOR);
        addMouseListeners();
        playAnimation();
    }

    /**
     * Continuously updates the state of the rectangles, moving the active ones.
     */
    private void playAnimation() {
        while (true) {
            for (ExtRect rect : rectangles) {
                if (rect.isActived()) {
                    moveRectangle(rect);
                }
            }
            pause(PAUSE_TIME);
        }
    }

    /**
     * Moves a rectangle horizontally within the bounds of the window.
     *
     * @param rect The rectangle to move.
     */
    private void moveRectangle(ExtRect rect) {
        double x = rect.getX();
        if ((x + rect.getWidth()) >= getWidth() || x <= 0) {
            rect.setDx(rect.getDx() * -1);
        }
        rect.move(rect.getDx(), 0);
    }

    /**
     * Draws the specified number of rectangles centered horizontally with vertical padding.
     *
     * @param size     The size of each rectangle.
     * @param quantity The number of rectangles to draw.
     * @param color    The color of the rectangles.
     */
    private void drawRectangles(double size, int quantity, Color color) {
        double xAxis = getWidth() / 2.0 - size / 2.0;
        double padding = (getHeight() - (size * quantity)) / (quantity - 1);
        for (int i = 0; i < quantity; i++) {
            double yAxis = (size + padding) * i;
            ExtRect rect = new ExtRect(xAxis, yAxis, size, size);
            rect.setFilled(true);
            rect.setColor(color);
            rectangles.add(rect);
            add(rect);
        }
    }

    /**
     * Handles mouse click events to activate or deactivate rectangles.
     * Toggles the clicked rectangle and its immediate neighbors.
     *
     * @param e The mouse event.
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        ExtRect object = (ExtRect) getElementAt(e.getX(), e.getY());
        if (object != null) {
            object.setActived(!object.isActived());
            toggleNeighbors(object, object.isActived());
        }
    }

    /**
     * Toggles the activation state of the immediate neighbors of a rectangle.
     *
     * @param object    The rectangle that was clicked.
     * @param activated The new activation state.
     */
    private void toggleNeighbors(ExtRect object, boolean activated) {
        int index = rectangles.indexOf(object);
        if (index != -1) {
            if (index > 0) {
                rectangles.get(index - 1).setActived(activated);
            }
            if (index < rectangles.size() - 1) {
                rectangles.get(index + 1).setActived(activated);
            }
        }
    }
}
