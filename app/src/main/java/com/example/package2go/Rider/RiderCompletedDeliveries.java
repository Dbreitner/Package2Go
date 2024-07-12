package com.example.package2go.Rider;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.package2go.R;

public class RiderCompletedDeliveries extends AppCompatActivity {

    ImageView backBtn;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rider_completed_deliveries);

        //hooks
        backBtn = findViewById(R.id.back_to_dashboard_btn);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RiderCompletedDeliveries.super.onBackPressed();
            }
        });



    }
}