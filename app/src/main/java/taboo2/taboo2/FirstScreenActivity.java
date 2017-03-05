package taboo2.taboo2;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

public class FirstScreenActivity extends AppCompatActivity {

    //===========================================
    //-----PARAMS TO CHANGE ELEMENT LOCATION-----
    //===========================================

    // margin from border is ("phone width" / paramMarginBorder)
    private byte paramMarginBorder = 8;
    private byte textHeight = 16;
    // Change margin parameter to change margin from top to the first textView
    private float marginParameter = 1/5f;
    // Each parameter takes value from 0 to 255
    private int buttonsColor = Color.rgb(120, 120, 120);
    private short buttonsHeight = 150;
    private short marginsTopBottom = 100;

    //===========================================
    //-----------------VARIABLES-----------------
    //===========================================
    private int marginFromTop;
    private int phoneHeight;
    private int phoneWidth;
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
        addDesignToButtons();
    }

    //===========================================
    //------------------METHODS------------------
    //===========================================

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

        int marginsFromBorders = phoneWidth / paramMarginBorder;

        for(Button button: startButtons) {
            // text height
            button.setMinimumHeight(0);
            button.setMinHeight(0);
            button.setHeight(buttonsHeight);
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
            button.setTextSize(textHeight);
        }
    }

    private void addDesignToButtons() {

        for(Button button: startButtons) {
            button.setBackgroundColor(buttonsColor);
        }
    }
}
