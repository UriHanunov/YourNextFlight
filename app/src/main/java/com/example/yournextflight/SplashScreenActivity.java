package com.example.yournextflight;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import gr.net.maroulis.library.EasySplashScreen;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EasySplashScreen config = new EasySplashScreen(SplashScreenActivity.this)
                .withFullScreen()
                .withTargetActivity(MainActivity.class)
                .withSplashTimeOut(1500)
                .withBackgroundColor(Color.parseColor("#1a1b29"))
                .withBeforeLogoText("Your Next Flight")
                .withLogo(R.mipmap.ic_launcher_round);

        config.getBeforeLogoTextView().setTextColor(Color.BLACK);

        View easySplashScreen = config.create();
        setContentView(easySplashScreen);
    }
}
