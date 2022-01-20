package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

public class SearchResultActivity extends AppCompatActivity {

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
            adapter.addItem(new RecipeItem(Recipelist.get(i).get(0), Recipelist.get(i).get(1), Recipelist.get(i).get(8), Recipelist.get(i).get(2),
                    Recipelist.get(i).get(3), Recipelist.get(i).get(4), Recipelist.get(i).get(5), Recipelist.get(i).get(6),
                    Recipelist.get(i).get(7)));
        }
        recycler_rcp.setAdapter(adapter);

        dbAc.close();

        adapter.setOnItemClickListener(new OnRecipeItemClickListener() {
            @Override
            public void onItemClick(RecipeItemAdapter.ViewHolder holder, View view, int position) {
                RecipeItem item = adapter.getItem(position);
                Intent intent = new Intent(SearchResultActivity.this, RecipeActivity.class);
                intent.putExtra("recipe_code", recipe_code.get(position));
                intent.putExtra("recipe_name",item.rcp_txt);
                intent.putExtra("img_url",item.img_url);
                intent.putExtra("recipe_sum", item.txt_sum);
                intent.putExtra("recipe_type", item.txt_type);
                intent.putExtra("recipe_time", item.txt_time);
                intent.putExtra("recipe_tip",item.txt_tip);
                intent.putExtra("recipe_eff",item.txt_eff);
                intent.putExtra("recipe_like",item.txt_like);
                startActivity(intent);  // activity 이동
            }
        });

        imgBtn_home = findViewById(R.id.imgBtn_home);
        imgBtn_home.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(SearchResultActivity.this, SubMainActivity.class);
                startActivity(intent);  // activity 이동
            }
        });

        imgBtn_user = findViewById(R.id.imgBtn_user);
        imgBtn_user.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(SearchResultActivity.this, UserActivity.class);
                startActivity(intent);  // activity 이동
            }
        });

    }
}