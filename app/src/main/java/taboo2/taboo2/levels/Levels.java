package taboo2.taboo2.levels;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Levels {

    private static List<Map<String, String>> listWithAllMaps = new ArrayList<>();
    public static List<Map<String, String>> easyLevel = new ArrayList<>();

    public static List<Map<String, String>> getListWithAllMaps() {
        return listWithAllMaps;
    }
}
