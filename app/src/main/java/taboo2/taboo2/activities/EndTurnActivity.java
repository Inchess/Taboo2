package taboo2.taboo2.activities;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import taboo2.taboo2.R;
import taboo2.taboo2.designs.Designs;
import taboo2.taboo2.global.Global;
import taboo2.taboo2.scores.GreenTeamScores;
import taboo2.taboo2.scores.RedTeamScores;

public class EndTurnActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_turn);
        init();
        addTextHeightAndLocationToTextViews();
        addMarginsToTextViews();
        addColorAndRadius();
        setTextHeight();
        changeTeam();
        addText();
    }

    /* ==========================================
    -------------HARDCODED VARIABLES-------------
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

    private Button nextRound;
    private Designs designs;
    private Global global;
    private GreenTeamScores greenTeamScores;
    private RedTeamScores redTeamScores;
    private TextView greenScores;
    private TextView redScores;
    private TextView nowPlays;
    private TextView[] textViews;

    /* ==========================================
    -------------------METHODS-------------------
    ========================================== */

    private void init() {
        nextRound = (Button) findViewById(R.id.next_round);
        designs = new Designs();
        greenScores = (TextView) findViewById(R.id.end_turn_green_team_scores);
        redScores = (TextView) findViewById(R.id.end_turn_red_team_scores);
        nowPlays = (TextView) findViewById(R.id.now_plays);
        textViews = new TextView[]{greenScores, redScores, nowPlays};
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

    private void changeTeam() {
        global.changeTeam();
    }

    public void addText() {
        nextRound.setText("Start next round");
        greenScores.setText(Integer.toString(greenTeamScores.getTeamScore()));
        redScores.setText(Integer.toString(redTeamScores.getTeamScore()));
        nowPlays.setText(global.getCurrentTeamText());
    }

    public void newRound(View view) {
        Intent playGameActivity = new Intent(this, PlayGameActivity.class);
        startActivity(playGameActivity);
    }
}
