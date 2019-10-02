package sample.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

import static javafx.scene.layout.BorderStrokeStyle.*;

public class PlayController implements Initializable {
    @FXML
    private Button prve;
    @FXML
    private Button druhe;
    private double cas;
    private double before;
    private double after;
    @FXML
    public void play() {
        prve.setVisible(false);
        druhe.setVisible(true);
        druhe.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
        int time = (int) (Math.random() * 2500 + 500);
        druhe.setText("Cakajte");
        try {
            TimeUnit.MILLISECONDS.sleep(time);
            druhe.setText("START!");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        before = System.currentTimeMillis();

    }
    public void play2(){
            after = System.currentTimeMillis();
            cas=after-before;

            if(cas <= 0)
                System.out.println("PODVADZAL SI");
            else
                System.out.println("Cas: " + (cas) +"ms");
        }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        druhe.setVisible(false);
        prve.setText("Stlacte ak ste pripraveny");
        prve.setBackground(new Background(new BackgroundFill(Color.PINK,CornerRadii.EMPTY,Insets.EMPTY)));
    }
}
