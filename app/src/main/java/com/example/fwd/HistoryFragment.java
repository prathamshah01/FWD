package com.example.fwd;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.fwd.databinding.ActivityViewRequestsBinding;
import com.example.fwd.databinding.FragmentHistoryBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;


public class HistoryFragment extends Fragment {

    private FragmentHistoryBinding binding;
    RecyclerView recyclerView1;
    DatabaseReference database;
    HistoryDataAdapter MyAdapter;
    ArrayList<Donator> list= new ArrayList<>();;
//    CheckInternet internet = new CheckInternet();


    public HistoryFragment() {
        // Required empty public constructor

    }



//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//
//    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        binding = FragmentHistoryBinding.inflate(inflater,container,false);
        return binding.getRoot();


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        binding = FragmentHistoryBinding.inflate(getLayoutInflater());

        database = FirebaseDatabase.getInstance().getReference("Donator");

        recyclerView1 = binding.recyclerView1;
        recyclerView1.setHasFixedSize(true);
        recyclerView1.setLayoutManager(new LinearLayoutManager(getContext()));

        MyAdapter = new HistoryDataAdapter(getContext(),list);
        recyclerView1.setAdapter(MyAdapter);


        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

//                list.clear();

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