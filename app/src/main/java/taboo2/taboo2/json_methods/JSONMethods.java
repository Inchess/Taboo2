package taboo2.taboo2.json_methods;

import android.content.Context;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import taboo2.taboo2.global.Global;
import taboo2.taboo2.levels.Levels;


public class JSONMethods {

    public JSONMethods() {
        createFilesArray();
        init();
    }

    private void init() {
        levels = new Levels();
        list_keysToForbiddenWords = new ArrayList<>();
    }

    /* ==========================================
    ------------------VARIABLES------------------
    ========================================== */

    private String string_wordToGuess;
    private static List<String> files;
    private static Map<String, String> allWords;
    private List<String> list_keysToForbiddenWords;
    private List<String> list_notRequiredWords;
    private List<String> list_requiredWords;
    private List<String> list_wordsToTextViews;
    private Levels levels;

    public void createArrayWithAllWordsToGuess() {
        allWords = levels.easyLevel.get((int)(Math.random()*levels.easyLevel.size()));
    }

    /* ==========================================
    ------------------AAAAAAAAA------------------
    ========================================== */

    private void createFilesArray() {
        files = new ArrayList<>();
        if (Global.isEasyLevelChecked()) {
            files.add("Taboo_easy.json");
        }
        if (Global.isAverageLevelChecked()) {
            files.add("Taboo_Average.json");
        }
        if (Global.isDifficultLevelChecked()) {
            files.add("Taboo_difficult.json");
        }
        if (Global.isVeryDifficultLevelChecked()) {
            files.add("Taboo_veryDifficult.json");
        }
    }

    /* ==========================================
    ------------------AAAAAAAAA------------------
    ========================================== */

    public void initWordToGuess() {
        string_wordToGuess = allWords.get("searched");
    }

    public void createListWithKeysToForbiddenWords() {
        for ( String key: allWords.keySet() ) {
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

    public void modifyKeysToForbiddenWords() {
        list_wordsToTextViews = new ArrayList<>();
        String forbiddenWord = null;
        for (String key : list_requiredWords) {
            forbiddenWord = allWords.get(key);
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
