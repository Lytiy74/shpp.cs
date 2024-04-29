package com.shpp.p2p.cs.azaika.assignment5;

import com.shpp.cs.a.console.TextProgram;

import java.io.*;
import java.util.ArrayList;

public class Assignment5Part4 extends TextProgram {
    @Override
    public void run() {
        System.out.println(extractColumn("Movies.csv",1).toString());
    }
    private ArrayList<String> extractColumn(String filename, int columnIndex){
        ArrayList<String> arrayList = new ArrayList<>();
        try(BufferedReader bf = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = bf.readLine()) != null) {
                String[] split = line.split(",(?=(?:[^\"]*\"[^\"]*\")*(?![^\"]*\"))");
                arrayList.add(split[columnIndex]);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return arrayList;
    }

}
