package sample.Controllers;

import ENUMs.State;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import sample.Models.CustomComparator;
import sample.Models.Record;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static ENUMs.State.PLAY;

public class PlayController implements Initializable {

    @FXML
    private Label playArea;

    @FXML
    private AnchorPane anchorPane;

    private State state;
    private long before, after;
    private boolean cheated;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        playArea.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE,CornerRadii.EMPTY,Insets.EMPTY)));

        state = State.LOADING;
        cheated = false;

        playArea.setOnMouseClicked(e -> {
            switch (state){
                case LOADING:
                    state = PLAY;
                    long time = (long) (Math.random() * 2500 + 500);
                    new Thread(()->{
                        Platform.runLater(() -> {
                            playArea.setText("Pripravit!");
                            playArea.setBackground(new Background(new BackgroundFill(Color.ORANGE,CornerRadii.EMPTY,Insets.EMPTY)));
                        });
                        sleep(time);
                        Platform.runLater(() -> {
                            if (cheated == false){
                                playArea.setBackground(new Background(new BackgroundFill(Color.GREEN,CornerRadii.EMPTY,Insets.EMPTY)));
                                playArea.setText("START");
                                before = System.currentTimeMillis();
                            }
                        });
                    }).start();
                    break;
                case PLAY:
                    state = State.MENU;
                    after = System.currentTimeMillis();
                    if (after - before > 999999){
                        playArea.setBackground(new Background(new BackgroundFill(Color.RED,CornerRadii.EMPTY,Insets.EMPTY)));
                        playArea.setText("PODVADZAL SI KLINI RE ODCHOD DO MENU");
                        cheated = true;
                    }else {
                        playArea.setText((after - before) + "ms. KLIKNI PRE ODCHOD DO MENU");
                        MenuController.records.add(new Record(NewPlayerController.menoHraca, Double.parseDouble(String.valueOf((after - before)))));
                        write();
                        showAroundMe(new Record(NewPlayerController.menoHraca, Double.parseDouble(String.valueOf((after - before)))));
                    }
                    break;
                case MENU:
                    goToScene("Menu.fxml", "Menu");
                    break;
            }
        });
    }

    private void sleep (long time){
        try {
            Thread.sleep(time);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
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

    private void write(){
        BufferedWriter bw= null;
        try {
            MenuController.records.sort(new CustomComparator());
            bw = new BufferedWriter(new FileWriter(System.getProperty("user.dir") + "/src/sample/Files/Player_ratings.txt"));
            for(Record record : MenuController.records){
                if (record!=null){
                    bw.write(record.getTime() + ":" + record.getName() + "\n");
                }
            }
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showAroundMe(Record record){
        int index;
        ObservableList<Record> temp = FXCollections.observableArrayList();
        MenuController.records.sort(new CustomComparator());

        for(Record item : MenuController.records){
            temp.add(item);
        }

        for (index = 0; index < temp.size(); index++) {
            if (temp.get(index).getName().equals(record.getName()))
                if (temp.get(index).getTime().equals(record.getTime()))
                    break;
        }

        ArrayList<Record> topList = new ArrayList<>();

        if(temp.size() <= 10){
            for (Record item : temp) {
                topList.add(item);
            }
        }else if(index - 5 < 0){
            for (int i = 0; i <= index +5; i++){
                if (i < temp.size())
                    topList.add(temp.get(i));
            }
        }else if(index + 5 > temp.size()){
            for (int i = index - 5; i < temp.size(); i++){
                topList.add(temp.get(i));
            }
        }else {
            for (int i = index - 5; i <= index + 5; i++){
                topList.add(temp.get(i));
            }
        }

        showAlert(topList);
    }

    private void showAlert(ArrayList<Record> list){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Scoreboard");
        alert.setHeaderText(null);
        alert.setContentText(getStringBuilderText(list));
        alert.showAndWait();
    }

    private String getStringBuilderText(ArrayList<Record> list){
        StringBuilder sb = new StringBuilder();
        for (Record record : list){
            sb.append(record.toString());
            sb.append("\n");
        }
        return sb.toString();
    }
}
