package com.example.megamart;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetailedReportActivity extends AppCompatActivity {
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_report);

        String start = getIntent().getStringExtra("startDate");
        String end = getIntent().getStringExtra("endDate");

    TextView details = findViewById(R.id.detailsTextView);
        details.setText("Showing detailed report from " + start + " to " + end);

        // You can add a RecyclerView here to show filtered event/volunteer data
    }
}