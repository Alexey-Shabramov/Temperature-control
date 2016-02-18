package temperature.control.singleton;

import temperature.control.entity.UnevenMovement;

public class UnevenMovementModule {
    private static volatile UnevenMovement instance = null;

    private UnevenMovementModule() {
    }

    public static UnevenMovement getInstance() {
        UnevenMovement localInstance = instance;
        if (localInstance == null) {
            synchronized (UnevenMovement.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new UnevenMovement();
                }
            }
        }
        return localInstance;
    }
}
