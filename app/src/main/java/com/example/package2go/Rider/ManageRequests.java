package com.example.package2go.Rider;

import android.os.Bundle;

import android.os.Bundle;
import android.util.Log;
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


import androidx.activity.EdgeToEdge;


import com.example.package2go.R;

public class ManageRequests extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RequestAdapter adapter;
    private List<DeliveryHelperClass> requestList;

    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_manage_requests);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        requestList = new ArrayList<>();
        adapter = new RequestAdapter(requestList);
        recyclerView.setAdapter(adapter);

        reference = FirebaseDatabase.getInstance().getReference("deliveries");

        fetchRequests();
    }

        private void fetchRequests() {
            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    requestList.clear();
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        DeliveryHelperClass request = snapshot.getValue(DeliveryHelperClass.class);
                        if ("awaiting_confirmation".equals(request.getStatus())) {
                            requestList.add(request);
                        }
                    }
                    adapter.notifyDataSetChanged();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Log.e("ManageRequests", "onCancelled", databaseError.toException());
                    Toast.makeText(ManageRequests.this, "Failed to load requests.", Toast.LENGTH_SHORT).show();
                }
            });


    }
}