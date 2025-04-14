package com.example.megamart;



import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class PermissionSettingActivity extends AppCompatActivity {

    private static final int LOCATION_PERMISSION_CODE = 100;
    private static final int STORAGE_PERMISSION_CODE = 101;

    @SuppressLint("UseSwitchCompatOrMaterialCode")
    private Switch switchLocation;
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    private Switch switchStorage;

    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission_setting);

        // Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null) {
            getSupportActionBar().setTitle(R.string.plogmate);
        }

        // Preferences
        SharedPreferences prefs = getSharedPreferences("permissions", MODE_PRIVATE);
        editor = prefs.edit();

        switchLocation = findViewById(R.id.switch_location_permission);
        switchStorage = findViewById(R.id.switch_storage_permission);

        // Restore switch states
        switchLocation.setChecked(prefs.getBoolean("location_enabled", false));
        switchStorage.setChecked(prefs.getBoolean("storage_enabled", false));

        switchLocation.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                checkAndRequestPermission(Manifest.permission.ACCESS_FINE_LOCATION, LOCATION_PERMISSION_CODE);
            } else {
                editor.putBoolean("location_enabled", false).apply();
            }
        });

        switchStorage.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                checkAndRequestPermission(Manifest.permission.READ_EXTERNAL_STORAGE, STORAGE_PERMISSION_CODE);
            } else {
                editor.putBoolean("storage_enabled", false).apply();
            }
        });
    }

    private void checkAndRequestPermission(String permission, int requestCode) {
        if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{permission}, requestCode);
        } else {
            onPermissionGranted(permission);
        }
    }

    private void onPermissionGranted(String permission) {
        if (Manifest.permission.ACCESS_FINE_LOCATION.equals(permission)) {
            editor.putBoolean("location_enabled", true).apply();
            enableLocationServices();
            Toast.makeText(this, "Location enabled", Toast.LENGTH_SHORT).show();
        } else if (Manifest.permission.READ_EXTERNAL_STORAGE.equals(permission)) {
            editor.putBoolean("storage_enabled", true).apply();
            Toast.makeText(this, "Storage permission granted", Toast.LENGTH_SHORT).show();
        }
    }

    private void enableLocationServices() {
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        boolean gpsEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);

        if (!gpsEnabled) {
            Toast.makeText(this, "Please enable location from settings", Toast.LENGTH_LONG).show();
            startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            onPermissionGranted(permissions[0]);
        } else {
            Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
            if (requestCode == LOCATION_PERMISSION_CODE) {
                switchLocation.setChecked(false);
            } else if (requestCode == STORAGE_PERMISSION_CODE) {
                switchStorage.setChecked(false);
            }
        }
    }
}