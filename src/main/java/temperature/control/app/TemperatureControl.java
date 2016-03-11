package temperature.control.app;

import com.dalsemi.onewire.OneWireException;
import com.dalsemi.onewire.application.tag.TaggedDevice;
import com.dalsemi.onewire.container.OneWireContainer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import temperature.control.entity.SingletonFactory;
import temperature.control.entity.sensor.GlobalSensorList;
import temperature.control.entity.sensor.GlobalSensorMap;
import temperature.control.util.DevicesUpdaterUtil;
import temperature.control.util.SettingsFileUtil;

import java.util.Enumeration;

public class TemperatureControl extends Application {

    private static OneWireContainer oneWireContainer = null;
    private static GlobalSensorMap globalSensorMap = SingletonFactory.getGlobalSensorMap();
    private static GlobalSensorList globalSensorList = SingletonFactory.getGlobalSensorList();

    public static void main(String[] args) {
        try {
            SettingsFileUtil.loadSettings();
            DevicesUpdaterUtil.setDefaultAdapter();
            DevicesUpdaterUtil.setDefaultPortList();
            System.out.println(SingletonFactory.getGenericAdapter().getBaseAdapter());
            for (Enumeration owd_enum = SingletonFactory.getGenericAdapter().getBaseAdapter().getAllDeviceContainers(); owd_enum.hasMoreElements(); ) {
                oneWireContainer = (OneWireContainer) owd_enum.nextElement();
                globalSensorMap.put(oneWireContainer.getAddressAsString(), new TaggedDevice(SingletonFactory.getGenericAdapter().getBaseAdapter(), oneWireContainer.getAddressAsString()));
                globalSensorList.add(oneWireContainer.getAddressAsString());
                System.out.println("Device Found: " + oneWireContainer.getAddressAsString());
            }
        } catch (OneWireException e) {
            e.printStackTrace();
        }
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(TemperatureControl.class.getResource("/fxml/MainPane.fxml"));
        primaryStage.setTitle("Temperature control");
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(root, 916, 800));
        primaryStage.setOnHiding(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("Application Closed by click to Close Button(X)");
                        System.exit(0);
                    }
                });
            }
        });
        primaryStage.show();
    }
}
