package com.shpp.p2p.cs.azaika.assignment7;

/*
 * File: NameSurfer.java
 * ---------------------
 * When it is finished, this program will implements the viewer for
 * the baby-name database described in the assignment handout.
 */

import com.shpp.cs.a.simple.SimpleProgram;

import java.awt.event.*;
import javax.swing.*;

public class NameSurfer extends SimpleProgram implements NameSurferConstants{
    private NameSurferDataBase dataBase;
    private NameSurferGraph nameSurferGraph;
    private JTextField textField;

	/* Method: init() */

    /**
     * This method has the responsibility for reading in the data base
     * and initializing the interactors at the top of the window.
     */
    public void init() {
        // Load the database from the specified file
        dataBase = new NameSurferDataBase(NAMES_DATA_FILE);
        // Set the size of the application window
        this.setSize(APPLICATION_WIDTH, APPLICATION_HEIGHT);
        // Add a label for the name input field
        this.add(new JLabel("Name:"), "North");
        // Initialize the text field for name input
        textField = new JTextField(10);
        // Set the action command for when the enter key is pressed
        textField.setActionCommand("EnterPressed");
        // Add an action listener to the text field
        textField.addActionListener(this);
        // Add the text field to the top of the window
        this.add(textField, "North");
        // Add a button to graph the name data
        this.add(new JButton("Graph"), "North");
        // Add a button to clear the graph
        this.add(new JButton("Clear"), "North");
        // Initialize the graph component
        nameSurferGraph = new NameSurferGraph();
        // Add the graph component to the window
        this.add(nameSurferGraph);
        // Add action listeners for the buttons
        this.addActionListeners();
    }


	/* Method: actionPerformed(e) */

    /**
     * This class is responsible for detecting when the buttons are
     * clicked, so you will have to define a method to respond to
     * button actions.
     */
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
        // Check if the Graph button or Enter key was pressed
        if (actionCommand.equals("Graph") || actionCommand.equals("EnterPressed")) {
            // Find the entry in the database for the given name
            NameSurferEntry entry = dataBase.findEntry(textField.getText().toLowerCase());
            System.out.println(entry);
            // Add the entry to the graph
            if(entry != null){
            nameSurferGraph.addEntry(entry);
            nameSurferGraph.update();
            // Print debug information to the console
            System.out.println("Graph: " + textField.getText());
            }
        } else if (actionCommand.equals("Clear")) {
            // Clear the graph
            nameSurferGraph.clear();
            nameSurferGraph.update();
            // Print debug information to the console
            System.out.println("Clear");
        }
    }

}
