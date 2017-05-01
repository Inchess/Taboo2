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

    int global_pointsToWin;
    int global_forbiddenWords;
    int global_correctAnswer;
    int global_incorrectAnswer;
    String global_timePerPlayer;

    Spinner spinner_pointsToWin;
    Spinner spinner_forbiddenWords;
    Spinner spinner_correctAnswer;
    Spinner spinner_incorrectAnswer;
    Spinner spinner_timePerPlayer;
    String[] array_pointsToWin = new String[]{"20", "25", "30", "35", "40", "50"};
    String[] array_forbiddenWords = new String[]{"3", "4", "5", "6", "7"};
    String[] array_pointsCorrectAnswer = new String[]{"1", "2", "3"};
    String[] array_pointsIncorrectAnswer = new String[]{"-3", "-2", "-1", "0"};
    String[] array_timePerPlayer = new String[]{"00:30", "00:45", "01:00", "01:15", "01:30"};
    int position_pointsToWin = 2;
    int position_forbiddenWords = 2;
    int position_pointsCorrectAnswer = 0;
    int position_pointsIncorrectAnswer = 2;
    int position_timePerPlayer = 2;
    Spinner[] spinners;
    ArrayAdapter adapter_pointsToWin;
    ArrayAdapter adapter_forbiddenWords;
    ArrayAdapter adapter_correctAnswer;
    ArrayAdapter adapter_incorrectAnswer;
    ArrayAdapter adapter_timePerPlayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        init();
        addOnItemSelectedListener();
        createArrayAdapters();
        addSpinnersView();
        setAdapters();
        setFirstVisibleElement();
    }

    private void init() {
        spinner_pointsToWin = (Spinner) findViewById(R.id.spinner$_points_to_win);
        spinner_forbiddenWords = (Spinner) findViewById(R.id.spinner$_forbidden_words);
        spinner_correctAnswer = (Spinner) findViewById(R.id.spinner$_points_correct_answer);
        spinner_incorrectAnswer = (Spinner) findViewById(R.id.spinner$_points_incorrect_answer);
        spinner_timePerPlayer = (Spinner) findViewById(R.id.spinner$_time_per_player);
        spinners = new Spinner[]{spinner_pointsToWin, spinner_forbiddenWords, spinner_correctAnswer,
                spinner_incorrectAnswer, spinner_timePerPlayer};
    }

    private void addOnItemSelectedListener() {
        for(Spinner spinner: spinners) {
            spinner.setOnItemSelectedListener(this);
        }
    }

    private void createArrayAdapters() {
        adapter_pointsToWin = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item, array_pointsToWin);
        adapter_forbiddenWords = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item, array_forbiddenWords);
        adapter_correctAnswer = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item, array_pointsCorrectAnswer);
        adapter_incorrectAnswer = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item, array_pointsIncorrectAnswer);
        adapter_timePerPlayer = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item, array_timePerPlayer);
    }

    private void addSpinnersView() {
        adapter_pointsToWin.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter_forbiddenWords.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter_correctAnswer.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter_incorrectAnswer.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter_timePerPlayer.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    }

    public void setAdapters() {
        spinner_pointsToWin.setAdapter(adapter_pointsToWin);
        spinner_forbiddenWords.setAdapter(adapter_forbiddenWords);
        spinner_correctAnswer.setAdapter(adapter_correctAnswer);
        spinner_incorrectAnswer.setAdapter(adapter_incorrectAnswer);
        spinner_timePerPlayer.setAdapter(adapter_timePerPlayer);
    }

    private void setFirstVisibleElement() {
        spinner_pointsToWin.setSelection(position_pointsToWin, true);
        spinner_forbiddenWords.setSelection(position_forbiddenWords, true);
        spinner_correctAnswer.setSelection(position_pointsCorrectAnswer, true);
        spinner_incorrectAnswer.setSelection(position_pointsIncorrectAnswer, true);
        spinner_timePerPlayer.setSelection(position_timePerPlayer, true);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if(parent.toString().contains("spinner$_points_to_win")) {
            global_pointsToWin = Integer.parseInt(array_pointsToWin[position]);
            Toast.makeText(this, "" + global_pointsToWin, Toast.LENGTH_SHORT).show();
        }
        if(parent.toString().contains("spinner$_forbidden_words")) {
            global_forbiddenWords = Integer.parseInt(array_forbiddenWords[position]);
            Toast.makeText(this, "" + global_forbiddenWords, Toast.LENGTH_SHORT).show();
        }
        if(parent.toString().contains("spinner$_points_correct_answer")) {
            global_correctAnswer = Integer.parseInt(array_pointsCorrectAnswer[position]);
            Toast.makeText(this, "" + global_correctAnswer, Toast.LENGTH_SHORT).show();
        }
        if(parent.toString().contains("spinner$_points_incorrect_answer")) {
            global_incorrectAnswer = Integer.parseInt(array_pointsIncorrectAnswer[position]);
            Toast.makeText(this, "" + global_incorrectAnswer, Toast.LENGTH_SHORT).show();
        }
        if(parent.toString().contains("spinner$_time_per_player")) {
            global_timePerPlayer = array_timePerPlayer[position];
            Toast.makeText(this, global_timePerPlayer, Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
