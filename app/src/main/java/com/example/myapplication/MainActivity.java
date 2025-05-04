package com.example.myapplication;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Spinner spinnerLanguages;
    Button buttonOk, buttonCancel;
    TextView textResult;
    String selectedLanguage = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinnerLanguages = findViewById(R.id.spinnerLanguages);
        buttonOk = findViewById(R.id.buttonOk);
        buttonCancel = findViewById(R.id.buttonCancel);
        textResult = findViewById(R.id.textResult);

        String[] languages = {"", "Java", "Python", "C++", "JavaScript", "Kotlin", "Swift"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, languages);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerLanguages.setAdapter(adapter);

        spinnerLanguages.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedLanguage = languages[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                selectedLanguage = "";
            }
        });

        buttonOk.setOnClickListener(view -> {
            if (selectedLanguage.isEmpty()) {
                Toast.makeText(MainActivity.this, "Будь ласка, оберіть мову програмування!", Toast.LENGTH_SHORT).show();
            } else {
                textResult.setText("Вибрана мова: " + selectedLanguage);
            }
        });

        buttonCancel.setOnClickListener(view -> {
            spinnerLanguages.setSelection(0); // повернути вибір до порожнього
            textResult.setText("");
        });
    }
}
