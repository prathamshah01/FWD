package com.example.fwd;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.net.Uri;
import android.os.Handler;
import android.provider.CallLog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Locale;

public class DonationDataAdapter extends RecyclerView.Adapter<DonationDataAdapter.DonationViewHolder> {

    Context context;
    ArrayList<Donator> list;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference databaseRef = database.getReference("Donator");


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
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.view_request_layout, null);
//        Log.i("Data",""+this.list.get(position));
        Donator donator = list.get(position);

        holder.Name.setText(donator.getName());
        holder.Phonenumber.setText(donator.getPhonenumber());
        holder.Address.setText(donator.getAddress());
        holder.Foodcount.setText(donator.getFoodcount());
        holder.Foodtype.setText(donator.getFoodtype());
        holder.Foodexpriy.setText(donator.getFoodexpriy());
        holder.Fromtime.setText(donator.getFromtime());
        holder.Totime.setText(donator.getTotime());
        holder.Status.setText(donator.getStatus());
        holder.myKey.setText(donator.getKey());


        String address = donator.getAddress();

//        NAVIGATION TO MAP ON IMAGE BUTTON
        holder.ibMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String map = "http://maps.google.co.in/maps?q=" + address;
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(map));

                context.startActivity(mapIntent);
            }
        });
//        NAVIGATION TO MAP ON IMAGE BUTTON FINISH


//        NAVIGATION TO CALL LOGS WITH PHONE NUMBER AND PERMISSION
        holder.Phonenumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final int MY_PERMISSIONS_REQUEST_CALL_PHONE = 1;

                if (ContextCompat.checkSelfPermission(context,
                        Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED){

                    String phone = donator.getPhonenumber();
                    Uri phoneUri = Uri.parse("" +phone);
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("tel:" + phoneUri));
                    context.startActivity(intent);

                }else {

                    if (ContextCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.CALL_PHONE},
                                MY_PERMISSIONS_REQUEST_CALL_PHONE);
//                    Toast.makeText(context, "You need to type the number manually because the permission was denied", Toast.LENGTH_SHORT).show();

                        new AlertDialog.Builder(context)
                                .setTitle("Permission Needed")
                                .setMessage("Calling permission needed")
                                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        ActivityCompat.requestPermissions((Activity) context, new String[] {Manifest.permission.CALL_PHONE},MY_PERMISSIONS_REQUEST_CALL_PHONE);
                                        String phone = donator.getPhonenumber();
                                        Uri phoneUri = Uri.parse("" +phone);
                                        Intent intent = new Intent(Intent.ACTION_VIEW);
                                        intent.setData(Uri.parse("tel:" + phoneUri));
                                        context.startActivity(intent);
                                    }
                                })
                                .setNegativeButton("Deny", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                })
                                .create().show();

                    } else {
                        ContextCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE);

//                        String phone = donator.getPhonenumber();
//                        Uri phoneUri = Uri.parse("" +phone);
//                        Intent intent = new Intent(Intent.ACTION_VIEW);
//                        intent.setData(Uri.parse("tel:" + phoneUri));
//                        context.startActivity(intent);
                    }

                }

            }
        });
//        NAVIGATION TO CALL LOGS WITH PHONE NUMBER AND PERMISSION FINISH


//        GETTING KEY AND CHANGING STATUS ON BUTTON CLICK

        holder.btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String myKey = donator.getKey();
//                Log.i("key",""+thiskey);
                DatabaseReference dataRef = databaseRef.child("/"+myKey+"/status");
                dataRef.setValue("Assigned");
            }
        });
//        GETTING KEY AND CHANGING STATUS ON BUTTON CLICKFINISHED


//        CHANGING COLOR OF CARD ON STATUS CHANGED
        if (holder.Status.getText().equals("Assigned")){
            holder.crdDonationDetails.setBackgroundResource(R.color.light_grey);
//            holder.crdDonationDetails.setVisibility(View.GONE);
        }
        else if(holder.Status.getText().equals("Expired")){
            holder.crdDonationDetails.setBackgroundResource(R.color.exceeded_color);
//            holder.crdDonationDetails.setVisibility(View.VISIBLE);
        }else{
            holder.crdDonationDetails.setBackgroundResource(R.color.default_card);
//            holder.crdDonationDetails.setVisibility(View.GONE);
        }

//        CHANGING COLOR OF CARD ON STATUS CHANGED FINISHED

        }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class DonationViewHolder extends RecyclerView.ViewHolder{
        TextView Name,Phonenumber,Foodtype,Foodexpriy,Foodcount,Fromtime,Totime,Address,Status,myKey;
        ImageButton ibMap;
        Button btnConfirm;
        CardView crdDonationDetails;
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
            ibMap = itemView.findViewById(R.id.ibMap);
            btnConfirm = itemView.findViewById(R.id.btnConfirm);
            crdDonationDetails = itemView.findViewById(R.id.crdDonationDetails);
            Status = itemView.findViewById(R.id.txtDStatus);
            myKey = itemView.findViewById(R.id.txtKey);
        }
    }



}
