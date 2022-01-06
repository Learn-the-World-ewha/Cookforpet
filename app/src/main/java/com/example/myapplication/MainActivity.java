package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.content.IntentCompat;

import android.content.Intent;
import android.view.View;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button btn_login;
    private Button btn_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_login = findViewById(R.id.btn_login);
        btn_login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);  // activity 이동
            }
        });
        btn_register = findViewById(R.id.btn_register);
        btn_register.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);  // activity 이동
            }
        });
    }
    public void onButton1Clicked(View v) {
        Toast.makeText(this, "My refrigerator에 추가되었습니다.", Toast.LENGTH_LONG).show();
    }
}
