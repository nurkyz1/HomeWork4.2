package com.example.ad2l2.ui.form;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ad2l2.R;
import com.example.ad2l2.databinding.FragmentFormBinding;

public class FormFragment extends Fragment {
private FragmentFormBinding binding;
private NavController navController;
private  int id;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        navController = NavHostFragment.findNavController(this);
        binding = FragmentFormBinding.inflate(inflater, container, false);
        getData();
        initListeners();

        return binding.getRoot();
    }
    public  void  initListeners(){
        binding.buttonSave.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putString("name", binding.editTitle.getText().toString());
            bundle.putString("number",binding.editDescription.getText().toString());
            bundle.putInt("id",id);
            getParentFragmentManager().setFragmentResult("key",bundle);
            close();
        });
    }
    public void  getData(){
        getParentFragmentManager().setFragmentResultListener("2", getViewLifecycleOwner(), new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                binding.editTitle.setText(result.getString("name1"));
                binding.editDescription.setText(result.getString("number1"));
                id = result.getInt("id");


            }
        });
    }

    private  void  close(){
        navController.navigateUp();
    }
}
