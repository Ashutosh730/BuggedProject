package com.admereselvyn.mic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.view.WindowInsets;
import android.view.WindowInsetsController;
import android.view.WindowManager;
import android.widget.Button;

import static maes.tech.intentanim.CustomIntent.customType;

public class SplashScreen extends AppCompatActivity {

    SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //This will disable force dark mode on
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_splash_screen);

        //Phone will go full screen mode with no status bar
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
//            final WindowInsetsController insetsController = getWindow().getInsetsController();
//            if (insetsController != null) {
//                insetsController.hide(WindowInsets.Type.statusBars());
//            }
//        } else {
//            getWindow().setFlags(
//                    WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                    WindowManager.LayoutParams.FLAG_FULLSCREEN
//            );
//        }

        //This will crete slight delay to move next screen
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {

                //Commented code for Onboarding Screen
//                 pref = getSharedPreferences("ActivityPREF", Context.MODE_PRIVATE);
//                boolean firstTime = pref.getBoolean("firstTime", true);
//
//                if(firstTime){
//
//                    SharedPreferences.Editor ed = pref.edit();
//                    ed.putBoolean("firstTime", false);
//                    ed.apply();

                    Intent intent = new Intent(getApplicationContext(), OnBoardingScreen.class);
                    startActivity(intent);
                    finish();
//
//                } else {
//
//                    Intent i=new Intent(getApplicationContext(), Login.class);
//                    startActivity(i);
//                    customType(SplashScreen.this,"fadein-to-fadeout");//This will create animation
//                    finish();
//                }
            }
        }, 2000);

    }
}