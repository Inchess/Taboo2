package taboo2.taboo2.json_methods;

import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.StringTokenizer;

import taboo2.taboo2.global.Global;
import taboo2.taboo2.levels.AdvancedLevel;
import taboo2.taboo2.levels.DifficultLevel;
import taboo2.taboo2.levels.EasyLevel;
import taboo2.taboo2.levels.Levels;
import taboo2.taboo2.levels.VeryDifficultLevel;


public class JSONMethods {

    public JSONMethods() {
        if (allWords == null) {
            createListWithWordsFromChosenLevels();
        }
        init();
    }

    private void init() {
        levels = new Levels();
        list_keysToForbiddenWords = new ArrayList<>();
        random = new Random();
    }

    /* ==========================================
    ------------------VARIABLES------------------
    ========================================== */

    private String string_wordToGuess;
    private static List<Map<String, String>> allMaps;
    private static List<Map<String, String>> allMapsCopy;
    private static Map<String, String> allWords;
    private List<String> list_keysToForbiddenWords;
    private List<String> list_notRequiredWords;
    private List<String> list_requiredWords;
    private List<String> list_wordsToTextViews;
    private Levels levels;
    private EasyLevel easyLevel;
    private AdvancedLevel advancedLevel;
    private DifficultLevel difficultLevel;
    private VeryDifficultLevel veryDifficultLevel;
    private Map<String, String> randomMap;
    private Random random;

    /* ==========================================
    ------------------AAAAAAAAA------------------
    ========================================== */

    private void createListWithWordsFromChosenLevels() {
        allMaps = new ArrayList<>();
        if (Global.isEasyLevelChecked()) {
            easyLevel = new EasyLevel();
            easyLevel.addEasyLevelWords(allMaps);
        }
        if (Global.isAverageLevelChecked()) {
            advancedLevel = new AdvancedLevel();
            advancedLevel.addAdvantageLevelWords(allMaps);
        }
        if (Global.isDifficultLevelChecked()) {
            difficultLevel = new DifficultLevel();
            difficultLevel.addDifficultLevelWords(allMaps);
        }
        if (Global.isVeryDifficultLevelChecked()) {
            veryDifficultLevel = new VeryDifficultLevel();
            veryDifficultLevel.addVeryDifficultLevelWords(allMaps);
        }

        allMapsCopy = allMaps;
    }

    public void getRandomMap() {
        int randomInt = (int)(Math.random()*allMapsCopy.size());
        randomMap = allMapsCopy.get(randomInt);
        allMapsCopy.remove(randomInt);
    }

    public void initWordToGuess() {
        string_wordToGuess = randomMap.get("searched");
    }

    public void createListWithKeysToForbiddenWords() {
        for ( String key: randomMap.keySet() ) {
            list_keysToForbiddenWords.add(key);
        }
    }

    public void addRequiredWords() {
        list_notRequiredWords = new ArrayList<>();
        list_requiredWords = new ArrayList<>();
        for (String key : list_keysToForbiddenWords) {
            if (key.contains("REQ")) {
                list_requiredWords.add(key);
            } else if (key.contains("word")){
                list_notRequiredWords.add(key);
            }
        }
    }

    public void addRestOfWords(TextView[] forbiddenWordstextViews) {
        int numOfTextViews = forbiddenWordstextViews.length;
        int numOfTextViewsLeft = numOfTextViews - list_requiredWords.size();
        for (int i = 0; i < numOfTextViewsLeft; i++) {
            int number;
            do {
                number = (int) (Math.random() * list_notRequiredWords.size());
            } while (list_requiredWords.contains(list_notRequiredWords.get(number)));
            list_requiredWords.add(list_notRequiredWords.get(number));
        }
    }

    /* ==========================================
    ------------------AAAAAAAAA------------------
    ========================================== */

    public void getForbiddenWordsFromKeys() {
        list_wordsToTextViews = new ArrayList<>();
        String forbiddenWord = null;
        for (String key : list_requiredWords) {
            forbiddenWord = randomMap.get(key);
            list_wordsToTextViews.add(forbiddenWord);
        }
    }


    public void addForbiddenWordsToFields(TextView[] textViews) {
        List<Integer> indexesAlreadyUsed = new ArrayList<>();
        for (TextView textView : textViews) {
            int number;
            do {
                number = (int) (Math.random() * textViews.length);
            } while (indexesAlreadyUsed.contains(number));
            indexesAlreadyUsed.add(number);
            String forbiddenWord = list_wordsToTextViews.get(number);
            textView.setText(forbiddenWord);
        }
    }

    public void addWordToGuessToField(TextView wordToGuessTextView) {
        wordToGuessTextView.setText(string_wordToGuess);
    }

    /* ==========================================
    -------------------GETTERS-------------------
    ========================================== */

    public List<String> getList_wordsToTextViews() {
        return list_wordsToTextViews;
    }

    public List<String> getList_keysToForbiddenWords() {
        return list_keysToForbiddenWords;
    }
}
