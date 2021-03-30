package com.example.ad2l2.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;

import com.example.ad2l2.R;
import com.example.ad2l2.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {


    private NavController navController;
    private FragmentHomeBinding binding;
    public View onCreateView(@NonNull LayoutInflater inflater,
                                ViewGroup container, Bundle savedInstanceState) {
        navController = NavHostFragment.findNavController(this);
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        Click();
        return binding.getRoot();

}
public void Click(){

  binding.fapAdd.setOnClickListener(v -> {
      navController.navigate(R.id.action_navigation_home_to_formFragment);
  });

}
}
