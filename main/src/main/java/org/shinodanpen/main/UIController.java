//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.shinodanpen.main;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

import static org.shinodanpen.main.Decrypter.decrypt;
import static org.shinodanpen.main.Encrypter.encrypt;

public class UIController {

    //Questo è il controller del programma JavaFX.
    // Qui sono contenute le variabili inerenti all'interfaccia grafica, assieme ai
    // metodi di cui fanno uso.

    @FXML
    private TextField inputDirectory, outputDirectory;
    @FXML
    private Button inputBrowseButton, encryptButton, decryptButton;

    private File inputFile, outputFile;
    private Stage stage;

    private BufferedReader br;
    private BufferedWriter bw;

    public UIController() {
    }

    @FXML
    private void onInputBrowseButtonClick() {
        FileChooser file = new FileChooser();
        file.setInitialDirectory(new File(System.getProperty("user.dir")));
        inputFile = file.showOpenDialog(this.stage);
        this.inputDirectory.setText(inputFile.getAbsolutePath());

        this.outputDirectory.setText(inputFile.getAbsolutePath());
        if (!inputFile.getAbsolutePath().isBlank()) {
            this.encryptButton.setDisable(false);
            this.decryptButton.setDisable(false);
        }

    }

    @FXML
    private void onEncryptButtonClick(){

        String temp = readFromFile(inputFile);

        writeToFile(new File(inputFile.getAbsolutePath() + ".ENCRYPTED"), Encrypter.encrypt(temp.toCharArray()));
        System.out.println("Successfully encrypted.");
    }

    @FXML
    private void onDecryptButtonClick(){
        writeToFile(new File(inputFile.getAbsolutePath() + ".DECRYPTED"), Decrypter.decrypt(readFromFile(inputFile)));
        System.out.println("Successfully decrypted.");
    }

    public String readFromFile(File inputFile){
        StringBuilder sb = new StringBuilder();
        String temp;
        try {
            br = new BufferedReader(new FileReader(inputFile));
            while ((temp = br.readLine()) != null){
                sb.append(temp);
            }
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return sb.toString();
    }

    public void writeToFile(File outputFile, String output){
        StringBuilder sb = new StringBuilder();
        try {
            bw = new BufferedWriter(new FileWriter(outputFile));
            bw.write(output);
            bw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setStage(Stage s) {
        this.stage = s;
    }
}
