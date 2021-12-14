package com.example.universityapplication.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.universityapplication.R;
import com.example.universityapplication.databinding.ItemContainerUserAccountBinding;
import com.example.universityapplication.databinding.ItemContainerUserBinding;
import com.example.universityapplication.listeners.UserListener;
import com.example.universityapplication.models.Teacher;
import com.example.universityapplication.models.User;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;
import java.util.List;

public class TeacherAdapter  extends RecyclerView.Adapter<TeacherAdapter.TeacherViewHolder>{

    private final List<User> users;
    private final UserListener userListener ;

    public TeacherAdapter(List<User> users, UserListener userListener) {
        this.users = users;
        this.userListener = userListener;
    }

    @NonNull
    @Override
    public TeacherAdapter.TeacherViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemContainerUserAccountBinding itemContainerUserAccountBinding = ItemContainerUserAccountBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent,
                false
        );
        return new TeacherAdapter.TeacherViewHolder(itemContainerUserAccountBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull TeacherViewHolder holder, int position) {
        holder.setUserData(users.get(position));
    }

    @Override
    public int getItemCount() {
        return users.size();
    }


    class TeacherViewHolder extends RecyclerView.ViewHolder {
        ItemContainerUserAccountBinding binding;

        TeacherViewHolder(ItemContainerUserAccountBinding itemContainerUserAccountBinding) {
            super(itemContainerUserAccountBinding.getRoot());
            binding = itemContainerUserAccountBinding;
        }

        void setUserData(User user) {
            binding.rvTeacherName.setText(user.name);
            binding.rvTeacherPhoto.setImageBitmap(getUserImage(user.image));
            binding.getRoot().setOnClickListener(v -> userListener.onUserClicked(user));

        }
    }


    private Bitmap getUserImage(String encodedImage) {
        byte[] bytes = Base64.decode(encodedImage, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);

    }

   }
