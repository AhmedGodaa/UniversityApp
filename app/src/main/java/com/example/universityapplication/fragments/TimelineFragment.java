package com.example.universityapplication.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.universityapplication.R;
import com.example.universityapplication.adapters.PostAdapter_RecyclerView;
import com.example.universityapplication.databinding.FragmentTimelineBinding;
import com.example.universityapplication.models.Post;
import com.example.universityapplication.utilities.PreferenceManager;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TimelineFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TimelineFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private PreferenceManager preferenceManager ;
    private FragmentTimelineBinding binding ;

    public TimelineFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MainFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TimelineFragment newInstance(String param1, String param2) {
        TimelineFragment fragment = new TimelineFragment();
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
        binding = FragmentTimelineBinding.inflate(getLayoutInflater());
        preferenceManager = new PreferenceManager(getContext());
        oldRecyclerView();



        return  binding.getRoot() ;
    }

    private void oldRecyclerView() {
        ArrayList posts = new ArrayList();
        posts.add(new Post("hello every body and welcome", R.drawable.im_bill, R.drawable.im_microsoft));
        posts.add(new Post("hello every body and welcome", R.drawable.im_elon, R.drawable.im_tesla));
        posts.add(new Post("hello every body and welcome", R.drawable.im_jeff, R.drawable.im_facebook));
        posts.add(new Post("hello every body and welcome", R.drawable.im_steve, R.drawable.im_apple));
        PostAdapter_RecyclerView adapter = new PostAdapter_RecyclerView(posts);
        binding.recyclerView.setAdapter(adapter);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }




}