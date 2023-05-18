package com.example.fwd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;

import com.example.fwd.databinding.ActivityViewRequestsBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;

public class viewRequests extends AppCompatActivity {

    private ActivityViewRequestsBinding binding;
    RecyclerView recyclerView;
    DatabaseReference database;
    DonationDataAdapter MyAdapter;
    ArrayList<Donator> list= new ArrayList<>();;
    CheckInternet internet = new CheckInternet();
    private Handler handler;
    private Runnable runnable;
    private static final int CHECK_INTERVAL = 3000; // 3 seconds



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityViewRequestsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar();

        internet.InternetConnectivityChecker(this);
        internet.start();


        database = FirebaseDatabase.getInstance().getReference("Donator");

        recyclerView = binding.recyclerView;
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        MyAdapter = new DonationDataAdapter(this,list);
        recyclerView.setAdapter(MyAdapter);


        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()){

                    String name = dataSnapshot.child("name").getValue().toString();
                    String phone = dataSnapshot.child("phonenumber").getValue().toString();
                    String foodType = dataSnapshot.child("foodtype").getValue().toString();
                    String expiry = dataSnapshot.child("foodexpriy").getValue().toString();
                    String count = dataSnapshot.child("foodcount").getValue().toString();
                    String from = dataSnapshot.child("fromtime").getValue().toString();
                    String to = dataSnapshot.child("totime").getValue().toString();
                    String address = dataSnapshot.child("address").getValue().toString();
                    String status = dataSnapshot.child("status").getValue().toString();
                    String key = dataSnapshot.child("key").getValue().toString();

                    Donator donator = new Donator(name,phone,foodType,expiry,count,from,to,address,status,key);

                    list.add(donator);


                }
                Collections.reverse(list);
                MyAdapter.notifyDataSetChanged();

//                Log.i("name",""+list);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

}