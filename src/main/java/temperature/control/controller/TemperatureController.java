package temperature.control.controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import jssc.SerialPortException;
import org.apache.commons.lang3.StringUtils;
import temperature.control.dict.Constants;
import temperature.control.dict.SystemOptions;
import temperature.control.entity.SingletonFactory;
import temperature.control.entity.settings.TemperatureOption;
import temperature.control.util.AlertFXUtil;
import temperature.control.view.NumericTextField;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class TemperatureController implements Initializable {
    @FXML
    public Button startWarming;

    @FXML
    public NumericTextField setLeftIronTemperature;

    @FXML
    public NumericTextField setRightIronTemperature;

    @FXML
    public ComboBox<String> systemOptionList;

    @FXML
    public RadioButton unevenDirectionOfMovement;

    @FXML
    public RadioButton evenDirectionOfMovement;

    @FXML
    public NumericTextField warmingValuesStatus;

    private TemperatureOption temperatureOption;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        systemOptionList = new ComboBox<String>();
        systemOptionList.setItems(FXCollections.observableArrayList(SystemOptions.OPTIONS));
    }

    @FXML
    public void evenRadioAction(ActionEvent actionEvent) {
        unevenDirectionOfMovement.setSelected(false);
        SingletonFactory.getTemperatureOption().setEvenDirectionOfMovement(true);
        SingletonFactory.getTemperatureOption().setControlPortAddressByMovment(SingletonFactory.getEvenMovement().getEvenControlPort());
    }

    @FXML
    public void unevenRadioAction(ActionEvent actionEvent) {
        evenDirectionOfMovement.setSelected(false);
        SingletonFactory.getTemperatureOption().setEvenDirectionOfMovement(false);
        SingletonFactory.getTemperatureOption().setControlPortAddressByMovment(SingletonFactory.getUnevenMovement().getUnevenControlPort());
    }

    @FXML
    public void beginWarming(ActionEvent actionEvent) throws Exception {
        temperatureOption = SingletonFactory.getTemperatureOption();
        if (!temperatureOption.isWarmingOn()) {
            List<String> errorList = new ArrayList<>();
            if (!StringUtils.isNotEmpty(setLeftIronTemperature.getText())
                    && !StringUtils.isNotEmpty(setRightIronTemperature.getText())) {
                errorList.add(Constants.ALERT_WARMING_VALUES_IS_EMPTY);
            }
            if (temperatureOption.getControlPortAddressByMovment() == null) {
                errorList.add(Constants.ALERT_CURRENT_SETTINGS_CONTROL_PORT_NOT_WORKING);
            }
            if (!evenDirectionOfMovement.isSelected() && !unevenDirectionOfMovement.isSelected()) {
                errorList.add(Constants.ALERT_MOVEMENT_IS_NOT_SET);
            }
            if (!errorList.isEmpty()) {
                createAlert(AlertFXUtil.prepareAlertMessage(errorList));
            } else {
                temperatureOption.setWarmingOn(true);
                try {
                    temperatureOption.getControlPortAddressByMovment().openPort();
                    temperatureOption.getControlPortAddressByMovment().setDTR(true);
                    temperatureOption.getControlPortAddressByMovment().setRTS(true);
                    temperatureOption.getControlPortAddressByMovment().closePort();
                } catch (SerialPortException e) {
                    e.printStackTrace();
                }
                if (!setLeftIronTemperature.getText().isEmpty()) {
                    temperatureOption.setInstallLeftTemperature(Double.valueOf(setLeftIronTemperature.getText()));
                }
                if (!setRightIronTemperature.getText().isEmpty()) {
                    temperatureOption.setRightTemperature(Double.valueOf(setRightIronTemperature.getText()));
                }
                if (evenDirectionOfMovement.isSelected()) {
                    temperatureOption.setEvenDirectionOfMovement(true);
                    temperatureOption.setControlPortAddressByMovment(SingletonFactory.getEvenMovement().getEvenControlPort());
                } else if (unevenDirectionOfMovement.isSelected()) {
                    temperatureOption.setEvenDirectionOfMovement(false);
                    temperatureOption.setControlPortAddressByMovment(SingletonFactory.getUnevenMovement().getUnevenControlPort());
                }
                startWarming.setText("Выключить нагрев");
            }
        } else {
            List<String> errorList = new ArrayList<>();
            if (temperatureOption.getControlPortAddressByMovment() == null) {
                errorList.add(Constants.ALERT_CURRENT_SETTINGS_CONTROL_PORT_NOT_WORKING);
            }
            if (!errorList.isEmpty()) {
                createAlert(AlertFXUtil.prepareAlertMessage(errorList));
            } else {
                temperatureOption.setWarmingOn(false);
                try {
                    temperatureOption.getControlPortAddressByMovment().openPort();
                    temperatureOption.getControlPortAddressByMovment().setDTR(false);
                    temperatureOption.getControlPortAddressByMovment().setRTS(false);
                    temperatureOption.getControlPortAddressByMovment().closePort();
                } catch (SerialPortException e) {
                    e.printStackTrace();
                }
                temperatureOption.setInstallLeftTemperature(null);
                temperatureOption.setInstallRightTemperature(null);
                startWarming.setText("Включить нагрев");
            }
        }
    }

    private void createAlert(String error) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("ПРОИЗОШЛА ОШИБКА!");
        alert.setHeaderText("Данное сообщение появляеться при наличии неточностей во введенной информации.");
        alert.setContentText(error);
        alert.showAndWait();
        alert = null;
    }
}
