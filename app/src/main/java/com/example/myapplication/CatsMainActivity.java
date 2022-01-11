package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

public class CatsMainActivity extends AppCompatActivity {

    private ImageButton imgBtn_search;
    private EditText editTxt_search;
    private String search;
    private ImageButton imgBtn_home;
    private ImageButton imgBtn_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cats_main);

        editTxt_search = findViewById(R.id.editTxt_search);

        imgBtn_search = findViewById(R.id.imgBtn_search);
        imgBtn_search.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                search = editTxt_search.getText().toString();
                Intent intent = new Intent(CatsMainActivity.this, CatSearchResultActivity.class);
                intent.putExtra("search",search);
                startActivity(intent);  // activity 이동
            }
        });

        imgBtn_home = findViewById(R.id.imgBtn_home);
        imgBtn_home.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(CatsMainActivity.this, TypeActivity.class);
                startActivity(intent);  // activity 이동
            }
        });

        imgBtn_user = findViewById(R.id.imgBtn_user);
        imgBtn_user.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(CatsMainActivity.this, UserActivity.class);
                startActivity(intent);  // activity 이동
            }
        });
    }
}