package com.example.ad2l2;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.Menu;
import android.view.View;

import com.example.ad2l2.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.util.ArrayList;

import static com.example.ad2l2.R.menu.bottom_nav_menu;

public class MainActivity extends AppCompatActivity {
    private NavController navController;
   private AppBarConfiguration appBarConfiguration;
   private ArrayList<Integer> bottomList= new ArrayList<>();
   private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        BottomNavigationView navView = findViewById(R.id.nav_view);
    appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications,R.id.navigation_profile)
                .build();
        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);


        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments) {
                bottomList.add(R.id.navigation_home);
                bottomList.add(R.id.navigation_dashboard);
                bottomList.add(R.id.navigation_notifications);

              if (bottomList.contains(destination.getId())){
                  binding.navView.setVisibility(View.VISIBLE);
              }else{
                  binding.navView.setVisibility(View.GONE);
              }
            if (destination.getId()==R.id.onBoardFragment){
               getSupportActionBar().hide();
             }else{
                getSupportActionBar().show();
              }
            }
        });
        if (!App.prefsHelper.isBoardShown()){
            navController.navigate(R.id.onBoardFragment);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {

        return NavigationUI.navigateUp(navController,appBarConfiguration )|| super.onSupportNavigateUp();
    }
   @Override
    public boolean onCreateOptionsMenu(Menu menu) {
       getMenuInflater().inflate(bottom_nav_menu, menu);
        return true;
    }





}