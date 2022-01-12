package com.example.myapplication;

import static android.view.View.*;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class DogsMainActivity extends AppCompatActivity {
    private ImageButton imgBtn_search;
    private EditText editTxt_search;
    private String search;
    private ImageButton imgBtn_home;
    private ImageButton imgBtn_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dogs_main);


        RecipeList rcplist = findViewById(R.id.rcplist);
        rcplist.setImg_rcp(R.drawable.ic_launcher_foreground);
        rcplist.setTxt_rcp("Recipe");
        rcplist.setTxt_mat("재료1, 재료2, 재료3, ...");

        editTxt_search = findViewById(R.id.editTxt_search);
        imgBtn_search = findViewById(R.id.imgBtn_search);
        imgBtn_search.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                search = editTxt_search.getText().toString();
                Intent intent = new Intent(DogsMainActivity.this, DogSearchResultActivity.class);
                intent.putExtra("search",search);
                startActivity(intent);  // activity 이동
            }
        });

        imgBtn_home = findViewById(R.id.imgBtn_home);
        imgBtn_home.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(DogsMainActivity.this, TypeActivity.class);
                startActivity(intent);  // activity 이동
            }
        });
        imgBtn_user = findViewById(R.id.imgBtn_user);
        imgBtn_user.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(DogsMainActivity.this, UserActivity.class);
                startActivity(intent);  // activity 이동
            }
        });

    }
}