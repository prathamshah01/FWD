package com.example.fwd;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fwd.databinding.FragmentHomeBinding;


public class HomeFragment extends Fragment {


    private FragmentHomeBinding binding;
    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       binding = FragmentHomeBinding.inflate(inflater,container,false);
       return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.crdDonation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Donation = new Intent(getContext(),DonationForm.class);
                startActivity(Donation);
                 
            }
        });

        binding.crdVolunteer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Volunteer = new Intent(getContext(),VolunteerForm.class);
                startActivity(Volunteer);
                 
            }
        });

        binding.crdViewRequests.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Requests = new Intent(getContext(), viewRequests.class);
                startActivity(Requests);
                 
            }
        }));

        binding.crdGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Gallery = new Intent(getContext(),Community.class);
                startActivity(Gallery);
                 
            }
        });

    }
}

