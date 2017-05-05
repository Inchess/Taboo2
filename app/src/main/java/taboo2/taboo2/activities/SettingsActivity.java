package taboo2.taboo2.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Arrays;

import taboo2.taboo2.R;
import taboo2.taboo2.global.Global;

public class SettingsActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

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
        addText();
    }

    /* ==========================================
    -------------HARDCODED VARIABLES-------------
    ========================================== */

    String string_pointsToWin = "Punkty do wygrania";
    String string_forbiddenWords = "Zakazane słowa";
    String string_points_correctAnswer = "Prawidłowa odpowiedź";
    String string_points_incorrectAnswer = "Nieprawidłowa odpowiedź";
    String string_timePerPlayer = "Czas na gracza";
    String string_questionsLevel = "Poziom trudności:";
    String string_easy = "Łatwy";
    String string_average = "Średni";
    String string_difficult = "Trudny";
    String string_veryDifficult = "Bardzo trudny";
    String string_saveAndQuit = "Zapisz i wróć";
    String[] array_pointsToWin = new String[]{"20", "25", "30", "35", "40", "50"};
    String[] array_forbiddenWords = new String[]{"3", "4", "5", "6", "7"};
    String[] array_pointsCorrectAnswer = new String[]{"1", "2", "3"};
    String[] array_pointsIncorrectAnswer = new String[]{"-3", "-2", "-1", "0"};
    String[] array_timePerPlayer = new String[]{"00:30", "00:45", "01:00", "01:30", "02:00"};

    /* ==========================================
    ------------------VARIABLES------------------
    ========================================== */

    int global_pointsToWin;
    int global_forbiddenWords;
    int global_correctAnswer;
    int global_incorrectAnswer;
    String global_timePerPlayer;
    Button saveChanges;
    Global global;
    Spinner spinner_pointsToWin;
    Spinner spinner_forbiddenWords;
    Spinner spinner_correctAnswer;
    Spinner spinner_incorrectAnswer;
    Spinner spinner_timePerPlayer;
    int position_pointsToWin;
    int position_forbiddenWords;
    int position_pointsCorrectAnswer;
    int position_pointsIncorrectAnswer;
    int position_timePerPlayer;
    Spinner[] spinners;
    ArrayAdapter adapter_pointsToWin;
    ArrayAdapter adapter_forbiddenWords;
    ArrayAdapter adapter_correctAnswer;
    ArrayAdapter adapter_incorrectAnswer;
    ArrayAdapter adapter_timePerPlayer;
    TextView view_pointsToWin;
    TextView view_forbiddenWords;
    TextView view_correctAnswer;
    TextView view_incorrectAnswer;
    TextView view_timePerPlayer;
    TextView view_questionLevel;
    CheckBox checkbox_easy;
    CheckBox checkbox_average;
    CheckBox checkbox_difficult;
    CheckBox checkbox_veryDifficult;

    private void init() {
        initSpinners();
        initCheckboxes();
        initTextViews();
        global = new Global();
        saveChanges = (Button) findViewById(R.id.button_save_changes);
        findIndexesOfElements();
    }

    public void initSpinners() {
        spinner_pointsToWin = (Spinner) findViewById(R.id.spinner$_points_to_win);
        spinner_forbiddenWords = (Spinner) findViewById(R.id.spinner$_forbidden_words);
        spinner_correctAnswer = (Spinner) findViewById(R.id.spinner$_points_correct_answer);
        spinner_incorrectAnswer = (Spinner) findViewById(R.id.spinner$_points_incorrect_answer);
        spinner_timePerPlayer = (Spinner) findViewById(R.id.spinner$_time_per_player);
        spinners = new Spinner[]{spinner_pointsToWin, spinner_forbiddenWords, spinner_correctAnswer,
                spinner_incorrectAnswer, spinner_timePerPlayer};
    }

    public void initCheckboxes() {
        checkbox_easy = (CheckBox) findViewById(R.id.level_easy);
        checkbox_average = (CheckBox) findViewById(R.id.level_average);
        checkbox_difficult = (CheckBox) findViewById(R.id.level_difficult);
        checkbox_veryDifficult = (CheckBox) findViewById(R.id.level_very_difficult);
    }

    public void initTextViews() {
        view_pointsToWin = (TextView) findViewById(R.id.textView$_points_to_win);
        view_forbiddenWords = (TextView) findViewById(R.id.textView$_forbidder_words);
        view_correctAnswer = (TextView) findViewById(R.id.textView$_points_correct_answer);
        view_incorrectAnswer = (TextView) findViewById(R.id.textView$_points_incorrect_answer);
        view_timePerPlayer = (TextView) findViewById(R.id.textView$_time_per_player);
        view_questionLevel = (TextView) findViewById(R.id.textView$_question_level);
    }

    public void findIndexesOfElements() {
        position_pointsToWin = Arrays.asList(array_pointsToWin).
                indexOf(Integer.toString(global.getPointsToWin()));
        position_forbiddenWords = Arrays.asList(array_forbiddenWords).
                indexOf(Integer.toString(global.getNumberOfForbiddenWords()));
        position_pointsCorrectAnswer = Arrays.asList(array_pointsCorrectAnswer).
                indexOf(Integer.toString(global.getPoints_correctAnswer()));
        position_pointsIncorrectAnswer = Arrays.asList(array_pointsIncorrectAnswer).
                indexOf(Integer.toString(global.getPoints_incorrectAnswer()));
        position_timePerPlayer = Arrays.asList(array_timePerPlayer).
                indexOf(global.getTimePerPlayer());

    }

    private void addOnItemSelectedListener() {
        for (Spinner spinner : spinners) {
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
        if (parent.toString().contains("spinner$_points_to_win")) {
            global_pointsToWin = Integer.parseInt(array_pointsToWin[position]);
        }
        if (parent.toString().contains("spinner$_forbidden_words")) {
            global_forbiddenWords = Integer.parseInt(array_forbiddenWords[position]);
        }
        if (parent.toString().contains("spinner$_points_correct_answer")) {
            global_correctAnswer = Integer.parseInt(array_pointsCorrectAnswer[position]);
        }
        if (parent.toString().contains("spinner$_points_incorrect_answer")) {
            global_incorrectAnswer = Integer.parseInt(array_pointsIncorrectAnswer[position]);
        }
        if (parent.toString().contains("spinner$_time_per_player")) {
            global_timePerPlayer = array_timePerPlayer[position];
        }
    }

    public void saveValues() {
        global.setNumberOfForbiddenWords(global_forbiddenWords);
        global.setPoints_correctAnswer(global_correctAnswer);
        global.setPoints_incorrectAnswer(global_incorrectAnswer);
        global.setPointsToWin(global_pointsToWin);
        global.setTimePerPlayer(global_timePerPlayer);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void addText() {
        view_pointsToWin.setText(string_pointsToWin);
        view_forbiddenWords.setText(string_forbiddenWords);
        view_correctAnswer.setText(string_points_correctAnswer);
        view_incorrectAnswer.setText(string_points_incorrectAnswer);
        view_timePerPlayer.setText(string_timePerPlayer);
        view_questionLevel.setText(string_questionsLevel);
        checkbox_easy.setText(string_easy);
        checkbox_average.setText(string_average);
        checkbox_difficult.setText(string_difficult);
        checkbox_veryDifficult.setText(string_veryDifficult);
        saveChanges.setText(string_saveAndQuit);
    }

    public void saveChanges(View view) {
        saveValues();
        Intent firstScreen = new Intent(this, StartScreenActivity.class);
        startActivity(firstScreen);
    }
}
