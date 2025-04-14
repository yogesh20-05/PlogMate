package com.example.megamart;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class SplashActivity extends AppCompatActivity {

    Animation fadeInAnim;
    ImageView ivSplashLogo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_splash);

        ivSplashLogo=findViewById(R.id.splahLogo);
        fadeInAnim= AnimationUtils.loadAnimation(SplashActivity.this,R.anim.fadin);
        ivSplashLogo.setAnimation(fadeInAnim);

        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        },3000);



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

