package com.example.ad2l2.ui.profile;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.ActivityResultRegistry;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.text.GetChars;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.ad2l2.App;
import com.example.ad2l2.R;
import com.example.ad2l2.databinding.FragmentProfileBinding;
import com.google.firebase.auth.FirebaseAuth;


public class ProfileFragment extends Fragment {
ImageView imageView;
private ActivityResultLauncher<String> mGetContent;
private FragmentProfileBinding binding;
private NavController navController;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

       View view= inflater.inflate(R.layout.fragment_profile, container, false);
       imageView = view.findViewById(R.id.iv);
       binding= FragmentProfileBinding.inflate(inflater,container,false);
       navController= NavHostFragment.findNavController(this);
       binding.editName.setText(App.prefsHelper.getForName());
       click();
       return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        imageView.setOnClickListener(v -> {
            openGallery();
        });
        mGetContent= registerForActivityResult(new ActivityResultContracts.GetContent()
                , new ActivityResultCallback<Uri>() {
                    @Override
                    public void onActivityResult(Uri uri) {
                        imageView.setImageURI(uri);
                    }
                });

    }
    private void openGallery() {
        mGetContent.launch("image/*");
    }

private  void  click() {
    binding.btn.setOnClickListener(v -> {

        App.prefsHelper.setForName(binding.editName.getText().toString().trim());

    });

}

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_signout,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==R.id.sign_out){
            item.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    navController.navigate(R.id.authFragment);
                    FirebaseAuth.getInstance().signOut();

                    return false;

                }
            });
         //   navController.navigate(R.id.authFragment);
        }
        return super.onOptionsItemSelected(item);
    }
}






