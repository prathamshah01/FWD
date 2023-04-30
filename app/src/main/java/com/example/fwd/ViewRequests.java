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
//    TextView detailsText;
//    LinearLayout layout;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        binding = ActivityViewRequestsBinding.inflate(getLayoutInflater());
//        setContentView(binding.getRoot());
//        getActionBar();
//
//        layout = binding.layout;
//        layout.getLayoutTransition().enableTransitionType(LayoutTransition.CHANGING);
//    }
//
//    public void expand(View view) {
//
//        int v = (detailsText.getVisibility() == View.GONE)? View.VISIBLE: View.GONE;
//
//        TransitionManager.beginDelayedTransition(layout, new AutoTransition());
//        detailsText.setVisibility(v);
//
//    }
}