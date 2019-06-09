package com.minozoy.magasid.meetme;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class Call extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call);

        setContentView(R.layout.activity_call);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ImageView imageView = (ImageView) findViewById(R.id.imageView);

        LinearLayout rek;
        rek = (LinearLayout) findViewById(R.id.lay_image);


        assert imageView != null;
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText text = findViewById(R.id.phone);
                assert text != null;
                String number = text.getText().toString();

                if (number.isEmpty())
                {
                    Toast.makeText(getApplicationContext(), "You didn't enter a a number", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Intent intent = new Intent(Intent.ACTION_CALL);
                    intent.setData(Uri.parse("tel:" + number));

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M);
                    if (checkSelfPermission(Manifest.CALL_PHONE) != PackageManager.PERMISSION_GRANTED)
                    {
                        Toast.makeText(getApplicationContext(), "Unissued call permission", Toast.LENGTH_SHORT).show();
                        requestPermissions(new String[] {Manifest.permission.CALL_PHONE}, 10);
                        return;
                    }
                }
                startActivity(intent);
            }
        });

        Button kod = findViewById(R.id.k)

    }
}
