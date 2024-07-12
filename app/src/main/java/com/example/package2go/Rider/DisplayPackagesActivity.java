package com.example.package2go.Rider;

import android.os.Bundle;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import androidx.activity.EdgeToEdge;
;

import com.example.package2go.HelperClasses.DeliveryHelperClass;
import com.example.package2go.R;


public class DisplayPackagesActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private PackageAdapter adapter;
    private List<DeliveryHelperClass> packageList;

    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_display_packages);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        packageList = new ArrayList<>();
        adapter = new PackageAdapter(packageList);
        recyclerView.setAdapter(adapter);

        reference = FirebaseDatabase.getInstance().getReference("deliveries");

        fetchPackages();

    }

    private void fetchPackages() {
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                packageList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    DeliveryHelperClass delivery = snapshot.getValue(DeliveryHelperClass.class);
                    packageList.add(delivery);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("DisplayPackagesActivity", "onCancelled", databaseError.toException());
                Toast.makeText(DisplayPackagesActivity.this, "Failed to load packages.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}