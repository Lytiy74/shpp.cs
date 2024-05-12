package com.shpp.p2p.cs.azaika.assignment7.namesurfer;

/*
 * File: NameSurfer.java
 * ---------------------
 * When it is finished, this program will implements the viewer for
 * the baby-name database described in the assignment handout.
 */

import com.shpp.cs.a.simple.SimpleProgram;

import java.awt.event.*;
import javax.swing.*;

public class NameSurfer extends SimpleProgram implements NameSurferConstants {
    private NameSurferDataBase dataBase;
    private NameSurferGraph nameSurferGraph;
    private JTextField textField;

	/* Method: init() */

    /**
     * This method has the responsibility for reading in the data base
     * and initializing the interactors at the top of the window.
     */
    public void init() {
        // loading db from specified file.
        dataBase = new NameSurferDataBase(NAMES_DATA_FILE);
        this.setSize(APPLICATION_WIDTH, APPLICATION_HEIGHT);
        this.add(new JLabel("Name"), "North");
        textField = new JTextField(10);
        this.add(textField, "North");
        this.add(new JButton("Graph"), "North");
        this.add(new JButton("Clear"), "North");
        nameSurferGraph = new NameSurferGraph();
        this.add(nameSurferGraph);
        this.addActionListeners();
    }

	/* Method: actionPerformed(e) */

    /**
     * This class is responsible for detecting when the buttons are
     * clicked, so you will have to define a method to respond to
     * button actions.
     */
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Graph":
                NameSurferEntry entry = dataBase.findEntry(textField.getText());
                nameSurferGraph.addEntry(entry);
                System.out.println("Graph: " + textField.getText());
                System.out.println(nameSurferGraph.getElementCount());
                break;
            case "Clear":
                nameSurferGraph.clear();
                System.out.println("Clear");
                break;
        }
    }
}
