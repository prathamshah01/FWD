package com.example.fwd;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fwd.databinding.FragmentHomeBinding;
import com.example.fwd.databinding.FragmentShareBinding;


public class ShareFragment extends Fragment {

    private FragmentShareBinding binding;


    public ShareFragment() {
        // Required empty public constructor
    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        binding.getRoot()inflater.inflate(R.layout.fragment_share, container, false);

        binding = FragmentShareBinding.inflate(inflater,container,false);
        return binding.getRoot();

    }
}