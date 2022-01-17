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

    private TextView txt_search, txt_result, mat_first, mat_second, mat_third;
    private ImageButton imgBtn_home;
    private ImageButton imgBtn_user;
    private ArrayList<RecipeItem> mRecipeItems;

    ArrayList<String> recipe_code;
    //DataBaseHelper DB;
    
    RecyclerView recycler_rcp;
    RecipeItemAdapter adapter;
    String mat_1st, mat_2nd, mat_3rd, name_rcp, img_rcp;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dog_search_result);

        //검색창에 검색어 유지
        txt_search = findViewById(R.id.txt_search);
        Intent intent = getIntent();
        String search = intent.getStringExtra("search");
        txt_search.setText(search);

        //레시피 목록 출력
        recycler_rcp = findViewById(R.id.recycler_rcp);
        LinearLayoutManager layoutManager =
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recycler_rcp.setLayoutManager(layoutManager);
        adapter = new RecipeItemAdapter();

        //DB open
        DatabaseAccess dbAc = DatabaseAccess.getInstance(getApplicationContext());
        dbAc.open();

        //결과값 갯수 출력
        Integer count = dbAc.getResultSum(search);
        txt_result = findViewById(R.id.txt_result);
        txt_result.setText(count);

        //mRecipeItems = new ArrayList<RecipeItem>();
        //결과 레시피코드 배열에 추가
        recipe_code = dbAc.getRecipeCode(search);

        Iterator it = recipe_code.iterator();
//        while (it.hasNext()){
//            String value = (String)it.next();
//            Cursor cursor2 = db.rawQuery("SELECT recipe_name, img_main" + " FROM recipe" +
//                    " WHERE recipe_code=?", new String[]{value});
//            Cursor cursor3 = db.rawQuery("SELECT mat_name"+" FROM materials"+
//                    " WHERE recipe_code=?"+" ORDER BY mat_num"+" LIMIT 3", new String[]{value});
//            name_rcp = cursor2.getString(0);
//            img_rcp = cursor2.getString(1);
//            while (cursor3.moveToNext()){
//                mat_1st = cursor3.getString(0);
//                mat_2nd = cursor3.getString(1);
//                mat_3rd = cursor3.getString(2);
//            }
//            mRecipeItems.add(new RecipeItem(img_rcp, name_rcp, mat_1st, mat_2nd, mat_3rd));
//            adapter.notifyDataSetChanged();
//            cursor2.close();
//            cursor3.close();
//        }
//
//        Iterator it2 = mRecipeItems.iterator();
//        while(it2.hasNext()){
//            adapter.addItem((RecipeItem) it2.next());
//        }
        recycler_rcp.setAdapter(adapter);
        dbAc.close();


//        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
//        databaseAccess.open();
//
//        //getting string value
//        String rcp_name = databaseAccess.getRecipeName(search);
//        String rcp_img = databaseAccess.getRecipeImg(search);
//
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