package com.example.megamart;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar; // Import Toolbar
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;

public class HomeActivity extends AppCompatActivity implements NavigationBarView.OnItemSelectedListener {

    BottomNavigationView bottomNavigationView;
    DrawerLayout drawerLayout;
    ImageButton buttonDrawerToggle;
    NavigationView navigationView;

    ExploreFragment exploreFragment = new ExploreFragment();
    SocialShelfFragment socialShelfFragment = new SocialShelfFragment();
    LalbindiFragment lalbindiFragment = new LalbindiFragment();
    PloggingFragment ploggingFragment = new PloggingFragment();
    MyProfileFragment myProfileFragment = new MyProfileFragment();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Consider removing EdgeToEdge if you want the toolbar to be truly at the top
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);

       // drawerLayout = findViewById(R.id.draw)
        //imp
        drawerLayout = findViewById(R.id.navid);
        buttonDrawerToggle = findViewById(R.id.btnDrawerToggle);
        navigationView = findViewById(R.id.navigationView);



        buttonDrawerToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.open();
            }
        });






        //start the next og navigation drawer from here
        // Toolbar setup
        Toolbar toolbar = findViewById(R.id.toolbar); // Assuming you have a Toolbar with id "toolbar"
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
           // getSupportActionBar().setTitle("PlogMate"); // Set your app title
        }

        bottomNavigationView = findViewById(R.id.homeBottomNav);
        bottomNavigationView.setSelectedItemId(R.id.menuHomeNavPlogging);
        bottomNavigationView.setOnItemSelectedListener(this);
        getSupportFragmentManager().beginTransaction().replace(R.id.homeFramelayout, ploggingFragment).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_general, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.menu_item_settings) {
            Toast.makeText(HomeActivity.this, "Settings", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(HomeActivity.this, SettingActivity.class));
            return true;
        } else if (itemId == R.id.menu_item_admin) {
            Toast.makeText(HomeActivity.this, "admin", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(HomeActivity.this, EditEventActivity.class));
            return true;
        }
        else if (itemId == R.id.menu_item_aboutus) {
            Toast.makeText(HomeActivity.this, "About us", Toast.LENGTH_SHORT).show(); // Corrected string
            startActivity(new Intent(HomeActivity.this, AboutUsActivity.class));
            return true;
        } else if (itemId == R.id.menu_item_help) {
            Toast.makeText(HomeActivity.this, "Help", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(HomeActivity.this, HelpActivity.class));
            return true;
        } else if (itemId == R.id.menu_item_logout) {
            new AlertDialog.Builder(this) // Use 'this' instead of 'HomeActivity.this' for context
                    .setTitle("PlogMate") // Consistent title
                    .setMessage("Are you sure you want to log out?") // Clearer message
                    .setPositiveButton("Cancel", (dialog, which) -> dialog.cancel()) // Improved lambda syntax
                    .setNegativeButton("Log out", (dialog, which) -> {
                        startActivity(new Intent(HomeActivity.this, LoginActivity.class));
                        finish(); // Close HomeActivity after logout
                    })
                    .show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.menuHomeNavExplore) {
            getSupportFragmentManager().beginTransaction().replace(R.id.homeFramelayout, exploreFragment).commit();
        } else if (itemId == R.id.menuHomeNavPlogging) {
            getSupportFragmentManager().beginTransaction().replace(R.id.homeFramelayout, ploggingFragment).commit();
        } else if (itemId == R.id.menuHomeNavLalbindi) {
            getSupportFragmentManager().beginTransaction().replace(R.id.homeFramelayout, lalbindiFragment).commit();
        } else if (itemId == R.id.menuHomeNavSocialShelf) {
            getSupportFragmentManager().beginTransaction().replace(R.id.homeFramelayout, socialShelfFragment).commit();
        } else if (itemId == R.id.menuHomeNavMyprofile) {
            getSupportFragmentManager().beginTransaction().replace(R.id.homeFramelayout, myProfileFragment).commit();
        }
        return true;
    }
}