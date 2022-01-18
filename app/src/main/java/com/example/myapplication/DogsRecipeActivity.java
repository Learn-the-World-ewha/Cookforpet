package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class DogsRecipeActivity extends AppCompatActivity {
    TextView txt_title, txt_tip, txt_type, txt_sum, txt_eff, txt_like, txt_time;
    ImageView img_main;
    RecyclerView recycler_mat, recycler_step;
    Button btn_complete;
    ToggleButton tog_like;
    String recipe_code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dogs_recipe);

        Intent intent = getIntent();
        recipe_code=intent.getStringExtra("recipe_code");

        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
        databaseAccess.open();

        //레시피 관련 정보 출력
        txt_title = (TextView)findViewById(R.id.txt_title);
        txt_type = (TextView)findViewById(R.id.txt_type);
        txt_sum = (TextView)findViewById(R.id.txt_sum);
        txt_tip = (TextView)findViewById(R.id.txt_tip);
        txt_eff = (TextView)findViewById(R.id.txt_eff);
        txt_time = (TextView)findViewById(R.id.txt_time);
        txt_like = (TextView)findViewById(R.id.txt_like);
        img_main = (ImageView)findViewById(R.id.img_main);
        ArrayList<String> RecipeInfo = databaseAccess.getRecipeInfo(recipe_code);

        //xml에 적용
        txt_like.setText(databaseAccess.getLikeSum(recipe_code));
        txt_title.setText(RecipeInfo.get(0));
        txt_sum.setText(RecipeInfo.get(1));
        switch (RecipeInfo.get(2)) {
            case "냥키친":
                txt_type.setText("Cat's");
                break;
            case "멍키친":
                txt_type.setText("Dog's");
                break;
        }
        txt_time.setText(RecipeInfo.get(3));
        txt_tip.setText(RecipeInfo.get(4));
        Glide.with(this)
                .load(RecipeInfo.get(5))
                .into(img_main);
        txt_eff.setText(RecipeInfo.get(6));

        //재료 리스트 출력
        recycler_mat = findViewById(R.id.recycler_mat);
        LinearLayoutManager layoutManager =
                new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recycler_mat.setLayoutManager(layoutManager);
        MaterialItemAdapter adapter = new MaterialItemAdapter();

        ArrayList<ArrayList<String>> Matlist = databaseAccess.getMatlist(recipe_code);
        for(int i=0; i<Matlist.size(); i++){
            adapter.addItem(new MaterialItem(Matlist.get(i).get(0), Matlist.get(i).get(1)));
        }
        recycler_mat.setAdapter(adapter);


        //step 리스트 출력
        recycler_step = findViewById(R.id.recycler_step);
        LinearLayoutManager layoutManager2 =
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recycler_mat.setLayoutManager(layoutManager2);
        StepItemAdapter adapter2 = new StepItemAdapter();
        ArrayList<ArrayList<String>> Steplist = databaseAccess.getSteplist(recipe_code);
        for(int i=0; i<Steplist.size(); i++){
            adapter2.addItem(new StepItem(Steplist.get(i).get(0), Steplist.get(i).get(1), Steplist.get(i).get(2)));
        }
        recycler_step.setAdapter(adapter2);

    }
    public void onButton1Clicked(View v) {
        Toast.makeText(this, "My refrigerator에 추가되었습니다.", Toast.LENGTH_LONG).show();
    }

}