package com.example.fwd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.fwd.databinding.ActivityLoginBinding;
import com.example.fwd.databinding.ActivityRegistrationLayoutBinding;

public class RegistrationLayout extends AppCompatActivity {

    private ActivityRegistrationLayoutBinding binding;

    //private RegistrationLayout
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegistrationLayoutBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent homeScreen = new Intent(RegistrationLayout.this,NavDrawerLayout.class);
                startActivity(homeScreen);
//                finish();
            }
        });
        binding.txtRegLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent login = new Intent(RegistrationLayout.this,LoginActivity.class);
                startActivity(login);

            }
        });
    }
}