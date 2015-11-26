package temperature.control.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

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

    public void saveNewReport(ActionEvent actionEvent) {

    }
}
