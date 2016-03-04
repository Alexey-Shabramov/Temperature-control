package temperature.control.util;

import com.dalsemi.onewire.OneWireAccessProvider;
import com.dalsemi.onewire.OneWireException;
import jssc.SerialPortList;
import temperature.control.entity.SingletonFactory;


public class DevicesUpdaterUtil {
    public static void setDefaultAdapter() throws OneWireException {
        SingletonFactory.getGenericAdapter().setBaseAdapter(OneWireAccessProvider.getDefaultAdapter());
    }

    public static void setDefaultPortList() {
        for (String portName : SerialPortList.getPortNames()) {
            SingletonFactory.getDefaultPortList().add(portName);
        }
    }
}
