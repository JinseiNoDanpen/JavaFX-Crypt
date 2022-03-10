package org.shinodanpen.main;

import java.util.Arrays;
import java.util.stream.IntStream;

import static org.shinodanpen.main.Main.Alphabet;

public class Encrypter {
    public static String encrypt(char[] input){
        StringBuilder result = new StringBuilder();
        int found;
        for (char c : input) {
            found = IntStream.range(0, Alphabet.length).filter(i -> Alphabet[i] == c).findFirst().orElse(-1);
            result.append(found+";");

        }
        return result.toString();
    }
}
