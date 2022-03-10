package org.shinodanpen.main;

import static org.shinodanpen.main.Main.Alphabet;

public class Decrypter {
    public static String decrypt(String input){
        StringBuilder result = new StringBuilder();
        String[] temp = input.split(";");

        int[] string = new int[temp.length];
        int i = 0;
        for(String s : temp){
            string[i] = Integer.parseInt(s);
            i++;
        }

        for(int c : string){
            result.append(Alphabet[c]);
        }
        return result.toString();
    }
}
