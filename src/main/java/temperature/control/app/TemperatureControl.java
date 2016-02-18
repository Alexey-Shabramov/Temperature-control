package temperature.control.app;

import com.dalsemi.onewire.OneWireAccessProvider;
import com.dalsemi.onewire.OneWireException;
import com.dalsemi.onewire.application.tag.TaggedDevice;
import com.dalsemi.onewire.container.OneWireContainer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import jssc.SerialPortList;
import temperature.control.singleton.AdapterModule;
import temperature.control.singleton.GlobalSensorListModule;
import temperature.control.singleton.GlobalSensorMapModule;
import temperature.control.singleton.PortListModule;

import java.util.Enumeration;

public class TemperatureControl extends Application {

    private static OneWireContainer oneWireContainer = null;

    public static void main(String[] args) {
        try {
            setDefaultAdapter();
            setDefaultPortList();
            System.out.println(AdapterModule.getInstance().getBaseAdapter());
            for (Enumeration owd_enum = AdapterModule.getInstance().getBaseAdapter().getAllDeviceContainers();
                 owd_enum.hasMoreElements(); ) {
                oneWireContainer = (OneWireContainer) owd_enum.nextElement();
                GlobalSensorMapModule.getInstance().put(oneWireContainer.getAddressAsString(), new TaggedDevice(AdapterModule.getInstance().getBaseAdapter(), oneWireContainer.getAddressAsString()));
                GlobalSensorListModule.getInstance().add(oneWireContainer.getAddressAsString());
                System.out.println("Device Found: " + oneWireContainer.getAddressAsString());
            }
        } catch (OneWireException e) {
            e.printStackTrace();
        }
        launch(args);
    }

    public static void setDefaultAdapter() throws OneWireException {
        AdapterModule.getInstance().setBaseAdapter(OneWireAccessProvider.getDefaultAdapter());
    }

    public static void setDefaultPortList() {
        for (String portName : SerialPortList.getPortNames()) {
            PortListModule.getInstance().add(portName);
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/MainPane.fxml"));
        primaryStage.setTitle("Temperature control");
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(root, 916, 800));
        primaryStage.show();
    }
}
