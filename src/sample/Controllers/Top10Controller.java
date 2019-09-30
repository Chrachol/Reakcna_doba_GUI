package sample.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import sample.Models.Record;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Top10Controller implements Initializable {
    @FXML
    private ListView<Record> zoznam;

    private ObservableList<Record>list= FXCollections.observableArrayList();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            ImportRecords();
        } catch (IOException e) {
            e.printStackTrace();
        }
        zoznam.setItems(list);
    }
    public void ImportRecords() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("F:\\skola\\Reakcna_doba_GUI\\src\\sample\\Files\\Player_ratings.txt"));
        try {
            String line;
            while ((line = br.readLine()) != null){
                String[] tempArray = line.split(":");
                list.add(new Record(tempArray[1], Double.parseDouble(tempArray[0])));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            br.close();
        }
    }
}

