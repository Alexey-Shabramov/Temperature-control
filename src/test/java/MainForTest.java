import org.apache.commons.lang3.StringUtils;
import temperature.control.entity.SingletonFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MainForTest {
    public static void main(String[] args) {
        List<String> settingsList = new ArrayList<>();
        try (BufferedReader br = Files.newBufferedReader(Paths.get("..\\settings.txt"))) {
            settingsList.addAll(br.lines().collect(Collectors.toList()).stream().map(line -> Arrays.asList(line.split("=")).get(1)).collect(Collectors.toList()));
            if (StringUtils.isNoneBlank(settingsList.get(0)) && !StringUtils.equals(settingsList.get(0), ",")) {
                SingletonFactory.getApplicationSettings().setDefaultAdapterAddress(StringUtils.removePattern(settingsList.get(0), ","));
            }
            if (StringUtils.isNoneBlank(settingsList.get(1)) && !StringUtils.equals(settingsList.get(1), ",")) {
                SingletonFactory.getApplicationSettings().setDefaultAdapterPort(StringUtils.removePattern(settingsList.get(1), ","));
            }
            if (StringUtils.isNoneBlank(settingsList.get(2)) && !StringUtils.equals(settingsList.get(2), ",")) {
                SingletonFactory.getApplicationSettings().setEvenRightIronSensorAddress(StringUtils.removePattern(settingsList.get(2), ","));
            }
            if (StringUtils.isNoneBlank(settingsList.get(3)) && !StringUtils.equals(settingsList.get(3), ",")) {
                SingletonFactory.getApplicationSettings().setEvenLeftIronSensorAddress(StringUtils.removePattern(settingsList.get(3), ","));
            }
            if (!StringUtils.isNoneBlank(settingsList.get(4)) && !StringUtils.equals(settingsList.get(4), ",")) {
                SingletonFactory.getApplicationSettings().setUnevenLeftIronSensorAddress(StringUtils.removePattern(settingsList.get(4), ","));
            }
            if (!StringUtils.isNoneBlank(settingsList.get(5)) && !StringUtils.equals(settingsList.get(5), ",")) {
                SingletonFactory.getApplicationSettings().setUnevenRightIronSensorAddress(StringUtils.removePattern(settingsList.get(5), ","));
            }
            if (!StringUtils.isNoneBlank(settingsList.get(6)) && !StringUtils.equals(settingsList.get(6), ",")) {
                SingletonFactory.getApplicationSettings().setEvenControlPortAddress(StringUtils.removePattern(settingsList.get(6), ","));
            }
            if (!StringUtils.isNoneBlank(settingsList.get(7)) && !StringUtils.equals(settingsList.get(7), ",")) {
                SingletonFactory.getApplicationSettings().setUnevenControlPortAddress(StringUtils.removePattern(settingsList.get(7), ","));
            }
            if (!StringUtils.isNoneBlank(settingsList.get(8)) && !StringUtils.equals(settingsList.get(8), ",")) {
                SingletonFactory.getApplicationSettings().setTemperatureLeftSensorAddress(StringUtils.removePattern(settingsList.get(8), ","));
            }
            if (!StringUtils.isNoneBlank(settingsList.get(9)) && !StringUtils.equals(settingsList.get(9), ",")) {
                SingletonFactory.getApplicationSettings().setTemperatureRightSensorAddress(StringUtils.removePattern(settingsList.get(9), ","));
            }
            System.out.println(SingletonFactory.getApplicationSettings().toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void convertDirectionsOfMovementData() {
    }
}
