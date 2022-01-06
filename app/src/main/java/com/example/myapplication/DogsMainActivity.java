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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dogs_main);

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

    }
}