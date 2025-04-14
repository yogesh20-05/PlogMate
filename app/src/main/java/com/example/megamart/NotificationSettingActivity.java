package com.example.megamart;



import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Switch;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class NotificationSettingActivity extends AppCompatActivity {

    Switch switchShowNotifications, switchSound, switchVibration, switchNotificationBadges,switch_lock_screen_notifications;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_setting);

        // Setup toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        // Initialize switches
        switchShowNotifications = findViewById(R.id.switch_show_notifications);
        switch_lock_screen_notifications=findViewById(R.id. switch_lock_screen_notifications);
        switchSound = findViewById(R.id.switch_sound);
        switchVibration = findViewById(R.id.switch_vibration);
        switchNotificationBadges = findViewById(R.id.switch_notification_badges);

        // Optional: Add listeners or save states
        switchShowNotifications.setOnCheckedChangeListener((buttonView, isChecked) -> {
            // Add your logic
        });
    }

    // Handle back arrow click
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish(); // Close activity and go back
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}