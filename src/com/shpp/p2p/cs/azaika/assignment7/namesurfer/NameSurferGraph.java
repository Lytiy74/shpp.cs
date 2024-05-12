package com.shpp.p2p.cs.azaika.assignment7.namesurfer;

/*
 * File: NameSurferGraph.java
 * ---------------------------
 * This class represents the canvas on which the graph of
 * names is drawn. This class is responsible for updating
 * (redrawing) the graphs whenever the list of entries changes
 * or the window is resized.
 */

import acm.graphics.*;

import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;

public class NameSurferGraph extends GCanvas
        implements NameSurferConstants, ComponentListener {
    HashMap<String, NameSurferEntry> entryHashMap = new HashMap<>();
    Color[] colors = {Color.BLACK,Color.BLUE,Color.GREEN,Color.RED};
    

    /**
     * Creates a new NameSurferGraph object that displays the data.
     */
    public NameSurferGraph() {
        addComponentListener(this);
    }


    /**
     * Clears the list of name surfer entries stored inside this class.
     */
    public void clear() {
        entryHashMap.clear();
        update();
    }
	
	/* Method: addEntry(entry) */

    /**
     * Adds a new NameSurferEntry to the list of entries on the display.
     * Note that this method does not actually draw the graph, but
     * simply stores the entry; the graph is drawn by calling update.
     */
    public void addEntry(NameSurferEntry entry) {
        entryHashMap.put(entry.getName(), entry);
        update();
    }


    /**
     * Updates the display image by deleting all the graphical objects
     * from the canvas and then reassembling the display according to
     * the list of entries. Your application must call update after
     * calling either clear or addEntry; update is also called whenever
     * the size of the canvas changes.
     */
    public void update() {
        removeAll();
        drawEntriesGraphs();
        drawSheet();
    }

    private void drawEntriesGraphs() {
        int j = 0;
        for (NameSurferEntry entry : entryHashMap.values()) {
            for (int i = 0; i < NDECADES; i++) {
                int x = getX(i);
                int y = getY(entry, i);
                GLabel label = new GLabel(entry.getName() + " " + entry.getRank(i), x, y);
                label.setColor(colors[j]);
                add(label);

                GLine line = new GLine(x, y, getX(i - 1), getY(entry, i - 1));
                line.setColor(colors[j]);
                add(line);
            }
            j++;
            j = Math.min(j, colors.length - 1);
        }
    }

    private int getX(int i) {
        return Math.max(i,0) * (getWidth() / NDECADES);
    }

    private int getY(NameSurferEntry entry, int i) {
        int maxY = getHeight() - GRAPH_MARGIN_SIZE;
        int y;
        int rank = entry.getRank(Math.max(i, 0));
        if (rank == 0){
            y = maxY;
        }else if (rank > maxY){
            y = maxY;
        }else y = Math.max(rank, GRAPH_MARGIN_SIZE);
        return y;
    }

    private void drawSheet() {
        //draw upper horizontal line
        add(new GLine(0, GRAPH_MARGIN_SIZE, getWidth(), GRAPH_MARGIN_SIZE));
        //draw lower horizontal line
        add(new GLine(0, getHeight() - GRAPH_MARGIN_SIZE, getWidth(),getHeight() - GRAPH_MARGIN_SIZE));
        for (int i = 0; i < NDECADES; i++) {
            //draw vertical line to separate decades
            add(new GLine(getX(i), 0, getX(i), getHeight()));
            int decade = START_DECADE + (10 * i);
            //draw label with number of year
            GLabel label = new GLabel(String.valueOf(decade));
            label.setLocation(getX(i),getHeight() - label.getDescent());
            add(label);
        }
    }


    /* Implementation of the ComponentListener interface */
    public void componentHidden(ComponentEvent e) {
    }

    public void componentMoved(ComponentEvent e) {
    }

    public void componentResized(ComponentEvent e) {
        update();
    }

    public void componentShown(ComponentEvent e) {
    }
}
