package com.example.myapplication;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class SelectionFragment extends Fragment {

    Spinner spinnerLanguages;
    Button buttonOk, buttonCancel;
    String selectedLanguage = "";
    OnLanguageSelectedListener callback;

    public interface OnLanguageSelectedListener {
        void onLanguageSelected(String language);
        void onClearRequested();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnLanguageSelectedListener) {
            callback = (OnLanguageSelectedListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnLanguageSelectedListener");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_selection, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        spinnerLanguages = view.findViewById(R.id.spinnerLanguages);
        buttonOk = view.findViewById(R.id.buttonOk);
        buttonCancel = view.findViewById(R.id.buttonCancel);

        String[] languages = {"", "Java", "Python", "C++", "JavaScript", "Kotlin", "Swift"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, languages);
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

        buttonOk.setOnClickListener(v -> {
            if (selectedLanguage.isEmpty()) {
                Toast.makeText(getContext(), "Будь ласка, оберіть мову програмування!", Toast.LENGTH_SHORT).show();
            } else {
                callback.onLanguageSelected(selectedLanguage);
            }
        });

        buttonCancel.setOnClickListener(v -> {
            spinnerLanguages.setSelection(0);
            callback.onClearRequested();
        });
    }
}
