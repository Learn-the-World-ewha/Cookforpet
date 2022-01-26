package com.example.myapplication;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class LikesFragment extends Fragment {
    TextView username_txt, cook_sum;
    RecyclerView recycler_rcp;
    RecipeItemAdapter adapter;
    ArrayList<ArrayList<String>> code_date;
    ViewGroup rootView;
    UserActivity activity;
    Context context;
    Integer count;
    String id;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = (ViewGroup) inflater.inflate(R.layout.fragment_likes, container, false);
        context = container.getContext();

        activity = (UserActivity) getActivity();


        return rootView;
    }
}