package com.example.universityapplication.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.universityapplication.R;
import com.example.universityapplication.adapters.PostAdapter_RecyclerView;
import com.example.universityapplication.models.Post;

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
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_timeline, container, false);
        RecyclerView rv_timeline  = view.findViewById(R.id.Frmain_rv_timeline);
        ArrayList posts = new ArrayList();
        posts.add(new Post("hello every body and welcome", R.drawable.bill, R.drawable.microsoft));
        posts.add(new Post("hello every body and welcome", R.drawable.elon, R.drawable.tesla));
        posts.add(new Post("hello every body and welcome", R.drawable.jeff, R.drawable.facebook1));
        posts.add(new Post("hello every body and welcome", R.drawable.steve, R.drawable.apple));
        PostAdapter_RecyclerView adapter = new PostAdapter_RecyclerView(posts);
        rv_timeline.setAdapter(adapter);
        rv_timeline.setLayoutManager(new LinearLayoutManager(getContext()));


        return  view ;
    }
}