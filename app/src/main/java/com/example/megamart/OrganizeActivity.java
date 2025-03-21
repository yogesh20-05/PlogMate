package com.example.megamart;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.example.megamart.AddEventActivity;
import com.example.megamart.EditEventActivity;
import com.example.megamart.R;

public class OrganizeActivity extends AppCompatActivity {

    private AppCompatButton btnAddEvent, btneditEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organize);

        // Initialize buttons
        btnAddEvent = findViewById(R.id.btnaddevent);
        btneditEvent = findViewById(R.id.btneditevent);

        // Set OnClickListener for Add Event button
        btnAddEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open AddEventActivity
                Intent intent = new Intent(OrganizeActivity.this, AddEventActivity.class);
                startActivity(intent);
            }
        });

        // Set OnClickListener for Remove Event button
        btneditEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open RemoveEventActivity
                Intent intent = new Intent(OrganizeActivity.this, EditEventActivity.class);
                startActivity(intent);
            }
        });
    }
}