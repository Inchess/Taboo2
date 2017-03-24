package taboo2.taboo2;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

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
        changeDpToPx();
        fieldLocationFromTop();
        adjustFieldsSize();
        addTextToFields();
        textHeightAndLocation();
        addDesignToButtons();

    }

    /* ==========================================
    -------------------METHODS-------------------
    ========================================== */

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

    private void changeDpToPx() {
        paramMarginBorder = global.dpToPx(paramMarginBorder);
        textHeight = global.dpToPx(textHeight);
        marginParameter = global.dpToPx(marginParameter);
        buttonsHeight = global.dpToPx(buttonsHeight);
        marginsTopBottom = global.dpToPx(marginsTopBottom);
        radius = global.dpToPx(radius);
    }

    private void fieldLocationFromTop() {
        int totalButtonHeights = startButtons.length*2*marginsTopBottom;
        marginFromTop = marginParameter;
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
            button.setTextSize(TypedValue.COMPLEX_UNIT_PX, textHeight);
        }
    }

    private void addDesignToButtons() {

        for(Button button: startButtons) {
            RoundRectShape rect = new RoundRectShape(
                    new float[] {radius,radius, radius,radius, radius,radius, radius,radius},
                    null,
                    null);
            ShapeDrawable bg = new ShapeDrawable(rect);
            bg.getPaint().setColor(buttonsColor);
            if (android.os.Build.VERSION.SDK_INT >= 16)
                button.setBackground(bg);
            else {
                button.setBackgroundDrawable(bg);
            }
        }
    }

    public void startGame(View view) {
        Intent teamNamesIntent = new Intent(this, PlayGameActivity.class);
        startActivity(teamNamesIntent);
    }


}
