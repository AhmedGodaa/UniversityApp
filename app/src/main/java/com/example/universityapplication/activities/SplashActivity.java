package com.example.universityapplication.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.os.Bundle;


import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import com.example.universityapplication.R;
import com.example.universityapplication.databinding.ActivityWelcomeBinding;
import com.example.universityapplication.utilities.Constants;
import com.example.universityapplication.utilities.PreferenceManager;
import com.google.firebase.firestore.DocumentSnapshot;

import java.util.Timer;
import java.util.TimerTask;


public class SplashActivity extends AppCompatActivity {
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
        intentToMainActivity();


    }

    //    if user available send to timeline activity if not send to login activity
    private void intentToMainActivity() {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                finish();
                setListeners();
            }
        }, 4000);
    }


    private void setListeners() {

        if (preferenceManager.getBoolean(Constants.KEY_IS_SIGNED_IN)) {
            openActivity(getApplicationContext(), TimelineActivity.class);
        } else {
            openActivity(getApplicationContext(), LoginActivity.class);
        }

    }

    private void openActivity(Context context, Class activity) {
        Intent intent = new Intent(context, activity);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);


    }


}