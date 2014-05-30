/*
 * File: NameSurferEntry.java
 * --------------------------
 * This class represents a single entry in the database.  Each
 * NameSurferEntry contains a name and a list giving the popularity
 * of that name for each decade stretching back to 1900.
 */

//import acm.util.*;
import java.util.*;
//import java.io.*;

public class NameSurferEntry implements NameSurferConstants {

/* Constructor: NameSurferEntry(line) */
/*
 * Creates a new NameSurferEntry from a data line as it appears
 * in the data file. Each line begins with the name, which is
 * followed by integers giving the rank of that name for each
 * decade. The implementation of the constructor has to divide up the 
 * line at the spaces, convert the digit strings to integers 
 * (using Integer.parseInt), and then store all of this 
 * information as the private state of the object in such a way 
 * that it is easy for the getName and getRank methods to return 
 * the appropriate values.
 */	

	public NameSurferEntry(String line) {
	
		//small test - print out "line" as is from NameSurferDatabase
		//System.out.println(line);

		//Search string for " " with StringTokenizer
		StringTokenizer tok = new StringTokenizer(line, " ");
		
		name = tok.nextToken();
		ranks = new int[NDECADES];
	
		for (int i=0; i < NDECADES; i++) {
			//get next token
			String element = tok.nextToken();
			//convert to integer
			int rank = Integer.parseInt(element);
			//put into array
			ranks[i] = rank;
			//how to do it in one line
			//ranks[i] = Integer.parseInt(tok.nextToken());
		}
	}

    /* Method: getName() */
    /*
     * Returns the name associated with this entry.
     */
	public String getName() {
		return name;
	}

    /* Method: getRank(decade) */
    /*
     * Returns the rank associated with an entry for a particular
     * decade. The decade value is an integer indicating how many
     * decades have passed since the first year in the database,
     * which is given by the constant START_DECADE.  If a name does
     * not appear in a decade, the rank value is 0.
     */
	public int getRank(int decade) { 
		return ranks[decade]; //return index element of ranks array
	}

    /* Method: toString() */
    /**
     * Returns a string that makes it easy to see the value of a
     * NameSurferEntry.
     */
	public String toString() {
		//turning the line into a string with
		//brackets around the numbers
		//+= (ranks must come from ranks)
		//return (name of new string);
		String repn = name;
		repn += " [";
		for (int i=0; i < NDECADES; i++) {
			repn += ranks[i];
			if (i < NDECADES - 1) {
				repn += " ";
			}
		}
		repn += "]";
		return repn;
	}

/* Private Instance Variables */	
	private String name;
	private int[] ranks;

}
