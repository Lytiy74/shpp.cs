package com.shpp.p2p.cs.azaika.assignment5;

import com.shpp.cs.a.console.TextProgram;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Assignment5Part3 extends TextProgram {
    // List of dictionary words
    private List<String> dictionaryEng = new ArrayList<>();
    // Path to the dictionary file
    private static final String pathToFile = "en-dictionary.txt";


    @Override
    public void run() {
        // Read the English dictionary from file
        dictionaryEng = readDictionary(pathToFile, dictionaryEng);
        // Test number plate
        String numberPlate = readLine("Enter number plate:");
        // Print the result of the game
        System.out.println(gameRoad(numberPlate));
    }

    // Method to read the English dictionary from file
    private List<String> readDictionary(String path, List<String> list) {
        try(BufferedReader br = new BufferedReader(new FileReader(String.valueOf(path)))) {
            String line;
            while((line = br.readLine()) != null){
                list.add(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    // Method to find a word from the dictionary matching a pattern
    private String gameRoad(String test) {
        // Build the regex pattern based on the test string
        String regex = buildRegex(test);

        // Iterate through the dictionary to find a matching word
        for (String word : dictionaryEng){
            if (word.matches(regex)) return word;
        }
        return ""; // Return an empty string if no match is found
    }

    // Method to build the regex pattern for matching a test string
    private static String buildRegex(String test) {
        // Create a StringBuilder to build the regex pattern
        StringBuilder regexBuilder = new StringBuilder();
        for (int i = 0; i < test.length(); i++) {
            // If the character is a letter, add regex pattern to match any characters before and after it
            if (Character.isLetter(test.charAt(i))) {
                regexBuilder.append(".*");
                regexBuilder.append(test.toLowerCase().charAt(i));
                regexBuilder.append("*");
            }
        }
        // Return the built regex pattern
        return regexBuilder.toString();
    }
}
