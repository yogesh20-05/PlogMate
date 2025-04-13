package com.example.megamart;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Environment;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.io.IOException;

public class ViewInsightsActivity extends AppCompatActivity {

    private DatePicker startDatePicker, endDatePicker;

    private final int totalEvents = 25; // Dummy value
    private final int totalVolunteers = 85; // Dummy value

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

        totalEventsText.setText("Total Events: " + totalEvents);
        totalVolunteersText.setText("Total Volunteers: " + totalVolunteers);

        generatePDFBtn.setOnClickListener(v -> generatePDF());
    }

    private void generatePDF() {
        Document document = new Document();
        String filePath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/event_insights.pdf";

        try {
            PdfWriter.getInstance(document, new FileOutputStream(filePath));
            document.open();

            document.add(new Paragraph("Event Insights Report"));
            document.add(new Paragraph(" "));

            int startDay = startDatePicker.getDayOfMonth();
            int startMonth = startDatePicker.getMonth() + 1;
            int startYear = startDatePicker.getYear();

            int endDay = endDatePicker.getDayOfMonth();
            int endMonth = endDatePicker.getMonth() + 1;
            int endYear = endDatePicker.getYear();

            document.add(new Paragraph("Date Range: " + startDay + "/" + startMonth + "/" + startYear +
                    " - " + endDay + "/" + endMonth + "/" + endYear));

            document.add(new Paragraph("Total Events: " + totalEvents));
            document.add(new Paragraph("Total Volunteers: " + totalVolunteers));

            document.close();
            Toast.makeText(this, "PDF saved to Downloads", Toast.LENGTH_LONG).show();

        } catch (DocumentException | IOException e) {
            Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }
}