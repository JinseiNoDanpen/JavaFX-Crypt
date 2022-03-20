package org.shinodanpen.algorithm;

import javafx.application.Application;

import java.util.Arrays;
import java.util.List;

public class Main {

    private static Character[] AlphabetArray = new Character[]{
            '\\', '|', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '\'', 'ì',
            '!', '\"', '£', '$', '%', '&', '/', '(', ')', '=', '?', '^',
            'q', 'w', 'e', 'r', 't', 'y', 'u', 'i', 'o', 'p', 'è', '+',
            'Q', 'W', 'E', 'R', 'T', 'Y', 'U', 'I', 'O', 'P', 'é', '*',
            '€', '[', ']', 'a', 's', 'd', 'f', 'g', 'h', 'j', 'k', 'l', 'ò', 'à', 'ù',
            'A', 'S', 'D', 'F', 'G', 'H', 'J', 'K', 'L', 'ç', '°', '§', '@', '#',
            '<', 'z', 'x', 'c', 'v', 'b', 'n', 'm', ',', '.', '-',
            '>', 'Z', 'X', 'C', 'V', 'B', 'N', 'M', ';', ':', '_', ' '   };

    public static List<Character> Alphabet = Arrays.asList(AlphabetArray);


    public static void main(String[] args) {

        //Questo main usato in concomitanza con il MainApplication è una semplice ricorrenza di JavaFX che
        //stranamente non si avvia correttamente dal file .jar senza il MainApplication.

        //In ogni caso, in questa classe è presente la chiave hard-codata dell'algoritmo.
        //Si tratta di un array di char che contiene tutti i caratteri che è possibile scrivere senza effettuare
        //combinazioni di tasti eccessivamente complicate.
        Application.launch(MainApplication.class, args);


    }
}
