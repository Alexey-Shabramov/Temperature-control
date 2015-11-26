package temperature.control.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import temperature.control.dict.SystemOptions;

import java.net.URL;
import java.util.ResourceBundle;

public class TemperatureController implements Initializable{
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

    @FXML
    public void beginWarming(ActionEvent actionEvent) {
        System.out.println("Запуск нагрева:");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
