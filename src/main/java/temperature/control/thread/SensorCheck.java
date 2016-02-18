package temperature.control.thread;

import com.dalsemi.onewire.adapter.DSPortAdapter;
import com.dalsemi.onewire.container.TemperatureContainer;
import temperature.control.singleton.AdapterModule;

public class SensorCheck implements Runnable {

    protected DSPortAdapter adapter = AdapterModule.getInstance().getBaseAdapter();
    private Object syncObj = new Object();
    private TemperatureContainer temperatureContainer = (TemperatureContainer) AdapterModule.getInstance().getBaseAdapter();
    private TemperatureContainer l_container = null;
    private DSPortAdapter l_adapter = null;

    @Override
    public void run() {
        synchronized (syncObj) {
            if (adapter == null)
                return;
            l_adapter = adapter;
            l_container = temperatureContainer;
        }
        try {
            l_adapter.beginExclusive(true);
            byte[] state = l_container.readDevice();
            l_container.doTemperatureConvert(state);
            double read = l_container.getTemperature(state);
        } catch (Exception e) {
            System.out.println("Error reading device! " + e.toString());
        } finally {
            l_adapter.endExclusive();
        }
    }
}
