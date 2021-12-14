package com.example.universityapplication.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.os.Bundle;


import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.example.universityapplication.R;
import com.example.universityapplication.databinding.ActivityWelcomeBinding;
import com.example.universityapplication.utilities.Constants;
import com.example.universityapplication.utilities.PreferenceManager;
import com.google.firebase.firestore.DocumentSnapshot;


public class WelcomeActivity extends AppCompatActivity {
    private ActivityWelcomeBinding binding;
    private PreferenceManager preferenceManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityWelcomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        preferenceManager = new PreferenceManager(getApplicationContext());
        Animation logoAnimation = AnimationUtils.loadAnimation(this, R.anim.anim_logo);
        binding.logo.setAnimation(logoAnimation);
        setListeners();


    }

    private void openActivity(Context context, Class activity) {
        Intent intent = new Intent(context, activity);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);


    }

    private void setListeners() {
        binding.logo.setOnClickListener(v -> {
            if (preferenceManager.getBoolean(Constants.KEY_IS_SIGNED_IN)) {
                openActivity(getApplicationContext(), TimelineActivity.class);
            } else {
                openActivity(getApplicationContext(), LoginActivity.class);
            }
        });
    }


}