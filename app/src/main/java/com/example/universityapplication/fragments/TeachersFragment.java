package com.example.universityapplication.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.universityapplication.R;
import com.example.universityapplication.adapters.RecyclerViewOnClickListener;
import com.example.universityapplication.activities.TeacherShowActivity;
import com.example.universityapplication.adapters.TeacherAdapter;
import com.example.universityapplication.adapters.UserAdapter;
import com.example.universityapplication.databinding.FragmentTeachersBinding;
import com.example.universityapplication.listeners.UserListener;
import com.example.universityapplication.models.Teacher;
import com.example.universityapplication.models.User;
import com.example.universityapplication.utilities.Constants;
import com.example.universityapplication.utilities.PreferenceManager;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TeachersFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TeachersFragment extends Fragment implements UserListener {
    ArrayList<Teacher> teachers;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private FragmentTeachersBinding binding;
    private PreferenceManager preferenceManager;

    public TeachersFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TeachersFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TeachersFragment newInstance(String param1, String param2) {
        TeachersFragment fragment = new TeachersFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentTeachersBinding.inflate(getLayoutInflater());
        preferenceManager = new PreferenceManager(getContext());
        getUsers();


        return binding.getRoot();
    }

    private void getUsers() {
        loading(true);
        FirebaseFirestore database = FirebaseFirestore.getInstance();
        database.collection(Constants.KEY_COLLECTION_USERS)
                .get()
                .addOnCompleteListener(task -> {
                    loading(false);
                    String currentUserId = preferenceManager.getString(Constants.KEY_USER_ID);
                    if (task.isSuccessful() && task.getResult() != null) {
                        List<User> users = new ArrayList<>();
                        for (QueryDocumentSnapshot queryDocumentSnapshot : task.getResult()) {

                            if (currentUserId.equals(queryDocumentSnapshot.getId())) {
                                continue;
                            }
                            User user = new User();
                            user.name = queryDocumentSnapshot.getString(Constants.KEY_NAME);
                            user.email = queryDocumentSnapshot.getString(Constants.KEY_EMAIL);
                            user.image = queryDocumentSnapshot.getString(Constants.KEY_IMAGE);
                            user.token = queryDocumentSnapshot.getString(Constants.KEY_FCM_TOKEN);
                            user.id = queryDocumentSnapshot.getId();
                            users.add(user);

                        }

                        if (users.size() > 0) {
                            TeacherAdapter teacherAdapter = new TeacherAdapter(users, this);
                            binding.userRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
                            binding.userRecyclerView.setAdapter(teacherAdapter);

                        } else {
                            showErrorMessage();
                        }
                    } else {
                        showErrorMessage();
                    }
                });

    }

    private void showErrorMessage() {
        Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onUserClicked(User user) {
        Toast.makeText(getContext(), "User Clicked", Toast.LENGTH_SHORT).show();
    }

    private void loading(Boolean isLoading) {
        if (isLoading) {
            binding.fireLoading.setVisibility(View.VISIBLE);
        } else {
            binding.fireLoading.setVisibility(View.GONE);
        }
    }
}
