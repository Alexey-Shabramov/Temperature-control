package temperature.control.validator;

public class NumericTextfieldValidator {
    public static boolean validateTextfield(String text) {
        return ("".equals(text) || text.matches("[0-9]"));
    }
}
