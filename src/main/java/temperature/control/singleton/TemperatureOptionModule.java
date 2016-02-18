package temperature.control.singleton;

import temperature.control.entity.TemperatureOption;

public class TemperatureOptionModule {
    private static volatile TemperatureOption instance = null;

    private TemperatureOptionModule() {
    }

    public static TemperatureOption getInstance() {
        TemperatureOption localInstance = instance;
        if (localInstance == null) {
            synchronized (TemperatureOption.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new TemperatureOption();
                }
            }
        }
        return localInstance;
    }
}
