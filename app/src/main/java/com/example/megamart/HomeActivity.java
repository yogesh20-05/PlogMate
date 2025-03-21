package com.example.megamart;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class HomeActivity extends AppCompatActivity implements NavigationBarView.OnItemSelectedListener {

    BottomNavigationView bottomNavigationView;

    ExploreFragment exploreFragment=new ExploreFragment();
    SocialShelfFragment socialShelfFragment=new SocialShelfFragment();
    LalbindiFragment lalbindiFragment=new LalbindiFragment();
    PloggingFragment ploggingFragment=new PloggingFragment();
    MyProfileFragment myProfileFragment=new MyProfileFragment();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);


        bottomNavigationView = findViewById(R.id.homeBottomNav);
        bottomNavigationView.setSelectedItemId(R.id.menuHomeNavPlogging);
        bottomNavigationView.setOnItemSelectedListener(this);
        getSupportFragmentManager().beginTransaction().replace(R.id.homeFramelayout,ploggingFragment).commit();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_general, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_item_settings) {
            Toast.makeText(HomeActivity.this, "Settings", Toast.LENGTH_SHORT).show();
            Intent i =  new Intent(HomeActivity.this, SettingActivity.class);
            startActivity(i);
        } else if (item.getItemId() == R.id.menu_item_map) {
            Toast.makeText(HomeActivity.this, "Map", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(HomeActivity.this, MapActivity.class);
            startActivity(i);

        } else if (item.getItemId() == R.id.menu_item_aboutus) {
                Toast.makeText(HomeActivity.this, "Aboutus", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(HomeActivity.this, AboutUsActivity.class);
                startActivity(i);

        }
        else if (item.getItemId() == R.id.menu_item_help) {
            Toast.makeText(HomeActivity.this, "Help", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(HomeActivity.this, HelpActivity.class);
            startActivity(i);

        }
        else if (item.getItemId() == R.id.menu_item_logout) {
            AlertDialog.Builder ad = new AlertDialog.Builder(HomeActivity.this);
            ad.setTitle("eBook");
            ad.setMessage("Are you sure , you want to logout ?");

            ad.setPositiveButton("cancle", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });

            ad.setNegativeButton("logout", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent i = new Intent(HomeActivity.this, LoginActivity.class);

                    startActivity(i);

                }
            }).create().show();


        }
        return true;
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==R.id.menuHomeNavExplore)
        {
            getSupportFragmentManager().beginTransaction().replace(R.id.homeFramelayout,exploreFragment).commit();
        }
        else if (item.getItemId()==R.id.menuHomeNavPlogging)
        {
            getSupportFragmentManager().beginTransaction().replace(R.id.homeFramelayout,ploggingFragment).commit();
        }
        else if (item.getItemId()==R.id.menuHomeNavLalbindi)
        {
            getSupportFragmentManager().beginTransaction().replace(R.id.homeFramelayout,lalbindiFragment).commit();
        }
        else if (item.getItemId()==R.id.menuHomeNavSocialShelf)
        {
            getSupportFragmentManager().beginTransaction().replace(R.id.homeFramelayout,socialShelfFragment).commit();
        }
        else if (item.getItemId()==R.id.menuHomeNavMyprofile)
        {
            getSupportFragmentManager().beginTransaction().replace(R.id.homeFramelayout,myProfileFragment).commit();
        }
        return true;
    }

}