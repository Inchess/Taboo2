package taboo2.taboo2;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class FirstScreenActivity extends AppCompatActivity {

    // Change margin parameter to change margin from top to the first textView
    private float marginParameter = 1/5f;
    private int marginFromTop;
    private int phoneHeight;
    private int phoneWidth;
    private short marginsTopBottom = 100;
    private Global global;
    private LinearLayout firstLayout;
    private Button startGame;
    private Button gameRules;
    private Button settings;
    private Button about;
    private Button[] startButtons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_screen);
        init();
        fieldLocationFromTop();
        adjustFieldsSize();
        addTextToFields();
        textHeightAndLocation();
        addTextAndDesignToButtons();
    }

    private void init() {
        global = new Global();
        phoneHeight = global.getPhoneHeight();
        phoneWidth = global.getPhoneWidth();
        startGame = (Button) findViewById(R.id.startGame);
        gameRules = (Button) findViewById(R.id.gameRules);
        settings = (Button) findViewById(R.id.settings);
        about = (Button) findViewById(R.id.about);
        startButtons = new Button[]{startGame, gameRules, settings, about};
        firstLayout = (LinearLayout) findViewById(R.id.activity_first_screen);
    }

    private void fieldLocationFromTop() {
        int totalButtonHeights = startButtons.length*2*marginsTopBottom;
        marginFromTop = (int)(marginParameter*(phoneHeight-totalButtonHeights));
    }

    private void adjustFieldsSize() {

        int marginsFromBorders = phoneWidth / 8;

        for(Button button: startButtons) {
            button.setBackgroundColor(Color.RED);
            // text height
            button.setHeight(160);
            // field width = phone width - margins
            ViewGroup.MarginLayoutParams mlp = (ViewGroup.MarginLayoutParams) button
                    .getLayoutParams();
            mlp.setMargins(marginsFromBorders, marginsTopBottom,
                    marginsFromBorders, marginsTopBottom);
            ViewGroup.MarginLayoutParams params =
                    (ViewGroup.MarginLayoutParams) startGame.getLayoutParams();
            params.setMargins(marginsFromBorders, marginFromTop,
                    marginsFromBorders, marginsTopBottom);
        }
    }

    private void addTextToFields() {
        startGame.setText("Rozpocznij grę");
        gameRules.setText("Zasady gry");
        settings.setText("Ustawienia");
        about.setText("O autorze");
    }

    private void textHeightAndLocation() {
        for(Button button: startButtons) {
            button.setGravity(Gravity.CENTER);
            button.setTextSize(18);
        }
    }

    private void addTextAndDesignToButtons() {
        
    }
}
