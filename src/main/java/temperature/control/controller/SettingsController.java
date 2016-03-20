package temperature.control.controller;


import com.dalsemi.onewire.OneWireException;
import com.dalsemi.onewire.adapter.DSPortAdapter;
import com.dalsemi.onewire.application.tag.TaggedDevice;
import com.dalsemi.onewire.container.OneWireContainer;
import com.dalsemi.onewire.container.TemperatureContainer;
import javafx.collections.FXCollections;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import jssc.SerialPortList;
import org.apache.commons.lang3.StringUtils;
import temperature.control.dict.Constants;
import temperature.control.entity.SingletonFactory;
import temperature.control.entity.adapter.GenericAdapter;
import temperature.control.entity.sensor.GlobalSensorList;
import temperature.control.entity.sensor.GlobalSensorMap;
import temperature.control.entity.settings.ApplicationSettings;
import temperature.control.util.DevicesUpdaterUtil;
import temperature.control.util.SettingsFileUtil;

import java.net.URL;
import java.util.*;


public class SettingsController implements Initializable {
    @FXML
    public Button updateAllButton;
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
    public ComboBox unevenPortList;
    @FXML
    public ComboBox evenPortList;
    @FXML
    public Button checkSensorByIdFromListButton;
    @FXML
    public Button checkSensorByIdButton;

    public GlobalSensorList globalSensorList;
    public GlobalSensorMap globalSensorMap;
    Map<String, String> choosenSensorsIdMap = new HashMap<>();
    private Object syncObj = new Object();
    private GenericAdapter defaultAdapter;
    @FXML
    private Service<Void> updateAllData = new Service<Void>() {
        @Override
        protected Task<Void> createTask() {
            return new Task<Void>() {
                @Override
                protected Void call() throws Exception {
                    updateAllButton.setDisable(true);
                    loggerInformation.appendText("Производиться обновление всей информации о датчиках и адаптерах... \n");
                    OneWireContainer oneWireContainer = null;
                    GlobalSensorMap globalSensorMap = SingletonFactory.getGlobalSensorMap();
                    GlobalSensorList globalSensorList = SingletonFactory.getGlobalSensorList();
                    globalSensorMap.clear();
                    globalSensorList.clear();
                    SettingsFileUtil.loadSettings();
                    try {
                        DevicesUpdaterUtil.setDefaultAdapter();
                        loggerInformation.appendText("Проверка и установка базового адаптера. \n");
                    } catch (OneWireException e) {
                        e.printStackTrace();
                    }
                    DevicesUpdaterUtil.setDefaultPortList();
                    loggerInformation.appendText("Установка списка портов. \n");
                    try {
                        loggerInformation.appendText("Поиск датчиков... \n");
                        for (Enumeration owd_enum = SingletonFactory.getGenericAdapter().getBaseAdapter().getAllDeviceContainers(); owd_enum.hasMoreElements(); ) {
                            oneWireContainer = (OneWireContainer) owd_enum.nextElement();
                            globalSensorMap.put(oneWireContainer.getAddressAsString(), new TaggedDevice(SingletonFactory.getGenericAdapter().getBaseAdapter(), oneWireContainer.getAddressAsString()));
                            globalSensorList.add(oneWireContainer.getAddressAsString());
                            System.out.println("Device Found: " + oneWireContainer.getAddressAsString());
                            loggerInformation.appendText("Обнаружен датчик: " + oneWireContainer.getAddressAsString() + "\n");
                        }
                    } catch (OneWireException e) {
                        e.printStackTrace();
                    }
                    updateAllButton.setDisable(false);
                    loggerInformation.appendText("Обновление успешно завершено. \n");
                    updateAllData.cancel();
                    return null;
                }
            };
        }
    };

