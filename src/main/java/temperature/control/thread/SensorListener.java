package temperature.control.thread;

import com.dalsemi.onewire.adapter.DSPortAdapter;
import com.dalsemi.onewire.application.tag.TaggedDevice;
import com.dalsemi.onewire.container.OneWireContainer;
import com.dalsemi.onewire.container.TemperatureContainer;
import com.dalsemi.onewire.utils.OWPath;
import temperature.control.entity.Sensor;

public class SensorListener implements Runnable {

    protected DSPortAdapter adapter = null;
    private Sensor sensor;
    private OneWireContainer oneWireContainer;
    private Object syncObj = new Object();
    private OWPath pathToDevice = null;
    private TaggedDevice taggedDevice = null;
    private TemperatureContainer container = null;

    public SensorListener() {

    }

    public SensorListener(String sensorName) {

    }

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }

    @Override
    public void run() {
        executeTask();
    }

    public void executeTask() {
        double lastTemperatureRead = Double.NaN;
        DSPortAdapter l_adapter = null;
        TemperatureContainer l_container = null;
        synchronized (syncObj) {
            if (adapter == null || container == null)
                return;
            l_adapter = adapter;
            l_container = container;
        }

        System.out.println("Polling Temperature...");
        try {
            l_adapter.beginExclusive(true);
            if (pathToDevice != null)
                pathToDevice.open();
            byte[] state = l_container.readDevice();
            l_container.doTemperatureConvert(state);
            double read = l_container.getTemperature(state);
            System.out.println("Температура равна: " + read);
            System.out.println("Done polling.");
        } catch (Exception e) {
            System.out.println("Error reading device! " + e.toString());
        } finally {
            l_adapter.endExclusive();
        }
    }
}
