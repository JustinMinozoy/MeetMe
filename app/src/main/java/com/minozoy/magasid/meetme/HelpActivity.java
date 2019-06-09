package com.minozoy.magasid.meetme;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telecom.Call;
import android.view.View;
import android.widget.Button;

public class HelpActivity extends AppCompatActivity {

    Button Report;
    Button Feedback;
    Button Call;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        Report = findViewById(R.id.report);
        Feedback = findViewById(R.id.feedback);
        Call = findViewById(R.id.call);

        Report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(HelpActivity.this, Report.class));
                finish();

            }
        });

        Feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(HelpActivity.this, Feedback.class));
                finish();

            }
        });

        Call.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {

                startActivity(new Intent(HelpActivity.this, Call.class));
                finish();

            }
        });

    }
}
