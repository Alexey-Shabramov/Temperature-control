package temperature.control.dict;

import java.util.ArrayList;
import java.util.Arrays;

public enum SystemOptions {
    FIRST("100"),
    SECOND("120"),
    THIRD("140"),
    FOURTH("160");

    private String title;

    public static ArrayList<String> OPTIONS = new ArrayList<String>(Arrays.asList(new String[]{FIRST.getTitle()
            , SECOND.getTitle()
            , THIRD.getTitle()
            , FOURTH.getTitle()}));

    SystemOptions(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
