package com.example.ad2l2.ui.home;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;

import com.example.ad2l2.App;
import com.example.ad2l2.MainActivity;
import com.example.ad2l2.R;
import com.example.ad2l2.databinding.FragmentHomeBinding;
import com.example.ad2l2.ui.room.NoteDao;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment  implements  Listen{

    private HomeAdapter homeAdapter;
    private NavController navController;
    private FragmentHomeBinding binding;
    private List<HomeViewModel> list= new ArrayList<>();
    private MainActivity mainActivity;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        homeAdapter = new HomeAdapter(this);

    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        navController = NavHostFragment.findNavController(this);
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        App.database.noteDao().getNotes().observe(requireActivity(), new Observer<List<HomeViewModel>>() {
            @Override
            public void onChanged(List<HomeViewModel> models) {
                homeAdapter.addList(models);
            }
        });

        binding.rv.setAdapter(homeAdapter);
        Click();
        getDataInform();

        requireActivity().getOnBackPressedDispatcher().
                addCallback(
                        getViewLifecycleOwner(),
                        new OnBackPressedCallback(true) {
                            @Override
                            public void handleOnBackPressed() {
                                alert();
                            }
                        });
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

//                        HomeViewModel viewModel=new HomeViewModel(a,b);
                        // homeAdapter.addItem(viewModel);

                        HomeViewModel model = homeAdapter.getModelToId(id);
                        if (model!= null){
                            model.setTitle(a);
                            model.setDescription(b);
                            App.database.noteDao().update(model);
                        }else {
                            App.database.noteDao().insert(new HomeViewModel(a,b));
                            FirebaseFirestore.getInstance().collection("notes")
                                    .add(model).addOnCompleteListener(task -> {
                                if (task.isSuccessful()){
                                    Toast.makeText(requireContext(), "Success", Toast.LENGTH_SHORT).show();
                                }else {
                                    Toast.makeText(requireContext(), "Failure", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }
                });

    }

    public void Click() {

        binding.fapAdd.setOnClickListener(v -> {
            navController.navigate(R.id.action_navigation_home_to_formFragment);
        });
       // binding.btn1.setOnClickListener(v -> {
         //   homeAdapter.addList(App.database.noteDao().getAllBySort());
           // binding.rv.setAdapter(homeAdapter);
       // });

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
    public void alert(){
        AlertDialog.Builder adg = new AlertDialog.Builder(binding.getRoot().getContext());
        String positive = "????";
        String negative = "??????";
        adg.setMessage("???? ???????????? ?????????? ?");
        adg.setPositiveButton(positive, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                requireActivity().finish();
            }
        });
        adg.setNegativeButton(negative, null);
        adg.show();
    }
    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_item, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.sortAZ) {
            homeAdapter.addList(App.database.noteDao().getAllBySort());
            binding.rv.setAdapter(homeAdapter);
            Snackbar.make(requireView(), "???????????????????????? A-??", Snackbar.LENGTH_SHORT).show();

        } else if (item.getItemId() == R.id.sortZA) {
            homeAdapter.addList(App.database.noteDao().getAllBySortRes());
            binding.rv.setAdapter(homeAdapter);
            Snackbar.make(requireView(), "???????????????????????? ??-??", Snackbar.LENGTH_SHORT).show();

        }
        else if (item.getItemId() == R.id.deleteAll){
            App.database.noteDao().deleteAll();
        }
        return super.onOptionsItemSelected(item);
    }
}
