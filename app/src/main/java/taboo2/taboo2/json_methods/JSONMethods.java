package taboo2.taboo2.json_methods;

import android.content.Context;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;


public class JSONMethods {

    /* ==========================================
    ------------------VARIABLES------------------
    ========================================== */

    private String string_wordToGuess;
    private JSONObject wholeJSON;
    private JSONObject array_wordToGuess;
    private Random random;
    private static InputStream inputStream;
    private String fileName = "Noc.json";
    private String arrayName = "Taboo_Easy";
    private static JSONArray array_AllWordsToGuess;
    private static List<Integer> indexes_usedWordsToGuess;
    private List<String> list_keysToForbiddenWords;
    private List<String> list_notRequiredWords;
    private List<String> list_keysToWordsToTextViews;
    private List<String> list_wordsToTextViews;
    private JSONObject json_ForbiddenWords = null;

    public JSONMethods(Context context) {
        try {
            inputStream = context.getAssets().open(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        init();
    }

    private void init() {
        random = new Random();
        if(array_AllWordsToGuess == null) {
            indexes_usedWordsToGuess = new ArrayList<>();
            createJSONObject();
            createArrayWithAllWordsToGuess();
        }
    }

    private void createJSONObject() {
        try {
            wholeJSON = new JSONObject(convertJSONFileToObject());
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private void createArrayWithAllWordsToGuess() {
        try {
            array_AllWordsToGuess = wholeJSON.getJSONArray(arrayName);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void initWordToGuess() {
        int index_wordToGuess;
        int numOfWordsToGuess = array_AllWordsToGuess.length();
        //do {
            index_wordToGuess = random.nextInt(numOfWordsToGuess);
        //} while(checkIfWordWasAlreadySearched(index_wordToGuess));
        indexes_usedWordsToGuess.add(index_wordToGuess);
        try {
            array_wordToGuess = array_AllWordsToGuess.getJSONObject(index_wordToGuess);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        string_wordToGuess = array_wordToGuess.keys().next();
    }

    private String convertJSONFileToObject() {
        String json;
        try {
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    public void createJSONWithForbiddenWords() {
        try {
            json_ForbiddenWords = array_wordToGuess.getJSONObject(string_wordToGuess);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void createListWithKeysToForbiddenWords() {
        Iterator<?> keys = json_ForbiddenWords.keys();
        list_keysToForbiddenWords = new ArrayList<>();
        while( keys.hasNext() ) {
            String key = (String)keys.next();
            list_keysToForbiddenWords.add(key);
        }
    }

    public void addRequiredWords() {
        list_notRequiredWords = new ArrayList<>();
        list_keysToWordsToTextViews = new ArrayList<>();
        for(String key: list_keysToForbiddenWords) {
            if(key.contains("REQ")) {
                list_keysToWordsToTextViews.add(key);
            }
            else {
                list_notRequiredWords.add(key);
            }
        }
    }

    public void addRestofWords(TextView[] forbiddenWordstextViews) {
        int numOfTextViews = forbiddenWordstextViews.length;
        int numOfTextViewsLeft = numOfTextViews - list_keysToWordsToTextViews.size();
        for(int i = 0; i < numOfTextViewsLeft; i++) {
            int number;
            do {
                number = (int)(Math.random()* list_notRequiredWords.size());
            } while(list_keysToWordsToTextViews.contains(list_notRequiredWords.get(number)));
            list_keysToWordsToTextViews.add(list_notRequiredWords.get(number));
        }
    }

    public void modifyKeysToForbiddenWords(){
        list_wordsToTextViews = new ArrayList<>();
        String forbiddenWord = null;
        for(String key: list_keysToWordsToTextViews) {
            try {
                forbiddenWord = json_ForbiddenWords.getString(key);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            list_wordsToTextViews.add(forbiddenWord);
        }
    }


    public void addForbiddenWordsToFields(TextView[] textViews) {
        List<Integer> indexesAlreadyUsed = new ArrayList<>();
        for(TextView textView: textViews) {
            int number;
            do {
                number = (int) (Math.random() * textViews.length);
            } while(indexesAlreadyUsed.contains(number));
            indexesAlreadyUsed.add(number);
            String forbiddenWord = list_wordsToTextViews.get(number);
            textView.setText(forbiddenWord);
        }
    }

    public void addWordToGuessToField(TextView wordToGuessTextView) {
        wordToGuessTextView.setText(string_wordToGuess);
    }

    public boolean checkIfWordWasAlreadySearched(int randomNumber) {
        if(indexes_usedWordsToGuess.contains(randomNumber)) {
            return true;
        }
        return false;

        /*
        Dodać opcję, że jeśli wykorzysta się wszystkie słowa to
        pojawi się informacja że słowa zostały zresetowane
        i będą wyświetlane od nowa
         */
    }

    /* ==========================================
    -------------------SETTERS-------------------
    ========================================== */
}
