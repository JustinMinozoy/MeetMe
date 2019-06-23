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
        setContentView(R.layout.activity_main);

        checkBox = (CheckBox) findViewById(R.id.confirm);
        layout = (RelativeLayout) findViewById(R.id.Grid);

        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBox.isChecked()) {
                    //layout.setVisibility(View.VISIBLE);
                    Intent intent = new Intent(MainActivity.this,profiles.class);
                    startActivity(intent);
                    finish();
                } else {
                    layout.setVisibility(View.INVISIBLE);
                }
            }
        });

        editText = (EditText) findViewById(R.id.phoneText);
        countryCodePicker = (CountryCodePicker) findViewById(R.id.ccp);
        countryCodePicker.registerCarrierNumberEditText(editText);

        firebaseAuth = FirebaseAuth.getInstance();

        findViewById(R.id.btnproceed).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String code = countryCodePicker.getFullNumberWithPlus();



                if (code.isEmpty() || code.length() < 10) {
                    editText.setError("Invalid Phone Number");
                    editText.requestFocus();
                    return;
                }

                String phonenumber = "+" + code ;

                Intent intent = new Intent(MainActivity.this, VerificationActivity.class);
                intent.putExtra("phonenumber", phonenumber);
                startActivity(intent);

            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            Intent intent = new Intent(MainActivity.this, VerificationActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

            startActivity(intent);
        }
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
