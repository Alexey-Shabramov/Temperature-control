package temperature.control.singleton;

import temperature.control.entity.DefaultPortList;

public class PortListModule {
    private static volatile DefaultPortList instance = null;

    private PortListModule() {
    }

    public static DefaultPortList getInstance() {
        DefaultPortList localInstance = instance;
        if (localInstance == null) {
            synchronized (DefaultPortList.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new DefaultPortList();
                }
            }
        }
        return localInstance;
    }
}
