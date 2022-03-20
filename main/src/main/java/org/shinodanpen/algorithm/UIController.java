//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.shinodanpen.algorithm;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.shinodanpen.algorithm.Main.Alphabet;

public class UIController {

    //Questo è il controller del programma JavaFX.
    // Qui sono contenute le variabili inerenti all'interfaccia grafica, assieme ai
    // metodi di cui fanno uso.

    @FXML
    private TextField inputDirectory, outputDirectory, keyField;
    @FXML
    private Button inputBrowseButton, encryptButton, decryptButton;
    @FXML
    private Alert alert;

    private File inputFile, outputFile;
    private Stage stage;

    private BufferedReader br;
    private BufferedWriter bw;

    private ArrayList<Character> AlphabetCopy;

    public UIController() {
    }

    @FXML
    public void showAlert(String testo) throws IOException {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                alert = new Alert(Alert.AlertType.CONFIRMATION, testo, ButtonType.OK);
                alert.show();
            }
        });
    }

    //Questo è il metodo invocato alla pressione del tasto di selezione del file da sottoporre all'algoritmo.
    //Semplicemente apre la finestra di dialogo per la selezione e rende attivi i pulsanti di azione.

    @FXML
    private void onInputBrowseButtonClick() {
        keyField.setText("");
        keyField.setEditable(true);
        FileChooser file = new FileChooser();
        file.setInitialDirectory(new File(System.getProperty("user.dir")));
        inputFile = file.showOpenDialog(this.stage);
        this.inputDirectory.setText(inputFile.getAbsolutePath());

        this.outputDirectory.setText(inputFile.getParent());
        if (!inputFile.getAbsolutePath().isBlank()) {
            this.encryptButton.setDisable(false);
            this.decryptButton.setDisable(false);
        }

    }

    //Questo è metodo invocato alla pressione del tasto per crittare.
    //Legge il file e lo copia in una stringa. Va a leggere la chiave inserita e dopo aver applicato la chiave all'alfabeto, esegue la crittazione.
    //Dopo di ciò, converte il numero di dimensione dello shift in una stringa esadecimale, che farà da effettiva chiave.
    @FXML
    private void onEncryptButtonClick(){

        String temp = readFromFile(inputFile);
        shift(Integer.parseInt(keyField.getText()));
        System.out.println("Chiave applicata.");

        writeToFile(new File(inputFile.getAbsolutePath() + ".ENCRYPTED"), Encrypter.encrypt(temp.toCharArray(), AlphabetCopy));
        System.out.println("Operazione completata.");

        AlphabetCopy.clear();
        AlphabetCopy = null;

        keyField.setEditable(false);
        keyField.setText(toHex(Integer.parseInt(keyField.getText())));
        System.out.println("La chiave è stata crittata.");
        try {
            showAlert("OPERAZIONE COMPLETATA. SALVARE LA CHIAVE IN UN POSTO SICURO.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Semplice metodo invocato alla pressione del tasto "PULISCI CHIAVE", cancella la chiave e rende il field modificabile.
    @FXML
    private void onClearKeyButtonClick(){
        keyField.setEditable(true);
        keyField.setText("");
    }

    //Metodo invocato alla pressione del tasto per decrittare.
    //Esegue il processo inverso della crittazione, ovvero tramite la chiave esadecimale, risale al numero di volte per cui shiftare l'alfabeto.
    //Dopo questo, esegue l'algoritmo in modo inverso, creando un nuovo file.
    @FXML
    private void onDecryptButtonClick(){

        int temp = fromHex(keyField.getText());
        shift(temp);
        System.out.println("Chiave applicata.");

        writeToFile(new File(inputFile.getAbsolutePath() + ".DECRYPTED"), Decrypter.decrypt(readFromFile(inputFile), AlphabetCopy));
        System.out.println("Operazione completata.");
        try {
            showAlert("DECRITTAZIONE COMPLETATA.");
        } catch (IOException e) {
            e.printStackTrace();
        }

        AlphabetCopy.clear();
        AlphabetCopy = null;
    }

    //Metodo per leggere riga per riga un file di testo, e copiarlo in una stringa tramite l'uso di StringBuilder.
    public String readFromFile(File inputFile){
        StringBuilder sb = new StringBuilder();
        String temp;
        try {
            br = new BufferedReader(new FileReader(inputFile, StandardCharsets.UTF_8));
            while ((temp = br.readLine()) != null){
                sb.append(temp);
                sb.append('\n');
            }
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return sb.toString();
    }

    //Metodo per scrivere su file dati come parametro il file in cui scrivere e il contenuto da scrivere.
    public void writeToFile(File outputFile, String output){
        try {
            bw = new BufferedWriter(new FileWriter(outputFile, StandardCharsets.UTF_8));
            bw.write(output);
            bw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Metodo che effettua lo shift della lista Alphabet, tramite l'uso della libreria Collections di Java.
    @FXML
    private void shift(int quantity){
        AlphabetCopy = new ArrayList<>(Alphabet);
        Collections.rotate(AlphabetCopy, quantity);

        System.out.println("lista post shift:");
        System.out.println(AlphabetCopy);
    }

    //Metodo per ottenere rapidamente la conversione in stringa esadecimale di un int con segno, tramite la classe wrapper Integer.
    private String toHex(int key){

        return Integer.toHexString(key);
    }

    //Metodo per ottenere un int segnato a partire da una stringa esadecimale, tramite la classe wrapper Long.
    private int fromHex(String key){

        return (int) Long.parseLong(key, 16);
    }

    //Convenzione di JavaFX
    public void setStage(Stage s) {
        this.stage = s;
    }
}
