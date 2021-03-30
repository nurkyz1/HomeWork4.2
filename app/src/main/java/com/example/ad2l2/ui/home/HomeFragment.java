package com.example.ad2l2.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;

import com.example.ad2l2.R;
import com.example.ad2l2.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment  implements  Listen{

    private HomeAdapter homeAdapter;
    private NavController navController;
    private FragmentHomeBinding binding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        homeAdapter = new HomeAdapter(this);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        navController = NavHostFragment.findNavController(this);
        binding = FragmentHomeBinding.inflate(inflater, container, false);

        binding.rv.setAdapter(homeAdapter);
        Click();
        getDataInform();
        return binding.getRoot();
    }

    private void getDataInform() {
        getParentFragmentManager().setFragmentResultListener("key",
                getViewLifecycleOwner(), new FragmentResultListener() {
                    @Override
                    public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                        String a = result.getString("name");
                        String b = result.getString("number");
                        int id = result.getInt("id");

                        HomeViewModel model = homeAdapter.getModelToId(id);
                        if (model!= null){
                            model.setTitle(a);
                            model.setDescription(b);
                            homeAdapter.notifyDataSetChanged();
                        }else {
                            homeAdapter.addList(new HomeViewModel(a,b));
                        }
                    }
                });

    }

    public void Click() {

        binding.fapAdd.setOnClickListener(v -> {
            navController.navigate(R.id.action_navigation_home_to_formFragment);
        });

    }

    @Override
    public void SetDataForForm(HomeViewModel homeViewModel, int position) {
        Bundle bundle = new Bundle();
        bundle.putString("name1",homeViewModel.getTitle());
        bundle.putString("number1",homeViewModel.getDescription());
        bundle.putInt("id",homeViewModel.getId());
        getParentFragmentManager().setFragmentResult("2",bundle);
        navController.navigate(R.id.action_navigation_home_to_formFragment);
    }
}
