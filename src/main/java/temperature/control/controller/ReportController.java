package temperature.control.controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import org.apache.commons.lang3.StringUtils;
import temperature.control.dict.SystemOptions;
import temperature.control.dict.Systems;
import temperature.control.entity.SingletonFactory;
import temperature.control.entity.settings.TemperatureOption;

import java.net.URL;
import java.util.ResourceBundle;

public class ReportController implements Initializable{

    @FXML
    public Button addNewReport;

    @FXML
    public ComboBox<String> systemListValue;

    @FXML
    public ComboBox<String> systemOptionValue;

    @FXML
    public TextField dateValue;

    @FXML
    public TextField leftIronImitationTemperature;

    @FXML
    public TextField rightIronImitationTemperature;

    @FXML
    public TextField engeneerName;

    @FXML
    public TextField leftIronTemperature;

    @FXML
    public TextField rightIronTemperature;

    @FXML
    public TextField outsideTemperatureValue;

    @FXML
    public RadioButton unevenDirectionOfMovement;

    @FXML
    public RadioButton evenDirectionOfMovement;

    public ReportController(){
        systemListValue = new ComboBox<String>();
        systemOptionValue = new ComboBox<String>();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        systemOptionValue.setItems(FXCollections.observableArrayList(SystemOptions.OPTIONS));
        systemListValue.setItems(FXCollections.observableArrayList(Systems.SYSTEMS));
    }

    @FXML
    public void saveNewReport(ActionEvent actionEvent) {

    }

    @FXML
    public void automaticReportInformationInsertion(ActionEvent actionEvent) {
        TemperatureOption temperatureOption = SingletonFactory.getTemperatureOption();
        if (StringUtils.isNotEmpty(temperatureOption.getInstallLeftTemperature().toString())) {
            leftIronImitationTemperature.setText(temperatureOption.getInstallLeftTemperature().toString());
        }
        if (StringUtils.isNotEmpty(temperatureOption.getInstallRightTemperature().toString())) {
            rightIronImitationTemperature.setText(temperatureOption.getInstallRightTemperature().toString());
        }
        systemOptionValue.getSelectionModel().select(temperatureOption.getSystemOption());
        if (temperatureOption.isEvenDirectionOfMovement()) {
            evenDirectionOfMovement.setSelected(true);
            unevenDirectionOfMovement.setSelected(false);
        } else {
            unevenDirectionOfMovement.setSelected(true);
            evenDirectionOfMovement.setSelected(false);
        }
    }
}
