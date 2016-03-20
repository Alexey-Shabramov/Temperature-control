package temperature.control.controller;

import com.dalsemi.onewire.OneWireException;
import com.dalsemi.onewire.adapter.DSPortAdapter;
import com.dalsemi.onewire.adapter.OneWireIOException;
import com.dalsemi.onewire.application.tag.TaggedDevice;
import com.dalsemi.onewire.container.TemperatureContainer;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.chart.LineChart;
import javafx.scene.control.TextField;
import org.apache.commons.lang3.StringUtils;
import temperature.control.dict.Constants;
import temperature.control.entity.SingletonFactory;
import temperature.control.entity.settings.TemperatureOption;

import java.net.URL;
import java.util.ResourceBundle;

public class SensorController implements Initializable {
    @FXML
    public TextField leftIronTemperature;
    @FXML
    public TextField rightIronTemperature;
    @FXML
    public TextField firstOutsideTemperatureSensorStatus;
    @FXML
    public TextField secondOutsideTemperatureSensorStatus;
    @FXML
    public TextField thirdIronSensorStatus;
    @FXML
    public TextField fourhtIronSensorStatus;
    @FXML
    public TextField firstIronSensorStatus;
    @FXML
    public TextField wirePortStatus;
    @FXML
    public TextField secondIronSensorStatus;
    @FXML
    public TextField outsideTemperatureValue;
    @FXML
    public LineChart<Number, Number> leftIronAreaChart;
    @FXML
    public LineChart<Number, Number> rightIronAreaChart;

    private TemperatureOption temperatureOption;

    private Object firstSyncObj = new Object();
    private Object secondSyncObj = new Object();
    private Object thirdSyncObj = new Object();

    @FXML
    private Service<Void> checkLeftIron = new Service<Void>() {
        @Override
        protected Task<Void> createTask() {
            return new Task<Void>() {
                @Override
                protected Void call() throws Exception {
                    while (true) {
                        if (SingletonFactory.getGenericAdapter().getBaseAdapter() != null
                                && SingletonFactory.getGenericAdapter().getBaseAdapter().adapterDetected()) {
                            wirePortStatus.setStyle(Constants.FX_TEXT_BACKGROUND_GREEN);
                            temperatureOption = SingletonFactory.getTemperatureOption();
                            String leftSensor;
                            if (temperatureOption.isEvenDirectionOfMovement()) {
                                leftSensor = SingletonFactory.getEvenMovement().getLeftIronSensor().getSensorId();
                            } else {
                                leftSensor = SingletonFactory.getUnevenMovement().getLeftIronSensor().getSensorId();
                            }
                            if (StringUtils.isNotBlank(leftSensor)
                                    && SingletonFactory.getGlobalSensorMap().containsKey(leftSensor)) {
                                DSPortAdapter l_adapter = null;
                                TemperatureContainer l_container = null;
                                synchronized (firstSyncObj) {
                                    l_adapter = SingletonFactory.getGenericAdapter().getBaseAdapter();
                                    l_container = (TemperatureContainer) ((TaggedDevice) SingletonFactory.getGlobalSensorMap().get(leftSensor)).getDeviceContainer();
                                }
                                try {
                                    l_adapter.beginExclusive(true);
                                    byte[] state = l_container.readDevice();
                                    l_container.doTemperatureConvert(state);
                                    temperatureOption.setLeftTemperature(l_container.getTemperature(state));
                                    leftIronTemperature.setText(Double.toString(l_container.getTemperature(state)));
                                    if (temperatureOption.isEvenDirectionOfMovement()) {
                                        firstIronSensorStatus.setStyle(Constants.FX_TEXT_BACKGROUND_GREEN);
                                        thirdIronSensorStatus.setStyle(Constants.FX_TEXT_BACKGROUND_RED);
                                    } else {
                                        thirdIronSensorStatus.setStyle(Constants.FX_TEXT_BACKGROUND_GREEN);
                                        firstIronSensorStatus.setStyle(Constants.FX_TEXT_BACKGROUND_RED);
                                    }
                                    System.out.println(Constants.TEMPERATURE_IS + Double.toString(l_container.getTemperature(state)));
                                } catch (Exception e) {
                                    System.out.println(Constants.ERROR_READING_DEVICE + e.toString());
                                    if (temperatureOption.isEvenDirectionOfMovement()) {
                                        firstIronSensorStatus.setStyle(Constants.FX_TEXT_BACKGROUND_YELLOW);
                                    } else {
                                        thirdIronSensorStatus.setStyle(Constants.FX_TEXT_BACKGROUND_YELLOW);
                                    }
                                } finally {
                                    l_adapter.endExclusive();
                                }
                            } else {
                                if (temperatureOption.isEvenDirectionOfMovement()) {
                                    firstIronSensorStatus.setStyle(Constants.FX_TEXT_BACKGROUND_RED);
                                } else {
                                    thirdIronSensorStatus.setStyle(Constants.FX_TEXT_BACKGROUND_RED);
                                }
                                System.out.println(Constants.SENSORS_NOT_FOUND);
                                continue;
                            }
                        } else {
                            wirePortStatus.setStyle(Constants.FX_TEXT_BACKGROUND_RED);
                        }
                    }
                }
            };
        }
    };

