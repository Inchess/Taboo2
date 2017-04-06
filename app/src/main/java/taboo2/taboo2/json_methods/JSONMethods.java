package taboo2.taboo2.json_methods;

import android.content.Context;
import android.util.Log;
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
            int number = -1;
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
            //listWithForbiddenWords.remove(number);
        }
    }

/*    public void generateArrayWithKeyToForbiddenWords(TextView[] textViewsForForbiddenWords) {
        int numOfTextViewForForbiddenWords = textViewsForForbiddenWords.length;
        JSONObject jsonWithForbiddenWords = null;
        try {
            jsonWithForbiddenWords = arrayWithWordToGuess.getJSONObject(wordToGuess);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        int numOfForbiddenWords = jsonWithForbiddenWords.length();
        Iterator<?> keys = jsonWi
    }

    public void addTextToFields(TextView textViewForWordToGuess,
                                TextView[] textViewsForForbiddenWords) {
        try {
            JSONObject jsonWithForbiddenWords = arrayWithWordToGuess.getJSONObject(wordToGuess);
            List<Integer> forbiddenWordsAlreadyUsed = new ArrayList<Integer>();
            List<String> listOfKeysToForbiddenWords = new ArrayList<String>();
            Iterator<?> keys = jsonWithForbiddenWords.keys();
            List<String> aaaa = new ArrayList<>();

            while( keys.hasNext() ) {
                String key = (String)keys.next();
                aaaa.add(key);
                Log.i("", aaaa.toString());
                if ( jsonWithForbiddenWords.get(key) instanceof JSONObject ) {

                }
            }
            for(int i = 0; i < numOfForbiddenWords; i++) {
                listOfKeysToForbiddenWords.add(arrayWithWordToGuess.keys().next());
                int b = listOfKeysToForbiddenWords.size();
            }
            for(TextView field: textViewsForForbiddenWords) {
                int random;
                do {
                    random = (int) (Math.random() * numOfForbiddenWords) + 1;
                } while (forbiddenWordsAlreadyUsed.contains(random));
                forbiddenWordsAlreadyUsed.add(random);
                String forbiddenWordNum = "word" + random;
                field.setText(jsonWithForbiddenWords.getString(forbiddenWordNum));
            }
            textViewForWordToGuess.setText(aaaa.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }*/

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
