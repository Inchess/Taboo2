package taboo2.taboo2.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import taboo2.taboo2.R;

public class SettingsActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner spinner_pointsToWin;
    Spinner spinner_forbiddenWords;
    Spinner spinner_correctAnswer;
    Spinner spinner_incorrectAnswer;
    Spinner spinner_timePerPlayer;
    String[] points_correctAnswer = new String[]{"1", "2", "3"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        init();

        spinner_correctAnswer.setOnItemSelectedListener(this);
        ArrayAdapter adapter_correctAnswer = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item, points_correctAnswer);
        adapter_correctAnswer.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_correctAnswer.setAdapter(adapter_correctAnswer);
        spinner_correctAnswer.setSelection(1, true);
    }

    private void init() {
        spinner_pointsToWin = (Spinner) findViewById(R.id.spinner$_points_to_win);
        spinner_forbiddenWords = (Spinner) findViewById(R.id.spinner$_forbidden_words);
        spinner_correctAnswer = (Spinner) findViewById(R.id.spinner$_points_correct_answer);
        spinner_incorrectAnswer = (Spinner) findViewById(R.id.spinner$_points_incorrect_answer);
        spinner_timePerPlayer = (Spinner) findViewById(R.id.spinner$_time_per_player);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        TextView myText = (TextView) view;
        Toast.makeText(this, "You " + myText.getText() + " " + position, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
