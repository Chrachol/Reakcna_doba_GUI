package sample.Controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;


public class MenuController {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    public void play(){
        goToScene("Play.fxml", "GAME");
    }

    @FXML
    public void newPlayer(){
        goToScene("NewPlayer.fxml", "CREATE NEW PLAYER");
    }

    @FXML
    public void top10(){
        goToScene("Top10.fxml", "TOP 10");
    }

    @FXML
    public void quit(){
        Platform.exit();
    }

    private void goToScene(String fxmlName, String title){
        try {
            Stage stage = (Stage) anchorPane.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../FXMLs/" + fxmlName));
            Parent root = loader.load();

            Scene scene = new Scene(root);

            stage.setTitle(title);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
