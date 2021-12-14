package com.example.universityapplication.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.universityapplication.R;
import com.example.universityapplication.adapters.TapAccessorAdapter;
import com.example.universityapplication.databinding.ActivityLoginBinding;
import com.google.android.material.tabs.TabLayout;

public class LoginActivity extends AppCompatActivity {

    private TabLayout loginTap;
    private TapAccessorAdapter tapAccessorAdapter;
    private ActivityLoginBinding binding;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        init();

        binding.viewPager.setAdapter(tapAccessorAdapter);
        loginTap.setupWithViewPager(viewPager);

    }

    private void init() {
        viewPager = findViewById(R.id.viewPager);
        loginTap = findViewById(R.id.loginTab);
        tapAccessorAdapter = new TapAccessorAdapter(getSupportFragmentManager());


    }
}