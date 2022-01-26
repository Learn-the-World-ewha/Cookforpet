package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    Button mLoginBtn;
    EditText mEmailidText, mPasswordText;
//    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

//        firebaseAuth = FirebaseAuth.getInstance();

        mLoginBtn = findViewById(R.id.btn_login);
        mEmailidText = findViewById(R.id.editTxt_id);
        mPasswordText = findViewById(R.id.editTxt_pwd);

        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String emailid = mEmailidText.getText().toString();
//                String pwd = mPasswordText.getText().toString();
//
//                firebaseAuth.signInWithEmailAndPassword(emailid, pwd).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        if (task.isSuccessful()) {
//                            Toast.makeText(getApplicationContext(), "로그인 성공", Toast.LENGTH_SHORT).show();
//                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//                            startActivity(intent);
//                        }
//                        else {
//                            Toast.makeText(getApplicationContext(), "로그인 오류", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                });
                Intent intent = new Intent(LoginActivity.this, SubMainActivity.class);
                startActivity(intent);
            }
        });


    }
}