package com.example.fwd;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fwd.databinding.FragmentFeedbackBinding;


public class FeedbackFragment extends Fragment {



    private FragmentFeedbackBinding binding;


    public FeedbackFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        binding.rgUi.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup radioGroup, int i) {
//                if(i == R.id.rbYes){
//                    Toast.makeText(getContext(), "Yes Selected", Toast.LENGTH_SHORT).show();
//                } else if (i == R.id.rbSomewhat) {
//                    Toast.makeText(getContext(), "Somewhat", Toast.LENGTH_SHORT).show();
//                } else if (i == R.id.rbNotVery){
//                    Toast.makeText(getContext(), "Female Selected", Toast.LENGTH_SHORT).show();
//                }
//                else {
//                    Toast.makeText(getContext(), "NotExplored", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });



    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentFeedbackBinding.inflate(getLayoutInflater());
        return binding.getRoot();





    }
}