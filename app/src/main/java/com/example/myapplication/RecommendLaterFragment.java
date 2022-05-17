package com.example.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = (ViewGroup)inflater.inflate(R.layout.fragment_recommend_later, container, false);


        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        setupViews();
        showItems();
        setButton2();
        recycler_rcp.setAdapter(adapter);

        adapter.setOnItemClickListener(new OnRecipeItemClickListener() {
            @Override
            public void onItemClick(RecipeItemAdapter.ViewHolder holder, View view, int position) {
                RecipeItem item = adapter.getItem(position);
                Intent intent = new Intent(activity, RecipeActivity.class);
                intent.putExtra("recipe_code", recipe_code.get(position));
                intent.putExtra("recipe_name",item.rcp_txt);
                intent.putExtra("img_url",item.img_url);
                intent.putExtra("recipe_sum", item.txt_sum);
                intent.putExtra("recipe_type", item.txt_type);
                intent.putExtra("recipe_time", item.txt_time);
                intent.putExtra("recipe_tip",item.txt_tip);
                intent.putExtra("recipe_eff",item.txt_eff);
                intent.putExtra("recipe_like",item.txt_like);
                startActivity(intent);  // activity 이동
            }
        });
    }

    private void setupViews() {

        recycler_rcp = (RecyclerView) rootView.findViewById(R.id.recycler_rcp);
        LinearLayoutManager layoutManager =
                new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false);
        recycler_rcp.setLayoutManager(layoutManager);
        reference = FirebaseDatabase.getInstance().getReference("Cookforpet");
        adapter = new RecipeItemAdapter();
    }

    private void showItems() {
        ArrayList <RecipeItem> items=getLikeRecipeItems();
        adapter.setItems(items);
    }

    private ArrayList<RecipeItem> getLikeRecipeItems() {
        //recipe_code = activity.dbAc.getLikeRecommendCode(user.getUid());
        recipe_code = activity.dbAc.getRecommendCode(3,"관절관련");
        ArrayList<ArrayList<String>> recipeLists = activity.dbAc.getRecipelist(recipe_code);

        ArrayList<RecipeItem> result = new ArrayList<>();
        for (ArrayList<String> receiptList : recipeLists) {
            RecipeItem item = new RecipeItem(
                    receiptList.get(0),
                    receiptList.get(1),
                    receiptList.get(8),
                    receiptList.get(2),
                    receiptList.get(3),
                    receiptList.get(4),
                    receiptList.get(5),
                    receiptList.get(6),
                    receiptList.get(7));
            result.add(item);
        }
        return result;
    }

    private void setButton2(){
        recommend_btn = rootView.findViewById(R.id.recommend_btn);
        recommend_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { activity.onFragmentChanged(0); }
        });
    }
}