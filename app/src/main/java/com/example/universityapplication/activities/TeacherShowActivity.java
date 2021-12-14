package com.example.universityapplication.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.universityapplication.R;
import com.example.universityapplication.databinding.ActivityTeacherShowBinding;
import com.example.universityapplication.models.Post;

import java.util.ArrayList;

public class TeacherShowActivity extends AppCompatActivity {
        private ActivityTeacherShowBinding binding ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTeacherShowBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


    }}