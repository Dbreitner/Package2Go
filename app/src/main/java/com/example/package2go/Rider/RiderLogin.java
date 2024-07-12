package com.example.package2go.Rider;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.package2go.BusinessOwner.BusinessOwnerDashboard;
import com.example.package2go.Common.LoginSignup.Login;
import com.example.package2go.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RiderLogin extends AppCompatActivity {

    Button loginButton;

    private FirebaseAuth auth;
    private EditText loginEmail, password;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rider_login);

        auth = FirebaseAuth.getInstance();
        loginEmail = findViewById(R.id.rider_email_field);
        password = findViewById(R.id.rider_password_field);

        loginButton = findViewById(R.id.rider_login_btn);
        loginButton.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view) {

                String email = loginEmail.getText().toString();
                String pass = password.getText().toString();

                if (!email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    if (!pass.isEmpty()) {
                        auth.signInWithEmailAndPassword(email, pass)
                                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                    @Override
                                    public void onSuccess(AuthResult authResult) {
                                        Toast.makeText(RiderLogin.this, "Login Successful", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(RiderLogin.this,UserDashboard.class));
                                        finish();
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(RiderLogin.this, "Login Failed", Toast.LENGTH_SHORT).show();
                                    }
                                });
                    } else {
                        password.setError("Empty fields are not allowed");
                    }
                } else if (email.isEmpty()) {
                    loginEmail.setError("Empty fields are not allowed");
                } else {
                    loginEmail.setError("Please enter correct email");
                }
            }
        });


    }
}