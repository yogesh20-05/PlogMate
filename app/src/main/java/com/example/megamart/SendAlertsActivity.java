package com.example.megamart;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

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
}