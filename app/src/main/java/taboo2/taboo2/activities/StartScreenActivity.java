package taboo2.taboo2.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import taboo2.taboo2.R;
import taboo2.taboo2.designs.Designs;
import taboo2.taboo2.global.Global;
import taboo2.taboo2.global.PhoneParams;

public class StartScreenActivity extends AppCompatActivity {

    /* ==========================================
    -------------HARDCODED VARIABLES-------------
    ========================================== */

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

    private int marginsFromBorders;
    private int marginFromTop;
    private int phoneWidth;
    private Button startGame;
    private Button gameRules;
    private Button settings;
    private Button about;
    private Button[] startButtons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_screen);
        init();
        changeDpToPx();
        fieldLocationFromTop();
        setButtonHeight();
        setButtonsMargins();
        setMarginToFirstButton();
        addTextToFields();
        textHeightAndLocation();
        addColorAndRadius();

    }

    /* ==========================================
    -------------------METHODS-------------------
    ========================================== */

    private void init() {
        PhoneParams phoneParams = new PhoneParams();
        designs = new Designs();
        phoneWidth = phoneParams.getPhoneWidth();
        startGame = (Button) findViewById(R.id.startGame);
        gameRules = (Button) findViewById(R.id.gameRules);
        settings = (Button) findViewById(R.id.settings);
        about = (Button) findViewById(R.id.about);
        startButtons = new Button[]{startGame, gameRules, settings, about};
    }

    private void changeDpToPx() {
        paramMarginBorder = PhoneParams.dpToPx(paramMarginBorder);
        textHeight = PhoneParams.dpToPx(textHeight);
        marginParameter = PhoneParams.dpToPx(marginParameter);
        buttonsHeight = PhoneParams.dpToPx(buttonsHeight);
        marginsTopBottom = PhoneParams.dpToPx(marginsTopBottom);
        radius = PhoneParams.dpToPx(radius);
        marginsFromBorders = phoneWidth / paramMarginBorder;
    }

    private void fieldLocationFromTop() {
        marginFromTop = marginParameter;
    }

    private void setButtonHeight() {
        designs.textViews_buttonsHeight(startButtons, buttonsHeight);
    }

    private void setButtonsMargins() {
        designs.views_setMargins(startButtons, marginsTopBottom, marginsFromBorders);
    }

    private void setMarginToFirstButton() {
        designs.setMarginToOneView(startGame, marginFromTop, marginsTopBottom, marginsFromBorders);
    }

    private void addTextToFields() {
        startGame.setText(Global.STRING_START);
        gameRules.setText(Global.STRING_RULES);
        settings.setText(Global.STRING_SETTINGS);
        about.setText(Global.STRING_ABOUT);
    }

    private void textHeightAndLocation() {
        designs.views_textHeightAndLocation(startButtons, textHeight);
    }

    private void addColorAndRadius() {
        designs.views_addColorAndRadius(startButtons, buttonsColor, radius);
    }

    public void startGame(View view) {
        Intent teamNamesIntent = new Intent(this, PlayGameActivity.class);
        startActivity(teamNamesIntent);
    }

    public void settings(View view) {
        Intent settingsIntent = new Intent(this, SettingsActivity.class);
        startActivity(settingsIntent);
    }
}
