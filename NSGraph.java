/*
 * File: NameSurferGraph.java
 * ---------------------------
 * This class represents the canvas on which the graph of
 * names is drawn. This class is responsible for updating
 * (redrawing) the graphs whenever the list of entries changes
 * or the window is resized.
 */

import acm.graphics.*;
import java.awt.event.*;
import java.util.*;
import java.awt.*;

//NameSurferGraph is a subclass of GCanvas that implements a couple interfaces, NameSurferConstants and ComponentListener
public class NameSurferGraph extends GCanvas implements NameSurferConstants, ComponentListener {

	/* Method: NameSurferGraph() */
	/* Creates a new NameSurferGraph object that displays the data.
	*/
	public NameSurferGraph() {
		//there is something now listening to this object
		addComponentListener(this);
		//creates an empty arraylist called "entries"
		entries = new ArrayList<NameSurferEntry> ();	
	}
	
	/* Method: clear() */
	/* Clears the list of NameSurferEntries stored inside this class.
	*/
	public void clear() {
		entries.clear();
		update();
	}
	
	/* Method: addEntry(entry) */
	/*
	* Adds a new NameSurferEntry to the list of entries.
	* Note that this method does not actually draw the graph, but
	* simply stores the entry; the graph is drawn by calling update.
	*/
	public void addEntry(NameSurferEntry entry) {
		entries.add(entry);
		update();
	}
	
	/* Method: update() */
	/* Updates the display image by deleting all the graphical objects
	* from the canvas and then reassembling the display according to
	* the list of entries. Your application must call update after
	* calling either clear or addEntry; update is also called whenever
	* the size of the canvas changes.
	*/
	public void update() {
		this.removeAll();
		drawCanvas();
		drawEntries(); 
	}
		
	//setup grid with the height, width, lines, and margins
	private void drawCanvas() {
		this.drawVerticalLinesAndLabels();
		this.drawHorizontalLines();
	}
	
	private void drawVerticalLinesAndLabels() {
        for(int i = 0; i < NDECADES; i++) {
        	double x = i * (getWidth() / NDECADES);
        	double y1 = 0;
            double y2 = getHeight();
            GLine line = new GLine(x, y1, x, y2);
            add(line);
            GLabel label = new GLabel("" + (START_DECADE + (10 * i)), x + 2, y2 - 2);
            add(label);
        }
    }

    private void drawHorizontalLines() {
        double x1 = 0;
        double y1 = GRAPH_MARGIN_SIZE;
        double x2 = getWidth();
        double y2 = getHeight() - GRAPH_MARGIN_SIZE;
        GLine topLine = new GLine(x1, y1, x2, y1);
        add(topLine);
        GLine bottomLine = new GLine(x1, y2, x2, y2);
        add(bottomLine);
    }
	
	private void drawEntries() {
		int i = 0;
		for (NameSurferEntry entry : entries) { //foreach
			//drawEntry(entry) takes a single NameSurferEntry looking at ranks array 
			//and looking at width and height of GCanvas and doing the math
			
			drawEntry(entry, graphColors[i % graphColors.length]);
			i++;
		}
	}
	
	private void drawEntry(NameSurferEntry entry, Color c) {
		for (int i = 0; i < NDECADES - 1; i++) {
			int rank = entry.getRank(i);
			int nextRank = entry.getRank(i + 1);
			double x1 = i * (getWidth() / NDECADES);
			double x2 = x1 + (getWidth() / NDECADES); 
			//double x2 = (i + 1) * (getWidth() / NDECADES);
		 	double y1 = GRAPH_MARGIN_SIZE + (rank * (getHeight() - (GRAPH_MARGIN_SIZE * 2)) / ((double) MAX_RANK));
		 	if (rank == 0) {
		 		y1 = getHeight() - GRAPH_MARGIN_SIZE;
		 	}
		 	double y2 = GRAPH_MARGIN_SIZE + (nextRank * (getHeight() - (GRAPH_MARGIN_SIZE * 2)) / ((double) MAX_RANK));
			if (nextRank == 0) {
		 		y2 = getHeight() - GRAPH_MARGIN_SIZE;
		 	}
		 	GLine rankLine = new GLine (x1, y1, x2, y2);
		 	rankLine.setColor(c); 
			add(rankLine);
			GLabel rankLabel = new GLabel(entry.getName() + " " + rank, x1, y1);
			rankLabel.setColor(c);
	        add(rankLabel);	
	        if (i == NDECADES - 2) {
	        	GLabel lastLabel = new GLabel(entry.getName() + " " + nextRank, x2, y2);
	        	add(lastLabel);
	        }
		}
		//now add Color to each GLine and GLabel
		//hint: look at ACM Documentation for appropriate constructors giving colors
	}
	
	/*private instance variables*/
	private ArrayList <NameSurferEntry> entries;
	private static final Color[] graphColors = {Color.BLACK, Color.RED, Color.BLUE, Color.MAGENTA};
	
	/* Implementation of the ComponentListener interface */
	public void componentHidden(ComponentEvent e) { }
	public void componentMoved(ComponentEvent e) { }
	public void componentResized(ComponentEvent e) { update(); }
	public void componentShown(ComponentEvent e) { }

}
