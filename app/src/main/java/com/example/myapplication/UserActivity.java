package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class UserActivity extends AppCompatActivity {

    private Button btn_ref;
    private Button btn_likes;
    private Button btn_logout;
    private Button btn_delete;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        btn_ref = findViewById(R.id.btn_ref);
        btn_ref.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserActivity.this, RefrigeratorActivity.class);
                startActivity(intent);  // activity 이동
            }
        });

        btn_likes = findViewById(R.id.btn_likes);
        btn_likes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserActivity.this, LikesActivity.class);
                startActivity(intent);  // activity 이동
            }
        });

        btn_logout = findViewById(R.id.btn_logout);
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.signOut();

                Intent intent = new Intent(UserActivity.this, MainActivity.class);
                startActivity(intent);
                finish();


            }
        });


        btn_delete = findViewById(R.id.btn_delete);
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserActivity.this, DeleteAccountActivity.class);
                startActivity(intent);
            }
        });
    }
}