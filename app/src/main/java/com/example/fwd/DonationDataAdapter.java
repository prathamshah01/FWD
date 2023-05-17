package com.example.fwd;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DonationDataAdapter extends RecyclerView.Adapter<DonationDataAdapter.DonationViewHolder> {

    Context context;
    ArrayList<Donator> list;

    public DonationDataAdapter(Context context, ArrayList<Donator> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public DonationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.view_request_layout,parent,false);
        return  new DonationViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull DonationViewHolder holder, int position) {

        Donator donator = list.get(position);

        holder.Name.setText(donator.getName());
        holder.Phonenumber.setText(donator.getPhonenumber());
        holder.Address.setText(donator.getAddress());
        holder.Foodcount.setText(donator.getFoodcount());
        holder.Foodtype.setText(donator.getFoodtype());
        holder.Foodexpriy.setText(donator.getFoodexpriy());
        holder.Fromtime.setText(donator.getFromtime());
        holder.Totime.setText(donator.getTotime());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class DonationViewHolder extends RecyclerView.ViewHolder{
        TextView Name,Phonenumber,Foodtype,Foodexpriy,Foodcount,Fromtime,Totime,Address;

        public DonationViewHolder(@NonNull View itemView) {
            super(itemView);

            Name = itemView.findViewById(R.id.txtDName);
            Phonenumber = itemView.findViewById(R.id.txtDPhone);
            Foodtype = itemView.findViewById(R.id.txtDFoodType);
            Foodcount = itemView.findViewById(R.id.txDIncludes);
            Foodexpriy = itemView.findViewById(R.id.txtDExpiry);
            Fromtime = itemView.findViewById(R.id.txtDAFrom);
            Totime = itemView.findViewById(R.id.txtDATo);
            Address = itemView.findViewById(R.id.txtDAdd);

        }
    }

}
