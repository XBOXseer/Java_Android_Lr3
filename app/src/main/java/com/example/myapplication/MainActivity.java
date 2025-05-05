package com.example.myapplication;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

public class MainActivity extends AppCompatActivity implements SelectionFragment.OnLanguageSelectedListener {

    private ResultFragment resultFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Додаємо обидва фрагменти
        FragmentManager fm = getSupportFragmentManager();

        if (savedInstanceState == null) {
            fm.beginTransaction()
                    .replace(R.id.fragment_selection, new SelectionFragment())
                    .replace(R.id.fragment_result, new ResultFragment())
                    .commit();
        }

        // Після ініціалізації activity пробуємо знайти фрагмент результату
        resultFragment = (ResultFragment) fm.findFragmentById(R.id.fragment_result);
    }

    @Override
    public void onLanguageSelected(String language) {
        resultFragment = (ResultFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_result);
        if (resultFragment != null) {
            resultFragment.setLanguage(language);
        }
    }

    @Override
    public void onClearRequested() {
        resultFragment = (ResultFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_result);
        if (resultFragment != null) {
            resultFragment.clearResult();
        }
    }
}
