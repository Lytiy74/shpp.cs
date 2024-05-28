package com.shpp.p2p.cs.azaika.assignment7;

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
import java.util.LinkedHashMap;

public class NameSurferGraph extends GCanvas
        implements NameSurferConstants, ComponentListener {
    private final LinkedHashMap<String, NameSurferEntry> entryLinkHashMap;
    private final Color[] colors = {Color.BLUE, Color.RED, Color.MAGENTA, Color.BLACK};
    

    /**
     * Creates a new NameSurferGraph object that displays the data.
     */
    public NameSurferGraph() {
        addComponentListener(this);
        this.entryLinkHashMap = new LinkedHashMap<>();
    }


    /**
     * Clears the list of name surfer entries stored inside this class.
     */
    public void clear() {
        entryLinkHashMap.clear();
        update();
    }
	
	/* Method: addEntry(entry) */

    /**
     * Adds a new NameSurferEntry to the list of entries on the display.
     * Note that this method does not actually draw the graph, but
     * simply stores the entry; the graph is drawn by calling update.
     */
    public void addEntry(NameSurferEntry entry) {
        entryLinkHashMap.put(entry.getName(), entry);
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
        int colorIndex = 0; // Color index for cycling through colors array

        // Iterate through each NameSurferEntry in the entryLinkHashMap
        for (NameSurferEntry entry : entryLinkHashMap.values()) {
            // Draw the graph for each decade
            for (int i = 0; i < NDECADES; i++) {
                // Calculate the x and y coordinates for the start and end points of the line
                int x1 = getX(Math.max(i - 1, 0));
                int y1 = getY(entry, Math.max(i - 1, 0));
                int x2 = getX(i);
                int y2 = getY(entry, i);

                // Determine the rank string to display (use "*" for rank 0)
                String rankString = entry.getRank(i) == 0 ? "*" : String.valueOf(entry.getRank(i));

                // Create and add a label for the name and rank at the end point of the line
                GLabel label = new GLabel(entry.getName() + " " + rankString, x2, y2);
                label.setColor(colors[colorIndex]); // Set the label color
                add(label); // Add the label to the canvas

                // Create and add a line representing the rank from one decade to the next
                GLine line = new GLine(x1, y1, x2, y2);
                line.setColor(colors[colorIndex]); // Set the line color
                add(line); // Add the line to the canvas
            }
            colorIndex++; // Move to the next color
            // Reset the color index if it exceeds the length of the colors array
            colorIndex = colorIndex == colors.length ? 0 : colorIndex;
        }
    }

    private int getX(int i) {
        return Math.max(i,0) * (getWidth() / NDECADES);
    }

    private int getY(NameSurferEntry entry, int i) {
        int maxY = getHeight() - GRAPH_MARGIN_SIZE;
        int rank = entry.getRank(i);
        if (rank == 0) {
            return maxY;
        } else {
            return GRAPH_MARGIN_SIZE + (maxY - GRAPH_MARGIN_SIZE) * rank / MAX_RANK;
        }
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
