package com.example.da08.musicplayerproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

public class LoginActivity extends AppCompatActivity {

    EditText email,password;
    ImageButton btnSignIn, btnSignUp;
    ImageView imgLogo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email = (EditText)findViewById(R.id.email);
        password = (EditText)findViewById(R.id.password);
        imgLogo = (ImageView)findViewById(R.id.imgLogo);

        btnSignIn = (ImageButton)findViewById(R.id.btnSignIn);
        btnSignUp = (ImageButton)findViewById(R.id.btnSignUp);

    }
}
