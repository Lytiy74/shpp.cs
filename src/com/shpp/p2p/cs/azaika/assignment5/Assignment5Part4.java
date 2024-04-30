package com.shpp.p2p.cs.azaika.assignment5;

import com.shpp.cs.a.console.TextProgram;

import java.io.*;
import java.util.ArrayList;

public class Assignment5Part4 extends TextProgram {
    private static final String FILE_PATH = "Food.csv";
    @Override
    public void run() {
        System.out.println(extractColumn(FILE_PATH,1).toString());
    }
    /**
     * Extracts a specified column from a CSV file.
     *
     * @param filename    the name of the CSV file
     * @param columnIndex the index of the column to extract
     * @return            an ArrayList containing the values of the specified column
     */
    private ArrayList<String> extractColumn(String filename, int columnIndex){
        ArrayList<String> arrayList = new ArrayList<>();
        try(BufferedReader bf = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = bf.readLine()) != null) {
                // Split the CSV line by comma, ignoring commas within quotes
                String[] split = line.split(",(?=(?:[^\"]*\"[^\"]*\")*(?![^\"]*\"))");
                arrayList.add(split[columnIndex]);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found. Try again");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return arrayList;
    }

}