    @FXML
    private Service<Void> checkRightIron = new Service<Void>() {
        @Override
        protected Task<Void> createTask() {
            return new Task<Void>() {
                @Override
                protected Void call() throws Exception {
                    int areaChartCount = 0;
                    while (true) {
                        temperatureOption = SingletonFactory.getTemperatureOption();
                        String rightSensor;
                        if (temperatureOption.isEvenDirectionOfMovement()) {
                            rightSensor = SingletonFactory.getEvenMovement().getRightIronSensor().getSensorId();
                        } else {
                            rightSensor = SingletonFactory.getUnevenMovement().getRightIronSensor().getSensorId();
                        }
                        if (StringUtils.isNotBlank(rightSensor)
                                && SingletonFactory.getGlobalSensorMap().containsKey(rightSensor)) {
                            TaggedDevice taggedDevice = (TaggedDevice) SingletonFactory.getGlobalSensorMap().get(rightSensor);
                            DSPortAdapter l_adapter = null;
                            TemperatureContainer l_container = null;
                            synchronized (secondSyncObj) {
                                l_adapter = SingletonFactory.getGenericAdapter().getBaseAdapter();
                                l_container = (TemperatureContainer) taggedDevice.getDeviceContainer();
                            }
                            try {
                                l_adapter.beginExclusive(true);
                                byte[] state = l_container.readDevice();
                                l_container.doTemperatureConvert(state);
                                temperatureOption.setRightTemperature(l_container.getTemperature(state));
                                rightIronTemperature.setText(Double.toString(l_container.getTemperature(state)));
                                if (temperatureOption.isEvenDirectionOfMovement()) {
                                    secondIronSensorStatus.setStyle(Constants.FX_TEXT_BACKGROUND_GREEN);
                                    fourhtIronSensorStatus.setStyle(Constants.FX_TEXT_BACKGROUND_RED);
                                } else {
                                    fourhtIronSensorStatus.setStyle(Constants.FX_TEXT_BACKGROUND_GREEN);
                                    secondIronSensorStatus.setStyle(Constants.FX_TEXT_BACKGROUND_RED);
                                }
                            } catch (OneWireIOException e) {
                                if (temperatureOption.isEvenDirectionOfMovement()) {
                                    secondIronSensorStatus.setStyle(Constants.FX_TEXT_BACKGROUND_YELLOW);
                                } else {
                                    fourhtIronSensorStatus.setStyle(Constants.FX_TEXT_BACKGROUND_YELLOW);
                                }
                                System.out.println(Constants.ERROR_READING_DEVICE + e.toString());
                            } finally {
                                l_adapter.endExclusive();
                            }
                        } else {
                            if (temperatureOption.isEvenDirectionOfMovement()) {
                                secondIronSensorStatus.setStyle(Constants.FX_TEXT_BACKGROUND_RED);
                            } else {
                                fourhtIronSensorStatus.setStyle(Constants.FX_TEXT_BACKGROUND_RED);
                            }
                            System.out.println(Constants.SENSORS_NOT_FOUND);
                            continue;
                        }
                    }
                }
            };
        }
    };

    private Service<Void> checkLeftOutsideTemperatureSensor = new Service<Void>() {
        @Override
        protected Task<Void> createTask() {
            return new Task<Void>() {
                @Override
                protected Void call() throws Exception {
                    while (true) {
                        String leftSensor = SingletonFactory.getOutsideTemperatureSensors().getLeftOutsideTemperatureSensor().getSensorId();

                        if (StringUtils.isNotBlank(leftSensor)
                                && SingletonFactory.getGlobalSensorMap().containsKey(leftSensor)) {
                            TaggedDevice taggedDevice = (TaggedDevice) SingletonFactory.getGlobalSensorMap().get(leftSensor);
                            DSPortAdapter l_adapter = null;
                            TemperatureContainer l_container = null;
                            synchronized (thirdSyncObj) {
                                l_adapter = SingletonFactory.getGenericAdapter().getBaseAdapter();
                                l_container = (TemperatureContainer) taggedDevice.getDeviceContainer();
                            }
                            try {
                                l_adapter.beginExclusive(true);
                                byte[] state = l_container.readDevice();
                                l_container.doTemperatureConvert(state);
                                firstOutsideTemperatureSensorStatus.setStyle(Constants.FX_TEXT_BACKGROUND_GREEN);
                                temperatureOption.setOuterTemperature(l_container.getTemperature(state));
                                outsideTemperatureValue.setText(Double.toString(l_container.getTemperature(state)));
                            } catch (OneWireIOException e) {
                                firstOutsideTemperatureSensorStatus.setStyle(Constants.FX_TEXT_BACKGROUND_YELLOW);
                                System.out.println(Constants.ERROR_READING_DEVICE + e.toString());
                            } finally {
                                l_adapter.endExclusive();
                            }
                        } else {
                            System.out.println(Constants.SENSORS_NOT_FOUND);
                            firstOutsideTemperatureSensorStatus.setStyle(Constants.FX_TEXT_BACKGROUND_RED);
                            continue;
                        }
                    }
                }
            };
        }
    };

    public SensorController() {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            if (SingletonFactory.getGenericAdapter().getBaseAdapter() != null
                    && SingletonFactory.getGenericAdapter().getBaseAdapter().adapterDetected()) {
                wirePortStatus.setStyle(Constants.FX_TEXT_BACKGROUND_GREEN);
            }
        } catch (OneWireException e) {
            e.printStackTrace();
        }
        leftIronTemperature.setAlignment(Pos.CENTER);
        rightIronTemperature.setAlignment(Pos.CENTER);
        firstOutsideTemperatureSensorStatus.setAlignment(Pos.CENTER);
        secondOutsideTemperatureSensorStatus.setAlignment(Pos.CENTER);
        checkLeftIron.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent t) {
                checkLeftIron.reset();
            }
        });
        checkLeftIron.start();
        checkRightIron.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent t) {
                checkRightIron.reset();
            }
        });
        checkRightIron.start();
        checkLeftOutsideTemperatureSensor.restart();
    }
}
