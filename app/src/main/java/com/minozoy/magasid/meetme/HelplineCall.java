package com.minozoy.magasid.meetme;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HelplineCall extends AppCompatActivity {

    Button buttoncall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_helpline_call);

     buttoncall = findViewById(R.id.call);

     buttoncall.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {

         }
     });

    }

    public void onDial(View view){
        Intent intent = new Intent(Intent.ACTION_CALL);
        //Intent.setData(Uri.parse("tel:0770694723"));

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
            return;
        }
        startActivity(intent);

    }

}
