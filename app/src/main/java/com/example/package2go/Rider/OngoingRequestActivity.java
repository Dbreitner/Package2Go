package com.example.package2go.Rider;

import android.os.Bundle;

import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.package2go.HelperClasses.DeliveryHelperClass;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


import com.example.package2go.R;

public class OngoingRequestActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private OngoingRequestAdapter adapter;
    private List<DeliveryHelperClass> ongoingRequestList;
    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ongoing_requests);

        recyclerView = findViewById(R.id.recycler_view2);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ongoingRequestList = new ArrayList<>();
        adapter = new OngoingRequestAdapter(this, ongoingRequestList);
        recyclerView.setAdapter(adapter);

        reference = FirebaseDatabase.getInstance().getReference("deliveries");

        reference.orderByChild("status").equalTo("accepted").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ongoingRequestList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    DeliveryHelperClass request = snapshot.getValue(DeliveryHelperClass.class);
                    ongoingRequestList.add(request);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(OngoingRequestActivity.this, "Failed to load requests: " + databaseError.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }
}