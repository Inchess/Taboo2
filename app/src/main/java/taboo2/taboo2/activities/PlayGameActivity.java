package taboo2.taboo2.activities;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

import taboo2.taboo2.R;
import taboo2.taboo2.designs.Designs;
import taboo2.taboo2.json_methods.JSONMethods;
import taboo2.taboo2.phone_params.Global;

public class PlayGameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_game);
        init();
        addTextHeightAndLocationToTextViews();
        addMarginsToTextViews();
        addColorAndRadius();
        setTextHeight();
        createListWithKeys();
        //addTextToFields();
    }

    /* ==========================================
    --------------HARDCODE VARIABLES-------------
    ========================================== */

    private int fieldsColor = Color.rgb(120, 120, 120);
    private int all_marginTop = 20;
    private int all_marginBottom = 30;
    private int all_marginSide = 50;
    private int radius = 10;
    private int fieldHeight = 150;
    private int textHeight = 80;

    /* ==========================================
    ------------------VARIABLES------------------
    ========================================== */

    private int searchedWordNum;
    private String searchedWord;
    private Global global;
    private Designs designs;
    private Button correctAnswer;
    private static JSONMethods jsonMethods;
    private Random random;
    private TextView searchingWord;
    private TextView word1;
    private TextView word2;
    private TextView word3;
    private TextView word4;
    private TextView word5;
    private TextView[] textViews;

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
        jsonMethods = new JSONMethods(this);
        correctAnswer.setText("OK");

    }

    public void createListWithKeys() {
        jsonMethods.createListWithKeys(textViews);
    }

    private void addTextHeightAndLocationToTextViews() {
        designs.views_textHeightAndLocation(textViews, textHeight);
    }

    public void addMarginsToTextViews() {
        designs.views_setMargins(textViews, all_marginTop, all_marginSide);
    }

    public void addColorAndRadius() {
        designs.views_addColorAndRadius(textViews, fieldsColor, radius);
    }

    public void setTextHeight() {
        designs.textViews_textHeight(textViews, fieldHeight);
    }

/*    public void addTextToFields() {
        jsonMethods.addTextToFields(searchingWord, textViews);
    }*/

    public void nextWord(View view) {
        super.recreate();
    }

}
