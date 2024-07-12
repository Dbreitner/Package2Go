package com.example.package2go.Rider;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.package2go.HelperClasses.DeliveryHelperClass;
import com.example.package2go.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;



    public class OngoingRequestAdapter extends RecyclerView.Adapter<OngoingRequestAdapter.OngoingRequestViewHolder> {

        private List<DeliveryHelperClass> ongoingRequestList;
        private Context context;

        public OngoingRequestAdapter(Context context, List<DeliveryHelperClass> ongoingRequestList) {
            this.context = context;
            this.ongoingRequestList = ongoingRequestList;
        }

        @NonNull
        @Override
        public OngoingRequestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ongoing_request, parent, false);
            return new OngoingRequestViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull OngoingRequestViewHolder holder, int position) {
            DeliveryHelperClass request = ongoingRequestList.get(position);

            holder.tvCustomerName.setText(request.getCustomerName());
            holder.tvCustomerAddress.setText(request.getCustomerAddress());
            holder.tvCustomerPhoneNo.setText(request.getCustomerPhoneNo());

            holder.btnComplete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    markRequestAsCompleted(request.getDeliveryId());
                }
            });
        }

        @Override
        public int getItemCount() {
            return ongoingRequestList.size();
        }

        public class OngoingRequestViewHolder extends RecyclerView.ViewHolder {
            TextView tvCustomerName, tvCustomerAddress, tvCustomerPhoneNo;
            Button btnComplete;

            public OngoingRequestViewHolder(@NonNull View itemView) {
                super(itemView);
                tvCustomerName = itemView.findViewById(R.id.tv_customer_name);
                tvCustomerAddress = itemView.findViewById(R.id.tv_customer_address);
                tvCustomerPhoneNo = itemView.findViewById(R.id.tv_customer_phone_no);
                btnComplete = itemView.findViewById(R.id.btn_complete);
            }
        }

        private void markRequestAsCompleted(String deliveryId) {
            DatabaseReference reference = FirebaseDatabase.getInstance().getReference("deliveries").child(deliveryId);
            reference.child("status").setValue("completed")
                    .addOnSuccessListener(aVoid -> {
                        Toast.makeText(context, "Request marked as completed", Toast.LENGTH_SHORT).show();
                    })
                    .addOnFailureListener(e -> {
                        Toast.makeText(context, "Failed to update request: " + e.getMessage(), Toast.LENGTH_LONG).show();
                    });
        }

    }
