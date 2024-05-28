package com.shpp.p2p.cs.azaika.assignment7;

/*
 * File: NameSurferEntry.java
 * --------------------------
 * This class represents a single entry in the database.  Each
 * NameSurferEntry contains a name and a list giving the popularity
 * of that name for each decade stretching back to 1900.
 */

import java.util.*;

public class NameSurferEntry implements NameSurferConstants {
    private final String name;
    private final int[] rank;

	/* Constructor: NameSurferEntry(line) */

    /**
     * Creates a new NameSurferEntry from a data line as it appears
     * in the data file.  Each line begins with the name, which is
     * followed by integers giving the rank of that name for each
     * decade.
     */
    public NameSurferEntry(String line) {
        // Split the input line into parts based on spaces
        String[] parts = line.split(" ");
        // The first part is the name
        name = parts[0];
        // Initialize the rank array to hold the rankings for each decade
        rank = new int[parts.length - 1];
        // Populate the rank array with the integer rankings
        for (int i = 1; i < parts.length; i++) {
            rank[i - 1] = Integer.parseInt(parts[i]);
        }
    }

	/* Method: getName() */

    /**
     * Returns the name associated with this entry.
     */
    public String getName() {
        return this.name;
    }

	/* Method: getRank(decade) */

    /**
     * Returns the rank associated with an entry for a particular
     * decade.  The decade value is an integer indicating how many
     * decades have passed since the first year in the database,
     * which is given by the constant START_DECADE.  If a name does
     * not appear in a decade, the rank value is 0.
     */
    public int getRank(int decade) {
        return decade < rank.length ? rank[decade] : 0;
    }

	/* Method: toString() */

    /**
     * Returns a string that makes it easy to see the value of a
     * NameSurferEntry.
     */
    public String toString() {
        // You need to turn this stub into a real implementation //
        return name + " " + Arrays.toString(rank);
    }
}

