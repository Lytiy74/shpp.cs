package com.shpp.p2p.cs.azaika.assignment5;

import com.shpp.cs.a.console.TextProgram;

public class Assignment5Part1 extends TextProgram {
    private static final String VOWEL_LETTERS = "AEIOUY";

    @Override
    public void run() {
        /* Repeatedly prompt the user for a word and print out the estimated
         * number of syllables in that word.
         */
        while (true) {
            String word = readLine("Enter a single word: ");
            println("  Syllable count: " + syllablesInWord(word));
        }
    }

    /**
     * Given a word, estimates the number of syllables in that word according to the
     * heuristic specified in the handout.
     *
     * @param word A string containing a single word.
     * @return An estimate of the number of syllables in that word.
     */
    private int syllablesInWord(String word) {
        int syllables = 0;
        char[] chars = word.toUpperCase().toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (isAVowelLetter(chars[i])
                    && !isDoubleVowelLetter(chars, i)
                    && !isELastCharacter(chars, i)) {
                syllables++;
            }
        }
        return syllables == 0 ? 1 : syllables;
    }

    private static boolean isDoubleVowelLetter(char[] chars, int i) {
        return (isAVowelLetter(chars[i]) && i != chars.length - 1) && (isAVowelLetter(chars[i + 1]) && chars[chars.length-1]!='E');
    }


    private static boolean isELastCharacter(char[] chars, int i) {
        return chars[i] == 'E' && i == chars.length - 1 && !(chars.length <= 3);
    }

    private static boolean isAVowelLetter(char chars) {
        return VOWEL_LETTERS.indexOf(chars) > -1;
    }


}
