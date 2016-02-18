package temperature.control.singleton;


import temperature.control.entity.EvenMovement;

public class EvenMovementModule {
    private static volatile EvenMovement instance = null;

    private EvenMovementModule() {
    }

    public static EvenMovement getInstance() {
        EvenMovement localInstance = instance;
        if (localInstance == null) {
            synchronized (EvenMovement.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new EvenMovement();
                }
            }
        }
        return localInstance;
    }
}
