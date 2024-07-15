package com.example.fptdictionary.tuvungtienganh;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fptdictionary.R;

public class HomeActivity extends AppCompatActivity {

    int SPLASH_TIME_OUT = 1000;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(HomeActivity.this, MainActivity.class);
                startActivity(intent);

                finish();
            }
        },SPLASH_TIME_OUT);
    }
}