package taboo2.taboo2;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import taboo2.taboo2.designs.Designs;

public class FirstScreenActivity extends AppCompatActivity {

    /* ==========================================
    ------PARAMS TO CHANGE ELEMENT LOCATION------
    ========================================== */

    /* There are 3 type of values depending on screen width
    1. 240 - 600px
    2. 601 - 800px
    3. 801 - infinity
     */

    // margin from border is ("phone width" / paramMarginBorder)
    private int paramMarginBorder = 1;
    private int textHeight = 20;
    // Change margin parameter to change margin from top to the first textView
    private int marginParameter = 70;
    // Each parameter takes value from 0 to 255
    private int buttonsColor = Color.rgb(120, 120, 120);
    private int buttonsHeight = 55;
    private int marginsTopBottom = 25;
    private int radius = 10;

    /* ==========================================
    ------------------VARIABLES------------------
    ========================================== */

    private Designs designs;
    private Global global;

    private int marginsFromBorders;
    private int marginFromTop;
    private int phoneHeight;
    private int phoneWidth;
    private LinearLayout firstLayout;
    private Button startGame;
    private Button gameRules;
    private Button settings;
    private Button about;
    private Button[] startButtons;

    private String string_startGame = "Rozpocznij grę";
    private String string_gameRules = "Zasady gry";
    private String string_settings = "Ustawienia";
    private String string_about = "O autorze";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_screen);
        init();
        changeDpToPx();
        fieldLocationFromTop();
        setButtonTextHeight();
        setButtonsMargins();
        setMarginToFirstButton();
        addTextToFields();
        textHeightAndLocation();
        addDesignToButtons();

    }

    /* ==========================================
    -------------------METHODS-------------------
    ========================================== */

    private void init() {
        global = new Global();
        designs = new Designs();
        phoneHeight = global.getPhoneHeight();
        phoneWidth = global.getPhoneWidth();
        startGame = (Button) findViewById(R.id.startGame);
        gameRules = (Button) findViewById(R.id.gameRules);
        settings = (Button) findViewById(R.id.settings);
        about = (Button) findViewById(R.id.about);
        startButtons = new Button[]{startGame, gameRules, settings, about};
        firstLayout = (LinearLayout) findViewById(R.id.activity_first_screen);
    }

    private void changeDpToPx() {
        paramMarginBorder = global.dpToPx(paramMarginBorder);
        textHeight = global.dpToPx(textHeight);
        marginParameter = global.dpToPx(marginParameter);
        buttonsHeight = global.dpToPx(buttonsHeight);
        marginsTopBottom = global.dpToPx(marginsTopBottom);
        radius = global.dpToPx(radius);
        marginsFromBorders = phoneWidth / paramMarginBorder;
    }

    private void fieldLocationFromTop() {
        marginFromTop = marginParameter;
    }

    private void setButtonTextHeight() {
        designs.buttons_textHeight(startButtons, buttonsHeight);
    }

    private void setButtonsMargins() {
        designs.views_setMargins(startButtons, marginsTopBottom, marginsFromBorders);
    }

    private void setMarginToFirstButton() {
        designs.setMarginToOneView(startGame, marginFromTop, marginsTopBottom, marginsFromBorders);
    }

    private void addTextToFields() {
        startGame.setText(string_startGame);
        gameRules.setText(string_gameRules);
        settings.setText(string_settings);
        about.setText(string_about);
    }

    private void textHeightAndLocation() {
        designs.buttons_textHeightAndLocation(startButtons, textHeight);
    }

    private void addDesignToButtons() {
        designs.views_addColorAndRadius(startButtons, buttonsColor, radius);
    }

    public void startGame(View view) {
        Intent teamNamesIntent = new Intent(this, PlayGameActivity.class);
        startActivity(teamNamesIntent);
    }


}
