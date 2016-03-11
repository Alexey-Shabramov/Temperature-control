package temperature.control.util;

import jssc.SerialPort;
import org.apache.commons.lang3.StringUtils;
import temperature.control.entity.SingletonFactory;
import temperature.control.entity.movement.EvenMovement;
import temperature.control.entity.movement.UnevenMovement;
import temperature.control.entity.sensor.OutsideTemperatureSensors;
import temperature.control.entity.settings.ApplicationSettings;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class SettingsFileUtil {
    private static StringBuilder stringBuilder = new StringBuilder();
    private static ApplicationSettings applicationSettings = SingletonFactory.getApplicationSettings();
    private static EvenMovement evenMovement;
    private static UnevenMovement unevenMovement;
    private static OutsideTemperatureSensors outsideTemperatureSensors;
    private static BufferedReader br;
    private static BufferedWriter bw;

    public static void loadSettings() {
        List<String> settingsList = new ArrayList<>();
        Map<String, String> settingsMap = new HashMap<>();
        try (BufferedReader br = Files.newBufferedReader(Paths.get(new File("src/main/resources/settings/settings.txt").getPath()))) {
            settingsList.addAll(br.lines().collect(Collectors.toList()).stream().map(line -> Arrays.asList(line.split("=")).get(1)).collect(Collectors.toList()));
            if (StringUtils.isNoneBlank(settingsList.get(0)) && !StringUtils.equals(settingsList.get(0), ",")) {
                applicationSettings.setDefaultAdapterAddress(StringUtils.removePattern(settingsList.get(0), ","));
                settingsMap.put("adapter.default.address", StringUtils.removePattern(settingsList.get(0), ","));
            }
            if (StringUtils.isNoneBlank(settingsList.get(1)) && !StringUtils.equals(settingsList.get(1), ",")) {
                applicationSettings.setDefaultAdapterPort(StringUtils.removePattern(settingsList.get(1), ","));
                settingsMap.put("adapter.default.port", StringUtils.removePattern(settingsList.get(1), ","));
            }
            if (StringUtils.isNoneBlank(settingsList.get(2)) && !StringUtils.equals(settingsList.get(2), ",")) {
                applicationSettings.setEvenRightIronSensorAddress(StringUtils.removePattern(settingsList.get(2), ","));
                settingsMap.put("even.right_iron.sensor.address", StringUtils.removePattern(settingsList.get(2), ","));
            }
            if (StringUtils.isNoneBlank(settingsList.get(3)) && !StringUtils.equals(settingsList.get(3), ",")) {
                applicationSettings.setEvenLeftIronSensorAddress(StringUtils.removePattern(settingsList.get(3), ","));
                settingsMap.put("even.left_iron.sensor.address", StringUtils.removePattern(settingsList.get(3), ","));
            }
            if (StringUtils.isNoneBlank(settingsList.get(4)) && !StringUtils.equals(settingsList.get(4), ",")) {
                applicationSettings.setUnevenLeftIronSensorAddress(StringUtils.removePattern(settingsList.get(4), ","));
                settingsMap.put("odd.left_iron.sensor.address", StringUtils.removePattern(settingsList.get(4), ","));
            }
            if (StringUtils.isNoneBlank(settingsList.get(5)) && !StringUtils.equals(settingsList.get(5), ",")) {
                applicationSettings.setUnevenRightIronSensorAddress(StringUtils.removePattern(settingsList.get(5), ","));
                settingsMap.put("odd.right_iron.sensor.address", StringUtils.removePattern(settingsList.get(5), ","));
            }
            if (StringUtils.isNoneBlank(settingsList.get(6)) && !StringUtils.equals(settingsList.get(6), ",")) {
                applicationSettings.setEvenControlPortAddress(StringUtils.removePattern(settingsList.get(6), ","));
                settingsMap.put("even.control.port.address", StringUtils.removePattern(settingsList.get(6), ","));
            }
            if (StringUtils.isNoneBlank(settingsList.get(7)) && !StringUtils.equals(settingsList.get(7), ",")) {
                applicationSettings.setUnevenControlPortAddress(StringUtils.removePattern(settingsList.get(7), ","));
                settingsMap.put("odd.control.port.address", StringUtils.removePattern(settingsList.get(7), ","));
            }
            if (StringUtils.isNoneBlank(settingsList.get(8)) && !StringUtils.equals(settingsList.get(8), ",")) {
                applicationSettings.setTemperatureLeftSensorAddress(StringUtils.removePattern(settingsList.get(8), ","));
                settingsMap.put("temperature.left.sensor.address", StringUtils.removePattern(settingsList.get(8), ","));
            }
            if (StringUtils.isNoneBlank(settingsList.get(9)) && !StringUtils.equals(settingsList.get(9), ",")) {
                applicationSettings.setTemperatureRightSensorAddress(StringUtils.removePattern(settingsList.get(9), ","));
                settingsMap.put("temperature.right.sensor.address", StringUtils.removePattern(settingsList.get(9), ","));
            }
            convertSettingsToDirections();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void convertSettingsToDirections() {
        evenMovement = SingletonFactory.getEvenMovement();
        unevenMovement = SingletonFactory.getUnevenMovement();
        outsideTemperatureSensors = SingletonFactory.getOutsideTemperatureSensors();
        evenMovement.getLeftIronSensor().setSensorId(applicationSettings.getEvenLeftIronSensorAddress());
        evenMovement.getRightIronSensor().setSensorId(applicationSettings.getEvenRightIronSensorAddress());
        evenMovement.setEvenControlPort(new SerialPort(applicationSettings.getEvenControlPortAddress()));
        unevenMovement.getLeftIronSensor().setSensorId(applicationSettings.getUnevenLeftIronSensorAddress());
        unevenMovement.getRightIronSensor().setSensorId(applicationSettings.getUnevenRightIronSensorAddress());
        unevenMovement.setUnevenControlPort(new SerialPort(applicationSettings.getUnevenControlPortAddress()));
        outsideTemperatureSensors.getLeftOutsideTemperatureSensor().setSensorId(applicationSettings.getTemperatureLeftSensorAddress());
        outsideTemperatureSensors.getRightOutsideTemperatureSensor().setSensorId(applicationSettings.getTemperatureRightSensorAddress());
    }

    public static void saveSensor(String sensorMentalName, String newSensorId) {
        String oldFileName = new File("src/main/resources/settings/settings.txt").getPath();
        String tmpFileName = new File("src/main/resources/settings/settings_tmp.txt").getPath();
        String[] parts;
        try {
            br = new BufferedReader(new FileReader(oldFileName));
            bw = new BufferedWriter(new FileWriter(tmpFileName));
            String line;
            while ((line = br.readLine()) != null) {
                if (line.contains(sensorMentalName)) {
                    parts = line.split("[=]", 2);
                    stringBuilder.append(parts[0]).append("=").append(newSensorId).append(",");
                    bw.write(stringBuilder.toString() + "\n");
                } else {
                    bw.write(line + "\n");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            stringBuilder.setLength(0);
            try {
                if (br != null)
                    br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (bw != null)
                    bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        File oldFile = new File(oldFileName);
        oldFile.delete();
        File newFile = new File(tmpFileName);
        newFile.renameTo(oldFile);
    }
}
