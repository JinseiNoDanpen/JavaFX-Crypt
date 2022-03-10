package org.shinodanpen.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApplication extends Application {

    //In questa classe istanziamo la nostra finestra JavaFX e settiamo il controller.

    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("ui-panel.fxml"));
        Scene scene = new Scene((Parent)fxmlLoader.load());
        stage.setTitle("JavaFX Encrypt and Decrypt");
        stage.getIcons().add(new Image("https://i.imgur.com/9AQt0kx.png"));
        stage.setScene(scene);
        stage.show();
        UIController controller = (UIController)fxmlLoader.getController();
        controller.setStage(stage);
        stage.setOnCloseRequest((e) -> {
            try {
                this.stop();
            } catch (Exception var3) {
                System.out.println("Application closed.");
            }

            System.exit(0);
        });
    }
}