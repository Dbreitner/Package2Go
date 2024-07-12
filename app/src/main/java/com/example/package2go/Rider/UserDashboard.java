package com.example.package2go.Rider;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.package2go.BusinessOwner.BusinessOwnerDashboard;
import com.example.package2go.R;

public class UserDashboard extends AppCompatActivity {
    Button completedDel, deliveryRequests, ongoingDel;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_dashboard);

        completedDel = findViewById(R.id.completed_del_btn);
        deliveryRequests = findViewById(R.id.delivery_req_btn);
        ongoingDel = findViewById(R.id.ongoing_btn);

        completedDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserDashboard.this, RiderCompletedDeliveries.class);
                startActivity(intent);
            }
        });

        deliveryRequests.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserDashboard.this, ManageRequests.class);
                startActivity(intent);
            }
        });

        ongoingDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserDashboard.this, OngoingRequestActivity.class);
                startActivity(intent);
            }
        });


    }
}