package sample.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class NewPlayerController {

    @FXML
    private TextField playerName;
    public static String menoHraca;
    @FXML
    public void submit(){
        try {
            menoHraca=playerName.getText();
            Stage stage = (Stage) playerName.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../FXMLs/Menu.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);

            stage.setTitle("MENU");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
