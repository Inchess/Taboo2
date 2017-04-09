package taboo2.taboo2.activities;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import taboo2.taboo2.R;
import taboo2.taboo2.designs.Designs;
import taboo2.taboo2.json_methods.JSONMethods;

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
        addForbiddenWords();
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

    private String searchedWord;
    private Designs designs;
    private Button correctAnswer;
    private static JSONMethods jsonMethods;
    private TextView word1;
    private TextView word2;
    private TextView word3;
    private TextView word4;
    private TextView word5;
    private TextView wordToGuess;
    private TextView[] textViews;

    /* ==========================================
    -------------------METHODS-------------------
    ========================================== */

    private void init() {
        word1 = (TextView) findViewById(R.id.word1);
        word2 = (TextView) findViewById(R.id.word2);
        word3 = (TextView) findViewById(R.id.word3);
        word4 = (TextView) findViewById(R.id.word4);
        word5 = (TextView) findViewById(R.id.word5);
        wordToGuess = (TextView) findViewById(R.id.searchingWord) ;
        correctAnswer = (Button) findViewById(R.id.correct_answer);
        textViews = new TextView[]{word1, word2, word3, word4, word5};
        designs = new Designs();
        jsonMethods = new JSONMethods(this);
        correctAnswer.setText("OK");

    }

    public void addForbiddenWords() {
        jsonMethods.initWordToGuess();
        jsonMethods.createJSONWithForbiddenWords();
        jsonMethods.createListWithKeysToForbiddenWords();
        jsonMethods.addRequiredWords();
        jsonMethods.addRestofWords(textViews);
        jsonMethods.modifyKeysToForbiddenWords();
        jsonMethods.addForbiddenWordsToFields(textViews);
        jsonMethods.addWordToGuessToField(wordToGuess);
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

    public void nextWord(View view) {
        super.recreate();
    }

}
