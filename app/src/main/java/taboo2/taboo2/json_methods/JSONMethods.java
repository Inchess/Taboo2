package taboo2.taboo2.json_methods;

import android.content.Context;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

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
    private List<String> listOfKeysToForbiddenWords;
    private List<String> listOfKeys;
    private List<String> listWithWordsToPlay;
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

    public void createJSONWithForbiddenWords() {
        try {
            jsonWithForbiddenWords = arrayWithWordToGuess.getJSONObject(wordToGuess);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void createListWithKeysToForbiddenWords(TextView[] textViews) {
        Iterator<?> keys = jsonWithForbiddenWords.keys();
        listOfKeysToForbiddenWords = new ArrayList<>();
        while( keys.hasNext() ) {
            String key = (String)keys.next();
            listOfKeysToForbiddenWords.add(key);
        }
    }

    public void addRequiredWords() {
        listOfKeys = new ArrayList<>();
        int numOfForbiddenWords = listOfKeysToForbiddenWords.size();
        listWithWordsToPlay = new ArrayList<>();
        for(int i = 0; i < numOfForbiddenWords; i++) {
            if(listOfKeysToForbiddenWords.get(i).contains("REQ")) {
                listWithWordsToPlay.add(listOfKeysToForbiddenWords.get(i));
            }
            else {
                listOfKeys.add(listOfKeysToForbiddenWords.get(i));
            }
        }
    }

    public void addRestofWords(TextView[] forbiddenWordstextViews) {
        int numOfTextViews = forbiddenWordstextViews.length;
        int numOfTextViewsLeft = numOfTextViews - listWithWordsToPlay.size();
        for(int i = 0; i < numOfTextViewsLeft; i++) {
            int number;
            do {
                number = (int)(Math.random()*listOfKeys.size());
            } while(listWithWordsToPlay.contains(listOfKeys.get(number)));
            listWithWordsToPlay.add(listOfKeys.get(number));
        }
    }

    public void addForbiddenWordsToFields(TextView[] textViews) {
        List<Integer> a = new ArrayList<>();
        for(TextView textView: textViews) {
            int number;
            do {
                number = (int) (Math.random() * textViews.length);
            } while(a.contains(number));
            a.add(number);
            String some = null;
            try {
                some = jsonWithForbiddenWords.getString(listWithWordsToPlay.get(number));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            textView.setText(some);
        }
    }

    public void addWordToGuessToField(TextView wordToGuessTextView) {
        wordToGuessTextView.setText(wordToGuess);
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
