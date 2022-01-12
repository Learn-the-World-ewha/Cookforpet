package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class DogsRecipeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dogs_recipe);

        MatList matlist = findViewById(R.id.matlist);
        matlist.setTxt_mat("다진 소고기");
        matlist.setTxt_unit("200g");

        StepList steplist = findViewById(R.id.steplist);
        steplist.setImg_step(R.drawable.ic_launcher_foreground);
        steplist.setTxt_stepNum("1");
        steplist.setTxt_step("당근을 잘게 다집니다.");
    }
    public void onButton1Clicked(View v) {
        Toast.makeText(this, "My refrigerator에 추가되었습니다.", Toast.LENGTH_LONG).show();
    }

}