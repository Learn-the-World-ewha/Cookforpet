package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class CatsRecipeActivity extends AppCompatActivity {

    DataBaseHelper DB;
    RecyclerView recycler_rcp, recycler_mat;
    TextView txt_title, txt_tip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cats_recipe);

        recycler_mat = findViewById(R.id.recycler_mat);
        LinearLayoutManager layoutManager =
                new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recycler_mat.setLayoutManager(layoutManager);
        MaterialItemAdapter adapter = new MaterialItemAdapter();
        adapter.addItem(new MaterialItem("다진 소고기", "100g"));
        adapter.addItem(new MaterialItem("올리브 오일", "1tbsp"));
        adapter.addItem(new MaterialItem("닭가슴살", "300g"));
        adapter.addItem(new MaterialItem("양배추", "200g"));
        recycler_mat.setAdapter(adapter);

        StepList steplist = findViewById(R.id.steplist);
        steplist.setImg_step(R.drawable.ic_launcher_foreground);
        steplist.setTxt_stepNum("1");
        steplist.setTxt_step("양파와 닭고기를 잘게 썰어주세요.");
    }
    public void onButton1Clicked(View v) {
        Toast.makeText(this, "My refrigerator에 추가되었습니다.", Toast.LENGTH_LONG).show();
    }
}