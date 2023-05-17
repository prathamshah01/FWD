package com.example.fwd;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fwd.databinding.FragmentFeedbackBinding;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class FeedbackFragment extends Fragment {


    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("Feedback");
    private FragmentFeedbackBinding binding;
    String uiValue = "";
    String issueValue = "";
    String shareValue = "";
    String usageValue = "";
    String futureValue = "";


    public FeedbackFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentFeedbackBinding.inflate(getLayoutInflater());
        return binding.getRoot();


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            validation();
            }
        });


        binding.rgUi.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton selectedRadioButton = binding.getRoot().findViewById(checkedId);
                uiValue = selectedRadioButton.getText().toString();
            }
        });

        binding.rgIssue.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton selectedRadioButton = binding.getRoot().findViewById(checkedId);
                issueValue = selectedRadioButton.getText().toString();
            }
        });

        binding.rgShare.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton selectedRadioButton = binding.getRoot().findViewById(checkedId);
                shareValue = selectedRadioButton.getText().toString();
            }
        });

        binding.rgUsage.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton selectedRadioButton = binding.getRoot().findViewById(checkedId);
                usageValue = selectedRadioButton.getText().toString();
            }
        });

        binding.rgFuture.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton selectedRadioButton = binding.getRoot().findViewById(checkedId);
                futureValue = selectedRadioButton.getText().toString();
            }
        });



    }



    private void validation() {

        String name = binding.edUserName.getText().toString().trim();
        String number = binding.edUserPhone.getText().toString();
        String suggetstion = binding.edSuggestions.getText().toString().trim();


        if (binding.rgUi.getCheckedRadioButtonId() == -1 && binding.rgIssue.getCheckedRadioButtonId() == -1 && binding.rgShare.getCheckedRadioButtonId() == -1 && binding.rgUsage.getCheckedRadioButtonId() == -1 && binding.rgFuture.getCheckedRadioButtonId() == -1) {
            Toast.makeText(getContext(), "There could be a few unanswered questions", Toast.LENGTH_SHORT).show();
        } else if (name.equals("")) {
            binding.edUserName.setError("Please enter name");
        } else if (number.equals("")) {
            binding.edUserPhone.setError("Please enter number");
        } else if (number.length()<10) {
            binding.edUserPhone.setError("Please enter a valid number");
        } else if (suggetstion.equals("")) {
            binding.edSuggestions.setError("Please enter some Suggestions for usj");
        } else {
            String key = myRef.push().getKey();
            FeedbackModel feedbackModel = new FeedbackModel(name, number,uiValue,issueValue,shareValue,usageValue,futureValue,suggetstion);
            myRef.child(key).setValue(feedbackModel);

            Toast.makeText(getContext(), "Thank you for taking the time to provide your feedback. ", Toast.LENGTH_SHORT).show();



//            Fragment fragment = new HomeFragment();
//            FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
//            ft.replace(R.id.constraintMain, fragment).commit();

        }
    }


}

