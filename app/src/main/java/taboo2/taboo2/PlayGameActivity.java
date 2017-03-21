package taboo2.taboo2;

import android.graphics.Color;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

public class PlayGameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_game);
        init();
        addDesignToButtons();
    }

    /* ==========================================
    ------------------VARIABLES------------------
    ========================================== */

    private int buttonsColor = Color.rgb(120, 120, 120);
    private Global global;
    private TextView searchingWord;
    private TextView word1;
    private TextView word2;
    private TextView word3;
    private TextView word4;
    private TextView word5;
    private TextView[] textViews;

    /* ==========================================
    -------------------METHODS-------------------
    ========================================== */

    private void init() {
        global = new Global();
        searchingWord = (TextView) findViewById(R.id.searchingWord);
        word1 = (TextView) findViewById(R.id.word1);
        word2 = (TextView) findViewById(R.id.word2);
        word3 = (TextView) findViewById(R.id.word3);
        word4 = (TextView) findViewById(R.id.word4);
        word5 = (TextView) findViewById(R.id.word5);
        textViews = new TextView[]{searchingWord, word1, word2, word3, word4, word5};
    }

    private void addDesignToButtons() {

        for(TextView textView: textViews) {
            ViewGroup.MarginLayoutParams mlp = (ViewGroup.MarginLayoutParams) textView
                    .getLayoutParams();
            mlp.setMargins(20, 50, 20, 50);
            textView.setHeight(150);
            RoundRectShape rect = new RoundRectShape(
                    new float[] {10,10 , 10,10 , 10,10 , 10,10},
                    null,
                    null);
            ShapeDrawable bg = new ShapeDrawable(rect);
            bg.getPaint().setColor(buttonsColor);
            if (android.os.Build.VERSION.SDK_INT >= 16)
                textView.setBackground(bg);
            else {
                textView.setBackgroundDrawable(bg);
            }
            textView.setGravity(Gravity.CENTER);
            textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, 100);
        }
    }

}
