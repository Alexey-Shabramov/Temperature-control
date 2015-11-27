package temperature.control.dict;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum Systems {
    PONAB("ПОНАБ"),
    ASDK("АСДК-Б"),
    DISK("ДИСК-Б"),
    KTSM("КТСМ-02");

    private String title;

    public static final List<String> SYSTEMS = new ArrayList<String>(Arrays.asList(new String[]{PONAB.getTitle()
            , ASDK.getTitle()
            , DISK.getTitle()
            , KTSM.getTitle()}));

    Systems(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
