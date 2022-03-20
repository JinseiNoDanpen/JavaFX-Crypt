package org.shinodanpen.algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Encrypter {

    //In questo metodo, dato in input un array di char, che nel nostro caso è la conversione ottenuta dalla stringa in cui è stato copiato
    //il contenuto del file, sostituisce a ogni carattere individuato, il suo corrispondente indice nella lista, separando ogni carattere con un punto e virgola.

    public static String encrypt(char[] input, ArrayList<Character> alphabet){
        StringBuilder result = new StringBuilder();
        int found;
        for (char c : input) {
            found = IntStream.range(0, alphabet.size())
                    .filter(i -> alphabet.get(i).equals(c))
                    .findFirst()
                    .orElse(-1);
            result.append(found+";");
            if(found == -1){
                System.out.println(c);
            }


        }
        return result.toString();
    }
}
