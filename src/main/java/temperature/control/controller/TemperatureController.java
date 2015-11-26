package temperature.control.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class TemperatureController {
    @FXML
    private Button startWarming;

    @FXML
    private TextField setLeftIronTemperature;

    @FXML
    private TextField setRightIronTemperature;

    @FXML
    private ComboBox systemOptionList;

    @FXML
    private RadioButton unevenDirectionOfMovement;

    @FXML
    private RadioButton evenDirectionOfMovement;

    @FXML
    private TextField warmingValuesStatus;

    public void beginWarming(ActionEvent actionEvent) {
        System.out.println("Запуск нагрева:");
    }
}
