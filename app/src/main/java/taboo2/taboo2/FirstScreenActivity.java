package taboo2.taboo2;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class FirstScreenActivity extends AppCompatActivity {

    private int marginFromTop;
    private int phoneHeight;
    private int phoneWidth;
    private short marginsTopBottom = 100;
    private Global global;
    private LinearLayout firstLayout;
    private TextView startGame;
    private TextView gameRules;
    private TextView settings;
    private TextView about;
    private TextView[] startFields;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_screen);
        init();
        fieldLocationFromTop();
        adjustFieldsSize();
        addTextToFields();
        textHeightAndLocation();
    }

    private void init() {
        global = new Global();
        phoneHeight = global.getPhoneHeight();
        phoneWidth = global.getPhoneWidth();
        startGame = (TextView) findViewById(R.id.startGame);
        gameRules = (TextView) findViewById(R.id.gameRules);
        settings = (TextView) findViewById(R.id.settings);
        about = (TextView) findViewById(R.id.about);
        startFields = new TextView[]{startGame, gameRules, settings, about};
        firstLayout = (LinearLayout) findViewById(R.id.activity_first_screen);
    }

    private void fieldLocationFromTop() {
        int totalFieldHeights = startFields.length*2*marginsTopBottom;
        float marginParameter = 1/3f;
        marginFromTop = (int)(marginParameter*(phoneHeight-totalFieldHeights));
        LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
    }

    private void adjustFieldsSize() {

        int marginsFromBorders = phoneWidth / 8;

        for(TextView textView: startFields) {
            textView.setBackgroundColor(Color.RED);
            // text height
            textView.setHeight(160);
            // field width = phone width - margins
            ViewGroup.MarginLayoutParams mlp = (ViewGroup.MarginLayoutParams) textView
                    .getLayoutParams();
            mlp.setMargins(marginsFromBorders, marginsTopBottom,
                    marginsFromBorders, marginsTopBottom);
            ViewGroup.MarginLayoutParams params =
                    (ViewGroup.MarginLayoutParams) startGame.getLayoutParams();
            params.width = 2000; params.leftMargin = 100; params.topMargin = 200;
        }
    }

    private void addTextToFields() {
        startGame.setText("Rozpocznij grę");
        gameRules.setText("Zasady gry");
        settings.setText("Ustawienia");
        about.setText("O autorze");
    }

    private void textHeightAndLocation() {
        for(TextView textView: startFields) {
            textView.setGravity(Gravity.CENTER);
            textView.setTextSize(18);
        }
    }
}
