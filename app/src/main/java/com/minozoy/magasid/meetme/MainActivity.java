package com.minozoy.magasid.meetme;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


     private long backpress;
     private Toast toast;
     private Button button;
     public CheckBox checkBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkBox = (CheckBox) findViewById(R.id.confirm);

        button = (Button) findViewById(R.id.btn_signin);

        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBox.isChecked()) {
                    button.setVisibility(View.VISIBLE);
                } else {
                    button.setVisibility(View.INVISIBLE);
                }
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,profiles.class));
                finish();
            }
        });

    }

    @Override
    public void onBackPressed() {

        if(backpress + 2000 > System.currentTimeMillis()){
            toast.cancel();
            super.onBackPressed();
            return;
        }else{
            toast = Toast.makeText(getBaseContext(),"Press again to exit",Toast.LENGTH_SHORT);
            toast.show();
        }
        backpress = System.currentTimeMillis();

    }
}
