package taboo2.taboo2.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

import taboo2.taboo2.R;
import taboo2.taboo2.designs.Designs;
import taboo2.taboo2.global.Global;
import taboo2.taboo2.json_methods.JSONMethods;
import taboo2.taboo2.levels.Levels;
import taboo2.taboo2.scores.GreenTeamScores;
import taboo2.taboo2.scores.RedTeamScores;

public class PlayGameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_game);
        init();
        tryLevels();
        getValuesFromJSON();
        createTextViews();
        addWordsToTextViews();
        addTextHeightAndLocationToTextViews();
        addMarginsToTextViews();
        addColorAndRadius();
        setTextHeight();
        addScores();
        getGlobalValues();
        addTimer();
    }

    private void tryLevels() {
        List<Map<String, String>> a = levels.easyLevel;
        List<Map<String, String>> b = levels.easyLevel;
        Map<String, String> c = levels.easyLevel.get(0);
        String d = levels.easyLevel.get((int)(Math.random()*levels.easyLevel.size())).get("searched");
        String e = levels.easyLevel.get((int)(Math.random()*levels.easyLevel.size())).get("searched");
        String f = levels.easyLevel.get((int)(Math.random()*levels.easyLevel.size())).get("searched");
        String g = levels.easyLevel.get((int)(Math.random()*levels.easyLevel.size())).get("searched");
        String h = levels.easyLevel.get((int)(Math.random()*levels.easyLevel.size())).get("searched");
        String i = levels.easyLevel.get((int)(Math.random()*levels.easyLevel.size())).get("searched");
        String j = levels.easyLevel.get((int)(Math.random()*levels.easyLevel.size())).get("searched");
        String k = levels.easyLevel.get((int)(Math.random()*levels.easyLevel.size())).get("searched");


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
    private Levels levels;
    private Button incorrectAnswer;
    // To remove
    private Button changeTeam;
    private static JSONMethods jsonMethods;
    private TextView scoresGreen;
    private TextView scoresRed;
    private TextView wordToGuess;
    private TextView[] textViews;
    private ProgressBar progressBar;
    private CountDownTimer countDownTimer;
    private int i = 0;
    private TextView timer;
    private int numberForbiddenWords;
    private LinearLayout playGame;
    private int progressStatus = 0;
    private Handler handler;
    private int progressBarMaximumValue;
    private int countDownStatus;

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
        timer = (TextView) findViewById(R.id.timer);
        designs = new Designs();
        jsonMethods = new JSONMethods();
        greenTeamScores = new GreenTeamScores();
        redTeamScores = new RedTeamScores();
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        correctAnswer.setText("Correct");
        incorrectAnswer.setText("Incorrect");
        changeTeam.setText("End turn");
        handler = new Handler();
        progressBarMaximumValue = global.getTimePerPlayer();
        countDownStatus = progressBarMaximumValue;
        levels = new Levels();
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
        jsonMethods.createArrayWithAllWordsToGuess();
        jsonMethods.initWordToGuess();
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
            recreateJson();
        }
    }

    private void recreateJson() {
        jsonMethods = new JSONMethods();
        getValuesFromJSON();
        addWordsToTextViews();
        addScores();
    }

    public void incorrectAnswer(View view) {
        Global.getCurrentPlayingTeam().addPointToTeamScore(points_incorrectAnswer);
        recreateJson();
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

    public void addTimer() {
        progressBar.setMax(progressBarMaximumValue);
        progressBar.setProgress(progressStatus);
        timer.setText(countDownStatus+"");
        new Thread(new Runnable() {
            public void run() {
                while (progressStatus < progressBarMaximumValue) {
                    progressStatus +=1;
                    countDownStatus -=1;
                    handler.post(new Runnable() {
                        public void run() {
                            progressBar.setProgress(progressStatus);
                            timer.setText(countDownStatus+"");
                        }
                    });
                    try{
                        Thread.sleep(1000);
                    }catch(InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
