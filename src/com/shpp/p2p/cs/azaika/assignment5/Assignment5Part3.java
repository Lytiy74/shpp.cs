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
       dictionaryEng = readDictionary(pathToFile);
        // Request three letters
        String letters;
        do{
            letters = readLine("Enter three letters:");
        }while (!letters.matches("[A-Za-z]{3}"));
        // Print the result of the game
        List<String> words = getListOfWordsOfMatchedLetters(letters);
        printResultToConsole(words);
    }

    private static void printResultToConsole(List<String> words) {
        System.out.println("Total words: " + words.size());
        for (String word : words){
            System.out.println(word);
        }
    }

    // Method to read the English dictionary from file
    private List<String> readDictionary(String path) {
        ArrayList<String> dictionary = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(String.valueOf(path)))) {
            String line;
            while((line = br.readLine()) != null){
                dictionary.add(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return dictionary;
    }

    // Method to find a word from the dictionary matching a pattern
    private ArrayList<String> getListOfWordsOfMatchedLetters(String test) {
        // Build the regex pattern based on the test string
        String regex = buildRegex(test);
        ArrayList <String> result = new ArrayList<>();

        // Iterate through the dictionary to find a matching word
        for (String word : dictionaryEng){
            if (word.matches(regex)) result.add(word);
        }
        return result;
    }

    // Method to build the regex pattern for matching a test string
    private static String buildRegex(String test) {
        // Create a StringBuilder to build the regex pattern
        StringBuilder regexBuilder = new StringBuilder();
        for (int i = 0; i < test.length(); i++) {
            //  add regex pattern to match any characters before and after it
                regexBuilder.append(".*");
                regexBuilder.append(test.toLowerCase().charAt(i));
                regexBuilder.append(".*");
        }
        // Return the built regex pattern
        return regexBuilder.toString();
    }
}
