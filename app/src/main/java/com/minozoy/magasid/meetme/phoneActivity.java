package com.minozoy.magasid.meetme;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.hbb20.CountryCodePicker;

import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

public class phoneActivity extends AppCompatActivity  {
    private EditText editText;

    private CountryCodePicker countryCodePicker;
    private String TAG;
    public String verifiCation;
    private String phoneNumber;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone);

        editText = (EditText) findViewById(R.id.phoneText);
        countryCodePicker = (CountryCodePicker) findViewById(R.id.ccp);
        countryCodePicker.registerCarrierNumberEditText(editText);

        firebaseAuth = FirebaseAuth.getInstance();

        findViewById(R.id.btnproceed).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String code = countryCodePicker.getFullNumberWithPlus();



                if (code.isEmpty() || code.length() < 10) {
                    editText.setError("Invalid Telephone");
                    editText.requestFocus();
                    return;
                }

                String phonenumber = "+" + code ;

                Intent intent = new Intent(phoneActivity.this, VerificationActivity.class);
                intent.putExtra("phonenumber", phonenumber);
                startActivity(intent);

            }
        });



    }

    @Override
    protected void onStart() {
        super.onStart();

        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            Intent intent = new Intent(phoneActivity.this, VerificationActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

            startActivity(intent);
        }
    }



}
