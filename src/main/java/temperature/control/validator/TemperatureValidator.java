package temperature.control.validator;

import org.apache.commons.lang3.StringUtils;
import temperature.control.entity.TemperatureOption;

public class TemperatureValidator {
    public static boolean validateTemperaturePaneOption(TemperatureOption temperatureOption) {
        return StringUtils.isNotEmpty(temperatureOption.getLeftTemperature())
                && StringUtils.isNotEmpty(temperatureOption.getRightTemperature())
                && StringUtils.isNotEmpty(temperatureOption.getDirectionOfMotion())
                && StringUtils.isNotEmpty(temperatureOption.getSystemOption());
    }
}
