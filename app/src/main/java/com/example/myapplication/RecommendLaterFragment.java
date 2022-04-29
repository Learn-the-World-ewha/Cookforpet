package com.example.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class RecommendLaterFragment extends Fragment {
    Button recommend_btn;
    RecyclerView recycler_rcp;
    RecipeItemAdapter adapter;
    ArrayList<String> recipe_code;
    SubMainActivity activity;
    ViewGroup rootView;

    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    final FirebaseUser user = firebaseAuth.getCurrentUser();
    private DatabaseReference reference;


    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof Activity)
            activity = (SubMainActivity) context; //서브메인액티비티 얻어내기
    }
    public void onDetach(Context context){
        super.onDetach();
        activity=null;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = (ViewGroup)inflater.inflate(R.layout.fragment_recommend_later, container, false);

        recommend_btn = rootView.findViewById(R.id.recommend_btn);
        recommend_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.onFragmentChanged(0);
            }
        });

//        recycler_rcp = (RecyclerView) rootView.findViewById(R.id.recycler_rcp);
//        LinearLayoutManager layoutManager =
//                new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false);
//        recycler_rcp.setLayoutManager(layoutManager);
//        reference = FirebaseDatabase.getInstance().getReference("Cookforpet");
//        adapter = new RecipeItemAdapter();
//
//        // 파이어베이스에서 추천 recipe_code 배열 받아오기
//        ArrayList<ArrayList<String>> Recipelist = activity.dbAc.getRecipelist(recipe_code);
//        for(int i=0; i<Recipelist.size(); i++){
//            adapter.addItem(new RecipeItem(Recipelist.get(i).get(0), Recipelist.get(i).get(1), Recipelist.get(i).get(8), Recipelist.get(i).get(2),
//                    Recipelist.get(i).get(3), Recipelist.get(i).get(4), Recipelist.get(i).get(5), Recipelist.get(i).get(6),
//                    Recipelist.get(i).get(7)));
//        }
//
//        recycler_rcp.setAdapter(adapter);
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
}