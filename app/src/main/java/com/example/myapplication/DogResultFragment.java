package com.example.myapplication;

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
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class DogResultFragment extends Fragment {
    private TextView txt_result;
    Button btn1, btn2, btn3;
    RecyclerView recycler_rcp;
    RecipeItemAdapter adapter;
    ArrayList<String> recipe_code;
    String search;
    Context context;
    Integer count;
    SubMainActivity activity;
    ViewGroup rootView;
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        activity = (SubMainActivity) getActivity();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        activity = null;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        int type=2;
        rootView = (ViewGroup) inflater.inflate(R.layout.fragment_dog_result, container, false);
        context = container.getContext();

        activity = (SubMainActivity) getActivity();
        search = activity.getSearch();

        //Type 선택
        btn1 = rootView.findViewById(R.id.btn1);
        btn2 = rootView.findViewById(R.id.btn2);
        btn3 = rootView.findViewById(R.id.btn3);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.onFragmentChanged(1);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.onFragmentChanged(2);
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.onFragmentChanged(3);
            }
        });

        //결과값 갯수 출력
        count = activity.dbAc.getResultSum(search, type);
        txt_result = rootView.findViewById(R.id.txt_result);
        txt_result.setText(count.toString());

        //레시피 목록 출력
        recycler_rcp = rootView.findViewById(R.id.recycler_rcp);
        LinearLayoutManager layoutManager =
                new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        recycler_rcp.setLayoutManager(layoutManager);
        adapter = new RecipeItemAdapter();

        recipe_code= activity.dbAc.getRecipeCode(search, type);
        ArrayList<ArrayList<String>> Recipelist = activity.dbAc.getRecipelist(recipe_code);
        for(int i=0; i<Recipelist.size(); i++){
            adapter.addItem(new RecipeItem(Recipelist.get(i).get(0), Recipelist.get(i).get(1), Recipelist.get(i).get(8), Recipelist.get(i).get(2),
                    Recipelist.get(i).get(3), Recipelist.get(i).get(4), Recipelist.get(i).get(5), Recipelist.get(i).get(6),
                    Recipelist.get(i).get(7)));
        }
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
}