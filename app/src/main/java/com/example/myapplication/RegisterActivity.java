package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    private static final String TAG = "register";
    EditText mEmailidText, mPasswordText, mPasswordcheckText, mName;
    Button mregisterBtn;
    private FirebaseAuth firebaseAuth; //파이어베이스 인증처리
    private DatabaseReference reference; //실시간 데이터베이스
    private boolean validate = false; //중복 체크

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        firebaseAuth=FirebaseAuth.getInstance();
        reference= FirebaseDatabase.getInstance().getReference("Cookforpet");

        mEmailidText=findViewById(R.id.editTxt_id);
        mPasswordText=findViewById(R.id.editTxt_pwd);
        mPasswordcheckText=findViewById(R.id.editTxt_pwd2);
        mName=findViewById(R.id.editTxt_name);
        mregisterBtn=findViewById(R.id.btn_register);

        mregisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id=mEmailidText.getText().toString();
                String pwd=mPasswordText.getText().toString();
                String pwdcheck=mPasswordcheckText.getText().toString();

                if(pwd.equals(pwdcheck)) {
                    final ProgressDialog mDialog=new ProgressDialog(RegisterActivity.this);
                    mDialog.setMessage("가입중입니다");
                    mDialog.show();

                    firebaseAuth.createUserWithEmailAndPassword(id,pwd).addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()) {
                                mDialog.dismiss();

                                FirebaseUser user=firebaseAuth.getCurrentUser();
                                UserAccount account=new UserAccount();
                                account.setEmailid(user.getEmail());
                                account.setPwd(pwd);
                                account.setIdToken((user.getUid()));

                                reference.child("UserAccount").child(user.getUid()).setValue(account);

                                Toast.makeText(RegisterActivity.this, "회원가입 완료", Toast.LENGTH_SHORT).show();
                            }

                            else{
                                mDialog.dismiss();
                                Toast.makeText(RegisterActivity.this, "회원가입 실패", Toast.LENGTH_SHORT).show();
                                return;
                            }
                        }
                    });
                }
                else{
                    Toast.makeText(RegisterActivity.this, "비밀번호 오류", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}