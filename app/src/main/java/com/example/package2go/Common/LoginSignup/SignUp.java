package com.example.package2go.Common.LoginSignup;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.package2go.BusinessOwner.ProfileActivity;
import com.example.package2go.R;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity {
    //Variables

    private static final String TAG = "SignUp";
    TextInputLayout regName, regUsername, regEmail, regPhoneNo, regPassword;
    Button regBtn, regToLoginBtn;

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_sign_up);

        //Hooks to all xml elements in activity_sign_up.xml
        regName = findViewById(R.id.signup_fullname);
        regUsername = findViewById(R.id.signup_username);
        regEmail = findViewById(R.id.signup_email);
        regPhoneNo = findViewById(R.id.signup_phone);
        regPassword = findViewById(R.id.signup_password);
        regBtn = findViewById(R.id.reg_btn);
        regToLoginBtn = findViewById(R.id.signup_login_button);
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();


                regBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        createAccount(regEmail.getEditText().getText().toString(), regPassword.getEditText().getText().toString());
                    }
                });
            }

    private void createAccount(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser user = mAuth.getCurrentUser();
                        if (user != null) {
                            saveUserDetails(user);
                        }
                    } else {
                        Log.w(TAG, "createUserWithEmail:failure", task.getException());
                        if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                            Toast.makeText(SignUp.this, "User with this email already exist.", Toast.LENGTH_SHORT).show();
                        } else if (task.getException() instanceof FirebaseAuthWeakPasswordException) {
                            Toast.makeText(SignUp.this, "Password is too weak.", Toast.LENGTH_SHORT).show();
                        } else if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                            Toast.makeText(SignUp.this, "Invalid email format.", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(SignUp.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void saveUserDetails(FirebaseUser user) {
        String userId = user.getUid();
        String name = regName.getEditText().getText().toString();
        String email = user.getEmail();
        String phone = regPhoneNo.getEditText().getText().toString();

        User userDetails = new User(name, email, phone);

        mDatabase.child("users").child(userId).setValue(userDetails)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(SignUp.this, "User details saved.", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(SignUp.this, ProfileActivity.class));
                        finish();
                    } else {
                        Toast.makeText(SignUp.this, "Failed to save user details.", Toast.LENGTH_SHORT).show();
                    }
                });
    }


    public static class User {
        public String name;
        public String email;
        public String phone;

        public User() {
            // Default constructor required for calls to DataSnapshot.getValue(User.class)
        }

        public User(String name, String email, String phone) {
            this.name = name;
            this.email = email;
            this.phone = phone;
        }
    }


}


