package sample.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import sample.Models.CustomComparator;
import sample.Models.Record;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Top10Controller implements Initializable {
    @FXML
    private ListView<Record> zoznam;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<Record> temp = FXCollections.observableArrayList();
        for (int i = 0; i < 10; i++){
            if (i < MenuController.records.size())
                temp.add(MenuController.records.get(i));
        }
        zoznam.setItems(temp);
    }
}

