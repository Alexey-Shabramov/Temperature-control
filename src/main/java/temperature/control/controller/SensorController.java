package temperature.control.controller;

import com.dalsemi.onewire.adapter.DSPortAdapter;
import com.dalsemi.onewire.application.tag.TaggedDevice;
import com.dalsemi.onewire.container.TemperatureContainer;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import org.apache.commons.lang3.StringUtils;
import temperature.control.entity.SingletonFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class SensorController implements Initializable {
    @FXML
    public TextField leftIronTemperature;

    @FXML
    public TextField rightIronTemperature;

    public String sensorId = "B2011592990EFF28";
    private Object syncObj = new Object();

    private Service<Void> checkLeftIron = new Service<Void>() {
        @Override
        protected Task<Void> createTask() {
            return new Task<Void>() {
                @Override
                protected Void call() throws Exception {
                    while (true) {
                        if (StringUtils.isNotBlank(sensorId)
                                && SingletonFactory.getGlobalSensorMap().containsKey(sensorId)) {
                            TaggedDevice taggedDevice = (TaggedDevice) SingletonFactory.getGlobalSensorMap().get(sensorId);
                            DSPortAdapter l_adapter = null;
                            TemperatureContainer l_container = null;
                            synchronized (syncObj) {
                                l_adapter = SingletonFactory.getGenericAdapter().getBaseAdapter();
                                l_container = (TemperatureContainer) taggedDevice.getDeviceContainer();
                            }
                            try {
                                l_adapter.beginExclusive(true);
                                byte[] state = l_container.readDevice();
                                l_container.doTemperatureConvert(state);
                                leftIronTemperature.setText(Double.toString(l_container.getTemperature(state)));
                                System.out.println("Температура равна: " + Double.toString(l_container.getTemperature(state)));
                            } catch (Exception e) {
                                System.out.println("Error reading device!" + e.toString());
                            } finally {
                                l_adapter.endExclusive();
                            }
                        }
                    }
                }
            };
        }
    };

    private Service<Void> checkRightIron = new Service<Void>() {
        @Override
        protected Task<Void> createTask() {
            return new Task<Void>() {
                @Override
                protected Void call() throws Exception {
                    return null;
                }
            };
        }
    };

    public SensorController() {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        leftIronTemperature.setAlignment(Pos.CENTER);
        rightIronTemperature.setAlignment(Pos.CENTER);
        checkLeftIron.start();
    }
}
