package taboo2.taboo2.json_methods;

import android.content.Context;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by y50-70 on 28.03.2017.
 */

public class JSONMethods {

    /* ==========================================
    ------------------VARIABLES------------------
    ========================================== */

    private int searchedWordNum;
    private String searchedWord;
    private JSONObject obj;
    private JSONObject searchedWordArray;
    private Random random;
    private static InputStream inputStream;;
    private String fileName = "Noc.json";
    private String arrayName = "Taboo_Easy";
    private static JSONArray jsonArray;
    private static List<Integer> searchedWordsIndexes;

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
        if(jsonArray == null) {
            searchedWordsIndexes = new ArrayList<Integer>();
            createJSONObject();
            createJSONArray();
        }
        addSearchedWord();
    }

    public void createJSONObject() {
        try {
            obj = new JSONObject(loadJSONFromAsset());
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public void createJSONArray() {
        try {
            jsonArray = obj.getJSONArray(arrayName);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void addSearchedWord() {
        int numOfKeyWords = jsonArray.length();
        do {
            searchedWordNum = random.nextInt(numOfKeyWords);
        } while(checkIfWordWasSearched(searchedWordNum));
        searchedWordsIndexes.add(searchedWordNum);
        try {
            searchedWordArray = jsonArray.getJSONObject(searchedWordNum);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        searchedWord = searchedWordArray.keys().next();
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
            JSONObject jsonSearchedWord = searchedWordArray.getJSONObject(searchedWord);
            int numOfForbiddenWords = jsonSearchedWord.length();
            List<Integer> usedWords = new ArrayList<Integer>();
            for(TextView field: forbiddenWords) {
                int random;
                do {
                    random = (int) (Math.random() * numOfForbiddenWords) + 1;
                } while (usedWords.contains(random));
                usedWords.add(random);
                String forbiddenWordNum = "word" + random;
                field.setText(jsonSearchedWord.getString(forbiddenWordNum));
            }
            searchedWordView.setText(searchedWord);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public boolean checkIfWordWasSearched(int randomNumber) {
        if(searchedWordsIndexes.contains(randomNumber)) {
            return true;
        }
        return false;
    }

}
