package com.example.megamart;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.megamart.R;

public class HelpActivity extends AppCompatActivity {

    TextView question1, answer1, question2, answer2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        setTitle("Help &Support");

        // Linking UI Elements
        question1 = findViewById(R.id.question1);
        answer1 = findViewById(R.id.answer1);
        question2 = findViewById(R.id.question2);
        answer2 = findViewById(R.id.answer2);

        // Click Event for Question 1
        question1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (answer1.getVisibility() == View.GONE) {
                    answer1.setVisibility(View.VISIBLE);
                } else {
                    answer1.setVisibility(View.GONE);
                }
            }
        });

        // Click Event for Question 2
        question2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (answer2.getVisibility() == View.GONE) {
                    answer2.setVisibility(View.VISIBLE);
                } else {
                    answer2.setVisibility(View.GONE);
                }
            }
        });
    }
}