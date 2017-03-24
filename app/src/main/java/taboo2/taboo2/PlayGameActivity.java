package taboo2.taboo2;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class PlayGameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_game);
        init();
        addDesignToButtons();
        createJSONObject();
        addSearchedWord();
        addTextToFields();
    }

    /* ==========================================
    ------------------VARIABLES------------------
    ========================================== */

    private int buttonsColor = Color.rgb(120, 120, 120);
    private Global global;
    private Button correctAnswer;
    private JSONObject obj;
    private TextView searchingWord;
    private TextView word1;
    private TextView word2;
    private TextView word3;
    private TextView word4;
    private TextView word5;
    private TextView[] textViews;
    private String fileName = "Noc.json";

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

    }

    private void addDesignToButtons() {

        for(TextView textView: textViews) {
            ViewGroup.MarginLayoutParams mlp = (ViewGroup.MarginLayoutParams) textView
                    .getLayoutParams();
            mlp.setMargins(20, 50, 20, 50);
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

    public void createJSONObject() {
        try {
            obj = new JSONObject(loadJSONFromAsset());
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

    }

    public void addTextToFields() {
        try {
            JSONArray ar = obj.getJSONArray("Noc");
            JSONObject job = ar.getJSONObject(0);
            int numOfForbiddenWords = job.length();
            List<Integer> usedWords = new ArrayList<Integer>();
            for(TextView field: textViews) {
                int random;
                do {
                    random = (int) (Math.random() * numOfForbiddenWords) + 1;
                } while (usedWords.contains(random));
                usedWords.add(random);
                String forbiddenWordNum = "word" + random;
                field.setText(job.getString(forbiddenWordNum));
            }
            correctAnswer.setText("OK");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void nextWord(View view) {
        super.recreate();
    }

}
