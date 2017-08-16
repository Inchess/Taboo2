package taboo2.taboo2.levels;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EasyLevel {

    public void addEasyLevelWords(List<Map<String, String>> listWithAllMaps) {
        listWithAllMaps.add(new HashMap<String, String>() {{
            put("searched", "Noc 3 easy");
            put("REQ_word1", "----Ciemność----");
            put("REQ_word2", "----Księżyc----");
            put("REQ_word3", "----Dzień----");
            put("word4", "Spać");
            put("word5", "Słońce");
            put("word6", "Łóżko");
            put("word7", "Duch");
        }});
        listWithAllMaps.add(new HashMap<String, String>() {{
            put("searched", "Dzień 2 easy");
            put("word1", "Praca");
            put("REQ_word2", "----Noc----");
            put("REQ_word3", "----Słońce----");
            put("word4", "Ranek");
            put("word5", "Doba");
        }});
    }

}
