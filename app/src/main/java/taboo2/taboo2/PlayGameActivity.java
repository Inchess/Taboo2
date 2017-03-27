package taboo2.taboo2;

import android.graphics.Color;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import taboo2.taboo2.designs.Designs;

public class PlayGameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_game);
        init();
        addDesignToButtons();
        addMarginsToTextViews();
        createJSONObject();
        createJSONArray();
        addSearchedWord();
        addTextToFields();
    }

    /* ==========================================
    --------------HARDCODE VARIABLES-------------
    ========================================== */

    private int buttonsColor = Color.rgb(120, 120, 120);
    private int all_marginTop = 20;
    private int all_marginBottom = 30;
    private int all_marginSide = 50;

    /* ==========================================
    ------------------VARIABLES------------------
    ========================================== */

    private int searchedWordNum;
    private String searchedWord;
    private Global global;
    private Designs designs;
    private Button correctAnswer;
    private JSONObject obj;
    private JSONObject job;
    private Random random;
    private TextView searchingWord;
    private TextView word1;
    private TextView word2;
    private TextView word3;
    private TextView word4;
    private TextView word5;
    private TextView[] textViews;
    private String fileName = "Noc.json";
    private static JSONArray ar;

    /* ==========================================
    -------------------METHODS-------------------
    ========================================== */

    private void init() {
        global = new Global();
        searchingWord = (TextView) findViewById(R.id.searchingWord);
        word1 = (TextView) findViewById(R.id.word1);
        word2 = (TextView) findViewById(R.id.word2);
        word3 = (TextView) findViewById(R.id.word3);
        word4 = (TextView) findViewById(R.id.word4);
        word5 = (TextView) findViewById(R.id.word5);
        correctAnswer = (Button) findViewById(R.id.correct_answer);
        textViews = new TextView[]{word1, word2, word3, word4, word5};
        random = new Random();
        designs = new Designs();

    }

    private void addDesignToButtons() {

        for(TextView textView: textViews) {
            textView.setHeight(150);
            RoundRectShape rect = new RoundRectShape(
                    new float[] {10,10 , 10,10 , 10,10 , 10,10},
                    null,
                    null);
            ShapeDrawable bg = new ShapeDrawable(rect);
            bg.getPaint().setColor(buttonsColor);
            if (android.os.Build.VERSION.SDK_INT >= 16)
                textView.setBackground(bg);
            else {
                textView.setBackgroundDrawable(bg);
            }
            textView.setGravity(Gravity.CENTER);
            textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, 100);
        }
    }

    public void addMarginsToTextViews() {
        designs.views_setMargins(textViews, all_marginTop, all_marginSide);
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
            ar = obj.getJSONArray("Taboo");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getAssets().open(fileName);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    public void addSearchedWord() {
        int numOfSearcherWords = ar.length();
        searchedWordNum = random.nextInt(numOfSearcherWords);
        try {
            job = ar.getJSONObject(searchedWordNum);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        searchedWord = job.keys().next();
    }

    public void addTextToFields() {
        try {
            JSONObject jsonSearchedWord = job.getJSONObject(searchedWord);
            int numOfForbiddenWords = jsonSearchedWord.length();
            List<Integer> usedWords = new ArrayList<Integer>();
            for(TextView field: textViews) {
                int random;
                do {
                    random = (int) (Math.random() * numOfForbiddenWords) + 1;
                } while (usedWords.contains(random));
                usedWords.add(random);
                String forbiddenWordNum = "word" + random;
                field.setText(jsonSearchedWord.getString(forbiddenWordNum));
            }
            searchingWord.setText(searchedWord);
            correctAnswer.setText("OK");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void nextWord(View view) {
        super.recreate();
    }

}
