package com.example.fwd;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_splash);
//        getSupportActionBar().hide();



        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences sharedPreferences = getSharedPreferences("Leftovers", Context.MODE_PRIVATE);


               boolean isLogin = sharedPreferences.getBoolean("isLogin",false);
                Intent intent;

                if(isLogin){
                    intent=new Intent(SplashActivity.this,NavDrawerLayout.class);
                    startActivity(intent);
                    finish();
                }
                else{
                    intent=new Intent(SplashActivity.this,Home.class);
                    startActivity(intent);
                    finish();
                }


            }
        },1000);
    }
}