package com.shpp.p2p.cs.azaika.assignment3;

import acm.graphics.GLine;
import acm.graphics.GOval;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;

public class Assignment3Part6 extends WindowProgram {
    @Override
    public void run() {
        drawAxes();
        drawSinAndCos();
    }

    private void drawAxes() {
        GLine xAxis = new GLine(0, getHeight() / 2, getWidth(), getHeight() / 2);
        add(xAxis);

        GLine yAxis = new GLine(getWidth() / 2, 0, getWidth() / 2, getHeight());
        add(yAxis);
    }
    private void drawSinAndCos() {
        long startTime = System.currentTimeMillis();
        long endLoopTime = 0;
        for (double i = 0; i < 20; i += 0.02) {
            if ((endLoopTime - startTime) >= 5000){
                break;
            }
            double x = i * 30;
            double ySin = Math.sin(i) * 100 + getHeight()/2;
            double yCos = Math.cos(i) * 100 + getHeight()/2;
            GOval cos = new GOval(x, yCos, 4, 4);
            cos.setFilled(true);
            cos.setColor(Color.red);
            add(cos);
            GOval sin = new GOval(x, ySin, 4, 4);
            sin.setFilled(true);
            sin.setColor(Color.black);
            add(sin);
            pause(1000 / 60);
            endLoopTime = System.currentTimeMillis();
        }
    }
}
