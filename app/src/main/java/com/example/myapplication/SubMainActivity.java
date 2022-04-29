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
import java.util.concurrent.CountDownLatch;

public class SubMainActivity extends AppCompatActivity {
    private ImageButton imgBtn_search;
    private EditText editTxt_search;
    Intent intent;

    String effect, email, idToken, name, pwd;
    ArrayList<String> recipe_code;
    int pettype;

    Recipecode recipecode;


    ArrayList<String> list = new ArrayList<String>();

    DatabaseAccess dbAc;
    RecommendFragment recommendFragment;
    ResultFragment resultFragment;
    DogResultFragment dogResultFragment;
    CatResultFragment catResultFragment;
    RecommendLaterFragment recommendLaterFragment;

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

        final InputMethodManager manager = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE) ;


        //recommendFragment = (RecommendFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        recommendFragment = new RecommendFragment();
        resultFragment = new ResultFragment();
        dogResultFragment = new DogResultFragment();
        catResultFragment = new CatResultFragment();
        recommendLaterFragment = new RecommendLaterFragment();



        dbAc = DatabaseAccess.getInstance(getApplicationContext());
        dbAc.open();

        editTxt_search = findViewById(R.id.editTxt_search);
        imgBtn_search = findViewById(R.id.imgBtn_search);
        imgBtn_search.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                manager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                onFragmentChanged(1);
            }
        });

    }
    public void onFragmentChanged(int index){
        if (index == 0) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, recommendFragment).commit();
        } else if (index == 1){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, resultFragment).commit();
        } else if (index == 2){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, dogResultFragment).commit();
        } else if (index == 3){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, catResultFragment).commit();
        } else if (index == 4){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, recommendLaterFragment).commit();
        }
    }
    public String getSearch(){
        String s = editTxt_search.getText().toString();
        return s;
    }



}
