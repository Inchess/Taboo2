package taboo2.taboo2;

import android.graphics.Color;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

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
    private byte paramMarginBorder240_600 = 20;
    private byte paramMarginBorder601_800 = 8;
    private byte paramMarginBorder801_inf = 4;
    private byte textHeight240_600 = 20;
    private byte textHeight601_800 = 16;
    private byte textHeight801_inf = 16;
    // Change margin parameter to change margin from top to the first textView
    private float marginParameter240_600 = 1/10f;
    private float marginParameter601_800 = 1/5f;
    private float marginParameter801_inf = 1/15f;
    // Each parameter takes value from 0 to 255
    private int buttonsColor = Color.rgb(120, 120, 120);
    private short buttonsHeight240_600 = 70;
    private short buttonsHeight601_800 = 150;
    private short buttonsHeight801_inf = 100;
    private short marginsTopBottom240_600 = 30;
    private short marginsTopBottom601_800 = 120;
    private short marginsTopBottom801_inf = 20;
    private short radius240_600 = 30;
    private short radius601_800 = 100;
    private short radius801_inf = 100;

    /* ==========================================
    ------------------VARIABLES------------------
    ========================================== */
    private byte paramMarginBorder;
    private byte textHeight;
    private float marginParameter;
    private int marginFromTop;
    private int phoneHeight;
    private int phoneWidth;
    private short buttonsHeight;
    private short marginsTopBottom;
    private short radius;
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
        setVariableValues();
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

    private void setVariableValues() {
        if(240 <= phoneWidth && phoneWidth <= 600) {
            paramMarginBorder = paramMarginBorder240_600;
            textHeight = textHeight240_600;
            marginParameter = marginParameter240_600;
            buttonsHeight = buttonsHeight240_600;
            marginsTopBottom = marginsTopBottom240_600;
            radius = radius240_600;
        } else if(600 < phoneWidth && phoneWidth <= 800) {
            paramMarginBorder = paramMarginBorder601_800;
            textHeight = textHeight601_800;
            marginParameter = marginParameter601_800;
            buttonsHeight = buttonsHeight240_600;
            marginsTopBottom = marginsTopBottom601_800;
            radius = radius601_800;
        } else if(800 < phoneWidth) {
            paramMarginBorder = paramMarginBorder801_inf;
            textHeight = textHeight801_inf;
            marginParameter = marginParameter801_inf;
            buttonsHeight = buttonsHeight801_inf;
            marginsTopBottom = marginsTopBottom801_inf;
            radius = radius801_inf;
        }
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
}
