package com.example.ad2l2.ui.auth;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ad2l2.R;
import com.example.ad2l2.databinding.FragmentAuthBinding;


public class AuthFragment extends Fragment {
private FragmentAuthBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding= FragmentAuthBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }


}