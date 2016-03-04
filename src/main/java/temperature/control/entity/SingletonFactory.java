package temperature.control.entity;


import temperature.control.entity.adapter.DefaultPortList;
import temperature.control.entity.adapter.GenericAdapter;
import temperature.control.entity.movement.EvenMovement;
import temperature.control.entity.movement.UnevenMovement;
import temperature.control.entity.sensor.GlobalSensorList;
import temperature.control.entity.sensor.GlobalSensorMap;
import temperature.control.entity.sensor.OutsideTemperatureSensors;
import temperature.control.entity.settings.ApplicationSettings;
import temperature.control.entity.settings.TemperatureOption;
import temperature.control.util.PausableThreadExecutor;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

public class SingletonFactory {
    private static volatile GenericAdapter genericAdapter = null;
    private static volatile EvenMovement evenMovement = null;
    private static volatile GlobalSensorList globalSensorList = null;
    private static volatile GlobalSensorMap globalSensorMap = null;
    private static volatile DefaultPortList defaultPortList = null;
    private static volatile TemperatureOption temperatureOption = null;
    private static volatile PausableThreadExecutor pausableThreadExecutor = null;
    private static volatile UnevenMovement unevenMovement = null;
    private static volatile ApplicationSettings applicationSettings = null;
    private static volatile OutsideTemperatureSensors outsideTemperatureSensors = null;


    public static synchronized GenericAdapter getGenericAdapter() {
        if (genericAdapter == null) {
            genericAdapter = new GenericAdapter();
        }
        return genericAdapter;
    }

    public static EvenMovement getEvenMovement() {
        EvenMovement localInstance = evenMovement;
        if (localInstance == null) {
            synchronized (EvenMovement.class) {
                localInstance = evenMovement;
                if (localInstance == null) {
                    evenMovement = localInstance = new EvenMovement();
                }
            }
        }
        return localInstance;
    }

    public static UnevenMovement getUnevenMovement() {
        UnevenMovement localInstance = unevenMovement;
        if (localInstance == null) {
            synchronized (UnevenMovement.class) {
                localInstance = unevenMovement;
                if (localInstance == null) {
                    unevenMovement = localInstance = new UnevenMovement();
                }
            }
        }
        return localInstance;
    }

    public static GlobalSensorList getGlobalSensorList() {
        GlobalSensorList localInstance = globalSensorList;
        if (localInstance == null) {
            synchronized (GlobalSensorList.class) {
                localInstance = globalSensorList;
                if (localInstance == null) {
                    globalSensorList = localInstance = new GlobalSensorList();
                }
            }
        }
        return localInstance;
    }

    public static GlobalSensorMap getGlobalSensorMap() {
        GlobalSensorMap localInstance = globalSensorMap;
        if (localInstance == null) {
            synchronized (GlobalSensorMap.class) {
                localInstance = globalSensorMap;
                if (localInstance == null) {
                    globalSensorMap = localInstance = new GlobalSensorMap();
                }
            }
        }
        return localInstance;
    }

    public static DefaultPortList getDefaultPortList() {
        DefaultPortList localInstance = defaultPortList;
        if (localInstance == null) {
            synchronized (DefaultPortList.class) {
                localInstance = defaultPortList;
                if (localInstance == null) {
                    defaultPortList = localInstance = new DefaultPortList();
                }
            }
        }
        return localInstance;
    }

    public static TemperatureOption getTemperatureOption() {
        TemperatureOption localInstance = temperatureOption;
        if (localInstance == null) {
            synchronized (TemperatureOption.class) {
                localInstance = temperatureOption;
                if (localInstance == null) {
                    temperatureOption = localInstance = new TemperatureOption();
                }
            }
        }
        return localInstance;
    }

    public static PausableThreadExecutor getPausableThreadExecutor() {
        PausableThreadExecutor localInstance = pausableThreadExecutor;
        if (localInstance == null) {
            synchronized (PausableThreadExecutor.class) {
                localInstance = pausableThreadExecutor;
                if (localInstance == null) {
                    pausableThreadExecutor = localInstance = new PausableThreadExecutor(0, Integer.MAX_VALUE,
                            60L, TimeUnit.SECONDS,
                            new SynchronousQueue<Runnable>());
                }
            }
        }
        return localInstance;
    }

    public static ApplicationSettings getApplicationSettings() {
        ApplicationSettings localInstance = applicationSettings;
        if (localInstance == null) {
            synchronized (ApplicationSettings.class) {
                localInstance = applicationSettings;
                if (localInstance == null) {
                    applicationSettings = localInstance = new ApplicationSettings();
                }
            }
        }
        return localInstance;
    }

    public static OutsideTemperatureSensors getOutsideTemperatureSensors() {
        OutsideTemperatureSensors localInstance = outsideTemperatureSensors;
        if (localInstance == null) {
            synchronized (OutsideTemperatureSensors.class) {
                localInstance = outsideTemperatureSensors;
                if (localInstance == null) {
                    outsideTemperatureSensors = localInstance = new OutsideTemperatureSensors();
                }
            }
        }
        return localInstance;
    }
}
