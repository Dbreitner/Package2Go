package com.example.package2go.BusinessOwner;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.package2go.Common.LoginSignup.BusinessStartUpScreen;
import com.example.package2go.R;
import com.example.package2go.Rider.RiderCompletedDeliveries;

public class BusinessOwnerDashboard extends AppCompatActivity {

    Button button, sendPkg, buttonOpenProfile, logOut_btn;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_owner_dashboard);

        button = findViewById(R.id.completed_del_btn);
        sendPkg = findViewById(R.id.send_pkg_btn);
        logOut_btn= findViewById(R.id.logout_btn);

        buttonOpenProfile = findViewById(R.id.profile_btn);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BusinessOwnerDashboard.this, CompleteDelivery.class);
                startActivity(intent);
            }
        });

        sendPkg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BusinessOwnerDashboard.this, SendPackge.class);
                startActivity(intent);
            }
        });

        logOut_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BusinessOwnerDashboard.this, BusinessStartUpScreen.class);
                startActivity(intent);
            }
        });


        buttonOpenProfile = findViewById(R.id.profile_btn);
        buttonOpenProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BusinessOwnerDashboard.this, ProfileActivity.class);
                startActivity(intent);
            }
        });
    }







}