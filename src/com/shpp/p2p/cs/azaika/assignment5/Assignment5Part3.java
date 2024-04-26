package com.shpp.p2p.cs.azaika.assignment5;

import com.shpp.cs.a.console.TextProgram;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Assignment5Part3 extends TextProgram {
    private List<String> dictionaryEng = new ArrayList<>();
    private static final String  pathToFile = "en-dictionary.txt";

    @Override
    public void run() {
       dictionaryEng = readDictionary(pathToFile, dictionaryEng);
       String numberPlate = "XCX";
        System.out.println(gameRoad(numberPlate));
    }

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

    private String gameRoad(String test) {
        String regex = buildRegex(test);

        for (String word : dictionaryEng){
            if (word.matches(regex)) return word;
        }
        return "";
    }

    private static String buildRegex(String test) {
        StringBuilder regexBuilder = new StringBuilder();
        for (int i = 0; i < test.length(); i++) {
            if (Character.isLetter(test.charAt(i))) {
                regexBuilder.append(".*");
                regexBuilder.append(test.toLowerCase().charAt(i));
                regexBuilder.append(".*");
            }
        }
        return regexBuilder.toString();
    }
}
