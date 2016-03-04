package temperature.control.util;

import jssc.SerialPort;
import org.apache.commons.lang3.StringUtils;
import temperature.control.entity.SingletonFactory;
import temperature.control.entity.movement.EvenMovement;
import temperature.control.entity.movement.UnevenMovement;
import temperature.control.entity.sensor.OutsideTemperatureSensors;
import temperature.control.entity.settings.ApplicationSettings;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SettingsFileUtil {
    private static ApplicationSettings applicationSettings = SingletonFactory.getApplicationSettings();
    private static EvenMovement evenMovement = SingletonFactory.getEvenMovement();
    private static UnevenMovement unevenMovement = SingletonFactory.getUnevenMovement();
    private static OutsideTemperatureSensors outsideTemperatureSensors = SingletonFactory.getOutsideTemperatureSensors();

    public static void loadSettings() {
        List<String> settingsList = new ArrayList<>();
        try (BufferedReader br = Files.newBufferedReader(Paths.get("D:\\My-Java-projects\\Temperature-control\\src\\main\\resources\\settings\\settings.txt"))) {
            settingsList.addAll(br.lines().collect(Collectors.toList()).stream().map(line -> Arrays.asList(line.split("=")).get(1)).collect(Collectors.toList()));
            if (StringUtils.isNoneBlank(settingsList.get(0)) && !StringUtils.equals(settingsList.get(0), ",")) {
                applicationSettings.setDefaultAdapterAddress(StringUtils.removePattern(settingsList.get(0), ","));
            }
            if (StringUtils.isNoneBlank(settingsList.get(1)) && !StringUtils.equals(settingsList.get(1), ",")) {
                applicationSettings.setDefaultAdapterPort(StringUtils.removePattern(settingsList.get(1), ","));
            }
            if (StringUtils.isNoneBlank(settingsList.get(2)) && !StringUtils.equals(settingsList.get(2), ",")) {
                applicationSettings.setEvenRightIronSensorAddress(StringUtils.removePattern(settingsList.get(2), ","));
            }
            if (StringUtils.isNoneBlank(settingsList.get(3)) && !StringUtils.equals(settingsList.get(3), ",")) {
                applicationSettings.setEvenLeftIronSensorAddress(StringUtils.removePattern(settingsList.get(3), ","));
            }
            if (StringUtils.isNoneBlank(settingsList.get(4)) && !StringUtils.equals(settingsList.get(4), ",")) {
                applicationSettings.setUnevenLeftIronSensorAddress(StringUtils.removePattern(settingsList.get(4), ","));
            }
            if (StringUtils.isNoneBlank(settingsList.get(5)) && !StringUtils.equals(settingsList.get(5), ",")) {
                applicationSettings.setUnevenRightIronSensorAddress(StringUtils.removePattern(settingsList.get(5), ","));
            }
            if (StringUtils.isNoneBlank(settingsList.get(6)) && !StringUtils.equals(settingsList.get(6), ",")) {
                applicationSettings.setEvenControlPortAddress(StringUtils.removePattern(settingsList.get(6), ","));
            }
            if (StringUtils.isNoneBlank(settingsList.get(7)) && !StringUtils.equals(settingsList.get(7), ",")) {
                applicationSettings.setUnevenControlPortAddress(StringUtils.removePattern(settingsList.get(7), ","));
            }
            if (StringUtils.isNoneBlank(settingsList.get(8)) && !StringUtils.equals(settingsList.get(8), ",")) {
                applicationSettings.setTemperatureLeftSensorAddress(StringUtils.removePattern(settingsList.get(8), ","));
            }
            if (StringUtils.isNoneBlank(settingsList.get(9)) && !StringUtils.equals(settingsList.get(9), ",")) {
                applicationSettings.setTemperatureRightSensorAddress(StringUtils.removePattern(settingsList.get(9), ","));
            }
            convertSettingsToDirections();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void convertSettingsToDirections() {
        evenMovement.getLeftIronSensor().setSensorId(applicationSettings.getEvenLeftIronSensorAddress());
        evenMovement.getRightIronSensor().setSensorId(applicationSettings.getEvenRightIronSensorAddress());
        evenMovement.setEvenControlPort(new SerialPort(applicationSettings.getEvenControlPortAddress()));

        unevenMovement.getLeftIronSensor().setSensorId(applicationSettings.getUnevenLeftIronSensorAddress());
        unevenMovement.getRightIronSensor().setSensorId(applicationSettings.getUnevenRightIronSensorAddress());
        unevenMovement.setUnevenControlPort(new SerialPort(applicationSettings.getUnevenControlPortAddress()));

        outsideTemperatureSensors.getLeftOutsideTemperatureSensor().setSensorId(applicationSettings.getTemperatureLeftSensorAddress());
        outsideTemperatureSensors.getRightOutsideTemperatureSensor().setSensorId(applicationSettings.getTemperatureRightSensorAddress());

    }
}
