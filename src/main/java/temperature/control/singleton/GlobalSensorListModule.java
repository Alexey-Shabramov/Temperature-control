package temperature.control.singleton;


import temperature.control.entity.GlobalSensorList;

public class GlobalSensorListModule {
    private static volatile GlobalSensorList instance = null;

    private GlobalSensorListModule() {
    }

    public static GlobalSensorList getInstance() {
        GlobalSensorList localInstance = instance;
        if (localInstance == null) {
            synchronized (GlobalSensorList.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new GlobalSensorList();
                }
            }
        }
        return localInstance;
    }
}
