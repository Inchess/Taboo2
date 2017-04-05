package taboo2.taboo2.json_methods;

import android.content.Context;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by y50-70 on 28.03.2017.
 */

public class JSONMethods {

    /* ==========================================
    ------------------VARIABLES------------------
    ========================================== */

    private int wordToGuessIndex;
    private String wordToGuess;
    private JSONObject obj;
    private JSONObject arrayWithSearchedWord;
    private Random random;
    private static InputStream inputStream;;
    private String fileName = "Noc.json";
    private String arrayName = "Taboo_Easy";
    private static JSONArray arrayWithAllWordsToGuess;
    private static List<Integer> usedWordsToGuessIndexes;

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
            usedWordsToGuessIndexes = new ArrayList<Integer>();
            createJSONObject();
            createArrayWithAllWordsToGuess();
        }
        initWordToGuess();
    }

    public void createJSONObject() {
        try {
            obj = new JSONObject(loadJSONFromAsset());
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
        } while(checkIfWordWasSearched(wordToGuessIndex));
        usedWordsToGuessIndexes.add(wordToGuessIndex);
        try {
            arrayWithSearchedWord = arrayWithAllWordsToGuess.getJSONObject(wordToGuessIndex);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        wordToGuess = arrayWithSearchedWord.keys().next();
    }

    public String loadJSONFromAsset() {
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

    public void addTextToFields(TextView searchedWordView, TextView[] forbiddenWords) {
        try {
            JSONObject jsonSearchedWord = arrayWithSearchedWord.getJSONObject(wordToGuess);
            int numOfForbiddenWords = jsonSearchedWord.length();
            List<Integer> usedWords = new ArrayList<Integer>();
            List<String> listOfKeys = new ArrayList<String>();
            for(int i = 0; i < numOfForbiddenWords; i++) {
                listOfKeys.add(arrayWithSearchedWord.keys().next());
                int b = listOfKeys.size();
            }
            for(TextView field: forbiddenWords) {
                int random;
                do {
                    random = (int) (Math.random() * numOfForbiddenWords) + 1;
                } while (usedWords.contains(random));
                usedWords.add(random);
                String forbiddenWordNum = "word" + random;
                field.setText(jsonSearchedWord.getString(forbiddenWordNum));
            }
            searchedWordView.setText(wordToGuess);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public boolean checkIfWordWasSearched(int randomNumber) {
        if(usedWordsToGuessIndexes.contains(randomNumber)) {
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
