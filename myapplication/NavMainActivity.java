package com.example.myapplication;

import android.os.Bundle;
import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

public class NavMainActivity extends AppCompatActivity {
    AppBarConfiguration appBarConfiguration;
    DrawerLayout drawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_main);
//        Toolbar toolbar=findViewById(R.id.toolbar);
          NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
            NavigationView navView = findViewById(R.id.nav_view);
            NavigationUI.setupWithNavController(navView, navController);
            drawerLayout=findViewById(R.id.drawer_layout);
            AppBarConfiguration appBarConfiguration =
                    new AppBarConfiguration.Builder(navController.getGraph())
                            .setDrawerLayout(drawerLayout)
                            .build();

        }

//        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
//        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);




//            
        }


