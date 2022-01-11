package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RefDetailActivity extends AppCompatActivity {

    private Button btn_usedall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ref_detail);

        btn_usedall = findViewById(R.id.btn_usedall);
        btn_usedall.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(RefDetailActivity.this, RefrigeratorActivity.class);
                startActivity(intent);  // activity 이동
            }
        });
    }
}