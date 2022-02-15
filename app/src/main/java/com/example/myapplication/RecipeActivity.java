package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class RecipeActivity extends AppCompatActivity {
    TextView txt_title, txt_tip, txt_type, txt_sum, txt_eff, txt_like, txt_time;
    ImageView img_main;
    RecyclerView recycler_mat, recycler_step;
    Button btn_complete;
    ToggleButton tog_like;
    String recipe_code, recipe_name, img_url, user_code, cook_date;
    String recipe_type, recipe_tip, recipe_sum, recipe_time, recipe_like, recipe_eff;
    MaterialItemAdapter adapter;
    StepItemAdapter adapter2;
    Intent intent;
    DatabaseAccess databaseAccess;
    Date mDate;
    long mNow;
    SimpleDateFormat mFormat = new SimpleDateFormat("yyyy-mm-dd");

    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    final FirebaseUser user = firebaseAuth.getCurrentUser();
    private DatabaseReference reference;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_actionbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int curId = item.getItemId();
        switch (curId) {
            case R.id.menu_home:
                intent = new Intent(RecipeActivity.this, SubMainActivity.class);
                startActivity(intent);  // activity 이동
                break;
            case R.id.menu_user:
                intent = new Intent(RecipeActivity.this, UserActivity.class);
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
        setContentView(R.layout.activity_dogs_recipe);

        Intent intent = getIntent();
        recipe_code = intent.getStringExtra("recipe_code");
        recipe_name = intent.getStringExtra("recipe_name");
        img_url = intent.getStringExtra("img_url");
        recipe_eff = intent.getStringExtra("recipe_eff");
        recipe_like = intent.getStringExtra("recipe_like");
        recipe_time = intent.getStringExtra("recipe_time");
        recipe_sum = intent.getStringExtra("recipe_sum");
        recipe_tip = intent.getStringExtra("recipe_tip");
        recipe_type = intent.getStringExtra("recipe_type");

        databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
        databaseAccess.open();

        //레시피 관련 정보 출력
        txt_title = findViewById(R.id.txt_title);
        txt_type = findViewById(R.id.txt_type);
        txt_sum = findViewById(R.id.txt_sum);
        txt_tip = findViewById(R.id.txt_tip);
        txt_eff = findViewById(R.id.txt_eff);
        txt_time = findViewById(R.id.txt_time);
        txt_like = findViewById(R.id.txt_like);
        img_main = findViewById(R.id.img_main);

        //xml에 적용
        txt_title.setText(recipe_name);   //recipe_name 출력
        Glide.with(this)                 //img_main 출력
                .load(img_url)
                .into(img_main);

        txt_sum.setText(recipe_sum);     //recipe_sum 출력
        if (recipe_type.equals("냥키친"))  //type 출력
            txt_type.setText("Cat's");
        else
            txt_type.setText("Dog's");
        txt_time.setText(recipe_time);    //cook_time 출력
        txt_tip.setText(recipe_tip);     //tip 출력
        txt_eff.setText(recipe_eff);     //effect 출력
        txt_like.setText(recipe_like);  //like 출력


        //재료 리스트 출력
        recycler_mat = findViewById(R.id.recycler_mat);
        LinearLayoutManager layoutManager =
                new LinearLayoutManager(recycler_mat.getContext(), LinearLayoutManager.HORIZONTAL, false);
        recycler_mat.setLayoutManager(layoutManager);
        adapter = new MaterialItemAdapter();

        ArrayList<ArrayList<String>> Matlist = databaseAccess.getMatlist(recipe_code);
        for (int i = 0; i < Matlist.size(); i++) {
            adapter.addItem(new MaterialItem(Matlist.get(i).get(0), Matlist.get(i).get(1)));
        }
        recycler_mat.setAdapter(adapter);


        //step 리스트 출력
        recycler_step = findViewById(R.id.recycler_step);
        LinearLayoutManager layoutManager2 =
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recycler_step.setLayoutManager(layoutManager2);
        adapter2 = new StepItemAdapter();

        ArrayList<ArrayList<String>> Steplist = databaseAccess.getSteplist(recipe_code);
        for (int i = 0; i < Steplist.size(); i++) {
            adapter2.addItem(new StepItem(Steplist.get(i).get(0), Steplist.get(i).get(1), Steplist.get(i).get(2)));
        }
        recycler_step.setAdapter(adapter2);

        reference = FirebaseDatabase.getInstance().getReference("Cookforpet");
        DatabaseReference usercode = reference.child("UserAccount").child(user.getUid());
        usercode.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful())
                    Log.e("firebase", "Error getting data", task.getException());
                else
                    user_code = task.getResult().getValue(String.class);
            }
        });

        tog_like = findViewById(R.id.tog_like);
        tog_like.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){

                } else {

                }
            }
        });


        //방문 기록
        databaseAccess.insertVisit(user_code, recipe_code);
        //databaseAccess.close();
    }

    public void onButton1Clicked(View v) {
        Toast.makeText(this, "My refrigerator에 추가되었습니다.", Toast.LENGTH_LONG).show();
        mNow = System.currentTimeMillis();
        mDate = new Date(mNow);
        cook_date = mFormat.format(mDate);

        ContentValues cv = new ContentValues();
        cv.put("user_code", user_code);
        cv.put("recipe_code", recipe_code);
        cv.put("cook_date", cook_date);
        databaseAccess.insertCook(cv);
    }
}