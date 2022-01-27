package com.example.myapplication;

import androidx.annotation.NonNull;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.*;
import android.view.View;
import android.app.ActionBar;
import android.app.ProgressDialog;
import android.content.Intent;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.DataSnapshot;

public class RegisterActivity extends AppCompatActivity {

    EditText mEmailText, mPasswordText, mPasswordcheckText, mNameText;
    Button mregisterBtn, mCheckBtn;
    RadioGroup rg_petType,rg_effect;
    RadioButton rpetType,reffect;

    private FirebaseAuth firebaseAuth; //파이어베이스 인증처리
    private DatabaseReference reference; //실시간 데이터베이스

    boolean check=false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        firebaseAuth=FirebaseAuth.getInstance();
        reference=FirebaseDatabase.getInstance().getReference("Cookforpet");

        mEmailText=findViewById(R.id.editTxt_id);
        mPasswordText=findViewById(R.id.editTxt_pwd);
        mPasswordcheckText=findViewById(R.id.editTxt_pwd2);
        mNameText=findViewById(R.id.editTxt_name);
        mregisterBtn=findViewById(R.id.btn_register);
        mCheckBtn=findViewById(R.id.btn_check);
        rg_petType=findViewById(R.id.rGroup_pettype);
        rg_effect=findViewById(R.id.rGroup_effect);



//아이디 중복 체크
        mCheckBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reference.child("UserAccount").orderByChild("emailid").equalTo(mEmailText.getText().toString()).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            Toast.makeText(RegisterActivity.this, "이미 존재하는 아이디입니다", Toast.LENGTH_SHORT).show();
                            check=true;
                        }
                        else {
                            Toast.makeText(RegisterActivity.this, "사용 가능한 아이디입니다", Toast.LENGTH_SHORT).show();
                            check = false;
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) { }
                });
            }
        });




        mregisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=mNameText.getText().toString();
                String email=mEmailText.getText().toString();
                String pwd=mPasswordText.getText().toString();
                String pwdcheck=mPasswordcheckText.getText().toString();
                rpetType=(RadioButton) findViewById(rg_petType.getCheckedRadioButtonId());
                String type=rpetType.getText().toString();
                reffect=(RadioButton) findViewById(rg_effect.getCheckedRadioButtonId());
                String effect=reffect.getText().toString();



//                if(check==true){
//                    Toast.makeText(RegisterActivity.this,"아이디를 다시 입력해주세요",Toast.LENGTH_LONG).show();
//                }


                if(pwd.equals(pwdcheck)){
                    final ProgressDialog mDialog=new ProgressDialog(RegisterActivity.this);
                    mDialog.setMessage("가입중입니다...");
                    mDialog.show();

                    firebaseAuth.createUserWithEmailAndPassword(email,pwd).addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                mDialog.dismiss();

                                FirebaseUser user=firebaseAuth.getCurrentUser();
                                UserAccount account=new UserAccount();
                                account.setName(name);
                                account.setEmailid(user.getEmail());
                                account.setPwd(pwd);
                                account.setIdToken((user.getUid()));
                                account.setPettype(type);
                                account.setEffect(effect);

                                reference.child("UserAccount").child(user.getUid()).setValue(account);


                                Toast.makeText(RegisterActivity.this,"회원가입 완료",Toast.LENGTH_SHORT).show();
                                Intent intent=new Intent(RegisterActivity.this, LoginActivity.class);
                                startActivity(intent);
                            }

                            else{
                                mDialog.dismiss();
                                Toast.makeText(RegisterActivity.this,"회원가입 실패",Toast.LENGTH_SHORT).show();
                                return;
                            }
                        }
                    });
                }
                else{
                    Toast.makeText(RegisterActivity.this,"비밀번호 오류",Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}