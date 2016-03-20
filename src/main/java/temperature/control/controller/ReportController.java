package temperature.control.controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.apache.commons.lang3.StringUtils;
import temperature.control.dict.Constants;
import temperature.control.dict.SystemOptions;
import temperature.control.dict.Systems;
import temperature.control.entity.SingletonFactory;
import temperature.control.entity.settings.TemperatureOption;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

public class ReportController implements Initializable {

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

    @FXML
    public TextArea loggerInfo;

    public ReportController() {
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

    public void automaticReportInformationInsertion(ActionEvent actionEvent) {
        loggerInfo.appendText(Constants.AUTOFINDING_DATA);
        TemperatureOption temperatureOption = SingletonFactory.getTemperatureOption();
        if (temperatureOption.getInstallLeftTemperature() != null
                && StringUtils.isNotEmpty(temperatureOption.getInstallLeftTemperature().toString())) {
            leftIronImitationTemperature.setText(temperatureOption.getInstallLeftTemperature().toString());
        }
        if (temperatureOption.getInstallRightTemperature() != null
                && StringUtils.isNotEmpty(temperatureOption.getInstallRightTemperature().toString())) {
            rightIronImitationTemperature.setText(temperatureOption.getInstallRightTemperature().toString());
        }
        if (temperatureOption.getLeftTemperature() != null
                && StringUtils.isNotEmpty(temperatureOption.getLeftTemperature().toString())) {
            leftIronTemperature.setText(temperatureOption.getLeftTemperature().toString());
        }
        if (temperatureOption.getRightTemperature() != null
                && StringUtils.isNotEmpty(temperatureOption.getRightTemperature().toString())) {
            rightIronTemperature.setText(temperatureOption.getRightTemperature().toString());
        }
        systemOptionValue.getSelectionModel().select(temperatureOption.getSystemOption());
        if (temperatureOption.isEvenDirectionOfMovement()) {
            evenDirectionOfMovement.setSelected(true);
            unevenDirectionOfMovement.setSelected(false);
        } else {
            unevenDirectionOfMovement.setSelected(true);
            evenDirectionOfMovement.setSelected(false);
        }
        if (temperatureOption.getOuterTemperature() != null && StringUtils.isNoneEmpty(temperatureOption.getOuterTemperature().toString())) {
            outsideTemperatureValue.setText(temperatureOption.getOuterTemperature().toString());
        }
        Date reportDate = new Date();
        dateValue.setText(new SimpleDateFormat("dd.MM.yyyy").format(reportDate));
        loggerInfo.appendText(Constants.AUTOFINDING_DATA_IS_ADDED);
    }

    @FXML
    public void сleanLogger(ActionEvent actionEvent) {
        loggerInfo.setText("");
    }
}
