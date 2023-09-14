package com.example.revlisapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.example.revlisapp.Fragment.Record;
import com.example.revlisapp.Fragment.HomePage;
import com.example.revlisapp.Fragment.Profile;
import com.example.revlisapp.Fragment.Category;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    private FrameLayout fragmentContainer;
    private BottomAppBar bar;
    private BottomNavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initPermission();
        fragmentContainer = findViewById(R.id.container_all);
        bar = findViewById(R.id.bar);
        setMain();
        navigationView = findViewById(R.id.navigationView);
        navigationView.setOnItemSelectedListener(NaviSelectedListener);
    }

    public void initPermission(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                && checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1000);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                && checkSelfPermission(android.Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
        }
        if (Build.VERSION.SDK_INT >= 33
                && checkSelfPermission(Manifest.permission.READ_MEDIA_IMAGES) != PackageManager.PERMISSION_GRANTED
                && checkSelfPermission(Manifest.permission.READ_MEDIA_VIDEO) != PackageManager.PERMISSION_GRANTED
                && checkSelfPermission(Manifest.permission.READ_MEDIA_AUDIO) != PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[]{Manifest.permission.READ_MEDIA_IMAGES},1);
            requestPermissions(new String[]{Manifest.permission.READ_MEDIA_VIDEO},1);
            requestPermissions(new String[]{Manifest.permission.READ_MEDIA_AUDIO},1);
        }
    }

    private NavigationBarView.OnItemSelectedListener NaviSelectedListener = new NavigationBarView.OnItemSelectedListener(){
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()){
                case R.id.homepage:
                    getSupportFragmentManager().beginTransaction().replace(R.id.container_all,new HomePage()).commit();
                    return true;
                case R.id.record:
                    getSupportFragmentManager().beginTransaction().replace(R.id.container_all,new Record()).commit();
                    return true;
                case R.id.category:
                    try{
                        getSupportFragmentManager().beginTransaction().replace(R.id.container_all,new Category()).commit();
                    }catch (Exception e){
                        Log.e("runFragment",e.toString());
                    }
                    return true;
                case R.id.profile:
                    getSupportFragmentManager().beginTransaction().replace(R.id.container_all,new Profile()).commit();
                    return true;
            }
            return false;
        }
    };

    private void setMain(){
        this.getSupportFragmentManager().beginTransaction().add(R.id.container_all, new HomePage()).commit();
    }
}