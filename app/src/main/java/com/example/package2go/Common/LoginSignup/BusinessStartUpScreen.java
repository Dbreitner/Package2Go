package com.example.package2go.Common.LoginSignup;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.package2go.R;

public class BusinessStartUpScreen extends AppCompatActivity {
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_start_up_screen);

        button = findViewById(R.id.login_btn);

    }


    public void loginBtn(View view) {


        Intent intent = new Intent(getApplicationContext(), Login.class);
        startActivity(intent);
        finish();

    }


    public void signUpBtn(View view) {


        Intent intent = new Intent(getApplicationContext(), SignUp.class);
        startActivity(intent);
        finish();

    }
}