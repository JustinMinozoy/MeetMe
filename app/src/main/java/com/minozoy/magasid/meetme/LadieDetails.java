package com.minozoy.magasid.meetme;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class LadieDetails extends AppCompatActivity {

    TextView textTitle, textDesc;
    ImageView image12;
    Button button;

    private static final int Request_Call =1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ladie_details);
        Toolbar toolbar = findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);

        //initialize views
        textTitle = findViewById(R.id.title);
        textDesc = findViewById(R.id.Price);
        image12 = findViewById(R.id.image);
        button = findViewById(R.id.btn_call);

        //get Data from intent
        String Image = getIntent().getStringExtra("Image");
        String title = getIntent().getStringExtra("Title");
        String desc = getIntent().getStringExtra("Description");

        textTitle.setText(title);
        textDesc.setText(desc);
        button.setText("Call for : "+title);
        Picasso.get().load(Image).into(image12);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callPerson();
            }
        });

    }

    private void callPerson() {
        String  nummber = "0703394151";

        if(ContextCompat.checkSelfPermission(LadieDetails.this,
                Manifest.permission.CALL_PHONE )!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(LadieDetails.this,new String[] {Manifest.permission.CALL_PHONE},Request_Call);

        }else {
            String dial = "tel:" +nummber;
            startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == Request_Call){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                callPerson();
            }else {
                Toast.makeText(this,"PERMISSION DENIED",Toast.LENGTH_SHORT).show();
            }
        }

    }

    // handle on back pressed , go to previous activity
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
