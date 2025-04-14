package com.example.megamart;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;

public class LanguageSettingActivity extends AppCompatActivity {

    ListView listView;
    String[] languages = {"Use system language", "English", "Hindi", "Marathi", "Spanish", "French"};
    String[] languageCodes = {"", "en", "hi", "mr", "es", "fr"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language_setting);

        listView = findViewById(R.id.languageList);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, languages);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener((parent, view, position, id) -> {
            String selectedCode = languageCodes[position];

            // Save language preference
            SharedPreferences prefs = getSharedPreferences("AppSettings", MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("AppLanguage", selectedCode);
            editor.apply();

            // Apply language and restart the app
            LocaleHelper.setLocale(LanguageSettingActivity.this, selectedCode);
            Intent intent = new Intent(LanguageSettingActivity.this, HomeActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finishAffinity();
        });
    }
}