    public SettingsController() {
        this.defaultAdapter = SingletonFactory.getGenericAdapter();
        this.globalSensorList = SingletonFactory.getGlobalSensorList();
        this.globalSensorMap = SingletonFactory.getGlobalSensorMap();
        checkSensorIdList = new ComboBox();
        evenLeftSensorIdList = checkSensorIdList;
        evenRightSensorIdList = checkSensorIdList;
        unevenLeftSensorIdList = checkSensorIdList;
        unevenRightSensorIdList = checkSensorIdList;
        temperatureLeftSensorIdList = checkSensorIdList;
        temperatureRightSensorIdList = checkSensorIdList;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loggerInformation.textProperty().addListener((observable, oldValue, newValue) -> {
            loggerInformation.setScrollTop(Double.MAX_VALUE);
        });
        List totalSerialPortList = Arrays.asList(SerialPortList.getPortNames());
        try {
            if (defaultAdapter.getBaseAdapter() != null) {
                adapterList.getItems().addAll(defaultAdapter.getBaseAdapter().getAdapterName());
                adapterPortList.getItems().add(defaultAdapter.getBaseAdapter().getPortName());
            }
        } catch (OneWireException e) {
            e.printStackTrace();
        }
        ApplicationSettings applicationSettings = SingletonFactory.getApplicationSettings();
        evenPortList.setItems(FXCollections.observableArrayList(totalSerialPortList));
        evenPortList.getSelectionModel().select(applicationSettings.getEvenControlPortAddress());

        unevenPortList.setItems(FXCollections.observableArrayList(totalSerialPortList));
        unevenPortList.getSelectionModel().select(applicationSettings.getUnevenControlPortAddress());

        checkSensorIdList.setItems(FXCollections.observableArrayList(globalSensorList));

        evenLeftSensorIdList.setItems(FXCollections.observableArrayList(globalSensorList));
        evenLeftSensorIdList.getSelectionModel().select((applicationSettings.getEvenLeftIronSensorAddress()));

        evenRightSensorIdList.setItems(FXCollections.observableArrayList(globalSensorList));
        evenRightSensorIdList.getSelectionModel().select((applicationSettings.getEvenRightIronSensorAddress()));

        unevenLeftSensorIdList.setItems(FXCollections.observableArrayList(globalSensorList));
        unevenLeftSensorIdList.getSelectionModel().select((applicationSettings.getEvenLeftIronSensorAddress()));

        unevenRightSensorIdList.setItems(FXCollections.observableArrayList(globalSensorList));
        unevenRightSensorIdList.getSelectionModel().select(applicationSettings.getUnevenRightIronSensorAddress());

        temperatureLeftSensorIdList.setItems(FXCollections.observableArrayList(globalSensorList));
        temperatureLeftSensorIdList.getSelectionModel().select(applicationSettings.getTemperatureLeftSensorAddress());

        temperatureRightSensorIdList.setItems(FXCollections.observableArrayList(globalSensorList));
        temperatureRightSensorIdList.getSelectionModel().select(applicationSettings.getTemperatureRightSensorAddress());
    }

