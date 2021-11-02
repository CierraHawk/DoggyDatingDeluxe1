package com.codepath.doggydatingdeluxe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class RegistryActivity extends AppCompatActivity {
    public static final String TAG = "RegistryActivity";
    private Button btnSignup;
    private EditText etNewUsername;
    private EditText etNewPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registry);

        etNewPassword = findViewById(R.id.etNewPassword);
        etNewUsername = findViewById(R.id.etNewUsername);
        btnSignup = findViewById(R.id.btnSignUp);
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createUser();
            }
        });
    }

    public void createUser(){
        ParseUser user = new ParseUser();
        user.setUsername(etNewUsername.getText().toString());
        user.setPassword(etNewPassword.getText().toString());
        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if(e == null){
                    goMainActivity();
                    Toast.makeText(RegistryActivity.this, "Sign Up Successful", Toast.LENGTH_SHORT).show();
                    return;
                }
                else{
                    Log.e(TAG,"Issue Creating Account", e);
                    Toast.makeText(RegistryActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

    private void goMainActivity() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }

}