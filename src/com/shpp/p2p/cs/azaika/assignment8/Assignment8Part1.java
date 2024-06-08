package com.shpp.p2p.cs.azaika.assignment8;

import acm.graphics.GDimension;
import acm.graphics.GRect;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.HashSet;

public class Assignment8Part1 extends WindowProgram {


    private static final double SQUARE_SIZE = 20.0;
    public static final int APPLICATION_WIDTH = 300;
    public static final int APPLICATION_HEIGHT = 300;
    private static final HashSet<GRect> rects = new HashSet<>();
    private static final double PAUSE_TIME = 1000/30;
    private GRect currentRect;

    @Override
    public void run() {
        addMouseListeners();

        drawChessBoard();
        startAnimation();
    }

    private void startAnimation() {
        while (true) {
            if (currentRect != null) {
                animate();
            }
            pause(PAUSE_TIME);
        }
    }
    private void drawChessBoard() {
        int quantitySquaresOfXAxis = (int) (APPLICATION_WIDTH / SQUARE_SIZE);
        int quantitySquaresOfYAxis = (int) (APPLICATION_HEIGHT / SQUARE_SIZE);
        int chessOrder = 0;

        for (int i = 0; i < quantitySquaresOfYAxis; i++) {
            for (int j = 0; j < quantitySquaresOfXAxis; j++) {
                double x = j * SQUARE_SIZE;
                double y = i * SQUARE_SIZE;
                GRect rect = new GRect(x, y, SQUARE_SIZE, SQUARE_SIZE);
                rect.setColor(Color.white);
                if (chessOrder % 2 == 0) {
                    rect.setFilled(true);
                    rect.setColor(Color.black);
                    add(rect);
                    rects.add(rect);
                }
                chessOrder++;


            }
        }
    }
    public void mouseMoved(MouseEvent event) {
        double x = event.getX();
        double y = event.getY();

        if (getElementAt(x,y) != null) {currentRect = (GRect) getElementAt(x,y);}

    }

    private void animate() {
        if (currentRect != null) {
            for (double i = (int) SQUARE_SIZE; i > SQUARE_SIZE / 2; i -= 1) {
                GDimension sizeOfBiggerSquare = currentRect.getSize();
                double diagonal = Math.sqrt(i * 2);
                double diagonalOfBigger = Math.sqrt(sizeOfBiggerSquare.getHeight() * 2);
                double delta = diagonalOfBigger - diagonal;
                currentRect.move(delta, delta);
                currentRect.setSize(i, i);
                pause(PAUSE_TIME);
                repaint();
            }
        }
        currentRect = null;
    }


}
