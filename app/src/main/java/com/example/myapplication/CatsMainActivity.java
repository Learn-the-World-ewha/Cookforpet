package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

public class CatsMainActivity extends AppCompatActivity {

    String val = "";
    private ImageButton imgBtn_search;
    private EditText editTxt_search;
    private String search;
    private ImageButton imgBtn_home;
    private ImageButton imgBtn_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cats_main);

        RecyclerView recyclerView = findViewById(R.id.recycler_rcp);
        LinearLayoutManager layoutManager =
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        RecipeItemAdapter adapter = new RecipeItemAdapter();
        adapter.addItem(new RecipeItem("https://recipe.bom.co.kr/uploads/posts//images//20190109//5c354090b2189.png", "고양이 닭고기 소고기 볶음 -  체중조절에 효과적인", "다진 소고기, 올리브추 오일, 닭가슴살, 양배"));
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new OnRecipeItemClickListener() {
            @Override
            public void onItemClick(RecipeItemAdapter.ViewHolder holder, View view, int position) {
                RecipeItem item = adapter.getItem(position);
                Intent intent = new Intent(CatsMainActivity.this, CatSearchResultActivity.class);
                startActivity(intent); //activity 이동
            }
        });

        editTxt_search = findViewById(R.id.editTxt_search);
        imgBtn_search = findViewById(R.id.imgBtn_search);
        imgBtn_search.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                search = editTxt_search.getText().toString();
                Intent intent = new Intent(CatsMainActivity.this, CatSearchResultActivity.class);
                intent.putExtra("search",search);
                startActivity(intent);  // activity 이동
            }
        });

        imgBtn_home = findViewById(R.id.imgBtn_home);
        imgBtn_home.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(CatsMainActivity.this, TypeActivity.class);
                startActivity(intent);  // activity 이동
            }
        });

        imgBtn_user = findViewById(R.id.imgBtn_user);
        imgBtn_user.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(CatsMainActivity.this, UserActivity.class);
                startActivity(intent);  // activity 이동
            }
        });
    }
}