package temperature.control.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {


    public MainController() {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    public void exitApplication(ActionEvent event) {
        Platform.exit();
    }
}
