package com.example.myapplication;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class RefDetailFragment extends Fragment {
    TextView img_main, txt_title, txt_tip, txt_type, txt_date;
    Button btn_usedall;
    RecyclerView recycler_rcp;
    RefrigItemAdapter adapter;
    ArrayList<ArrayList<String>> code_date;
    ViewGroup rootView;
    UserActivity activity;
    Context context;
    Integer count;
    String id;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = (ViewGroup) inflater.inflate(R.layout.fragment_refrig, container, false);
        context = container.getContext();

        activity = (UserActivity) getActivity();

        img_main = rootView.findViewById(R.id.img_main);
        txt_title = rootView.findViewById(R.id.txt_title);
        txt_tip = rootView.findViewById(R.id.txt_tip);
        txt_type = rootView.findViewById(R.id.txt_type);
        txt_date = rootView.findViewById(R.id.txt_date);

        if (getArguments() != null){
            img_main.setText(getArguments().getString("img_url"));
            txt_title.setText(getArguments().getString("rcp_txt"));
            txt_tip.setText(getArguments().getString("tip_txt"));
            txt_type.setText(getArguments().getString("type_txt"));
            txt_date.setText(getArguments().getString("date_txt"));
        }


        btn_usedall = rootView.findViewById(R.id.btn_usedall);

        return rootView;

    }
}