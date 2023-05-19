package com.example.fwd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import com.example.fwd.databinding.ActivityMyAccountBinding;

public class MyAccount extends AppCompatActivity {

    private ActivityMyAccountBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMyAccountBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SharedPreferences sharedPreferences = getSharedPreferences("Leftovers", Context.MODE_PRIVATE);
        String value = sharedPreferences.getString("name", "Name");
        int count = sharedPreferences.getInt("total",0);

        TextView name = binding.txtAName;
        name.setText(value);

        binding.txtCount.setText(""+count);

    }


}