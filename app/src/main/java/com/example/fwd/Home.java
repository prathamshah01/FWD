package com.example.fwd;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fwd.databinding.ActivityHomeBinding;

public class Home extends AppCompatActivity {
    private ActivityHomeBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

       binding.BtnRegister.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

               openNewActivity();
           }
       });

       binding.txtLogin.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
               startActivity(intent);

           }
       });
    }

    public void openNewActivity(){
        Intent intent = new Intent(this,RegistrationLayout.class);
        startActivity(intent);
        
    }
}