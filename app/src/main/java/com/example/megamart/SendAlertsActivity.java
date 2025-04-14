package com.example.megamart;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class SendAlertsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_alerts);

        View rootLayout = findViewById(R.id.rootLayout);
        if (rootLayout != null) {
            // You can customize inset handling here if needed
            rootLayout.setOnApplyWindowInsetsListener(View::onApplyWindowInsets);
        }

        EditText alertMessage = findViewById(R.id.alertMessage);
        Button sendButton = findViewById(R.id.sendButton);

        sendButton.setOnClickListener(v -> {
            String message = alertMessage.getText().toString().trim();
            if (!message.isEmpty()) {
                Toast.makeText(SendAlertsActivity.this, "Alert sent: " + message, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(SendAlertsActivity.this, "Please enter a message", Toast.LENGTH_SHORT).show();
            }
        });
    }
    protected void attachBaseContext(Context newBase) {
        SharedPreferences prefs = newBase.getSharedPreferences("AppSettings", MODE_PRIVATE);
        String langCode = prefs.getString("AppLanguage", "en");
        Locale locale = new Locale(langCode);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.setLocale(locale);
        Context context = newBase.createConfigurationContext(config);
        super.attachBaseContext(context);}
}