package com.example.megamart;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditEventActivity extends AppCompatActivity {

    /*private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_event);

        fetchEvents();  // Call API to fetch events
        editEvent(1, "New Event", "2025-04-01", "10:00 AM", "New Address"); // Example update
    }

    // Fetch events from API
    private void fetchEvents() {
        RetrofitClient.getInstance().getApiService().getEvents().enqueue(new Callback<List<EventModel>>() {
            @Override
            public void onResponse(Call<List<EventModel>> call, Response<List<EventModel>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<EventModel> events = response.body();
                    for (EventModel event : events) {
                        Log.d(TAG, "Event: " + event.getName());
                    }
                } else {
                    Log.e(TAG, "Failed to fetch events");
                }
            }

            @Override
            public void onFailure(Call<List<EventModel>> call, Throwable t) {
                Log.e(TAG, "Error: " + t.getMessage());
            }
        });
    }

    // Edit event
    private void editEvent(int eventId, String name, String date, String time, String address) {
        RetrofitClient.getInstance().getApiService().editEvent(eventId, name, date, time, address)
                .enqueue(new Callback<ResponseModel>() {
                    @Override
                    public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            Toast.makeText(EditEventActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(EditEventActivity.this, "Failed to update event", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseModel> call, Throwable t) {
                        Log.e(TAG, "Error: " + t.getMessage());
                    }
                });
    }*/
    @Override
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
