package com.example.package2go.Rider;

import android.content.Context;
import android.content.Intent;
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

public class RequestAdapter  extends RecyclerView.Adapter<RequestAdapter.RequestViewHolder> {
    private List<DeliveryHelperClass> requestList;
    private Context context;

    public RequestAdapter(List<DeliveryHelperClass> requestList) {
        this.context = context;
        this.requestList = requestList;
    }


    @NonNull
    @Override
    public RequestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_request, parent, false);
        return new RequestViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RequestViewHolder holder, int position) {
        DeliveryHelperClass request = requestList.get(position);
        holder.tvCustomerName.setText(request.getCustomerName());
        holder.tvCustomerAddress.setText(request.getCustomerAddress());
        holder.tvPackageDesc.setText(request.getPackageDesc());
        holder.tvCustomerPhoneNo.setText(request.getCustomerPhoneNo());
        holder.tvPackagePrice.setText(request.getPackagePrice());

        holder.btnAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateRequestStatus(request.getDeliveryId(), "accepted");
                Intent intent = new Intent(context, OngoingRequestActivity.class);
                context.startActivity(intent);
            }
        });

        holder.btnDecline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateRequestStatus(request.getDeliveryId(), "declined");
            }
        });
    }

    @Override
    public int getItemCount() {
        return requestList.size();
    }

    public static class RequestViewHolder extends RecyclerView.ViewHolder {
        TextView tvCustomerName, tvCustomerAddress, tvPackageDesc, tvCustomerPhoneNo, tvPackagePrice;
        Button btnAccept, btnDecline;

        public RequestViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCustomerName = itemView.findViewById(R.id.tv_customer_name);
            tvCustomerAddress = itemView.findViewById(R.id.tv_customer_address);
            tvPackageDesc = itemView.findViewById(R.id.tv_package_desc);
            tvCustomerPhoneNo = itemView.findViewById(R.id.tv_customer_phone_no);
            tvPackagePrice = itemView.findViewById(R.id.tv_package_price);
            btnAccept = itemView.findViewById(R.id.btn_accept);
            btnDecline = itemView.findViewById(R.id.btn_decline);
        }
    }

    private void updateRequestStatus(String deliveryId, String status) {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("deliveries").child(deliveryId);
        reference.child("status").setValue(status)
                .addOnSuccessListener(aVoid -> {
                    Toast.makeText(context, "Request " + status, Toast.LENGTH_SHORT).show();
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(context, "Failed to update request: " + e.getMessage(), Toast.LENGTH_LONG).show();
                });
    }
}

