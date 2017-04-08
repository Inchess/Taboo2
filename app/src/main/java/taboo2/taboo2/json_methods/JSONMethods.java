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

    private int wordToGuessIndex;
    private String wordToGuess;
    private JSONObject obj;
    private JSONObject arrayWithWordToGuess;
    private Random random;
    private static InputStream inputStream;;
    private String fileName = "Noc.json";
    private String arrayName = "Taboo_Easy";
    private static JSONArray arrayWithAllWordsToGuess;
    private static List<Integer> indexes_usedWordsToGuess;
    JSONObject jsonWithForbiddenWords = null;

    public JSONMethods(Context context) {
        try {
            inputStream = context.getAssets().open(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        init();
    }

    public void init() {
        random = new Random();
        if(arrayWithAllWordsToGuess == null) {
            indexes_usedWordsToGuess = new ArrayList<Integer>();
            createJSONObject();
            createArrayWithAllWordsToGuess();
        }
        initWordToGuess();
    }

    public void createJSONObject() {
        try {
            obj = new JSONObject(convertJSONFileToObject());
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public void createArrayWithAllWordsToGuess() {
        try {
            arrayWithAllWordsToGuess = obj.getJSONArray(arrayName);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void initWordToGuess() {
        int numOfKeyWords = arrayWithAllWordsToGuess.length();
        do {
            wordToGuessIndex = random.nextInt(numOfKeyWords);
        } while(checkIfWordWasAlreadySearched(wordToGuessIndex));
        indexes_usedWordsToGuess.add(wordToGuessIndex);
        try {
            arrayWithWordToGuess = arrayWithAllWordsToGuess.getJSONObject(wordToGuessIndex);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        wordToGuess = arrayWithWordToGuess.keys().next();
    }

    public String convertJSONFileToObject() {
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

    public void createListWithKeys(TextView[] textViews) {
        try {
            jsonWithForbiddenWords = arrayWithWordToGuess.getJSONObject(wordToGuess);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Iterator<?> keys = jsonWithForbiddenWords.keys();
        List<String> listOfKeysToForbiddenWords = new ArrayList<>();

        while( keys.hasNext() ) {
            String key = (String)keys.next();
            listOfKeysToForbiddenWords.add(key);
        }
        createListWithForbiddenWords(listOfKeysToForbiddenWords, textViews);
    }

    public void createListWithForbiddenWords(List<String> listOfKeysToForbiddenWords, TextView[] textViews) {
        List<String> listOfKeys = new ArrayList<>();
        int numOfForbiddenWords = listOfKeysToForbiddenWords.size();
        List<String> listWithWordsToPlay = new ArrayList<>();
        for(int i = 0; i < numOfForbiddenWords; i++) {
            if(listOfKeysToForbiddenWords.get(i).contains("REQ")) {
                listWithWordsToPlay.add(listOfKeysToForbiddenWords.get(i));
            }
            else {
                listOfKeys.add(listOfKeysToForbiddenWords.get(i));
            }
        }
        int numOfTextViews = textViews.length;
        int numOfTextViewsLeft = numOfTextViews - listWithWordsToPlay.size();
        for(int i = 0; i < numOfTextViewsLeft; i++) {
            int number;
            do {
                number = (int)(Math.random()*listOfKeys.size());
            } while(listWithWordsToPlay.contains(listOfKeys.get(number)));
            listWithWordsToPlay.add(listOfKeys.get(number));
        }
        addTextToFields(listWithWordsToPlay, textViews);
    }

    public void addTextToFields(List<String> listWithForbiddenWords, TextView[] textViews) {
        List<Integer> a = new ArrayList<>();
        for(TextView textView: textViews) {
            int number;
            do {
                number = (int) (Math.random() * textViews.length);
            } while(a.contains(number));
            a.add(number);
            String some = null;
            try {
                some = jsonWithForbiddenWords.getString(listWithForbiddenWords.get(number));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            textView.setText(some);
        }
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

}
