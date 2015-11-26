package temperature.control.dict;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum SystemOptions {
    FIRST("100"),
    SECOND("120"),
    THIRD("140"),
    FOURTH("160");

    private String title;

    public static final List<String> TITLES = new ArrayList<String>(Arrays.asList(new String[]{FIRST.getTitle()
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
