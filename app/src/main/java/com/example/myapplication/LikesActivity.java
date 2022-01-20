package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class LikesActivity extends AppCompatActivity {

    private ImageButton imgBtn_rcp1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_likes);

        imgBtn_rcp1 = findViewById(R.id.imgBtn_rcp1);
        imgBtn_rcp1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(LikesActivity.this, RecipeActivity.class);
                startActivity(intent);  // activity 이동
            }
        });
    }
}