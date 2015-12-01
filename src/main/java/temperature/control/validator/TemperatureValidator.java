package temperature.control.validator;

import org.apache.commons.lang3.StringUtils;
import temperature.control.entity.Option;

public class TemperatureValidator {
    public static boolean validateTemperaturePaneOption(Option option){
        return StringUtils.isNotEmpty(option.getLeftTemperature())
                && StringUtils.isNotEmpty(option.getRightTemperature())
                && StringUtils.isNotEmpty(option.getDirectionOfMotion())
                && StringUtils.isNotEmpty(option.getSystemOption());
    }
}
