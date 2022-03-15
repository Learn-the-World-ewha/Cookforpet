package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SubMainActivity extends AppCompatActivity {
    private ImageButton imgBtn_search;
    private EditText editTxt_search;
    Intent intent;

    String effect, email, idToken, name, pwd;
    int pettype;

    ArrayList<String> list = new ArrayList<String>();

    DatabaseAccess dbAc;
    RecommendFragment recommendFragment;
    ResultFragment resultFragment;
    DogResultFragment dogResultFragment;
    CatResultFragment catResultFragment;

    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    final FirebaseUser user = firebaseAuth.getCurrentUser();
    private DatabaseReference reference;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_actionbar,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int curId = item.getItemId();
        switch(curId){
            case R.id.menu_home:
                intent = new Intent(SubMainActivity.this, SubMainActivity.class);
                startActivity(intent);  // activity 이동
                break;
            case R.id.menu_user:
                intent = new Intent(SubMainActivity.this, UserActivity.class);
                startActivity(intent);  // activity 이동
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dogs_main);

//        final InputMethodManager manager = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE) ;


        //recommendFragment = (RecommendFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        recommendFragment = new RecommendFragment();
        resultFragment = new ResultFragment();
        dogResultFragment = new DogResultFragment();
        catResultFragment = new CatResultFragment();


        dbAc = DatabaseAccess.getInstance(getApplicationContext());
        dbAc.open();



        reference = FirebaseDatabase.getInstance().getReference("Cookforpet");
        DatabaseReference usertype = reference.child("UserAccount").child(user.getUid()).child("pettype");
        usertype.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String tmp =snapshot.getValue().toString();
                if (tmp.equals("Dog"))
                    pettype = 2;
                else if (tmp.equals("Cat"))
                    pettype = 3;
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        DatabaseReference usereffect = reference.child("UserAccount").child(user.getUid()).child("effect");
        usereffect.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                effect=snapshot.getValue().toString();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, recommendFragment).commit();

        editTxt_search = findViewById(R.id.editTxt_search);
        imgBtn_search = findViewById(R.id.imgBtn_search);
        imgBtn_search.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
//                manager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                onFragmentChanged(1);
            }
        });

//    25~마지막줄은 수정해야 함. 미완성 레시피 추천 함수임.
//            public void getVal(); {
//
//                DatabaseHelper dbHelper = new DatabaseHelper(this);
//                SQLiteDatabase db = dbHelper.getReadableDatabase();
//
//                Cursor cursor = db.rawQuery("SELECT * FROM recipe_ingredient where RECIPE_ID = 181",null);
//                //" and name = ?",new String[]{"홍길동"});
//                while (cursor.moveToNext())
//                {
//                    val += cursor.getString(2)+", ";
//
//                }
//                sqlresult.setText("재료: "+val);
//                cursor.close();
//                dbHelper.close();
//            }
    }
    public void onFragmentChanged(int index){
        if (index == 0){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, recommendFragment).commit();
        } else if (index == 1){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, resultFragment).commit();
        } else if (index == 2){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, dogResultFragment).commit();
        } else if (index == 3){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, catResultFragment).commit();
        }
    }
    public String getSearch(){
        String s = editTxt_search.getText().toString();
        return s;
    }

}