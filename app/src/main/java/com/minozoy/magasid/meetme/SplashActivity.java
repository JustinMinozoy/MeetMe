package com.minozoy.magasid.meetme;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import gr.net.maroulis.library.EasySplashScreen;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        EasySplashScreen easy_splash = new EasySplashScreen(SplashActivity.this)
                .withFullScreen()
                .withTargetActivity(MainActivity.class)
                .withSplashTimeOut(4000)
                .withBackgroundColor(Color.parseColor("#686de0"))
                .withHeaderText("")
                .withFooterText("Made By Eddy Taia & Mogasid")
                .withBeforeLogoText("")
                .withAfterLogoText("")
                .withLogo(R.drawable.p2);
        easy_splash.getAfterLogoTextView().setTextColor(Color.BLUE);
        easy_splash.getFooterTextView().setTextColor(Color.MAGENTA);
        easy_splash.getAfterLogoTextView().setTextColor(Color.BLUE);

        View view = easy_splash.create();
        setContentView(view);

    }
}
