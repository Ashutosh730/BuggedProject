package com.admereselvyn.mic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowInsets;
import android.view.WindowInsetsController;
import android.view.WindowManager;
import android.widget.Button;

import static maes.tech.intentanim.CustomIntent.customType;

public class OnBoardingScreen extends AppCompatActivity {

    float x1,x2;
    private SharedPreferences sp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_boarding_screen);
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

//        isFirstTime();

        Button btn =findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OnBoardingScreen.this,  Login.class);
                startActivity(intent);
            }
        });
    }

    private void isFirstTime() {
        // Checking is the user logged in or not
        sp = getSharedPreferences("isFirstPref",MODE_PRIVATE);

        if(sp.getBoolean("isFirst",false)){
            Intent i = new Intent(this, Login.class);
            startActivity(i);
            finish();
        }
        sp.edit().putBoolean("isFirst",true).apply();

    }

    //This method will create left and right gesture
    public boolean onTouchEvent(MotionEvent touchEvent){
        switch(touchEvent.getAction()){
            case (MotionEvent.ACTION_DOWN):
                x1 = touchEvent.getX();
                break;
            case (MotionEvent.ACTION_UP):
                x2 = touchEvent.getX();
                if( (x1>x2)&&(Math.abs(x1-x2)>200)){
                    Intent i = new Intent(OnBoardingScreen.this, OnBoardingScreen1.class);
                    startActivity(i);
                    customType(OnBoardingScreen.this,"left-to-right");//This will create animation
                }
                break;
        }
        return false;
    }

    @Override
    public void onBackPressed() {
        finishAffinity();
        System.exit(0);
    }
}