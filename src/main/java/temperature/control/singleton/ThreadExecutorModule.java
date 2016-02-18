package temperature.control.singleton;

import temperature.control.util.PausableThreadExecutor;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

public class ThreadExecutorModule {
    private static volatile PausableThreadExecutor instance = null;

    private ThreadExecutorModule() {
    }

    public static PausableThreadExecutor getInstance() {
        PausableThreadExecutor localInstance = instance;
        if (localInstance == null) {
            synchronized (PausableThreadExecutor.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new PausableThreadExecutor(0, Integer.MAX_VALUE,
                            60L, TimeUnit.SECONDS,
                            new SynchronousQueue<Runnable>());
                }
            }
        }
        return localInstance;
    }
}
