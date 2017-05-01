package taboo2.taboo2.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import taboo2.taboo2.R;

public class SettingsActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner spinner2;
    Spinner spinner_forbiddenWords;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        spinner2 = (Spinner) findViewById(R.id.spinner$_points_to_win);
        List<String> list = new ArrayList<>();
        list.add("list 1");
        list.add("list 2");
        list.add("list 3");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(dataAdapter);
        spinner2.setOnItemSelectedListener(this);
        spinner2.setSelection(2, true);

        spinner_forbiddenWords = (Spinner) findViewById(R.id.spinner$_forbidden_words);
        ArrayAdapter dataAdapter_forbiddenWords = ArrayAdapter.createFromResource(this,
                R.array.forbiddenWords, android.R.layout.simple_spinner_item);
        spinner_forbiddenWords.setAdapter(dataAdapter_forbiddenWords);
        spinner_forbiddenWords.setOnItemSelectedListener(this);
        spinner_forbiddenWords.setSelection(2, true);



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
