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
                .withBackgroundResource(R.drawable.flights)
//                .withBackgroundColor(R.drawable.flights)
                .withBeforeLogoText("Your Next Flight");
//                .withLogo(R.drawable.flights);

        config.getBeforeLogoTextView().setTextColor(Color.BLACK);
        config.getBeforeLogoTextView().setTextSize(32);

        View easySplashScreen = config.create();
        setContentView(easySplashScreen);
    }
}
