package com.example.universityapplication.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.universityapplication.databinding.ActivityTimelineBinding;
import com.example.universityapplication.fragments.TimelineFragment;
import com.example.universityapplication.fragments.SubjectsFragment;
import com.example.universityapplication.fragments.TeachersFragment;
import com.example.universityapplication.R;
import com.example.universityapplication.utilities.Constants;
import com.example.universityapplication.utilities.PreferenceManager;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Objects;


public class TimelineActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    private ActivityTimelineBinding binding;
    private PreferenceManager preferenceManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTimelineBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        preferenceManager = new PreferenceManager(getApplicationContext());


        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNav);
        bottomNavigationView.setBackground(null);
        bottomNavigationView.getMenu().getItem(3).setEnabled(false);
        bottomNavigationView.setOnNavigationItemSelectedListener(this::onNavigationItemSelected);


        setSupportActionBar(binding.toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,
                binding.drawerLayout,
                binding.toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        binding.drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        binding.navigationView.setNavigationItemSelectedListener(this);
        binding.floatingActionButton.setOnClickListener(v -> sendUserToMainActivity());


    }


    private void sendUserToMainActivity() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }


    @Override
    public void onBackPressed() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//        <   --------------------------  Bottom Navigation View ---------------------------   >
        if (item.getItemId() == R.id.menu_home) {
            TimelineFragment mainFragment = new TimelineFragment();
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.fragmentContainerTimeLine, mainFragment);
            ft.commit();
            binding.drawerLayout.closeDrawer(GravityCompat.START);
        }


        if (item.getItemId() == R.id.menu_teachers) {
            TeachersFragment teachersFragment = new TeachersFragment();
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.fragmentContainerTimeLine, teachersFragment);
            ft.commit();
            binding.drawerLayout.closeDrawer(GravityCompat.START);
        }

        if (item.getItemId() == R.id.menu_subjects) {

            SubjectsFragment subjectsFragment = new SubjectsFragment();
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.fragmentContainerTimeLine, subjectsFragment);
            ft.commit();
            binding.drawerLayout.closeDrawer(GravityCompat.START);
        }

//        <    ------------------------------  Navigation View  -------------------------------    >
        if (item.getItemId() == R.id.menu_chat) {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            binding.drawerLayout.closeDrawer(GravityCompat.START);
        }

        if (item.getItemId() == R.id.menu_logout) {
            signOut();
            binding.drawerLayout.closeDrawer(GravityCompat.START);


        }

        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);
        return super.onCreateOptionsMenu(menu);
    }

    private void showToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();

    }

    private void signOut() {
        showToast("Signing out...");
        FirebaseFirestore database = FirebaseFirestore.getInstance();
        DocumentReference documentReference = database.collection(Constants.KEY_COLLECTION_USERS).document(
                preferenceManager.getString(Constants.KEY_USER_ID)
        );
        HashMap<String, Object> updates = new HashMap<>();
        updates.put(Constants.KEY_FCM_TOKEN, FieldValue.delete());
        documentReference.update(updates)
                .addOnSuccessListener(unused -> {
                    preferenceManager.clear();
                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                    finish();
                })
                .addOnFailureListener(e -> showToast("Unable to sign out"));

    }


}

