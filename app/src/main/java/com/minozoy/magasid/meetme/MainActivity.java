package com.minozoy.magasid.meetme;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthProvider;
import com.hbb20.CountryCodePicker;

public class MainActivity extends AppCompatActivity {


     private long backpress;
     private Toast toast;
     private RelativeLayout layout;
     public CheckBox checkBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkBox = (CheckBox) findViewById(R.id.confirm);
        layout = (RelativeLayout) findViewById(R.id.Grid);

        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBox.isChecked()) {
                    layout.setVisibility(View.VISIBLE);

                } else {
                    layout.setVisibility(View.INVISIBLE);
                }
            }
        });


    }

    //code for google sign in

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
