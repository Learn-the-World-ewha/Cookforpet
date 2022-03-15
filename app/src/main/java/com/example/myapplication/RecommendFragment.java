package com.example.myapplication;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class RecommendFragment extends Fragment {
    SubMainActivity activity;
    //Context context;
    ViewGroup rootView;

    RecyclerView recycler_rcp;
    RecipeItemAdapter adapter;
    ArrayList<String> recipe_code;

    String effect;
    int petType;


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof Activity)
            activity = (SubMainActivity) context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = (ViewGroup) inflater.inflate(R.layout.fragment_recommend, container, false);
        //context = container.getContext();
        //activity = (SubMainActivity) getActivity();

        setRecyclerView();
//        //레시피 목록 출력
//        recycler_rcp = rootView.findViewById(R.id.recycler_rcp);
//        LinearLayoutManager layoutManager =
//                new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false);
//        recycler_rcp.setLayoutManager(layoutManager);
//        adapter = new RecipeItemAdapter();
//
//        petType = activity.pettype;
//        effect = activity.effect;
        //recipe_code= activity.dbAc.getRecommendCode(petType, effect);
//        ArrayList<ArrayList<String>> Recipelist = activity.dbAc.getRecipelist(recipe_code);
//        for(int i=0; i<Recipelist.size(); i++){
//            adapter.addItem(new RecipeItem(Recipelist.get(i).get(0), Recipelist.get(i).get(1), Recipelist.get(i).get(8), Recipelist.get(i).get(2),
//                    Recipelist.get(i).get(3), Recipelist.get(i).get(4), Recipelist.get(i).get(5), Recipelist.get(i).get(6),
//                    Recipelist.get(i).get(7)));
//        }
//        recycler_rcp.setAdapter(adapter);
//
//        adapter.setOnItemClickListener(new OnRecipeItemClickListener() {
//            @Override
//            public void onItemClick(RecipeItemAdapter.ViewHolder holder, View view, int position) {
//                RecipeItem item = adapter.getItem(position);
//                Intent intent = new Intent(activity, RecipeActivity.class);
//                intent.putExtra("recipe_code", recipe_code.get(position));
//                intent.putExtra("recipe_name",item.rcp_txt);
//                intent.putExtra("img_url",item.img_url);
//                intent.putExtra("recipe_sum", item.txt_sum);
//                intent.putExtra("recipe_type", item.txt_type);
//                intent.putExtra("recipe_time", item.txt_time);
//                intent.putExtra("recipe_tip",item.txt_tip);
//                intent.putExtra("recipe_eff",item.txt_eff);
//                intent.putExtra("recipe_like",item.txt_like);
//                startActivity(intent);  // activity 이동
//            }
//        });

        return rootView;
    }

    public void setRecyclerView(){
        //레시피 목록 출력
        recycler_rcp = rootView.findViewById(R.id.recycler_rcp);
        LinearLayoutManager layoutManager =
                new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false);
        recycler_rcp.setLayoutManager(layoutManager);
        adapter = new RecipeItemAdapter();

        petType = activity.pettype;
        effect = activity.effect;
        recipe_code= activity.dbAc.getRecommendCode(petType, effect);
        ArrayList<ArrayList<String>> Recipelist = activity.dbAc.getRecipelist(recipe_code);
        for(int i=0; i<Recipelist.size(); i++){
            adapter.addItem(new RecipeItem(Recipelist.get(i).get(0), Recipelist.get(i).get(1), Recipelist.get(i).get(8), Recipelist.get(i).get(2),
                    Recipelist.get(i).get(3), Recipelist.get(i).get(4), Recipelist.get(i).get(5), Recipelist.get(i).get(6),
                    Recipelist.get(i).get(7)));
        }
        recycler_rcp.setAdapter(adapter);
    }

}