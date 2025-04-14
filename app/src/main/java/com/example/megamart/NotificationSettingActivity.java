package com.example.megamart;



import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Switch;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class NotificationSettingActivity extends AppCompatActivity {

    @SuppressLint("UseSwitchCompatOrMaterialCode")
    Switch switchShowNotifications, switchSound, switchVibration, switchNotificationBadges, switch_lock_screen_notifications;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_setting); // Make sure this matches your XML file name

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Notification Settings");
        }

        // Initialize the switches AFTER setContentView
        switchShowNotifications = findViewById(R.id.switch_show_notifications);
        switch_lock_screen_notifications = findViewById(R.id.switch_lock_screen_notifications);
        switchSound = findViewById(R.id.switch_sound);
        switchVibration = findViewById(R.id.switch_vibration);
        switchNotificationBadges = findViewById(R.id.switch_notification_badges);

        SharedPreferences prefs = getSharedPreferences("NotificationPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        // Load saved switch states
        switchShowNotifications.setChecked(prefs.getBoolean("show_notifications", true));
        switch_lock_screen_notifications.setChecked(prefs.getBoolean("switch_lock_screen_notifications", true));
        switchSound.setChecked(prefs.getBoolean("sound", true));
        switchVibration.setChecked(prefs.getBoolean("vibration", true));
        switchNotificationBadges.setChecked(prefs.getBoolean("badges", true));

        // Save state when switch is toggled
        switchShowNotifications.setOnCheckedChangeListener((buttonView, isChecked) ->
                editor.putBoolean("show_notifications", isChecked).apply());

        switch_lock_screen_notifications.setOnCheckedChangeListener((buttonView, isChecked) ->
                editor.putBoolean("switch_lock_screen_notifications", isChecked).apply());

        switchSound.setOnCheckedChangeListener((buttonView, isChecked) ->
                editor.putBoolean("sound", isChecked).apply());

        switchVibration.setOnCheckedChangeListener((buttonView, isChecked) ->
                editor.putBoolean("vibration", isChecked).apply());

        switchNotificationBadges.setOnCheckedChangeListener((buttonView, isChecked) ->
                editor.putBoolean("badges", isChecked).apply());
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed(); // or finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}