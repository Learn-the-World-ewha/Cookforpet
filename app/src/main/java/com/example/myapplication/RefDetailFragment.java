package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class RefDetailFragment extends Fragment {
    TextView txt_title, txt_tip, txt_type, txt_date;
    ImageView img_main;
    Button btn_usedall;
    RecyclerView recycler_rcp;
    RefrigItemAdapter adapter;
    ArrayList<ArrayList<String>> code_date;
    ViewGroup rootView;
    UserActivity activity;
    Context context;
    Integer count;
    String id,img_url, rcp_code, cook_date;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = (ViewGroup) inflater.inflate(R.layout.fragment_ref_detail, container, false);
        context = container.getContext();

        activity = (UserActivity) getActivity();

        img_main = rootView.findViewById(R.id.img_main);
        txt_title = rootView.findViewById(R.id.txt_title);
        txt_tip = rootView.findViewById(R.id.txt_tip);
        txt_type = rootView.findViewById(R.id.txt_type);
        txt_date = rootView.findViewById(R.id.txt_date);

        Bundle bundle = getArguments();
        if (bundle != null){
            img_url = bundle.getString("img_url");
            txt_title.setText(bundle.getString("rcp_txt"));
            txt_tip.setText(bundle.getString("tip_txt"));
            txt_type.setText(bundle.getString("type_txt"));
            cook_date = bundle.getString("date_txt");
            rcp_code = bundle.getString("rcp_code");
            id = bundle.getString("user_code");
        }

        txt_date.setText(cook_date);
        Glide.with(this)                 //img_main 출력
                .load(img_url)
                .into(img_main);



        btn_usedall = rootView.findViewById(R.id.btn_usedall);
        btn_usedall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.dbAc.deleteCook(id, rcp_code, cook_date);
                Toast.makeText(activity, "My refrigerator에서 삭제되었습니다.", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(activity, UserActivity.class);
                startActivity(intent);
            }
        });

        return rootView;

    }
}