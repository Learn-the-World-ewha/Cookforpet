package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class LikesFragment extends Fragment {
    TextView username_txt, cook_sum;
    RecyclerView recycler_rcp;
    RecipeItemAdapter adapter;
    ArrayList<String> recipe_code;
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

        //유저 이름 띄우기
        username_txt=rootView.findViewById(R.id.name_txt);
        username_txt.setText(activity.user_name);

        id = activity.user_code;
        //결과값 갯수 출력
        count = activity.dbAc.getUserLikeSum(id);
        cook_sum = rootView.findViewById(R.id.like_sum);
        cook_sum.setText(count.toString());

        //레시피 목록 출력
        recycler_rcp = rootView.findViewById(R.id.recycler_rcp);
        LinearLayoutManager layoutManager =
                new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        recycler_rcp.setLayoutManager(layoutManager);
        adapter = new RecipeItemAdapter();

        LikesItems();
//        recipe_code= activity.dbAc.getUserLikeRecipe(id);
//        ArrayList<ArrayList<String>> Recipelist = activity.dbAc.getRecipelist(recipe_code);
//        for(int i=0; i<Recipelist.size(); i++){
//                adapter.addItem(new RecipeItem(Recipelist.get(i).get(0), Recipelist.get(i).get(1), Recipelist.get(i).get(8), Recipelist.get(i).get(2),
//                        Recipelist.get(i).get(3), Recipelist.get(i).get(4), Recipelist.get(i).get(5), Recipelist.get(i).get(6),
//                        Recipelist.get(i).get(7)));
//        }
        recycler_rcp.setAdapter(adapter);

        adapter.setOnItemClickListener(new OnRecipeItemClickListener() {
            @Override
            public void onItemClick(RecipeItemAdapter.ViewHolder holder, View view, int position) {
                RecipeItem item = adapter.getItem(position);
                Intent intent = new Intent(context, RecipeActivity.class);
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

        return rootView;
    }
    private void LikesItems() {
        ArrayList <RecipeItem> items=getLikesItems();
        adapter.setItems(items);
    }
    private ArrayList<RecipeItem> getLikesItems(){
        recipe_code= activity.dbAc.getUserLikeRecipe(id);
        ArrayList<ArrayList<String>> recipeLists = activity.dbAc.getRecipelist(recipe_code);

        ArrayList<RecipeItem> result= new ArrayList<>();
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
}