package temperature.control.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class TemperatureController {

    @FXML
    private Button startWarming;

    @FXML
    private TextField setLeftTemperature;

    @FXML
    private TextField setRightTemperature;

    public void beginWarming(ActionEvent actionEvent) {
        System.out.println("Запуск нагрева:");
    }
}
