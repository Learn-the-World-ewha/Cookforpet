package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TypeActivity extends AppCompatActivity {

    private Button btn_dog;
    private Button btn_cat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type);

        btn_dog = findViewById(R.id.btn_dog);
        btn_dog.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(TypeActivity.this, DogsMainActivity.class);
                startActivity(intent);  // activity 이동
            }
        });

        btn_cat = findViewById(R.id.btn_cat);
        btn_cat.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(TypeActivity.this, CatsMainActivity.class);
                startActivity(intent);  // activity 이동
            }
        });
    }
}