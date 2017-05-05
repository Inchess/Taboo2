package taboo2.taboo2.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import taboo2.taboo2.R;
import taboo2.taboo2.designs.Designs;
import taboo2.taboo2.global.Global;
import taboo2.taboo2.json_methods.JSONMethods;
import taboo2.taboo2.scores.GreenTeamScores;
import taboo2.taboo2.scores.RedTeamScores;

public class PlayGameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_game);
        init();
        getValuesFromJSON();
        createTextViews();
        addWordsToTextViews();
        addTextHeightAndLocationToTextViews();
        addMarginsToTextViews();
        addColorAndRadius();
        setTextHeight();
        addScores();
        getGlobalValues();
    }

    /* ==========================================
    -------------HARDCODED VARIABLES-------------
    ========================================== */

    private int fieldsColor = Color.rgb(120, 120, 120);
    private int all_marginTop = 20;
    private int all_marginSide = 50;
    private int radius = 10;
    private int fieldHeight = 150;
    private int textHeight = 80;

    /* ==========================================
    ------------------VARIABLES------------------
    ========================================== */

    private int points_correctAnswer;
    private int points_incorrectAnswer;
    private int points_toWin;
    private Global global;
    private GreenTeamScores greenTeamScores;
    private RedTeamScores redTeamScores;
    private Designs designs;
    private Button correctAnswer;
    private Button incorrectAnswer;
    // To remove
    private Button changeTeam;
    private static JSONMethods jsonMethods;
    private TextView scoresGreen;
    private TextView scoresRed;
    private TextView wordToGuess;
    private TextView[] textViews;
    private int numberForbiddenWords;
    private LinearLayout playGame;

    /* ==========================================
    -------------------METHODS-------------------
    ========================================== */

    private void init() {
        playGame = (LinearLayout) findViewById(R.id.layout$_forbidden_words);
        scoresGreen = (TextView) findViewById(R.id.score_green_team);
        scoresRed = (TextView) findViewById(R.id.score_red_team);
        global = new Global();
        numberForbiddenWords = global.getNumberOfForbiddenWords();
        wordToGuess = (TextView) findViewById(R.id.searchingWord) ;
        correctAnswer = (Button) findViewById(R.id.correct_answer);
        incorrectAnswer = (Button) findViewById(R.id.incorrect_answer);
        changeTeam = (Button) findViewById(R.id.change_team);
        designs = new Designs();
        jsonMethods = new JSONMethods(this);
        greenTeamScores = new GreenTeamScores();
        redTeamScores = new RedTeamScores();
        correctAnswer.setText("Correct");
        incorrectAnswer.setText("Incorrect");
        changeTeam.setText("End turn");
    }

    private void createTextViews() {
        TextView temp;
        int numOfForbiddenWords = Math.min(jsonMethods.getList_keysToForbiddenWords().size(),
                global.getNumberOfForbiddenWords());
        textViews = new TextView[numOfForbiddenWords];
        for(int i = 0; i < numOfForbiddenWords; i++) {
            temp = new TextView(this);
            temp.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
            playGame.addView(temp);
            textViews[i] = temp;
        }
    }

    public void getValuesFromJSON() {
        jsonMethods.initWordToGuess();
        jsonMethods.createJSONWithForbiddenWords();
        jsonMethods.createListWithKeysToForbiddenWords();
    }

    public void addWordsToTextViews() {
        jsonMethods.addRequiredWords();
        jsonMethods.addRestOfWords(textViews);
        jsonMethods.modifyKeysToForbiddenWords();
        jsonMethods.addForbiddenWordsToFields(textViews);
        jsonMethods.addWordToGuessToField(wordToGuess);
    }

    public void addScores() {
        scoresGreen.setText(String.format("%d", greenTeamScores.getTeamScore()));
        scoresRed.setText(String.format("%d", redTeamScores.getTeamScore()));
        scoresGreen.setBackgroundColor(Color.GREEN);
        scoresRed.setBackgroundColor(Color.RED);
        //scoresRed.setText(Integer.toString(scoresMethods.getRedTeamScore()));
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
        designs.textViews_buttonsHeight(textViews, fieldHeight);
    }

    public void correctAnswer(View view) {
        Global.getCurrentPlayingTeam().addPointToTeamScore(points_correctAnswer);
        if(Global.getCurrentPlayingTeam().getTeamScore() >= points_toWin) {
            teamWon(view);
        }
        else {
            super.recreate();
        }
    }

    public void incorrectAnswer(View view) {
        Global.getCurrentPlayingTeam().addPointToTeamScore(points_incorrectAnswer);
        super.recreate();
    }

    public void teamWon(View view) {
        Intent endGameActivity = new Intent(this, EndGameActivity.class);
        startActivity(endGameActivity);
    }

    public void getGlobalValues() {
        points_correctAnswer = global.getPoints_correctAnswer();
        points_incorrectAnswer = global.getPoints_incorrectAnswer();
        points_toWin = global.getPointsToWin();
    }

    // To remove at the end
    public void endTurn(View view) {
        Intent endTurnActivity = new Intent(this, EndTurnActivity.class);
        startActivity(endTurnActivity);
    }

}
