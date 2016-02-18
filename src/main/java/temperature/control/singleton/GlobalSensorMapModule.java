package temperature.control.singleton;

import temperature.control.entity.GlobalSensorMap;

public class GlobalSensorMapModule {
    private static volatile GlobalSensorMap instance = null;

    private GlobalSensorMapModule() {
    }

    public static GlobalSensorMap getInstance() {
        GlobalSensorMap localInstance = instance;
        if (localInstance == null) {
            synchronized (GlobalSensorMap.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new GlobalSensorMap();
                }
            }
        }
        return localInstance;
    }
}
