package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class DogsRecipeActivity extends AppCompatActivity {

    DataBaseHelper DB;
    RecyclerView recycler_rcp, recycler_mat;
    TextView txt_title, txt_tip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dogs_recipe);

        recycler_mat = findViewById(R.id.recycler_mat);
        LinearLayoutManager layoutManager =
                new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recycler_mat.setLayoutManager(layoutManager);
        MaterialItemAdapter adapter = new MaterialItemAdapter();
        adapter.addItem(new MaterialItem("다진 소고기", "200g"));
        adapter.addItem(new MaterialItem("간장", "1 큰술"));
        adapter.addItem(new MaterialItem("대파", "20g"));
        adapter.addItem(new MaterialItem("물", "200ml"));
        recycler_mat.setAdapter(adapter);


//        MatList matlist = findViewById(R.id.matlist);
//        matlist.setTxt_mat("다진 소고기");
//        matlist.setTxt_unit("200g");

        StepList steplist = findViewById(R.id.steplist);
        steplist.setImg_step(R.drawable.ic_launcher_foreground);
        steplist.setTxt_stepNum("1");
        steplist.setTxt_step("당근을 잘게 다집니다.");
    }
    public void onButton1Clicked(View v) {
        Toast.makeText(this, "My refrigerator에 추가되었습니다.", Toast.LENGTH_LONG).show();
    }

}