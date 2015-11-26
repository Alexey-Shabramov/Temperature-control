package temperature.control.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import temperature.control.dict.SystemOptions;
import temperature.control.dict.Systems;

import java.net.URL;
import java.util.ResourceBundle;

public class ReportController {

    @FXML
    private Button addNewReport;

    @FXML
    private ComboBox systemListValue;

    @FXML
    private ComboBox systemOptionValue;

    @FXML
    private TextField dateValue;

    @FXML
    private TextField leftWarmingValue;

    @FXML
    private TextField rightWarmingValue;

    @FXML
    private TextField engeneerName;

    @FXML
    private TextField currentShowedLeftValue;

    @FXML
    private TextField currentShowedRightValue;

    @FXML
    private TextField outsideTemperatureValue;

    @FXML
    private RadioButton unevenDirectionOfMovement;

    @FXML
    private RadioButton evenDirectionOfMovement;

    @FXML
    public void saveNewReport(ActionEvent actionEvent) {

    }

}
