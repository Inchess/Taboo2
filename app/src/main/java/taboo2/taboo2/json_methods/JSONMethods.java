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
    private InputStream inputStream;
    private JSONObject obj;
    private JSONObject searchedWordArray;
    private Random random;
    private String fileName = "Noc.json";
    private static JSONArray jsonArray;

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
        createJSONObject();
        createJSONArray();
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
            jsonArray = obj.getJSONArray("Taboo");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void addSearchedWord() {
        int numOfKeyWords = jsonArray.length();
        searchedWordNum = random.nextInt(numOfKeyWords);
        try {
            searchedWordArray = jsonArray.getJSONObject(searchedWordNum);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        searchedWord = searchedWordArray.keys().next();
    }

    public String loadJSONFromAsset() {
        String json = null;
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
}
