package temperature.control.singleton;

import temperature.control.entity.GenericAdapter;

public class AdapterModule {
    private static volatile GenericAdapter instance = null;

    private AdapterModule() {
    }

    public static synchronized GenericAdapter getInstance() {
        if (instance == null) {
            instance = new GenericAdapter();
        }
        return instance;
    }
}
