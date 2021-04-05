package com.example.ad2l2.ui.onBoard;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ad2l2.databinding.FragmentOnBoardBinding;

public class OnBoardFragment extends Fragment{
    private FragmentOnBoardBinding binding ;
    NavController navController;
    private  PagerAdapter pagerAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pagerAdapter = new PagerAdapter();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        navController= NavHostFragment.findNavController(this);

        binding = FragmentOnBoardBinding.inflate(inflater,container,false);

        binding.viewPager.setAdapter(new PagerAdapter());
        binding.indicator.setViewPager(binding.viewPager);
        pagerAdapter.registerAdapterDataObserver(binding.indicator.getAdapterDataObserver());
        binding.viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                if (position==2){
                    binding.button.setText("Finish");
                    binding.button.setOnClickListener(v -> {
                        navController.navigateUp();
                    });
                }else {
                    binding.button.setOnClickListener(v -> {
                        binding.viewPager.setCurrentItem(binding.viewPager.getCurrentItem()+1);
                    });
                }
                if (position==0){
                    binding.button1.setOnClickListener(v -> {
                        navController.navigateUp();
                    });
                }
                if(position==1){
                    binding.button1.setVisibility(View.GONE);
                }
            }
        });

        return binding.getRoot();
  }}
