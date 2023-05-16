package com.example.fwd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.bumptech.glide.load.model.Model;
import com.example.fwd.databinding.ActivityCommunityBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Community extends AppCompatActivity {
    private ActivityCommunityBinding binding;
    private RecyclerView recyclerView;
    private ArrayList<ImageUploadModel> list;
    private ImageRetriveAdapter adapter;
    private DatabaseReference imagedb = FirebaseDatabase.getInstance().getReference("Community");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCommunityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        recyclerView = binding.recyclerview;
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<>();
        adapter = new ImageRetriveAdapter(this, list);
        recyclerView.setAdapter(adapter);



        imagedb.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){

                    String imageUrl = dataSnapshot.child("imager").getValue().toString();
                    String descriptionUrl = dataSnapshot.child("description").getValue().toString();
                    ImageUploadModel imageUploadModel = new ImageUploadModel (imageUrl,descriptionUrl);
                    list.add(imageUploadModel);
                }
                adapter.notifyDataSetChanged();
//                Log.i ("name",""+list);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        


        binding.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent imgUpload = new Intent(Community.this,ImageUpload.class);
                startActivity(imgUpload);
                finish();
            }
        });

    }
}