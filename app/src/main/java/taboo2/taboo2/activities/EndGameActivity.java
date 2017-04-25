package taboo2.taboo2.activities;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import taboo2.taboo2.R;
import taboo2.taboo2.designs.Designs;
import taboo2.taboo2.global.Global;
import taboo2.taboo2.scores.GreenTeamScores;
import taboo2.taboo2.scores.RedTeamScores;

public class EndGameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_game);
        init();
        addTextHeightAndLocationToTextViews();
        addMarginsToTextViews();
        addColorAndRadius();
        setTextHeight();
        addText();
    }

    /* ==========================================
    --------------HARDCODE VARIABLES-------------
    ========================================== */

    private int fieldsColor = Color.rgb(120, 120, 120);
    private int all_marginTop = 50;
    private int all_marginBottom = 30;
    private int all_marginSide = 50;
    private int radius = 20;
    private int fieldHeight = 150;
    private int textHeight = 80;

    /* ==========================================
    ------------------VARIABLES------------------
    ========================================== */

    private Button newGame;
    private Designs designs;
    private Global global;
    private GreenTeamScores greenTeamScores;
    private RedTeamScores redTeamScores;
    private TextView greenScores;
    private TextView redScores;
    private TextView winningTeam;
    private TextView[] textViews;

    /* ==========================================
    -------------------METHODS-------------------
    ========================================== */

    public void init() {
        newGame = (Button) findViewById(R.id.new_game);
        designs = new Designs();
        greenScores = (TextView) findViewById(R.id.end_game_green_team_scores);
        redScores = (TextView) findViewById(R.id.end_game_red_team_scores);
        winningTeam = (TextView) findViewById(R.id.winning_team);
        textViews = new TextView[]{greenScores, redScores, winningTeam};
        greenTeamScores = new GreenTeamScores();
        redTeamScores = new RedTeamScores();
        global = new Global();
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

    public void addText() {
        newGame.setText("Start new game");
        greenScores.setText(Integer.toString(greenTeamScores.getTeamScore()));
        redScores.setText(Integer.toString(redTeamScores.getTeamScore()));
        winningTeam.setText(global.getCurrentTeamText() + " has won the game!");
    }

    public void newGame(View view) {
        resetTeamScores();
        Global.changeTeam();
        Intent playGameActivity = new Intent(this, PlayGameActivity.class);
        startActivity(playGameActivity);
    }

    public void resetTeamScores() {
        greenTeamScores.setTeamScore(0);
        redTeamScores.setTeamScore(0);
    }
}
