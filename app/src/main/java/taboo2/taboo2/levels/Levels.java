package taboo2.taboo2.levels;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Levels {

    public static List<Map<String, String>> easyLevel = new ArrayList<>();

    static {
        easyLevel.add(new HashMap<String, String>() {{
            put("searched", "Noc 3 average");
            put("REQ_word1", "----Ciemność----");
            put("REQ_word2", "----Księżyc----");
            put("REQ_word3", "----Dzień----");
            put("word4", "Spać");
            put("word5", "Słońce");
            put("word6", "Łóżko");
            put("word7", "Duch");
        }});
        easyLevel.add(new HashMap<String, String>() {{
            put("searched", "Dzień 2 average");
            put("word1", "Praca");
            put("REQ_word2", "----Noc----");
            put("REQ_word3", "----Słońce----");
            put("word4", "Ranek");
            put("word5", "Doba");
        }});
    }

}
