package com.example.package2go.Rider;

import android.view.LayoutInflater;
import android.view.View;

import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;


import androidx.recyclerview.widget.RecyclerView;

import com.example.package2go.HelperClasses.DeliveryHelperClass;
import com.example.package2go.R;

import java.util.List;

public class PackageAdapter extends RecyclerView.Adapter<PackageAdapter.PackageViewHolder>{

    private List<DeliveryHelperClass> packageList;

    public PackageAdapter(List<DeliveryHelperClass> packageList) {
        this.packageList = packageList;
    }

    @NonNull
    @Override
    public PackageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_package, parent, false);
        return new PackageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PackageViewHolder holder, int position) {
        DeliveryHelperClass delivery = packageList.get(position);
        holder.tvCustomerName.setText(delivery.getCustomerName());
        holder.tvCustomerAddress.setText(delivery.getCustomerAddress());
        holder.tvPackageDesc.setText(delivery.getPackageDesc());
        holder.tvCustomerPhoneNo.setText(delivery.getCustomerPhoneNo());
        holder.tvPackagePrice.setText(delivery.getPackagePrice());
    }

    @Override
    public int getItemCount() {
        return packageList.size();
    }

    public static class PackageViewHolder extends RecyclerView.ViewHolder {

        TextView tvCustomerName, tvCustomerAddress, tvPackageDesc, tvCustomerPhoneNo, tvPackagePrice;

        public PackageViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCustomerName = itemView.findViewById(R.id.tv_customer_name);
            tvCustomerAddress = itemView.findViewById(R.id.tv_customer_address);
            tvPackageDesc = itemView.findViewById(R.id.tv_package_desc);
            tvCustomerPhoneNo = itemView.findViewById(R.id.tv_customer_phone_no);
            tvPackagePrice = itemView.findViewById(R.id.tv_package_price);
        }
    }
}
