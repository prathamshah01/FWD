package com.example.fwd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
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

public class viewRequests extends AppCompatActivity {

    private ActivityViewRequestsBinding binding;

    private ImageButton imageButton;
    RecyclerView recyclerView;
    DatabaseReference database;
    DonationDataAdapter MyAdapter;
    ArrayList<Donator> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityViewRequestsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar();

        database = FirebaseDatabase.getInstance().getReference("Donator");

        recyclerView = binding.recyclerView;
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<>();
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

                    Donator donator = new Donator(name,phone,foodType,expiry,count,from,to,address);

                    list.add(donator);

                }
                MyAdapter.notifyDataSetChanged();
//                Log.i("name",""+list);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

}

//    LayoutInflater inflater = LayoutInflater.from(null);
//    View view = inflater.inflate(R.layout.view_request_layout, null);
//                    imageButton = findViewById(R.id.ibMap);
//                            imageButton.setOnClickListener(new View.OnClickListener() {
//@Override
//public void onClick(View v) {
//        Uri mapUri = Uri.parse("geo:0,0?q = " +Uri.encode(""+address));
//        Intent mapIntent = new Intent(Intent.ACTION_VIEW, mapUri);
//        mapIntent.setPackage("com.google.android.apps.maps");
//        startActivity(mapIntent);
//        }
//        });

