package com.example.fwd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.fwd.databinding.ActivityVolunteerFormBinding;

public class VolunteerForm extends AppCompatActivity {

    private ActivityVolunteerFormBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding =   ActivityVolunteerFormBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


//        binding.btnVolunteerSubmit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(this,)
//            }
//        });
    }
}