package com.example.megamart;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Environment;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Locale;

public class ViewInsightsActivity extends AppCompatActivity {

    private DatePicker startDatePicker, endDatePicker;

    private final int totalEvents = 25; // Dummy value
    private final int totalVolunteers = 85; // Dummy value
Button viewDetailedReportButton;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_insights);

        startDatePicker = findViewById(R.id.startDatePicker);
        endDatePicker = findViewById(R.id.endDatePicker);
        TextView totalEventsText = findViewById(R.id.totalEvents);
        TextView totalVolunteersText = findViewById(R.id.totalVolunteers);
        Button generatePDFBtn = findViewById(R.id.downloadPdfBtn);
        Button  viewDetailedReportButton= findViewById(R.id.viewDetailedReportButton);

        totalEventsText.setText("Total Events: " + totalEvents);
        totalVolunteersText.setText("Total Volunteers: " + totalVolunteers);

        generatePDFBtn.setOnClickListener(v -> generatePDF());
        viewDetailedReportButton.setOnClickListener(v -> {
            Intent intent = new Intent(ViewInsightsActivity.this, DetailedReportActivity.class);
            intent.putExtra("startDate", getSelectedDate(startDatePicker));
            intent.putExtra("endDate", getSelectedDate(endDatePicker));
            startActivity(intent);
        });
    }


    private void generatePDF() {
        Document document = new Document();
        try {
            // Generate unique filename using timestamp
            String timeStamp = new java.text.SimpleDateFormat("yyyyMMdd_HHmmss").format(new java.util.Date());
            String fileName = "Insights_Report_" + timeStamp + ".pdf";

            // Path to Downloads folder
            String directoryPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString();
            File file = new File(directoryPath, fileName);

            // Create PDF writer
            PdfWriter.getInstance(document, new FileOutputStream(file));
            document.open();

            // Add content
            document.add(new Paragraph("PlogMate Report"));
            document.add(new Paragraph("From: " + getSelectedDate(startDatePicker)));
            document.add(new Paragraph("To: " + getSelectedDate(endDatePicker)));
            document.add(new Paragraph("Total Events: " + totalEvents));
            document.add(new Paragraph("Total Volunteers: " + totalVolunteers));

            document.close();

            Toast.makeText(this, "PDF saved: " + file.getAbsolutePath(), Toast.LENGTH_LONG).show();

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Failed to generate PDF", Toast.LENGTH_SHORT).show();
        }
    }
    private String getSelectedDate(DatePicker datePicker) {
        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth() + 1;
        int year = datePicker.getYear();
        return day + "/" + month + "/" + year;
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