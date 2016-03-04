package temperature.control.controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import temperature.control.dict.Constants;
import temperature.control.dict.SystemOptions;
import temperature.control.entity.SingletonFactory;
import temperature.control.entity.settings.TemperatureOption;
import temperature.control.view.NumericTextField;

import java.net.URL;
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
        SingletonFactory.getUnevenMovement().setActive(false);
        unevenDirectionOfMovement.setSelected(false);
        SingletonFactory.getEvenMovement().setActive(true);
    }

    @FXML
    public void unevenRadioAction(ActionEvent actionEvent) {
        SingletonFactory.getUnevenMovement().setActive(true);
        evenDirectionOfMovement.setSelected(false);
        SingletonFactory.getEvenMovement().setActive(false);
    }

    @FXML
    public void beginWarming(ActionEvent actionEvent) {
        temperatureOption.setLeftTemperature(setLeftIronTemperature.getText());
        temperatureOption.setRightTemperature(setRightIronTemperature.getText());
        if(evenDirectionOfMovement.isSelected()){
            temperatureOption.setDirectionOfMotion(Constants.EVEN);
        }else if(unevenDirectionOfMovement.isSelected()){
            temperatureOption.setDirectionOfMotion(Constants.UNEVEN);
        }
    }
}
