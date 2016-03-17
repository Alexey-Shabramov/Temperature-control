package temperature.control.dict;

public class Constants {
    /**
     * Tables for base devices (ASDK, PONAB) options name
     */
    public static final String TABLE_100 = "100";
    public static final String TABLE_120 = "120";
    public static final String TABLE_140 = "140";
    public static final String TABLE_160 = "160";

    /**
     * Devices names
     */
    public static final String ASKD = "АСДК-Б";
    public static final String PONAB = "ПОНАБ";
    public static final String DISK = "ДИСК-Б";
    public static final String KTSM = "КТСМ-02";

    /**
     * Direction names
     */
    public static final String EVEN = "Четное";
    public static final String UNEVEN = "Нечетное";

    /**
     * Check-thread names
     */
    public static final String CHECK_RIGHT_SENSOR = "";
    public static final String CHECK_LEFT_SENSOR = "";

    /**
     * Sensors physical names
     */
    public static final String ADAPTER = "adapter.default.address";
    public static final String ADAPTER_PORT = "adapter.default.port";
    public static final String EVEN_RIGHT_IRON_SENSOR = "even.right_iron.sensor.address";
    public static final String EVEN_LEFT_IRON_SENSOR = "even.left_iron.sensor.address";
    public static final String UNEVEN_LEFT_IRON_SENSOR = "odd.left_iron.sensor.address";
    public static final String UNEVEN_RIGHT_IRON_SENSOR = "odd.right_iron.sensor.address";
    public static final String EVEN_CONTROL_PORT_ADDRESS = "even.control.port.address";
    public static final String UNEVEN_CONTROL_PORT_ADDRESS = "odd.control.port.address";
    public static final String TEMPERATURE_LEFT_SENSOR = "temperature.left.sensor.address";
    public static final String TEMPERATURE_RIGHT_SENSOR = "temperature.right.sensor.address";
    public static final String ALERT_WARMING_VALUES_IS_EMPTY = "Параметры температуры пустые. (Введите хотябы один параметр для одной из сторон).";
    public static final String ALERT_CURRENT_SETTINGS_CONTROL_PORT_NOT_WORKING = "Отсутствует значение контрольного порта для управления нагревом утюгов либо значение не верное.(Проверьте настройки).";
    public static final String ALERT_MOVEMENT_IS_NOT_SET = "Не задано текущее направление движения. Повторите ввод.";
    public static final String OPTION_IS_NOT_SET = "Не задана настройка для нагрева.";
}
