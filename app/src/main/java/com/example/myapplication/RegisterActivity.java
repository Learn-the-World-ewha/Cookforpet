package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RegisterActivity extends AppCompatActivity {

    private static final String TAG = "register";
    EditText mEmailidText, mPasswordText, mPasswordcheckText, mName;
    Button mregisterBtn, mCheckBtn;
    RadioGroup rg_petType, rg_effect;
    RadioButton rpetType, reffect;

    private FirebaseAuth firebaseAuth; //파이어베이스 인증처리
    private DatabaseReference reference; //실시간 데이터베이스
    boolean check=false; //중복 체크

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        firebaseAuth=FirebaseAuth.getInstance();
        reference= FirebaseDatabase.getInstance().getReference("Cookforpet");

        mEmailidText=findViewById(R.id.editTxt_id);
        mCheckBtn=findViewById(R.id.btn_check);
        mPasswordText=findViewById(R.id.editTxt_pwd);
        mPasswordcheckText=findViewById(R.id.editTxt_pwd2);
        mName=findViewById(R.id.editTxt_name);
        mregisterBtn=findViewById(R.id.btn_register);
        rg_petType=findViewById(R.id.rGroup_pettype);
        rg_effect=findViewById(R.id.rGroup_effect);

        //아이디 중복 체크 코드
        mCheckBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reference.child("UserAccount").orderByChild("emailid").equalTo(mEmailidText.getText().toString()).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            Toast.makeText(RegisterActivity.this, "이미 존재하는 아이디입니다", Toast.LENGTH_SHORT).show();
                            check=true;
                        }
                        else {
                            Toast.makeText(RegisterActivity.this, "사용 가능한 아이디입니다", Toast.LENGTH_SHORT).show();
                            check=false;
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) { }
                });
            }
        });


        // 회원가입 버튼을 눌렀을 때
        mregisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=mName.getText().toString();
                String id=mEmailidText.getText().toString();
                String pwd=mPasswordText.getText().toString();
                String pwdcheck=mPasswordcheckText.getText().toString();
                rpetType=(RadioButton) findViewById(rg_petType.getCheckedRadioButtonId());
                String type=rpetType.getText().toString();
                reffect=(RadioButton) findViewById(rg_effect.getCheckedRadioButtonId());
                String effect=reffect.getText().toString();


                if(pwd.equals(pwdcheck)) {
                    final ProgressDialog mDialog=new ProgressDialog(RegisterActivity.this);
                    mDialog.setMessage("가입중입니다...");
                    mDialog.show();

                    firebaseAuth.createUserWithEmailAndPassword(id,pwd).addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()) {
                                mDialog.dismiss();

                                FirebaseUser user=firebaseAuth.getCurrentUser();
                                UserAccount account=new UserAccount();
                                account.setName(name);
                                account.setEmailid(user.getEmail());
                                account.setPwd(pwd);
                                account.setIdToken(user.getUid());
                                account.setPettype(type);
                                account.setEffect(effect);

                                reference.child("UserAccount").child(user.getUid()).setValue(account);

                                Toast.makeText(RegisterActivity.this, "회원가입 완료", Toast.LENGTH_SHORT).show();
                                Intent intent=new Intent(RegisterActivity.this, LoginActivity.class);
                                startActivity(intent);
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