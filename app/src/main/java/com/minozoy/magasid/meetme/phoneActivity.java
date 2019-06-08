package com.minozoy.magasid.meetme;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class phoneActivity extends AppCompatActivity {

    private Button button;
    private EditText text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone);

        button = (Button)findViewById(R.id.btn_Login);
        text = (EditText)findViewById(R.id.phone);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openOne();
            }
        });
    }

    private void openOne() {

        startActivity(new Intent(phoneActivity.this,profiles.class));
        finish();
    }

    private void openDialogue() {
        Dialogue dialogue = new Dialogue();
        dialogue.show(getSupportFragmentManager(),"Code ");

    }

}
