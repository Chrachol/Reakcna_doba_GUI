package sample.Controllers;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sample.Models.CustomComparator;
import sample.Models.Record;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class MenuController implements Initializable {

    @FXML
    private AnchorPane anchorPane;

    public static ObservableList<Record> records;

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

    private void importRecords() throws IOException {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("D:\\Projects\\Java\\Reakcna_doba_GUI\\src\\sample\\Files\\Player_ratings.txt"));
            String line;
            while ((line = br.readLine()) != null){
                String[] tempArray = line.split(":");
                records.add(new Record(tempArray[1], Double.parseDouble(tempArray[0])));
            }
            records.sort(new CustomComparator());
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            br.close();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        records = FXCollections.observableArrayList();
        try {
            importRecords();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
