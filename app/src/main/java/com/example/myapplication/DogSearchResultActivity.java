package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class DogSearchResultActivity extends AppCompatActivity {

    private TextView txt_search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dog_search_result);

        txt_search = findViewById(R.id.txt_search);

        Intent intent = getIntent();
        String search = intent.getStringExtra("search");

        txt_search.setText(search);
    }
}