package temperature.control.util;

import java.util.List;

public class AlertFXUtil {
    private static StringBuilder stringBuilder = new StringBuilder();

    public static String prepareAlertMessage(List<String> errorList) {
        for (String error : errorList) {
            stringBuilder.append("- ")
                    .append(error)
                    .append(" ")
                    .append("\n");
        }
        String preparedString = stringBuilder.toString();
        stringBuilder.setLength(0);
        errorList.clear();
        return preparedString;
    }
}
