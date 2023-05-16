package com.example.fwd;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.LayoutTransition;
import android.os.Bundle;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.fwd.databinding.ActivityViewRequestsBinding;

public class ViewRequests extends AppCompatActivity {

    private ActivityViewRequestsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityViewRequestsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar();

    }

    public void expand(View view) {



    }
}