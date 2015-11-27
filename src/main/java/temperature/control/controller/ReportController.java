package temperature.control.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import java.net.URL;
import java.util.ResourceBundle;

public class ReportController implements Initializable{

    @FXML
    public Button addNewReport;

    @FXML
    public ComboBox systemListValue;

    @FXML
    public ComboBox systemOptionValue;

    @FXML
    public TextField dateValue;

    @FXML
    public TextField leftWarmingValue;

    @FXML
    public TextField rightWarmingValue;

    @FXML
    public TextField engeneerName;

    @FXML
    public TextField currentShowedLeftValue;

    @FXML
    public TextField currentShowedRightValue;

    @FXML
    public TextField outsideTemperatureValue;

    @FXML
    public RadioButton unevenDirectionOfMovement;

    @FXML
    public RadioButton evenDirectionOfMovement;

    @FXML
    public void saveNewReport(ActionEvent actionEvent) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
