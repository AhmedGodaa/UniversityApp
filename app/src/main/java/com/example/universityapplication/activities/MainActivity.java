package com.example.universityapplication.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.universityapplication.R;
import com.example.universityapplication.databinding.ActivityMainBinding;
import com.example.universityapplication.fragments.GroupsFragment;
import com.example.universityapplication.fragments.MainFragment;
import com.example.universityapplication.fragments.UsersFragment;
import com.example.universityapplication.utilities.Constants;
import com.example.universityapplication.utilities.PreferenceManager;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.HashMap;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private ActivityMainBinding binding;
    private PreferenceManager preferenceManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        preferenceManager = new PreferenceManager(getApplicationContext());
        setContentView(binding.getRoot());
        setListener();
        loadUserDetails();
        getToken();

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNav);
        bottomNavigationView.setBackground(null);
        bottomNavigationView.getMenu().getItem(3).setEnabled(false);
        bottomNavigationView.setOnNavigationItemSelectedListener(this::onNavigationItemSelected);


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,
                binding.drawerLayout, binding.toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);

        binding.drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        binding.navigationView.setNavigationItemSelectedListener(this);


    }

    private void setListener() {
        binding.tap1.setOnClickListener(v -> {
            MainFragment mainFragment = new MainFragment();
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.fragmentContainer, mainFragment);
            showToast("MainActivity Clicked");
            ft.commit();
        });

        binding.tap2.setOnClickListener(v -> {
            UsersFragment usersFragment = new UsersFragment();
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.fragmentContainer, usersFragment);
            showToast("Tap2 Clicked");
            ft.commit();
        });
        binding.tap3.setOnClickListener(v -> {
            GroupsFragment groupsFragment = new GroupsFragment();
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.fragmentContainer, groupsFragment);
            showToast("Tap2 Clicked");
            ft.commit();
        });

        binding.floatingActionButton.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), TimelineActivity.class);
            startActivity(intent);

        });
    }

    private void loadUserDetails() {
        // <  ----------------------- user image --------------------------  >

        byte[] bytes = Base64.decode(preferenceManager.getString(Constants.KEY_IMAGE), Base64.DEFAULT);
        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        binding.imageProfile.setImageBitmap(bitmap);
    }

    private void showToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();

    }

    private void getToken() {
        FirebaseMessaging.getInstance().getToken().addOnSuccessListener(this::updateToken);

    }

    private void updateToken(String token) {
        FirebaseFirestore database = FirebaseFirestore.getInstance();
        // we will send token to database first Key  And Value
        // (collection name  == KEY ) will be (user) as in firebase
        // (document text  Value ) will be (token ) as in
        DocumentReference documentReference =
                database.collection(Constants.KEY_COLLECTION_USERS).document(
                        preferenceManager.getString(Constants.KEY_USER_ID)
                );
        documentReference.update(Constants.KEY_FCM_TOKEN, token)
                .addOnFailureListener(e -> showToast("Unable to update token"));
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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_chats) {
            MainFragment mainFragment = new MainFragment();
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.fragmentContainer, mainFragment);
            ft.commit();
            binding.drawerLayout.closeDrawer(GravityCompat.START);
        }
        if (item.getItemId() == R.id.menu_users) {
            UsersFragment usersFragment = new UsersFragment();
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.fragmentContainer, usersFragment);
            ft.commit();
            binding.drawerLayout.closeDrawer(GravityCompat.START);


        }
        if (item.getItemId() == R.id.menu_groups) {
            GroupsFragment groupsFragment = new GroupsFragment();
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.fragmentContainer, groupsFragment);
            ft.commit();
            binding.drawerLayout.closeDrawer(GravityCompat.START);
        }


        if (item.getItemId() == R.id.menu_home) {
            Intent intent = new Intent(getApplicationContext(), TimelineActivity.class);
            startActivity(intent);
            binding.drawerLayout.closeDrawer(GravityCompat.START);

        }
        if (item.getItemId() == R.id.menu_logout) {
            binding.drawerLayout.closeDrawer(GravityCompat.START);
            signOut();

        }

        return true;
    }


}