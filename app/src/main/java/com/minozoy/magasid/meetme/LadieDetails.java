package com.minozoy.magasid.meetme;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class LadieDetails extends AppCompatActivity {

    TextView textTitle, textDesc;
    ImageView image12;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ladie_details);
        ActionBar actionBar = getSupportActionBar();

        //  Bar Title
        actionBar.setTitle("Full Lady Details");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        //initialize views
        textTitle = findViewById(R.id.title);
        textDesc = findViewById(R.id.Price);
        image12 = findViewById(R.id.image);

        //get Data from intent
        String Image = getIntent().getStringExtra("Image");
        String title = getIntent().getStringExtra("Title");
        String desc = getIntent().getStringExtra("Description");

        textTitle.setText(title);
        textDesc.setText(desc);
        Picasso.get().load(Image).into(image12);

    }

    // handle on back pressed , go to previous activity
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
