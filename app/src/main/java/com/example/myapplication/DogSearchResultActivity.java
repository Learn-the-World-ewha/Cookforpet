package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

public class DogSearchResultActivity extends AppCompatActivity {

    private TextView txt_search;
    //private ImageButton imgBtn_rcp1;
    private ImageButton imgBtn_home;
    private ImageButton imgBtn_user;
    private ArrayList<RecipeItem> mRecipeItems;

    ArrayList<String> recipe_name, recipe_code;
    ArrayList<String> mat_name;
    DataBaseHelper DB;
    RecyclerView recyclerView;
    RecipeItemAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dog_search_result);

        txt_search = findViewById(R.id.txt_search);
        Intent intent = getIntent();
        String search = intent.getStringExtra("search");
        txt_search.setText(search);

        recyclerView = findViewById(R.id.recycler_rcp);
        LinearLayoutManager layoutManager =
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new RecipeItemAdapter();
        recyclerView.setAdapter(adapter);


//        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
//        databaseAccess.open();
//
//        //getting string value
//        String rcp_name = databaseAccess.getRecipeName(search);
//        String rcp_img = databaseAccess.getRecipeImg(search);
//
//        //setting text to result field
//        //rcplist.setTxt_rcp(rcp_name);
//
//        adapter.addItem(new RecipeItem(rcp_img,
//                rcp_name,"재료1, 재료2, 재료3, ..."));
//        recyclerView.setAdapter(adapter);
//        databaseAccess.close();

        adapter.setOnItemClickListener(new OnRecipeItemClickListener() {
            @Override
            public void onItemClick(RecipeItemAdapter.ViewHolder holder, View view, int position) {
                RecipeItem item = adapter.getItem(position);
                Intent intent = new Intent(DogSearchResultActivity.this, DogsRecipeActivity.class);
                intent.putExtra("recipe_code",recipe_code.get(position));
                startActivity(intent);  // activity 이동
            }
        });

//        RecipeList rcplist = findViewById(R.id.rcplist);
//        rcplist.setImg_rcp(R.drawable.ic_launcher_foreground);
//        rcplist.setTxt_mat("재료1, 재료2, 재료3, ...");


//        imgBtn_rcp1 = findViewById(R.id.imgBtn_rcp1);
//        imgBtn_rcp1.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v){
//                Intent intent = new Intent(DogSearchResultActivity.this, DogsRecipeActivity.class);
//                startActivity(intent);  // activity 이동
//            }
//        });

        imgBtn_home = findViewById(R.id.imgBtn_home);
        imgBtn_home.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(DogSearchResultActivity.this, TypeActivity.class);
                startActivity(intent);  // activity 이동
            }
        });

        imgBtn_user = findViewById(R.id.imgBtn_user);
        imgBtn_user.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(DogSearchResultActivity.this, UserActivity.class);
                startActivity(intent);  // activity 이동
            }
        });

    }
}