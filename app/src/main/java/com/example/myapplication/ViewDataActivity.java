package com.example.myapplication;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ViewDataActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_data);

        TextView textView = findViewById(R.id.textViewData);

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(openFileInput("data.txt")));
            StringBuilder builder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line).append("\n");
            }
            reader.close();

            if (builder.length() == 0) {
                textView.setText("Сховище порожнє.");
            } else {
                textView.setText(builder.toString());
            }
        } catch (Exception e) {
            textView.setText("Помилка читання даних.");
        }
    }
}
