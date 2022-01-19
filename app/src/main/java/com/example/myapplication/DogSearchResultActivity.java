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
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Iterator;

public class DogSearchResultActivity extends AppCompatActivity {

    private TextView txt_search, txt_result;
    private ImageButton imgBtn_home;
    private ImageButton imgBtn_user;

    RecyclerView recycler_rcp;
    RecipeItemAdapter adapter;
    ArrayList<String> recipe_code;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dog_search_result);

        //검색창에 검색어 유지
        txt_search = findViewById(R.id.txt_search);
        Intent intent = getIntent();
        String search = intent.getStringExtra("search");
        txt_search.setText(search);

        //DB open
        DatabaseAccess dbAc = DatabaseAccess.getInstance(getApplicationContext());
        dbAc.open();

        //결과값 갯수 출력
        Integer count = dbAc.getResultSum(search);
        txt_result = findViewById(R.id.txt_result);
        txt_result.setText(count.toString());

        //레시피 목록 출력
        recycler_rcp = findViewById(R.id.recycler_rcp);
        LinearLayoutManager layoutManager =
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recycler_rcp.setLayoutManager(layoutManager);
        adapter = new RecipeItemAdapter();

        recipe_code= dbAc.getRecipeCode(search);
        ArrayList<ArrayList<String>> Recipelist = dbAc.getRecipelist(recipe_code);
        for(int i=0; i<Recipelist.size(); i++){
            adapter.addItem(new RecipeItem(Recipelist.get(i).get(0), Recipelist.get(i).get(1), Recipelist.get(i).get(2)));
        }
        recycler_rcp.setAdapter(adapter);

        dbAc.close();

        adapter.setOnItemClickListener(new OnRecipeItemClickListener() {
            @Override
            public void onItemClick(RecipeItemAdapter.ViewHolder holder, View view, int position) {
                RecipeItem item = adapter.getItem(position);
                Intent intent = new Intent(DogSearchResultActivity.this, DogsRecipeActivity.class);
                intent.putExtra("recipe_code", recipe_code.get(position));
                startActivity(intent);  // activity 이동
            }
        });

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