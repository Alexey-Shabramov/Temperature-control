package temperature.control.controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
    public Button startWarming;

    @FXML
    public TextField setLeftIronTemperature;

    @FXML
    public TextField setRightIronTemperature;

    @FXML
    public ComboBox<String> systemOptionList;

    @FXML
    public RadioButton unevenDirectionOfMovement;

    @FXML
    public RadioButton evenDirectionOfMovement;

    @FXML
    public TextField warmingValuesStatus;

    @FXML
    public void beginWarming(ActionEvent actionEvent) {
        System.out.println("Запуск нагрева:");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> systemOptionsList = FXCollections.observableArrayList(SystemOptions.OPTIONS);
        systemOptionList = new ComboBox();
        systemOptionList.getItems().setAll("Тест");
        systemOptionList.setValue("A");
        systemOptionsList.add("Tet");
        systemOptionList.setVisibleRowCount(4);
    }
}
