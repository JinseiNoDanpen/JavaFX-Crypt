package org.shinodanpen.algorithm;

import java.util.ArrayList;
import java.util.List;

//In questo metodo eseguiamo il processo inverso della crittazione, data in input una stringa, estrapola il corrispondente carattere
//in base al suo indice nella lista dell'alfabeto, separandoli da punti e virgola, e inserendoli in un array di stringhe.
//Questo array di stringhe viene poi utilizzato per effettuare la decrittazione.

public class Decrypter {
    public static String decrypt(String input, ArrayList<Character> alphabet){
        StringBuilder result = new StringBuilder();
        String[] temp = input.split(";");

        int[] string = new int[temp.length];
        int i = 0;
        for(String s : temp){
            try{
                string[i] = Integer.parseInt(s);
                i++;
            }catch (NumberFormatException e){
                continue;
            }

        }

        for(int c : string){
            if(c == -1){
                result.append(' ');
                break;
            }
            result.append(alphabet.get(c));
        }
        return result.toString();
    }
}
