package com.example.package2go.Rider;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.package2go.HelperClasses.DeliveryHelperClass;
import com.example.package2go.R;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context context;
    ArrayList<DeliveryHelperClass> list;

    public MyAdapter(Context context, ArrayList<DeliveryHelperClass> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.userentry,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        DeliveryHelperClass deliveryHelperClass = list.get(position);
        holder.name.setText(deliveryHelperClass.getCustomerName());
        holder.address.setText(deliveryHelperClass.getCustomerAddress());
        holder.phoneNo.setText(deliveryHelperClass.getCustomerPhoneNo());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView name, address, phoneNo;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name= itemView.findViewById(R.id.textName);
            address= itemView.findViewById(R.id.Address);
            phoneNo = itemView.findViewById(R.id.phoneNo);

        }
    }
}
