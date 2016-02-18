package temperature.control.controller;


import com.dalsemi.onewire.OneWireException;
import com.dalsemi.onewire.adapter.DSPortAdapter;
import com.dalsemi.onewire.application.tag.TaggedDevice;
import com.dalsemi.onewire.container.TemperatureContainer;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.apache.commons.lang3.StringUtils;
import temperature.control.entity.GenericAdapter;
import temperature.control.entity.GlobalSensorList;
import temperature.control.entity.GlobalSensorMap;
import temperature.control.singleton.AdapterModule;
import temperature.control.singleton.GlobalSensorListModule;
import temperature.control.singleton.GlobalSensorMapModule;

import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;


public class SettingsController implements Initializable {

    @FXML
    public TextArea loggerInformation;
    @FXML
    public TextField sensorId;
    @FXML
    public ComboBox checkSensorIdList;
    @FXML
    public ComboBox evenLeftSensorIdList;
    @FXML
    public ComboBox unevenLeftSensorIdList;
    @FXML
    public ComboBox evenRightSensorIdList;
    @FXML
    public ComboBox unevenRightSensorIdList;
    @FXML
    public ComboBox temperatureLeftSensorIdList;
    @FXML
    public ComboBox temperatureRightSensorIdList;
    @FXML
    public ComboBox adapterPortList;
    @FXML
    public ComboBox adapterList;
    @FXML
    public TextArea checkedSensorStatus;
    @FXML
    public Button checkSensorByIdFromListButton;
    @FXML
    public Button checkSensorByIdButton;
    public GlobalSensorList globalSensorList;
    public GlobalSensorMap globalSensorMap;
    private Object syncObj = new Object();
    private GenericAdapter defaultAdapter;
    private Properties properties;

    public SettingsController() {
        this.defaultAdapter = AdapterModule.getInstance();
        this.globalSensorList = GlobalSensorListModule.getInstance();
        this.globalSensorMap = GlobalSensorMapModule.getInstance();
        checkSensorIdList = new ComboBox();
        evenLeftSensorIdList = checkSensorIdList;
        evenRightSensorIdList = checkSensorIdList;
        unevenLeftSensorIdList = checkSensorIdList;
        unevenRightSensorIdList = checkSensorIdList;
        temperatureLeftSensorIdList = checkSensorIdList;
        temperatureRightSensorIdList = checkSensorIdList;
        properties = new Properties();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        adapterList.getItems().addAll(defaultAdapter.getBaseAdapter().getAdapterName());
        try {
            adapterPortList.getItems().add(defaultAdapter.getBaseAdapter().getPortName());
        } catch (OneWireException e) {
            e.printStackTrace();
        }
        checkSensorIdList.setItems(FXCollections.observableArrayList(globalSensorList));
        evenLeftSensorIdList.setItems(FXCollections.observableArrayList(globalSensorList));
        evenRightSensorIdList.setItems(FXCollections.observableArrayList(globalSensorList));
        unevenLeftSensorIdList.setItems(FXCollections.observableArrayList(globalSensorList));
        unevenRightSensorIdList.setItems(FXCollections.observableArrayList(globalSensorList));
        temperatureLeftSensorIdList.setItems(FXCollections.observableArrayList(globalSensorList));
        temperatureRightSensorIdList.setItems(FXCollections.observableArrayList(globalSensorList));
    }

    @FXML
    public void checkSensorStatus(ActionEvent actionEvent) {
        if (StringUtils.isNotBlank((CharSequence) checkSensorIdList.getSelectionModel().getSelectedItem())) {
            TaggedDevice taggedDevice = (TaggedDevice) GlobalSensorMapModule.getInstance().get(checkSensorIdList.getSelectionModel().getSelectedItem().toString());
            DSPortAdapter l_adapter = null;
            TemperatureContainer l_container = null;
            synchronized (syncObj) {
                if (AdapterModule.getInstance().getBaseAdapter() == null)
                    return;
                l_adapter = AdapterModule.getInstance().getBaseAdapter();
                l_container = (TemperatureContainer) taggedDevice.getDeviceContainer();
            }
            try {
                l_adapter.beginExclusive(true);
                byte[] state = l_container.readDevice();
                l_container.doTemperatureConvert(state);
                if (StringUtils.isNotEmpty(checkedSensorStatus.getText())) {
                    checkedSensorStatus.setText("");
                }
                checkedSensorStatus.setText("Статус: Доступен;  Проверка: Исправен;  Температура: " + Double.toString(l_container.getTemperature(state)));
                loggerInformation.appendText("Проверен датчик: " + checkSensorIdList.getSelectionModel().getSelectedItem().toString() + ";  Температура: " + Double.toString(l_container.getTemperature(state)) + "\n");
            } catch (Exception e) {
                System.out.println("Error reading device!" + e.toString());
                loggerInformation.appendText("Error reading device! ! \n");
                checkedSensorStatus.setText("Ошибка при чтении с адаптера!");
            } finally {
                l_adapter.endExclusive();
            }
        } else {
            loggerInformation.appendText("Вы ввели пустое значение! \n");
            checkedSensorStatus.setText("Вы ввели пустое значение!");

        }
    }

    public void getTemperatureFromSensor() {

    }

    @FXML
    public void checkSensorByIdTextfieldButton(ActionEvent actionEvent) {
        if (StringUtils.isNotBlank(sensorId.getText())
                && GlobalSensorMapModule.getInstance().containsKey(sensorId.getText())) {
            TaggedDevice taggedDevice = (TaggedDevice) GlobalSensorMapModule.getInstance().get(checkSensorIdList.getSelectionModel().getSelectedItem().toString());
            DSPortAdapter l_adapter = null;
            TemperatureContainer l_container = null;
            synchronized (syncObj) {
                if (AdapterModule.getInstance().getBaseAdapter() == null)
                    return;
                l_adapter = AdapterModule.getInstance().getBaseAdapter();
                l_container = (TemperatureContainer) taggedDevice.getDeviceContainer();
            }
            try {
                l_adapter.beginExclusive(true);
                byte[] state = l_container.readDevice();
                l_container.doTemperatureConvert(state);
                if (StringUtils.isNotEmpty(checkedSensorStatus.getText())) {
                    checkedSensorStatus.setText("");
                }
                checkedSensorStatus.setText("Статус: Доступен;  Проверка: Исправен;  Температура: " + Double.toString(l_container.getTemperature(state)));
                loggerInformation.appendText("Проверен датчик: " + sensorId.getText() + "Температура: " + Double.toString(l_container.getTemperature(state)));
            } catch (Exception e) {
                System.out.println("Error reading device!" + e.toString());
                loggerInformation.appendText("Error reading device! ! \n");

            } finally {
                l_adapter.endExclusive();
            }
        } else {
            loggerInformation.appendText("Вы ввели неверный ID или такого датчика не существует! \n");
            checkedSensorStatus.setText("Вы ввели неверный ID или такого датчика не существует! \n");

        }
    }
}
