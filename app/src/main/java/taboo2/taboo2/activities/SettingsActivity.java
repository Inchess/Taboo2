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
        checkCheckboxes();
    }

    /* ==========================================
    -------------HARDCODED VARIABLES-------------
    ========================================== */

    private final String string_pointsToWin = "Punkty do wygrania";
    private final String string_forbiddenWords = "Zakazane słowa";
    private final String string_points_correctAnswer = "Prawidłowa odpowiedź";
    private final String string_points_incorrectAnswer = "Nieprawidłowa odpowiedź";
    private final String string_timePerPlayer = "Czas na gracza (w sekundach)";
    private final String string_questionsLevel = "Poziom trudności:";
    private final String string_easy = "Łatwy";
    private final String string_average = "Średni";
    private final String string_difficult = "Trudny";
    private final String string_veryDifficult = "Bardzo trudny";
    private final String string_saveAndQuit = "Zapisz i wróć";
    private final String string_backToDefault = "Cofnij do ustawień domyślnych";
    private String[] array_pointsToWin = new String[]{"20", "25", "30", "35", "40", "50"};
    private String[] array_forbiddenWords = new String[]{"3", "4", "5", "6", "7"};
    private String[] array_pointsCorrectAnswer = new String[]{"1", "2", "3"};
    private String[] array_pointsIncorrectAnswer = new String[]{"-3", "-2", "-1", "0"};
    private String[] array_timePerPlayer = new String[]{"30", "45", "60", "75", "90"};

    /* ==========================================
    ------------------VARIABLES------------------
    ========================================== */

    private int global_pointsToWin;
    private int global_forbiddenWords;
    private int global_correctAnswer;
    private int global_incorrectAnswer;
    private int global_timePerPlayer;
    private Button saveChanges;
    private Button backToDefault;
    private Global global;
    private Spinner spinner_pointsToWin;
    private Spinner spinner_forbiddenWords;
    private Spinner spinner_correctAnswer;
    private Spinner spinner_incorrectAnswer;
    private Spinner spinner_timePerPlayer;
    private int position_pointsToWin;
    private int position_forbiddenWords;
    private int position_pointsCorrectAnswer;
    private int position_pointsIncorrectAnswer;
    private int position_timePerPlayer;
    private Spinner[] spinners;
    private ArrayAdapter adapter_pointsToWin;
    private ArrayAdapter adapter_forbiddenWords;
    private ArrayAdapter adapter_correctAnswer;
    private ArrayAdapter adapter_incorrectAnswer;
    private ArrayAdapter adapter_timePerPlayer;
    private TextView view_pointsToWin;
    private TextView view_forbiddenWords;
    private TextView view_correctAnswer;
    private TextView view_incorrectAnswer;
    private TextView view_timePerPlayer;
    private TextView view_questionLevel;
    private CheckBox checkbox_easy;
    private CheckBox checkbox_average;
    private CheckBox checkbox_difficult;
    private CheckBox checkbox_veryDifficult;

    private void init() {
        initSpinners();
        initCheckboxes();
        initTextViews();
        global = new Global();
        saveChanges = (Button) findViewById(R.id.button_save_changes);
        backToDefault = (Button) findViewById(R.id.button_back_to_default);
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
                indexOf(Integer.toString(global.getTimePerPlayer()));

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

    public void checkCheckboxes() {
        checkbox_easy.setChecked(Global.isEasyLevelChecked());
        checkbox_average.setChecked(Global.isAverageLevelChecked());
        checkbox_difficult.setChecked(Global.isDifficultLevelChecked());
        checkbox_veryDifficult.setChecked(Global.isVeryDifficultLevelChecked());
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
            global_timePerPlayer = Integer.parseInt(array_timePerPlayer[position]);
        }
    }

    public void saveValues() {
        global.setPointsToWin(global_pointsToWin);
        global.setNumberOfForbiddenWords(global_forbiddenWords);
        global.setPoints_correctAnswer(global_correctAnswer);
        global.setPoints_incorrectAnswer(global_incorrectAnswer);
        global.setTimePerPlayer(global_timePerPlayer);
        Global.setEasyLevelChecked(checkbox_easy.isChecked());
        Global.setAverageLevelChecked(checkbox_average.isChecked());
        Global.setDifficultLevelChecked(checkbox_difficult.isChecked());
        Global.setVeryDifficultLevelChecked(checkbox_veryDifficult.isChecked());
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
        backToDefault.setText(string_backToDefault);
    }

    public void saveChanges(View view) {
        saveValues();
        Intent firstScreen = new Intent(this, StartScreenActivity.class);
        startActivity(firstScreen);
    }

    public void backToDefault(View view) {
        global.setPointsToWin(Global.DEFAULT_POINTS_TO_WIN);
        global.setNumberOfForbiddenWords(Global.DEFAULT_NUMBER_OF_FORBIDDEN_WORDS);
        global.setPoints_correctAnswer(Global.DEFAULT_POINTS_CORRECT_ANSWER);
        global.setPoints_incorrectAnswer(Global.DEFAULT_POINTS_INCORRECT_ANSWER);
        global.setTimePerPlayer(Global.DEFAULT_TIME_PER_PLAYER);
        Global.setEasyLevelChecked(Global.DEFAULT_EASY_LEVEL_CHECKED);
        Global.setAverageLevelChecked(Global.DEFAULT_AVERAGE_LEVEL_CHECKED);
        Global.setDifficultLevelChecked(Global.DEFAULT_DIFFICULT_LEVEL_CHECKED);
        Global.setVeryDifficultLevelChecked(Global.DEFAULT_VERY_DIFFICULT_LEVEL_CHECKED);
        Intent settings = new Intent(this, SettingsActivity.class);
        startActivity(settings);
    }
}
