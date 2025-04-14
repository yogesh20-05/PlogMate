package com.example.megamart;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import java.util.Locale;

public class SettingActivity extends AppCompatActivity {

    Button btnSettingThemes, btnSettingPermission, btnSettingNotification, btnSettingSecurity,
            btnSettingLanguage, btnSettingSavedLoginInfo, btnSettingBlockedUser;

    private static final String PREFS_NAME = "AppSettings";
    private static final String THEME_KEY = "AppTheme";


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        applySavedTheme();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        btnSettingThemes = findViewById(R.id.btnTheme);
        btnSettingPermission = findViewById(R.id.btnSettingPermission);
        btnSettingNotification = findViewById(R.id.btnSettingNotification);
        btnSettingSecurity = findViewById(R.id.btnSettingSecurity);
        btnSettingLanguage = findViewById(R.id.btnSettingLanguage);
        btnSettingSavedLoginInfo = findViewById(R.id.btnSettingSavedLoginInfo);
        btnSettingBlockedUser = findViewById(R.id.btnSettingBlockedUser);

        btnSettingThemes.setOnClickListener(v -> showThemeSelectionDialog());

        btnSettingLanguage.setOnClickListener(v -> {
            Intent intent = new Intent(SettingActivity.this, LanguageSettingActivity.class);
            startActivity(intent);
        });
        btnSettingNotification.setOnClickListener(v -> {
            Intent intent = new Intent(SettingActivity.this, NotificationSettingActivity.class);
            startActivity(intent);
        });
        btnSettingPermission.setOnClickListener(v -> {
            Intent intent = new Intent(SettingActivity.this,PermissionSettingActivity.class);
            startActivity(intent);
        });
    }

    private void showThemeSelectionDialog() {
        String[] themes = {"Light", "Dark"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Choose Theme");
        builder.setItems(themes, (dialog, which) -> {
            if (which == 0) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                saveToPrefs(THEME_KEY, "light");
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                saveToPrefs(THEME_KEY, "dark");
            }
        });
        builder.show();
    }
    private void applySavedTheme() {
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        String theme = prefs.getString(THEME_KEY, "light");

        if (theme.equals("dark")) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }

    private void saveToPrefs(String key, String value) {
        SharedPreferences.Editor editor = getSharedPreferences(PREFS_NAME, MODE_PRIVATE).edit();
        editor.putString(key, value);
        editor.apply();
    }
}