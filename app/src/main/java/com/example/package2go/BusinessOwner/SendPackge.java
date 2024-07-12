package com.example.package2go.BusinessOwner;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.package2go.HelperClasses.DeliveryHelperClass;
import com.example.package2go.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SendPackge extends AppCompatActivity {

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    TextInputLayout custName, custPhoneNo, custAddress, pkgDesc, pkgPrice;

    Button sendPkgBtn;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_send_packge);

        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("deliveries");

        custName = findViewById(R.id.customer_name);
        custPhoneNo = findViewById(R.id.customer_phone);
        custAddress = findViewById(R.id.delivery_address);
        pkgPrice = findViewById(R.id.package_price);
        pkgDesc = findViewById( R.id.package_desc);
        sendPkgBtn = findViewById(R.id.send_package_btn);

        sendPkgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String customerName = custName.getEditText().getText().toString();
                String customerAddress = custAddress.getEditText().getText().toString();
                String packageDesc = pkgDesc.getEditText().getText().toString();
                String customerPhoneNo = custPhoneNo.getEditText().getText().toString();
                String packagePrice = pkgPrice.getEditText().getText().toString();

                // Generate unique delivery ID
                String deliveryId = reference.push().getKey();

                DeliveryHelperClass helperClass = new DeliveryHelperClass(deliveryId, customerName, customerAddress, packageDesc, customerPhoneNo, packagePrice, "awaiting_confirmation"
                );
                reference.child(deliveryId).setValue(helperClass)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                // Show success message
                                Toast.makeText(SendPackge.this, "Delivery request sent successfully!", Toast.LENGTH_LONG).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                // Show error message
                                Toast.makeText(SendPackge.this, "Failed to send delivery request: " + e.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        });
            }
        });







    }

}