    @FXML
    public void checkSensorStatus(ActionEvent actionEvent) {
        if (StringUtils.isNotBlank((CharSequence) checkSensorIdList.getSelectionModel().getSelectedItem())) {
            TaggedDevice taggedDevice = (TaggedDevice) globalSensorMap.get(checkSensorIdList.getSelectionModel().getSelectedItem().toString());
            DSPortAdapter l_adapter = null;
            TemperatureContainer l_container = null;
            synchronized (syncObj) {
                if (defaultAdapter.getBaseAdapter() == null)
                    return;
                l_adapter = defaultAdapter.getBaseAdapter();
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
                e.printStackTrace();
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

    @FXML
    public void checkSensorByIdTextfieldButton(ActionEvent actionEvent) {
        if (StringUtils.isNotBlank(sensorId.getText())
                && globalSensorMap.containsKey(sensorId.getText())) {
            DSPortAdapter l_adapter = null;
            TemperatureContainer l_container = null;
            synchronized (syncObj) {
                if (defaultAdapter.getBaseAdapter() == null)
                    return;
                l_adapter = defaultAdapter.getBaseAdapter();
                l_container = (TemperatureContainer) ((TaggedDevice) globalSensorMap.get(checkSensorIdList.getSelectionModel().getSelectedItem().toString())).getDeviceContainer();
            }
            try {
                l_adapter.beginExclusive(true);
                byte[] state = l_container.readDevice();
                l_container.doTemperatureConvert(state);
                if (StringUtils.isNotEmpty(checkedSensorStatus.getText())) {
                    checkedSensorStatus.setText("");
                }
                checkedSensorStatus.setText(Constants.SENSOR_CHECK_MESSAGE + Double.toString(l_container.getTemperature(state)));
                loggerInformation.appendText("Проверен датчик: " + sensorId.getText() + "Температура: " + Double.toString(l_container.getTemperature(state)));
            } catch (Exception e) {
                System.out.println(Constants.ERROR_READING_DEVICE + e.toString());
                loggerInformation.appendText(Constants.ERROR_READING_DEVICE);
            } finally {
                l_adapter.endExclusive();
            }
        } else {
            loggerInformation.appendText(Constants.WRONG_SENSOR_ID_INSERTED);
            checkedSensorStatus.setText(Constants.WRONG_SENSOR_ID_INSERTED);
        }
    }

    @FXML
    public void saveSensorsIdByMovement(ActionEvent actionEvent) {
        if (StringUtils.isNotEmpty((String) evenLeftSensorIdList.getSelectionModel().getSelectedItem())) {
            choosenSensorsIdMap.put(Constants.EVEN_LEFT_IRON_SENSOR, (String) evenLeftSensorIdList.getSelectionModel().getSelectedItem());
        }
        if (StringUtils.isNotEmpty((String) evenRightSensorIdList.getSelectionModel().getSelectedItem())) {
            choosenSensorsIdMap.put(Constants.EVEN_RIGHT_IRON_SENSOR, (String) evenRightSensorIdList.getSelectionModel().getSelectedItem());
        }
        if (StringUtils.isNotEmpty((String) unevenLeftSensorIdList.getSelectionModel().getSelectedItem())) {
            choosenSensorsIdMap.put(Constants.UNEVEN_LEFT_IRON_SENSOR, (String) unevenLeftSensorIdList.getSelectionModel().getSelectedItem());
        }
        if (StringUtils.isNotEmpty((String) unevenRightSensorIdList.getSelectionModel().getSelectedItem())) {
            choosenSensorsIdMap.put(Constants.UNEVEN_RIGHT_IRON_SENSOR, (String) unevenRightSensorIdList.getSelectionModel().getSelectedItem());
        }
        SettingsFileUtil.saveSensorsIdByMovement(choosenSensorsIdMap);
        loggerInformation.appendText("Адреса датчиков по направлению движения сохранены. \n");
        choosenSensorsIdMap.clear();
    }

    @FXML
    public void saveSensorIdByOuterTemperature(ActionEvent actionEvent) {
        if (StringUtils.isNotEmpty((String) temperatureLeftSensorIdList.getSelectionModel().getSelectedItem())) {
            choosenSensorsIdMap.put(Constants.TEMPERATURE_LEFT_SENSOR, (String) temperatureLeftSensorIdList.getSelectionModel().getSelectedItem());
        }
        if (StringUtils.isNotEmpty((String) temperatureRightSensorIdList.getSelectionModel().getSelectedItem())) {
            choosenSensorsIdMap.put(Constants.TEMPERATURE_RIGHT_SENSOR, (String) temperatureRightSensorIdList.getSelectionModel().getSelectedItem());
        }
        SettingsFileUtil.saveSensorsIdByMovement(choosenSensorsIdMap);
        loggerInformation.appendText(Constants.TEMPERATURE_SENSORS_ID_SAVED);
        choosenSensorsIdMap.clear();
    }

    @FXML
    public void saveControlPortsNumbers(ActionEvent actionEvent) {
        if (StringUtils.isNotEmpty((String) evenPortList.getSelectionModel().getSelectedItem())) {
            choosenSensorsIdMap.put(Constants.EVEN_CONTROL_PORT_ADDRESS, (String) evenPortList.getSelectionModel().getSelectedItem());
        }
        if (StringUtils.isNotEmpty((String) unevenPortList.getSelectionModel().getSelectedItem())) {
            choosenSensorsIdMap.put(Constants.UNEVEN_CONTROL_PORT_ADDRESS, (String) unevenPortList.getSelectionModel().getSelectedItem());
        }
        SettingsFileUtil.saveSensorsIdByMovement(choosenSensorsIdMap);
        loggerInformation.appendText(Constants.PORTS_ID_SAVED);
        choosenSensorsIdMap.clear();
    }

    @FXML
    public void updateAll(ActionEvent actionEvent) {
        updateAllData.restart();
    }

    @FXML
    public void сleanLogger(ActionEvent actionEvent) {
        loggerInformation.setText("");
    }

    @FXML
    public void saveAdapterAndPort(ActionEvent actionEvent) {
        if (StringUtils.isNotEmpty((String) adapterList.getSelectionModel().getSelectedItem())) {
            choosenSensorsIdMap.put(Constants.ADAPTER, (String) adapterList.getSelectionModel().getSelectedItem());
        }
        if (StringUtils.isNotEmpty((String) adapterPortList.getSelectionModel().getSelectedItem())) {
            choosenSensorsIdMap.put(Constants.ADAPTER_PORT, (String) adapterPortList.getSelectionModel().getSelectedItem());
        }
        loggerInformation.appendText("Адреса адаптера и портов сохранены. \n");
        choosenSensorsIdMap.clear();
    }
}
