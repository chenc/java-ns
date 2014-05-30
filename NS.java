/*
* File: NameSurfer.java
* ---------------------
* This program implements the viewer for the baby-name database.
*/

import acm.program.*;
import java.awt.event.*;
import javax.swing.*;

//NameSurfer is a subclass of ConsoleProgram and implements the NameSurferConstants interface
public class NameSurfer extends Program implements NameSurferConstants {

    /* Method: init() */
    /*
    * This method has the responsibility for reading in the database
    * and initializing the interactors at the top of the graphical window.
    */
    public void init() {
    	//object = instantiation happening here!
    	database = new NameSurferDataBase("names-data.txt");
    	graph = new NameSurferGraph();
    	//calling a method
    	initializeGraphics();
    }
    
    private void initializeGraphics() {
    	add(graph);
    	add(new JLabel("Name"), NORTH);
    	
    	nameField = new JTextField(20);
    	nameField.setActionCommand("Name");
    	add (nameField, NORTH);
    	nameField.addActionListener(this);
    	
    	graphButton = new JButton("Graph");
    	add (graphButton, NORTH);
    	
    	clearButton = new JButton("Clear");
    	add (clearButton, NORTH);
    
    	addActionListeners();
    }

    /* Method: actionPerformed(e) */
    /*
    * This class is responsible for detecting when the buttons are
    * clicked, so you will have to define a method to respond to
    * button actions.
    */
    public void actionPerformed(ActionEvent e) {
    	//	String cmd = e.getActionCommand();
    	//	if (cmd.equals("Graph")) {
    	//		println("graph me!");
    	//	} else if (cmd.equals("Clear")) {
    	//		println("clear me!");	
    	//	}
    	
    	//Textfield Simple way
    	//if (e.getSource() == nameField) {
    	//	println("Hello " + nameField.getText());
    	//}
	
    	//Textfield Modern way : useful because an interactor can trigger same command for multiple objects (i.e. button and textfield)
    	String cmd = e.getActionCommand();
    	if (cmd.equals("Name") || graphButton == e.getSource() ) {
    		//test output: println("Graph: " + nameField.getText());
    		String rawName = nameField.getText();
    		String name = Character.toUpperCase(rawName.charAt(0)) + rawName.substring(1).toLowerCase();
    		nameField.setText("");
    		//look up the nameSurfer entry for the name to get ranks
    		NameSurferEntry entry = database.findEntry(name);
    		//print ranks
    		println (entry);
    		if (entry != null) {
    			graph.addEntry(entry);
    		}
    	}
	
    	//Clear button action
    	else if (clearButton == e.getSource()) {
    		graph.clear();
    	}
    }
    
    /* Private Instance Variables */
    private JButton graphButton;
    private JButton clearButton;
    private JTextField nameField;
    private NameSurferDataBase database;
    private NameSurferGraph graph;
    
}

